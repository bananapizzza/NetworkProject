import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class TCPProtocol implements Protocol {
    public static final String TCP_PROTOCOL = "tcp";
    protected BufferedReader in;
    protected PrintWriter out;

    protected void bindToInputOutputStream(Socket socket) {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
