import java.net.*;
import java.io.*;

public class Pigeon extends Thread
{  private Socket          socket   = null;
   private int             ID       = -1;
   private DataInputStream streamIn =  null;

   public Pigeon(Socket _socket)
   {   socket = _socket;  ID = socket.getPort();
   }
   public void run()
   {  System.out.println("Server Thread " + ID + " running.");
      while (true)
      {  try
         {  System.out.println(streamIn.readUTF());
         }
         catch(IOException ioe) {  }
      }
   }
   public void open() throws IOException
   {  streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
   }
   public void close() throws IOException
   {  if (socket != null)    socket.close();
      if (streamIn != null)  streamIn.close();
   }
}