public class Client {
    private final int PORT = 9876;
    private final String SERVER = "localhost";
    private final String UDP_PROTOCOL = "udp";
    private Protocol protocol;

    public Client(String protocolType) {
        if (protocolType.equals(UDP_PROTOCOL)) {
            protocol = new UDPProtocol();
        }
    }

    public String sendMessageToServer(String msg) {
        protocol.sendPacket(msg, SERVER, PORT);
        String received = protocol.receivePacket();
        return received;
    }

    public void close() {
        protocol.closeSocket();
    }
}
