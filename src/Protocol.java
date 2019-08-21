import java.net.InetAddress;

public interface Protocol {
    public void pack(String msg, String hostname, int port);
    public void pack(String msg);
    public void pack(String msg, InetAddress address, int port);
    public String unpack();
    public void sendPacket();
    public void receivePacket();
    public void closeSocket();
}
