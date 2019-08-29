import java.net.InetAddress;

public interface Protocol {
    void sendPacket(String msg);

    void sendPacket(String msg, String hostname, int port);

    void sendPacket(String msg, InetAddress address, int port);

    String receivePacket();

    void closeSocket();
}
