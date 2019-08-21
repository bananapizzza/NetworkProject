public class Server extends Thread {
    private final int PORT = 9876;
    public static final String END = "end";
    private Protocol protocol;
    private boolean running;

    public Server(String type) {
        if(type.equals("udp")){
            protocol = new UDPProtocol(PORT);
        }
    }

    public void run() {
        running = true;
        while (running) {
            protocol.receivePacket();
            String received = protocol.unpack();
            protocol.pack(received);
            protocol.sendPacket();

            if (received.equals(END)) {
                running = false;
                continue;
            }
        }
        protocol.closeSocket();
    }
}
