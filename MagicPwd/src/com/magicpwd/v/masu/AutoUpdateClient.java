/********************************************************************
   * 项目名称                ：rochoc<p>
   * 包名称                  ：com.rochoc.autoupdate<p>
   * 文件名称                ：AutoUpdateClient.java<p>
   * 编写者                 ：kfzx-luoc<p>
   * 编写日期                ：2008-12-23<p>
   * 程序功能（类）描述    ：<p>
   * 自动升级客户端对像
   * 程序变更日期            ：
  * 变更作者                ：
  * 变更说明                ：
 ********************************************************************/
 package com.magicpwd.v.masu;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.Socket;

/**
 * @author Amon
 */
 public class AutoUpdateClient
 {
     private Socket socket = null;
     private OutputStream socketOut;
     private InputStream socketIn;
     private Config config = Config.getInstance();//配置文件对像
     private String currFileAbs = "";//当前更新文件的全路径
     public AutoUpdateClient()
     {
         try
         {
             socket = new Socket(config.getServerIp(),Integer.parseInt(config.getServerPort()));
             socket.setSoTimeout(30000);//30秒
         }catch(Exception e)
         {
             Config.print("创建与自动升级服务器之间的连接失败,"+e);
             e.printStackTrace();
         }
     }
     public void update()
     {
         if(socket == null)
         {
             Config.print("无法与服务器连接，升级失败");
             return;
         }
         try
         {
             socketOut = socket.getOutputStream();
             socketIn = socket.getInputStream();      
             
             //开始升级处理
             byte flag [] = new byte[1];
             byte cmd[] = new byte[8];//命令
             byte datahead [] = new byte[5];//数据头部，第一位用于标识数据，后四位为长度
             byte buffer[] = new byte[AUPD.BUFFER_SIZE];//存放数据头部和数据头部
             byte data[] = new byte[AUPD.DATA_SIZE];//存放具体的数据内容            
             //发送本地版本信息给服务器                
             socketOut.write(Config.getCmd(AUPD.SEND_CLIENT_VERSION));//发送传输版本信息命令
             sendClientVer();//发送版本信息
             while(true)
             {
                 //读取信息
                 int len = socketIn.read(flag,0,1);
                 if(len!=1)
                 {
                     Config.print("读取标识位失败");
                     socketOut.write(Config.getCmd(AUPD.BYE));//结束
                    break;
                 }
                 if(flag[0]==AUPD.CMD_DATA_SECT)//命令行
                 {
                     len = socketIn.read(cmd,0,8);
                     if(len!=8)
                     {
                         Config.print("读取命令失败");
                         socketOut.write(Config.getCmd(AUPD.BYE));//结束
                         break;
                     }
                     if(Config.parseCmd(cmd).equals(AUPD.RECEIVED_CLIENT_VERSION))//收到版本信息
                     {
                         Config.print("服务器成功收到版本信息");
                         socketOut.write(Config.getCmd(AUPD.READY_TO_UPDATE));
                         continue;
                     }else if(Config.parseCmd(cmd).equals(AUPD.SEND_FILE_ABSOULT))//接收文件全路径
                     {
                         Config.print("开始接收文件路径名");                      
                     }else if(Config.parseCmd(cmd).equals(AUPD.UPDATED_FAILURE))//更新失败
                     {
                         Config.print("版本更新失败");
                         socketOut.write(Config.getCmd(AUPD.BYE));//结束
                         break;
                    }else if(Config.parseCmd(cmd).equals(AUPD.UPDATED_SUCCESSFUL))//更新成功
                    {
                        Config.print("版本更新成功");
                        socketOut.write(Config.getCmd(AUPD.BYE));//结束
                        //打开最新信息
                        openFile(".\\config\\history.htm");
                        break;
                    }else if(Config.parseCmd(cmd).equals(AUPD.NOTNEED_UPDATED))//无需更新
                    {
                        Config.print("已经是最新版本，无需更新");
                        socketOut.write(Config.getCmd(AUPD.BYE));//结束
                       break;
                    }else if(Config.parseCmd(cmd).equals(AUPD.BYE))//结束链接
                    {
                       socketOut.write(Config.getCmd(AUPD.BYE));//结束
                        break;
                    }
                }else if(flag[0]==AUPD.MARK_DATA_SECT || flag[0]==AUPD.MARK_DATA_END)//数据内容
                {
                    if(Config.parseCmd(cmd).equals(AUPD.SEND_FILE_ABSOULT))//接收文件全路径
                     {
                        currFileAbs = receiveFileAbsPath(flag[0]);
                        if(currFileAbs!=null && !currFileAbs.equals(""))//成功
                        {
                            socketOut.write(Config.getCmd(AUPD.RECEIVED_FILE_ABSOULT));
                            Config.print("接收文件全路径‘"+currFileAbs+"’成功");
                        }else
                        {
                            Config.print("接收文件全路径失败");
                            socketOut.write(Config.getCmd(AUPD.BYE));//结束
                            break;
                        }
                   }else if(Config.parseCmd(cmd).equals(AUPD.START_TRANSMIT))//接收文件
                    {
                        if(receiveFile(flag[0]))
                        {
                            socketOut.write(Config.getCmd(AUPD.TERMINATE_TRANSMIT));
                        }else
                        {
                            socketOut.write(Config.getCmd(AUPD.BYE));
                        }
                    }else
                    {
                        Config.print("出现非期望数据,"+new String(cmd));
                       socketOut.write(Config.getCmd(AUPD.BYE));//结束
                       break;
                    }
                }else
                {
                    Config.print("非期望标识位,"+flag[0]);
                    socketOut.write(Config.getCmd(AUPD.BYE));//结束
                    break;
                }
            }//END while(true)
            
           //关闭资源及链接
            socketOut.close();
            socketIn.close();
            socket.close();
            Config.print("自动升级处理完毕");
        }catch(Exception e)
        {
           Config.print("升级处理失败,"+e);
            e.printStackTrace();
        }
    }
    private void openFile(String file)
    {
        try
       {
            Runtime.getRuntime().exec("cmd /c "+file);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private String receiveFileAbsPath(byte flag)
    {
        String absPath = "";
        //接收文件全路径
        try
       {
            //接收数据缓冲区
            byte flagb[] = new byte[1];//标志
            byte lenb [] = new byte[4];//长度
            //接收文件全路径
            StringBuffer strBuf = new StringBuffer();//用于接收信息
           int len = -1;
            boolean isFirst = true;
            boolean isOk = false;        
            flagb[0] = flag;
            while(true)
            {
                //第一次
               if(isFirst)
                {
                    isFirst = false;        
                }else
                {
                    len = socketIn.read(flagb,0,1);//读取标识位
                    if(len != 1)
                    {
                        Config.print(socket.getInetAddress() + ":读取数据标识位失败");
                        break;
                    }
                }
               //读取数据长度
                if(flagb[0]==AUPD.MARK_DATA_SECT)
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
                    System.out.println("len:"+len+"cLen="+cLen+">>"+new String(data,0,len));
                    int totLen = len;
                   while (totLen < cLen)//不足位要重重读取
                    {
                        strBuf.append(new String(data, 0, len));
                        len = socketIn.read(data, 0, cLen - totLen);
                        totLen = totLen + len;
                        System.out.println("len:"+len+"cLen="+cLen);
                    }
                    strBuf.append(new String(data, 0, len));
                }else if(flagb[0]==AUPD.MARK_DATA_END)//数据结束
              {
                    isOk = true;
                    break;
                }else
                {
                    Config.print(socket.getInetAddress()+":收到非期望数据,"+new String(flagb,0,1)+"<<");
                    break;
                }
            }//END while(true)
            if(isOk)//成功
           {            
                absPath = strBuf.toString();
          }else//失败
            {
                socketOut.write(Config.getCmd(AUPD.BYE));//结束
            }
      }catch(Exception e)
        {
            Config.print("接收文件全路径处理失败,"+e);
        }
        return absPath;
   }
    private boolean receiveFile(byte flag)
   {
       try
        {
           if(currFileAbs==null||currFileAbs.equals(""))
            {
                Config.print("无法获取更新文件信息，更新失败");
               return false;
            }
            File file;
           //先检查目录是否存在
            //得到目录
            int idx = currFileAbs.lastIndexOf(File.separator);
           String path = currFileAbs.substring(0,idx);
            file = new File(path);
            if(!file.isDirectory() || !file.exists())
            {
                Config.print("新创建目录:"+path);
                file.mkdir();
            }
           file = new File(currFileAbs);
            FileOutputStream fout = new FileOutputStream(file);
            //接收数据缓冲区
            byte flagb[] = new byte[1];//标志
            byte lenb [] = new byte[4];//长度
            int len = -1;
            boolean isFirst = true;
            boolean isOk = false;
            flagb[0] = flag;
            //接收上传的文件数据
            while (true)
            {
                //第一次
                if(isFirst)
                {
                    isFirst = false;        
                }else
              {
                    len = socketIn.read(flagb,0,1);//读取标识位
                    if(len != 1)
                    {
                        Config.print(socket.getInetAddress() + ":读取数据标识位失败");
                       break;
                    }
              }
                //读取数据长度
                if(flagb[0]==AUPD.MARK_DATA_SECT)
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
                       fout.write(data,0,len);
                        len = socketIn.read(data, 0, cLen - totLen);
                        totLen = totLen + len;
                  }
                    fout.write(data,0,len);
                }else if(flagb[0]==AUPD.MARK_DATA_END)//数据结束
                {
                    isOk = true;
                    break;
                }else
                {
                    Config.print(socket.getInetAddress()+":收到非期望数据,"+new String(flagb,0,1)+"<<");
                    break;
                }
            }//END while
            fout.flush();
           fout.close();
            if(isOk)
            {
                Config.print("成功更新文件："+file.getAbsolutePath());
                return true;
            }else
            {
                Config.print("更新文件："+file.getAbsolutePath()+"失败");                
                return false;
            }
        }catch(Exception e)
        {
            Config.print("下载更新文件'"+currFileAbs+"'失败,"+e);
            e.printStackTrace();
            return false;
        }
    }
    //发送客户端版本信息
    private void sendClientVer()
    {
        try
      {
            File verFile = new File(Config.cfgFile);
            if(!verFile.isFile() || !verFile.exists())
            {
                Config.print("版本信息文件不存在");
                return;
            }
            //开始发送
            FileInputStream fis = new FileInputStream(verFile);
           byte buffer[] = new byte[AUPD.BUFFER_SIZE];
           byte data[] = new byte[AUPD.DATA_SIZE];
           int len = 0;
            while((len=fis.read(data))!=-1)
            {
               //标识为数据段
                buffer[0] = AUPD.MARK_DATA_SECT;
               Config.copyArray(buffer,Config.getLen(len),1,0,4);//4位长度
                //组合数据包
                for (int i=0; i<len; i++)
                    buffer[i+5] = data[i];//前五位为头部1位标识+4位长度
                socketOut.write(buffer,0,len+5);//发送数据
            }//END while
            //标识为数据段已结束，并发送至服务器
            buffer[0] = AUPD.MARK_DATA_END;
            socketOut.write(buffer,0,1);
            socketOut.flush();
            fis.close();
            Config.print("版本信息传送完毕");
      }catch(Exception e)
        {
            Config.print("发送版本信息给服务器失败,"+e);
            e.printStackTrace();
       }
    }    
    //测试主流程
    public static void main(String args[])
    {
        AutoUpdateClient client = new AutoUpdateClient();
        client.update();
    }
}