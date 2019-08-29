import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCPServerProtocol is for a connection using Socket and ServerSocket.
 * It provides methods for sending a packet and receiving a packet as server side.
 */
public class TCPServerProtocol extends TCPProtocol {
    private Socket clientSocket;
    private ServerSocket serverSocket;

    /**
     * Server side gets a port number as a param for constructor.
     */
    public TCPServerProtocol(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a packet using TCP protocol.
     * Since TCP connects with a client first and send packet, it doesn't need to get hostname or IP address again.
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
        if (clientSocket == null) {
            try {
                clientSocket = serverSocket.accept();
                bindToInputOutputStream(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
