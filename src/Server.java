public class Server extends Thread {
    private final int PORT = 9876;
    public static final String END = "end";

    private Protocol protocol;
    private boolean running;

    public Server(String protocolType) {
        if (protocolType.equals(UDPProtocol.UDP_PROTOCOL)) {
            protocol = new UDPProtocol(PORT);
        } else if (protocolType.equals(TCPProtocol.TCP_PROTOCOL)) {
            protocol = new TCPServerProtocol(PORT);
        }
    }

    public void run() {
        running = true;
        while (running) {
            String received = protocol.receivePacket();
            protocol.sendPacket(received);

            if (received.equals(END)) {
                running = false;
                continue;
            }
        }
        protocol.closeSocket();
    }
}
