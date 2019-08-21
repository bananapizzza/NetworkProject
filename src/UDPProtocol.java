import java.io.IOException;
import java.net.*;

public class UDPProtocol implements Protocol {
    private DatagramSocket socket;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private byte[] buf = new byte[256];

    public UDPProtocol(){
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public UDPProtocol(int port){
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pack(String msg, String hostname, int port) {
        InetAddress address = getIPAddress(hostname);
        pack(msg, address, port);
    }

    @Override
    public void pack(String msg) {
        InetAddress address = receivePacket.getAddress();
        int port = receivePacket.getPort();
        pack(msg, address, port);
    }

    @Override
    public void pack(String msg, InetAddress address, int port) {
        buf = msg.getBytes();
        sendPacket = new DatagramPacket(buf, msg.length(), address, port);
    }

    @Override
    public String unpack() {
        return new String(receivePacket.getData(), 0, receivePacket.getLength());
    }

    @Override
    public void sendPacket() {
        try {
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receivePacket() {
        receivePacket = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(receivePacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeSocket() {
        socket.close();
    }

    private InetAddress getIPAddress(String hostname){
        InetAddress IPAddress = null;
        try {
            IPAddress = InetAddress.getByName(hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return IPAddress;
    }
}
