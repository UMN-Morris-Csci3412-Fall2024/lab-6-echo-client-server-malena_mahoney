package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            // Run forever, which is common for server style services
            while (true) {
                // Wait until someone connects
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Got a request!");

                    // Get the input and output streams for the client socket
                    InputStream input = clientSocket.getInputStream();
                    OutputStream output = clientSocket.getOutputStream();

                    // Read bytes from the client and write them back to the client
                    int byteRead;
                    while ((byteRead = input.read()) != -1) {
                        output.write(byteRead);
                        output.flush();
                    }
                } catch (IOException e) {
                    System.err.println("Client connection error: " + e.getMessage());
                }
            }
        } catch (IOException ioe) {
            System.err.println("Server error: " + ioe.getMessage());
        }
    }
}
