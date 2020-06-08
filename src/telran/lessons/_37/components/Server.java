package telran.lessons._37.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server
{
	int port;

	public Server(int port)
	{
		this.port = port;
	}

	@SuppressWarnings("InfiniteLoopStatement")
	public void init()
	{
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server is listening on port " + port);

			while(true) {
				run(serverSocket.accept());
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	protected void run(Socket socket)
	{
		try (
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream writer = new PrintStream(socket.getOutputStream())
		) {
			while (true) {
				String request = reader.readLine();
				if (request == null) {
					break;
				}

				writer.println(parseRequest(request));
			}
		} catch (Exception e) {
			System.out.println("Client disconnected unexpectedly");
			return;
		}

		System.out.println("Client connection closed");
	}

	protected abstract String parseRequest(String request);
}
