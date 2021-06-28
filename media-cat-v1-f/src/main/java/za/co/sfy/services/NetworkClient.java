package za.co.sfy.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkClient implements NetworkClientInterface {

	// XXX 's' is a terrible name for a variable, call it socket
	private Socket s;

//	public static void main(String[] args) {
//		String test = "1#CD#title#2#genre#12#artist1, artist2";
//		String test1 = "1#DVD#title#212#genre#leadactor#leadactress";
//		String test12 = "4#DVD#title";
//		new NetworkClient().writeProtocolToServer(test12);
//		String readProtocolFromServer = new NetworkClient().readProtocolFromServer();
//		System.out.println(readProtocolFromServer);
//	}

	public NetworkClient() {
		runClient();
	}
	
	// *************************************************************************

	public void runClient() {
		try {
			s = new Socket("127.0.0.1", 12121);
		} catch (IOException ex) {
			// XXX This is where we should be throwing a RuntimeException, If we cannot connect to the database the should the app continue to run?
			System.out.println("Error creating socket"); 
			ex.printStackTrace();
		}
	}
	
	// *************************************************************************

	public boolean writeProtocolToServer(String protocol) {
		try {
			OutputStream os = s.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println(protocol);
			System.out.println("CLIENT: Message sent");
			return true;
		} catch (IOException ex) {
			System.out.println("Error Writing: " + ex.getMessage());
		} finally {
			// XXX why are you not closing the connections? It seems that the code calling this code is responsible to managing the connections?
//			try {
//				if (s != null) {
//					s.close();
//				}
//			} catch (IOException ex) {
//			}
		}
		return false;
	}
	
	// *************************************************************************

	public String readProtocolFromServer() {
		String serverResponse = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			serverResponse = br.readLine();
			System.out.println("CLIENT: Message read");
		} catch (IOException ex) {
			System.out.println("Error Reading: " + ex);
		} finally {
//			try {
//				if (br != null) {
//					br.close();
//				}
//			} catch (IOException ex) {
//			}
		}
		return serverResponse;
	}
	
	// *************************************************************************

}
