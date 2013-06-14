import java.net.*;
import java.io.*;

public class ChatServer implements Runnable
{  private ServerSocket     server = null;
   private Thread           thread = null;
   private ChatServerThread client = null;

   public ChatServer(int port)
   {  try
      {  System.out.println("Binding to port " + port + ", please wait  ...");
         server = new ServerSocket(port);  
         System.out.println("Server started: " + server);
         start();
      }
      catch(IOException ioe)
      {  System.out.println(ioe); }
   }
   public void run()
   {  while (thread != null)
      {  try
         {  System.out.println("Waiting for a client ..."); 
            addThread(server.accept());
         }
         catch(IOException ie)
         {  System.out.println("Acceptance Error: " + ie); }
      }
   }
   public void addThread(Socket socket)
   {  System.out.println("Client accepted: " + socket);
      client = new ChatServerThread(this, socket);
      try
      {  client.open();
         client.start();
      }
      catch(IOException ioe)
      {  System.out.println("Error opening thread: " + ioe); }
   }
   public void start()
   {  if (thread == null)
      {  thread = new Thread(this); 
         thread.start();
      }
   }
   public void stop()
   {  if (thread != null)
      {  thread.stop(); 
         thread = null;
      }
   }
   public static void main(String args[])
   {  ChatServer server = null;
      if (args.length != 1)
         System.out.println("Usage: java ChatServer port");
      else
         server = new ChatServer(Integer.parseInt(args[0]));
   }
}