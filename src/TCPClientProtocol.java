import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClientProtocol extends TCPProtocol {
    private Socket clientSocket;

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
