import java.io.IOException;
import java.net.*;

public class Client {
    private final int PORT = 9876;
    private final String SERVER = "localhost";
    private DatagramSocket clientSocket;
    private byte[] buf;

    public Client() {
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public String sendMessageToServer(String msg) {
        String receivedSentence = null;
        InetAddress IPAddress = null;
        try {
            IPAddress = InetAddress.getByName(SERVER);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        buf = msg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, IPAddress, PORT);
        try {
            clientSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
        try {
            clientSocket.receive(receivePacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        receivedSentence = new String(receivePacket.getData());
        return receivedSentence;
    }

    public void close() {
        clientSocket.close();
    }
}
