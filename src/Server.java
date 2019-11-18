import java.io.IOException;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {

        DatagramSocket server = new DatagramSocket(Integer.parseInt(args[0]));
        byte[] receiveData = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        server.receive(receivePacket);

        InetAddress ipAddress = receivePacket.getAddress();
        int port = receivePacket.getPort();

        InThread input = new InThread(server);
        OutThread out = new OutThread(ipAddress, port, new DatagramSocket());

        input.start();
        out.start();
        System.out.println("Client has joined the chat!");
    }
}
