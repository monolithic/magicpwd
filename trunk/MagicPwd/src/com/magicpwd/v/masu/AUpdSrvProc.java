/********************************************************************
 * 项目名称                ：rochoc<p>
 * 包名称                  ：com.rochoc.autoupdate<p>
 * 文件名称                ：AUpdSrvProc.java<p>
 * 编写者                 ：kfzx-luoc<p>
 * 编写日期                ：2008-12-22<p>
 * 程序功能（类）描述    ：<p>
 * 自动更新服务端处理进程
 * 程序变更日期            ：
 * 变更作者                ：
 * 变更说明                ：
 ********************************************************************/
package com.magicpwd.v.masu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author Amon
 */
public class AUpdSrvProc extends Thread
{

    private Socket socket = null;
    private InputStream socketIn;
    private OutputStream socketOut;
    private Config config = Config.getInstance();//配置文件对像
    private ClientVerParser cvPaser = null;
    private HashMap cFiles = new HashMap();
    byte bFlag[] = new byte[1];//标识位
    byte bCmd[] = new byte[8];//命令

    public AUpdSrvProc(Socket socket)
    {
        this.socket = socket;
    }

    /**
     * 接收客户端的升级请求，并进行处理
     */
    public void run()
    {
        try
        {
            config.refresh();//重新更新配置信息
            socketIn = socket.getInputStream();
            socketOut = socket.getOutputStream();
            byte datahead[] = new byte[5];//数据头部，第一位用于标识是数据，后四位为长度
            byte buffer[] = new byte[AUPD.BUFFER_SIZE];//存放数据头部和数据头部
            byte data[] = new byte[AUPD.DATA_SIZE];//存放具体的数据内容
            while (true)
            {
                //读取标志位
                int len = socketIn.read(bFlag, 0, 1);
                if (len != 1)
                {
                    Config.print(socket.getInetAddress() + ":读取标识位失败");
                    socketOut.write(Config.getCmd(AUPD.BYE));//结束
                    break;
                }
                if (bFlag[0] == AUPD.CMD_DATA_SECT)//命令行
                {
                    len = socketIn.read(bCmd, 0, 8);
                    if (len != 8)
                    {
                        Config.print(socket.getInetAddress() + ":读取命令失败,CMD=" + bCmd);
                        socketOut.write(Config.getCmd(AUPD.BYE));//结束
                        break;
                    }
                    if (Config.parseCmd(bCmd).equals(AUPD.READY_TO_UPDATE))//客户端已经准备好更新了
                    {
                        Config.print(socket.getInetAddress() + ":客户端已经准备好接收更新文件");
                        int ret = sendUpdateFile();
                        switch (ret)
                        {
                            case 0:
                                socketOut.write(Config.getCmd(AUPD.UPDATED_FAILURE));//失败
                                break;
                            case 1:
                                socketOut.write(Config.getCmd(AUPD.UPDATED_SUCCESSFUL));//成功
                                break;
                            default:
                                socketOut.write(Config.getCmd(AUPD.NOTNEED_UPDATED));//无需更新
                                break;
                        }
                    }
                    else if (Config.parseCmd(bCmd).equals(AUPD.BYE))//结束链接
                    {
                        socketOut.write(Config.getCmd(AUPD.BYE));//结束
                        break;
                    }
                }
                else if (bFlag[0] == AUPD.MARK_DATA_SECT || bFlag[0] == AUPD.MARK_DATA_END)//数据内容
                {
                    if (Config.parseCmd(bCmd).equals(AUPD.SEND_CLIENT_VERSION))//进行版本信息接收处理
                    {
                        receiveClientVer(bFlag[0]);
                    }
                    else
                    {
                        Config.print("出现非期望数据," + new String(bCmd));
                        socketOut.write(Config.getCmd(AUPD.BYE));//结束
                        break;
                    }
                }
                else
                {
                    Config.print(socket.getInetAddress() + ":非期望标识位," + bFlag[0]);
                    socketOut.write(Config.getCmd(AUPD.BYE));//结束
                    break;
                }
            }//END while(ture)
            //关闭资源
            socketIn.close();
            socketOut.close();
            socket.close();
        }
        catch (IOException e)
        {
            Config.print("处理客户端升级请求失败," + socket.getInetAddress() + "," + e);
            e.printStackTrace();
        }
    }

