package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        // Create a server socket to listen for incoming connections on the specified port
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            // Run indefinitely to handle multiple client connections
            while (true) {
                // Wait for a client to connect
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Got a request!");

                    // Get the input stream to read data sent by the client
                    InputStream input = clientSocket.getInputStream();
                    // Get the output stream to send data back to the client
                    OutputStream output = clientSocket.getOutputStream();

                    // Read bytes from the client and write them back to the client (echo)
                    int byteRead;
                    while ((byteRead = input.read()) != -1) {
                        output.write(byteRead); // Write the byte back to the client
                        output.flush(); // Ensure the byte is sent immediately
                    }
                } catch (IOException e) {
                    // Handle any IO exceptions that occur during communication with the client
                    System.err.println("Client connection error: " + e.getMessage());
                }
            }
        } catch (IOException ioe) {
            // Handle any IO exceptions that occur while setting up the server
            System.err.println("Server error: " + ioe.getMessage());
        }
    }
}
