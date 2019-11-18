import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class InThread extends Thread {

    private DatagramSocket server;

    public InThread(DatagramSocket server) {
        this.server =  server;
    }

    public void run(){

        try {

            while(true) {
                byte[] receiveData = new byte[1024];

                DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
                server.receive(receivePacket);

                receiveData = receivePacket.getData();

                String sentence = new String(receiveData);
                System.out.println(sentence);
            }
        }
        catch (IOException exception){ }
    }
}

//int length = receiveData.length;
//byte[] correctReceive = new byte[length];

//for(int j = 0; j < length; j++) {
//correctReceive[j] = receiveData[j];
//}