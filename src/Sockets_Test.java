import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetSocketAddress;

public class Sockets_Test
{
    public static void main(String[] args)
    {
        System.out.println("Enter port for this server to listen on: ");
        Scanner keyboard = new Scanner(System.in);
        int iPort = keyboard.nextInt();

        //Get IP and port of server to connect to
        System.out.println("Enter IP address of server to connect to: ");
        String sOtherServerIP = new Scanner(System.in).nextLine();

        System.out.println("Enter port of the server to connect to: ");
        int iOtherServerPort = new Scanner(System.in).nextInt();

        //Start up server thread

        SocketServer oSocketServer = new SocketServer(iPort);
        Thread oServerThread = new Thread(oSocketServer);
        oServerThread.start();

        SocketClient oClient = new SocketClient();

        while(true)
        {
            System.out.println("Enter in a message to send: ");
            String sMessage = new Scanner(System.in).nextLine();

            String sReplyFromServer = oClient.connectForOneMessage(sOtherServerIP, iOtherServerPort, sMessage);

            System.out.println("[client]: Server reply: " + sReplyFromServer);
        }
    }
}
