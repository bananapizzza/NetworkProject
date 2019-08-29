import java.io.IOException;
import java.net.*;

/**
 * UDPProtocol is for a connection using DatagramSocket.
 * It provides methods for sending a packet and receiving a packet.
 */
public class UDPProtocol implements Protocol {

    public static final String UDP_PROTOCOL = "udp";
    /*
     * Fields used for the connection
     */
    private DatagramSocket socket;
    private byte[] buf = new byte[256];

    /*
     * Fields for storing the current connection information
     */
    private InetAddress hostAddress;
    private Integer hostPort;

    /**
     * There are 2 types of constructor.
     * Without any params and with a port number.
     */
    public UDPProtocol() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public UDPProtocol(int port) {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a packet using UDP protocol.
     * Get the message, hostname, and port
     * Make a Datagram packet and send.
     * In this case, send the message to the sender of the latest received message.
     *
     * @param msg the message to send
     */
    @Override
    public void sendPacket(String msg) {
        DatagramPacket sendPacket = pack(msg);
        send(sendPacket);
    }

    /**
     * Send a packet using UDP protocol.
     * Get the message, hostname, and port
     * Make a Datagram packet and send.
     *
     * @param msg      the message to send
     * @param hostname receiver's hostname
     * @param port     receiver's port number
     */
    @Override
    public void sendPacket(String msg, String hostname, int port) {
        DatagramPacket sendPacket = pack(msg, hostname, port);
        send(sendPacket);
    }

    /**
     * Send a packet using UDP protocol.
     * Get the message, address, and port
     * Make a Datagram packet and send.
     *
     * @param msg     the message to send
     * @param address receiver's IP address
     * @param port    receiver's port number
     */
    @Override
    public void sendPacket(String msg, InetAddress address, int port) {
        DatagramPacket sendPacket = pack(msg, address, port);
        send(sendPacket);
    }

    /**
     * Receive a packet using UDP protocol.
     *
     * @return the data of the received packet
     */
    @Override
    public String receivePacket() {
        DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(receivedPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String receivedData = unpack(receivedPacket);
        return receivedData;
    }

    /**
     * Close UDP socket.
     */
    @Override
    public void closeSocket() {
        socket.close();
    }

    private InetAddress getIPAddress(String hostname) {
        InetAddress IPAddress = null;
        try {
            IPAddress = InetAddress.getByName(hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return IPAddress;
    }


    private DatagramPacket pack(String msg, String hostname, int port) {
        InetAddress address = getIPAddress(hostname);
        return pack(msg, address, port);
    }

    private DatagramPacket pack(String msg) {
        if (hostAddress == null || hostPort == null) {
            throw new NullPointerException();
        }
        return pack(msg, hostAddress, hostPort);
    }

    private DatagramPacket pack(String msg, InetAddress address, int port) {
        buf = msg.getBytes();
        return new DatagramPacket(buf, msg.length(), address, port);
    }

    private void send(DatagramPacket sendPacket) {
        try {
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String unpack(DatagramPacket receivedPacket) {
        hostPort = receivedPacket.getPort();
        hostAddress = receivedPacket.getAddress();
        return new String(receivedPacket.getData(), 0, receivedPacket.getLength());
    }
}
