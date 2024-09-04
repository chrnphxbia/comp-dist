// Server.java
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        // Port number where the server will listen
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // Wait for a client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Set up input and output streams for communication
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            // Read message from the client
            String message = reader.readLine();
            System.out.println("Received from client: " + message);

            // Send a response to the client
            writer.println("Hello from the Java server!");

            // Close the socket connection
            socket.close();
            System.out.println("Connection closed");
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
