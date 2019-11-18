import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class OutThread extends Thread{

    InetAddress ipAddress;
    int port;
    DatagramSocket sender;

    public OutThread(InetAddress ip, int port, DatagramSocket socket) {
        this.ipAddress = ip;
        this.port = port;
        this.sender = socket;
    }

    public void run() {

        String name = "You";

        while (true) {

            Scanner scan = new Scanner(System.in);
            String send = scan.nextLine();

            boolean flag = true;
            if(send.equals("@name")) {
                System.out.println("Input your name: ");
                name = scan.nextLine();
                System.out.println("Your name has successfully changed!");
                flag = false;
            }

            if(send.equals("@quit")){
                String end = "change da world My final message. Goodb ye";
                DatagramPacket res = new DatagramPacket(end.getBytes(), end.getBytes().length, ipAddress, port);
                try {
                    sender.send(res);
                } catch (IOException e) { }

                System.exit(0);
                sender.close();
            }

            if(flag) {

                StringBuilder view = new StringBuilder();
                view.append(name).append(": ").append(send);
                System.out.println(view.toString());

                StringBuilder msg = new StringBuilder();
                if (!name.equals("You")) {
                    msg.append(name).append(": ").append(send);
                }
                else {
                    msg.append("Opponent").append(": ").append(send);
                }
                DatagramPacket dp = new DatagramPacket(msg.toString().getBytes(), msg.toString().getBytes().length, ipAddress, port);
                try {
                    sender.send(dp);
                }
                catch (IOException e) { }
            }
        }

    }
}
