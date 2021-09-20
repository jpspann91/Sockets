import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable
{
    private int thisServerPort;

    //This constructor forces port to be passed in, and that is necessary for
    //start up of ServerSocket
    public SocketServer(int iPort)
    {
        thisServerPort = iPort;
    }


    public void run()
    {
        try(ServerSocket oServerSocket = new ServerSocket(thisServerPort))
        {
            System.out.println("Server is listening on port: " + thisServerPort);

            while(true)
            {
                Socket oSocket = oServerSocket.accept(); //waiting/ listening for call.
                System.out.println("[server]: New client connected: " + oSocket.getRemoteSocketAddress());

                //Handling the input
                InputStream input = oSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                //Preparing for output.
                OutputStream output = oSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                String sReceivedMessage = reader.readLine();

                System.out.println("[server]: Server received message: " + sReceivedMessage);

                writer.println("Server received your message.");

                writer.flush();
            }
        }
        catch(IOException ex)
        {
            System.out.println("[server]: Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
