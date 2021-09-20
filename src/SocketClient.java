import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient
{
    public String connectForOneMessage(String sIP, int iPort, String sMessage)
    {
        try(Socket oSocket = new Socket())
        {
            //attempt to connect to server
            oSocket.connect(new InetSocketAddress(sIP, iPort), 5000);

            //Preparing for output.
            OutputStream output = oSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            //send message
            writer.println(sMessage);
            writer.flush();

            //Get reply from server
            InputStream input = oSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String sReturn = reader.readLine();
            oSocket.close();

            return sReturn;
        }
        catch(Exception ex)
        {
            System.out.println("[client]: Client exception: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }

    }
}