    /**
     * 方法名称：sendUpdateFile<p>
     * 方法功能：<p>
     * 参数说明：<p>
     * 返回：int<p>
     * 作者：kfzx-luoc
     * 日期：2008-12-23
     * @return 0.更新失败 1.更新成功 2.无需更新
     */
    private int sendUpdateFile()
    {
        try
        {
            //检查服务器和客户端版本号是否一致，如果一致辞，则无需升级
            if (config.getVerstion().equals(cvPaser.getVerstion()))
            {
                Config.print(socket.getInetAddress() + ":版本一致，无需更新");
                return 2;
            }
            //开始进行处理
            UpdFile srvFiles[] = config.getFiles();
            boolean isSuccess = true;
            for (int i = 0; i < srvFiles.length; i++)
            {
                UpdFile cf = (UpdFile) cFiles.get(srvFiles[i].getName());
                //文件不存在或版本号不一致则需要更新该文件
                if (cf == null || !cf.getVersion().equals(srvFiles[i].getVersion()))
                {
                    if (!sendFile(srvFiles[i]))
                    {
                        isSuccess = false;
                    }
                }
            }//END for
            //发送版本信息文件，发送更新信息文件
            if (isSuccess)
            {
                UpdFile verFile = new UpdFile("autoupdate.xml");
                verFile.setPath("." + File.separator + "config");
                verFile.setType(0);
                verFile.setVersion(config.getVerstion());
                if (!sendFile(verFile))
                {
                    Config.print(socket.getInetAddress() + ":发送版本文件失败");
                    return 0;
                }
                //发送更新信息
                UpdFile infFile = new UpdFile("history.htm");
                infFile.setPath("." + File.separator + "config");
                infFile.setType(0);
                infFile.setVersion(config.getVerstion());
                if (!sendFile(infFile))
                {
                    Config.print(socket.getInetAddress() + ":发送最新信息失败");
                }
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch (Exception e)
        {
            Config.print("处理需要更新文件失败," + e);
            e.printStackTrace();
            return 0;
        }
    }
    //0.失败 1.成功

    private boolean sendFileAbsPath(String path)
    {
        try
        {
            byte buffer[] = new byte[AUPD.BUFFER_SIZE];
            int len = 0;
            //标识为数据段
            buffer[0] = AUPD.MARK_DATA_SECT;
            Config.copyArray(buffer, Config.getLen(path.getBytes().length), 1, 0, 4);//4位长度
            //组合数据包
            for (int i = 0; i < path.getBytes().length; i++)
            {
                buffer[i + 5] = path.getBytes()[i];
            }
            socketOut.write(buffer, 0, path.getBytes().length + 5);//前五位为头部1位标识+4位长度
            //标识为数据段已结束，并发送至服务器
            buffer[0] = AUPD.MARK_DATA_END;
            socketOut.write(buffer, 0, 1);
            socketOut.flush();
            //检查客户端是否收到            
            len = socketIn.read(bFlag, 0, 1);
            if (len != 1)
            {
                Config.print(socket.getInetAddress() + ":读取标识位失败");
                socketOut.write(Config.getCmd(AUPD.BYE));//结束
                return false;
            }
            //读取命令
            len = socketIn.read(bCmd, 0, 8);
            if (len != 8)
            {
                Config.print(socket.getInetAddress() + ":读取命令失败,CMD=" + bCmd);
                socketOut.write(Config.getCmd(AUPD.BYE));//结束
                return false;
            }
            if (Config.parseCmd(bCmd).equals(AUPD.RECEIVED_FILE_ABSOULT))//成功
            {
                Config.print(socket.getInetAddress() + ":接收文件路径成功," + path);
                return true;
            }
            else if (Config.parseCmd(bCmd).equals(AUPD.BYE))//失败
            {
                Config.print(socket.getInetAddress() + ":接收文件路径失败，" + path);
                return false;
            }
            else//异常
            {
                return false;
            }
        }
        catch (Exception e)
        {
            Config.print(socket.getInetAddress() + ":发送文件路径失败," + path);
            e.printStackTrace();
            return false;
        }
    }
    //false.失败 true.成功

    private boolean sendFile(UpdFile file)
    {
        try
        {
            File f = new File(Config.formatPath(file.getPath()) + file.getName());
            if (!f.exists() || !f.isFile())
            {
                Config.print(file + ",不存在，无法更新");
                return false;
            }
            Config.print(socket.getInetAddress() + ":开始传输文件>>" + file);
            socketOut.write(Config.getCmd(AUPD.SEND_FILE_ABSOULT));//发送文件全路径
            String fileAbsPath = Config.formatPath(file.getPath()) + file.getName();
            if (!sendFileAbsPath(fileAbsPath))
            {
                return false;
            }
            socketOut.write(Config.getCmd(AUPD.START_TRANSMIT));//开始传输
            FileInputStream fin = new FileInputStream(f);
            //文件数据缓冲区
            byte[] data = new byte[AUPD.DATA_SIZE];
            // 发送数据缓冲区
            byte[] buffer = new byte[AUPD.BUFFER_SIZE];
            int len = -1;
            while ((len = fin.read(data)) != -1)
            {
                // 标识为数据段
                buffer[0] = AUPD.MARK_DATA_SECT;
                Config.copyArray(buffer, Config.getLen(len), 1, 0, 4);//存放长度
                // 组合数据包
                for (int i = 0; i < len; i++)
                {
                    buffer[i + 5] = data[i];
                }
                socketOut.write(buffer, 0, len + 5);
            }
            // 标识为数据段已结束，并发送至服务器
            buffer[0] = AUPD.MARK_DATA_END;
            socketOut.write(buffer, 0, 1);
            socketOut.flush();
            fin.close();
            //判断客户端是否收到
            len = socketIn.read(bFlag, 0, 1);
            if (len != 1)
            {
                Config.print(socket.getInetAddress() + ":读取标识位失败");
                socketOut.write(Config.getCmd(AUPD.BYE));//结束
                return false;
            }
            //读取命令
            len = socketIn.read(bCmd, 0, 8);
            if (len != 8)
            {
                Config.print(socket.getInetAddress() + ":读取命令失败,CMD=" + new String(bCmd));
                socketOut.write(Config.getCmd(AUPD.BYE));//结束
                return false;
            }
            if (Config.parseCmd(bCmd).equals(AUPD.TERMINATE_TRANSMIT))//成功
            {
                Config.print(socket.getInetAddress() + ":传输文件'" + file + "'成功");
                return true;
            }
            else if (Config.parseCmd(bCmd).equals(AUPD.BYE))//失败
            {
                Config.print(socket.getInetAddress() + ":传输文件失败，" + file);
                return false;
            }
            else//异常
            {
                Config.print(socket.getInetAddress() + ":传输文件异常，" + file + "," + new String(bCmd));
                return false;
            }
        }
        catch (Exception e)
        {
            Config.print("传输文件'" + file + "'失败," + e);
            e.printStackTrace();
            return false;
        }
    }

    private void receiveClientVer(byte flag)//第一位表示是数据内容还是结束内容
    {
        try
        {
            //接收数据缓冲区
            byte flagb[] = new byte[1];//标志
            byte lenb[] = new byte[4];//长度
            //接收版本号信息
            StringBuffer strBuf = new StringBuffer();//用于接收信息
            int len = -1;
            boolean isFirst = true;
            boolean isOk = false;
            flagb[0] = flag;
            while (true)
            {
                //第一次
                if (isFirst)
                {
                    isFirst = false;
                }
                else
                {
                    len = socketIn.read(flagb, 0, 1);//读取标识位
                    if (len != 1)
                    {
                        Config.print(socket.getInetAddress() + ":读取数据标识位失败");
                        break;
                    }
                }
                //读取数据长度
                if (flagb[0] == AUPD.MARK_DATA_SECT)
                {
                    len = socketIn.read(lenb, 0, 4);
                    if (len != 4)
                    {
                        Config.print(socket.getInetAddress() + ":读取数据头部失败");
                        break;
                    }
                }
                if (flagb[0] == AUPD.MARK_DATA_SECT)//数据内容
                {
                    int cLen = Integer.parseInt(new String(lenb, 0, 4));//数据内容长度
                    byte data[] = new byte[cLen];
                    len = socketIn.read(data, 0, cLen);
                    int totLen = len;
                    while (totLen < cLen)//不足位要重重读取
                    {
                        strBuf.append(new String(data, 0, len));
                        len = socketIn.read(data, 0, cLen - totLen);
                        totLen = totLen + len;
                    }
                    strBuf.append(new String(data, 0, len));
                }
                else if (flagb[0] == AUPD.MARK_DATA_END)//数据结束
                {
                    isOk = true;
                    break;
                }
                else
                {
                    Config.print(socket.getInetAddress() + ":收到非期望数据," + new String(flagb, 0, 1) + "<<");
                    break;
                }
            }//END while(true)
            if (isOk)//成功
            {
                socketOut.write(Config.getCmd(AUPD.RECEIVED_CLIENT_VERSION));//临时测试
                Config.print("接收客户端" + socket.getInetAddress() + " 版本信息成功");
                cvPaser = new ClientVerParser(new StringReader(strBuf.toString()));
                UpdFile files[] = cvPaser.getFiles();
                for (int i = 0; i < files.length; i++)
                {
                    cFiles.put(files[i].getName(), files[i]);
                }
            }
            else//失败
            {
                socketOut.write(Config.getCmd(AUPD.BYE));//结束
            }
        }
        catch (Exception e)
        {
            Config.print("接收客户端" + socket.getInetAddress() + " 版本号信息处理失败," + e);
        }
    }
}
