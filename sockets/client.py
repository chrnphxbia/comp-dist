# client.py
import socket

def main():
    # Server address and port
    host = 'localhost'
    port = 5000

    # Create a socket and connect to the server
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
        client_socket.connect((host, port))

        # Send a message to the server
        message = "Hello from the Python client!"
        client_socket.sendall(message.encode('utf-8'))

        # Receive a response from the server
        response = client_socket.recv(1024)
        print("Received from server:", response.decode('utf-8'))

if __name__ == "__main__":
    main()
