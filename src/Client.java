public class Client {
    private final int PORT = 9876;
    private final String SERVER = "localhost";
    private Protocol protocol;

    public Client(String type) {
        if(type.equals("udp")){
            protocol = new UDPProtocol();
        }
    }

    public String sendMessageToServer(String msg) {
        protocol.pack(msg, SERVER, PORT);
        protocol.sendPacket();
        protocol.receivePacket();
        String received = protocol.unpack();
        return received;
    }

    public void close() {
        protocol.closeSocket();
    }
}
