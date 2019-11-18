import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        try{
            byte[] sendData = new byte[1024];
            int port = Integer.parseInt(args[0]);

            InetAddress ipAddress = InetAddress.getByName(args[1]);
            client.send(new DatagramPacket(sendData, sendData.length, ipAddress, port));

            InThread in = new InThread(client);
            OutThread out = new OutThread(ipAddress, port, client);

            in.start();
            out.start();
            System.out.println("You have joined the chat!");
            out.join();
        }
        catch(RuntimeException ex){ }
        catch(InterruptedException ex) {}
    }

}
