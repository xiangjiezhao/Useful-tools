
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author zhaoxiangjie
 * @version 1.0
 * @date 2023/8/24
 */
public class SocketListenerUtil2 {
    public static void main(String[] args) {
        int port = 23;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Wait for the connect...");

            while (true) {
                Socket socket = serverSocket.accept();
                Thread clientThread = new Thread(() -> handleClientRequest(socket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket socket) {
        try {
            System.out.println("new connection,client address：" + socket.getInetAddress());

            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesRead);
                System.out.println("message received：" + message);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
