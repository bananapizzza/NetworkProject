import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * TCPClientProtocol is for a connection using Socket and ServerSocket.
 * It provides methods for sending a packet and receiving a packet as client side.
 */
public class TCPClientProtocol extends TCPProtocol {
    private Socket clientSocket;

    /**
     * There are 2 types of constructor.
     * With hostname and port or with IP address and port.
     */
    TCPClientProtocol(String hostname, int port) {
        try {
            clientSocket = new Socket(hostname, port);
            bindToInputOutputStream(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    TCPClientProtocol(InetAddress address, int port) {
        try {
            clientSocket = new Socket(address, port);
            bindToInputOutputStream(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a packet using TCP protocol.
     * Since TCP connects with a server first and send packet, it doesn't need to get hostname or IP address again.
     * Therefore we only use sendPacket(String msg) method.
     * @param msg the message to send
     */
    @Override
    public void sendPacket(String msg) {
        out.println(msg);
    }

    @Override
    public void sendPacket(String msg, String hostname, int port) {
        sendPacket(msg);
    }

    @Override
    public void sendPacket(String msg, InetAddress address, int port) {
        sendPacket(msg);
    }

    /**
     * Receive a packet using TCP protocol.
     *
     * @return the data of the received packet
     */
    @Override
    public String receivePacket() {
        String receivedData = "";
        try {
            receivedData = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receivedData;
    }

    /**
     * Close TCP socket and input/output stream.
     */
    @Override
    public void closeSocket() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
