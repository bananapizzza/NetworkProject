import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TCPServerClientTest {
    Client client;

    @Before
    public void setup() {
        new Server("tcp").start();
        client = new Client("tcp");
    }

    @Test
    public void testSendMessageToClientAndGetResponse() {
        String msg = client.sendMessageToServer("hello server");
        assertEquals("hello server", msg);
        msg = client.sendMessageToServer("server is working");
        assertFalse(msg.equals("hello server"));
    }

    @After
    public void tearDown() {
        client.sendMessageToServer("end");
        client.close();
    }
}