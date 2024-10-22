package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
        String server = (args.length == 0) ? "127.0.0.1" : args[0];

        try (Socket socket = new Socket(server, portNumber)) {
            // Get the input and output streams for the socket
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            // Read bytes from System.in and send them to the server
            int byteRead;
            while ((byteRead = System.in.read()) != -1) {
                output.write(byteRead);
                output.flush();
            }

            // Shutdown the output to signal the server we're done sending data
            socket.shutdownOutput();

            // Read the echoed bytes from the server and print them to System.out
            while ((byteRead = input.read()) != -1) {
                System.out.write(byteRead);
            }
            System.out.flush();
        } catch (IOException ioe) {
            System.err.println("Client error: " + ioe.getMessage());
        }
    }
}