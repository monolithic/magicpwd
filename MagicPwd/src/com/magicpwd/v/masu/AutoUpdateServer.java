/********************************************************************
   * 项目名称                ：rochoc<p>
   * 包名称                  ：com.rochoc.autoupdate<p>
   * 文件名称                ：AutoUpdateServer.java<p>
   * 编写者                 ：kfzx-luoc<p>
   * 编写日期                ：2008-12-22<p>
   * 程序功能（类）描述    ：<p>
   * 自动更新服务端
   * 程序变更日期            ：
  * 变更作者                ：
  * 变更说明                ：
 ********************************************************************/
 package com.magicpwd.v.masu;
 
 import java.io.IOException;
 import java.net.ServerSocket;
 import java.net.Socket;

/**
 * @author Amon
 */
 public class AutoUpdateServer extends Thread
 {
     private int port = 0;//服务端口号
     private Config config = Config.getInstance();//配置文件对像
     private ServerSocket srvSocket = null;
     public AutoUpdateServer()
     {
         port = Integer.parseInt(config.getServerPort());
         try
         {
             srvSocket = new ServerSocket(port);
             //setTimeout(60000);
             this.start();
             Config.print("自动更新服务器在端口'"+port+"'监听");
         } catch (IOException e)
         {
             e.printStackTrace();
         }
     }
     void setTimeout(int millis) throws IOException
     {
         if (srvSocket != null)
         {
             srvSocket.setSoTimeout(millis);
         }
     }
     void close() throws IOException
     {
         if (srvSocket != null)
         {
             srvSocket.close();
         }
    }
     /**
      * @author kfzx-luoc
      *
      * 执行监听处理，如果有客户端连接上来，则判断是否需要更新，
      * 如果需要更新，则给客户端传送最新版本文件
      */
     public void run()
     {
         try
        {
             while (true) 
             {
                 Socket clSocket = null;
                 try
                 {
                     clSocket = srvSocket.accept();
                     Config.print("客户端‘"+clSocket.getInetAddress()+"’连接成功");
                     //进行处理
                     AUpdSrvProc srvP = new AUpdSrvProc(clSocket);
                     srvP.start();
                 } catch (IOException ioe)
                 {
                     try
                     {
                         clSocket.close();
                     } catch (IOException e1)
                     {
                     }
                     Config.print("AutoUpdateServer proc client:"+clSocket.getInetAddress().getHostAddress()+" error,"+ioe);
                     ioe.printStackTrace();
                 }
             }
         }catch(Exception e)
         {
             Config.print("AutoUpdateServer running error,"+e);
             e.printStackTrace();
         } finally
         {
             try
             {
                 srvSocket.close();
             } catch (IOException e)
            {
            }
        }
    }    
    //测试主函数
    public static void main(String args[])
    {
        AutoUpdateServer server = new AutoUpdateServer();
        server.run();
    }
}

