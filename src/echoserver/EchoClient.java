package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
        // Determine the server address. Default to localhost if no arguments are provided.
        String server = (args.length == 0) ? "127.0.0.1" : args[0];

        // Establish a connection to the server
        try (Socket socket = new Socket(server, portNumber)) {
            // Get the input stream to read data from the server
            InputStream input = socket.getInputStream();
            // Get the output stream to send data to the server
            OutputStream output = socket.getOutputStream();

            // Read bytes from System.in (standard input) and send them to the server
            int byteRead;
            while ((byteRead = System.in.read()) != -1) {
                output.write(byteRead); // Write the byte to the server
                output.flush(); // Ensure the byte is sent immediately
            }

            // Shutdown the output stream to signal the server that no more data will be sent
            socket.shutdownOutput();

            // Read the echoed bytes from the server and print them to System.out (standard output)
            while ((byteRead = input.read()) != -1) {
                System.out.write(byteRead); // Write the byte to standard output
            }
            System.out.flush(); // Ensure all data is printed out
        } catch (IOException ioe) {
            // Handle any IO exceptions that occur during communication with the server
            System.err.println("Client error: " + ioe.getMessage());
        }
    }
}