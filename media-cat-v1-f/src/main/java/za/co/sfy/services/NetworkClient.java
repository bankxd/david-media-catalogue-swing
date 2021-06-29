package za.co.sfy.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkClient implements NetworkClientInterface {

	private Socket socket;

	public NetworkClient() {
		runClient();
	}

	public void runClient() {
		try {
			socket = new Socket("127.0.0.1", 12121);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}

	public boolean writeProtocolToServer(String protocol) {
		OutputStream outputStream = null;
		PrintWriter printWriter = null;
		try {
			outputStream = socket.getOutputStream();
			printWriter = new PrintWriter(outputStream, true);
			printWriter.println(protocol);
			System.out.println("CLIENT: Message sent");
			return true;
		} catch (IOException ex) {
			System.out.println("Error Writing: " + ex.getMessage());
		}
		return false;
	}

	public String readProtocolFromServer() {
		String serverResponse = null;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			serverResponse = bufferedReader.readLine();
			System.out.println("CLIENT: Message read");
		} catch (IOException ex) {
			System.out.println("Error Reading: " + ex);
		}
		return serverResponse;
	}
}
