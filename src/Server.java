public class Server extends Thread {
    private final int PORT = 9876;
    public static final String END = "end";
    private final String UDP_PROTOCOL = "udp";

    private Protocol protocol;
    private boolean running;

    public Server(String protocolType) {
        if (protocolType.equals(UDP_PROTOCOL)) {
            protocol = new UDPProtocol(PORT);
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
