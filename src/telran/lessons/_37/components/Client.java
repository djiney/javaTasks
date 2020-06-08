package telran.lessons._37.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class Client
{
	protected String host;
	protected int port;

	protected Socket socket;
	protected BufferedReader reader;
	protected PrintStream writer;

	public Client(String host, int port)
	{
		this.host = host;
		this.port = port;
	}

	public void init()
	{
		try {
			socket = new Socket(host, port);
			writer = new PrintStream(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while(shouldRun()) {
				run();
			}

			close();
		} catch (UnknownHostException e) {
			throw new RuntimeException("Unknown host: " + host);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	protected abstract boolean shouldRun();
	protected abstract void run();

	protected void close()
	{
		try {
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public String send(String request)
	{
		writer.println(request);

		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
