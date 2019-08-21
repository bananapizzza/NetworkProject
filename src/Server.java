import java.io.IOException;
import java.net.*;

public class Server extends Thread {
    private final int PORT = 9876;
    public static final String END = "end";

    private DatagramSocket serverSocket;
    private byte[] buf = new byte[256];
    private boolean running;

    public Server() {
        try {
            serverSocket = new DatagramSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        running = true;
        while (running) {
            DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
            try {
                serverSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String receivedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, IPAddress, port);
            try {
                serverSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (receivedSentence.equals(END)) {
                running = false;
                continue;
            }
        }
        serverSocket.close();
    }
}
