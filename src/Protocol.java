import java.io.Closeable;
import java.net.InetAddress;

public interface Protocol {
    public void sendPacket(String msg);
    public void sendPacket(String msg, String hostname, int port);
    public void sendPacket(String msg, InetAddress address, int port);
    public String receivePacket();
    public void closeSocket();
}
