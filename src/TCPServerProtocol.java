import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerProtocol extends TCPProtocol {
    private Socket clientSocket;
    private ServerSocket serverSocket;

    public TCPServerProtocol(int port) {
        try {
            serverSocket = new ServerSocket(port);
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
