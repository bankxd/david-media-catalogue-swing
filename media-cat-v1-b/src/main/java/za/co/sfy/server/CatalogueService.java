package za.co.sfy.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import za.co.sfy.dataAccess.CatalogueResource;
import za.co.sfy.dataAccess.CatalogueResourceInterface;
import za.co.sfy.domain.CD;
import za.co.sfy.domain.DVD;
import za.co.sfy.domain.MediaType;

public class CatalogueService {
	private ServerSocket serverSocket = null;

	public static void main(String[] args) {
		new CatalogueService(12121);
	}

	// *************************************************************************
	public CatalogueService(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException ex) {
			System.out.println("Error creating server: " + ex.getMessage());
		}
		runServer();
	}

	// *************************************************************************
	private void runServer() {
		System.out.println("Waiting for client. . .");
		while (true) {
			try {
				new SocketThread(serverSocket.accept()).start();
			} catch (IOException e) {
			}
		}
	}

	// *************************************************************************
	class SocketThread extends Thread {

		CatalogueResourceInterface cr;
		Socket socket;

		SocketThread(Socket socket) {
			this.socket = socket;
			cr = new CatalogueResource();
		}
		
		// *************************************************************************

		@Override
		public void run() {
			System.out.println("SERVER: Client Connected.");
			String response = processClientRequest();
			processServerResponse(response);

		}

		// *************************************************************************

		// Reader
		// CD - action | type | title | length | genre | tracks | artists
		// DVD - action | type | title | length | genre | leadactor | leadactress
		
		public String processClientRequest() {
			BufferedReader br = null;
			String response = null;
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String request = br.readLine();
				String[] protocol = request.split("#");

				int action = Integer.parseInt(protocol[0]);
				switch (action) {
				case 1: // create MediaType
					response = createRequest(protocol);
					break;
				case 2: // get MediaType List
					response = retrieveAllResponse(protocol);
					break;
				case 3: // update MediaType
					response = "Successfully updated MediaType";
					break;
				case 4: // delete MediaType
					response = deleteRequest(protocol);
					break;

				default:
					response = "Invalid request. Can only be (1,2,3,4)";
				}
			} catch (IOException e) {
				e.printStackTrace();
//			} finally {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} finally {
//					try {
//						socket.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
			}
			return response;
		}
		
		// *************************************************************************
		// Writer
		
		public void processServerResponse(String response) {
			PrintWriter pw = null;
			try {
				OutputStream os = socket.getOutputStream();
				pw = new PrintWriter(os, true);
				pw.println(response);
				System.out.println("SERVER: Message written");
			} catch (IOException e) {
				e.printStackTrace();
//			} finally {
//				try {
//					pw.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					try {
//						socket.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
			}
		}
		
		// *************************************************************************

		public String createRequest(String[] protocol) {
			try {
				if (protocol[1].equals("CD")) {
					List<String> artists = new ArrayList<>();
					for (String artist : protocol[6].split(", ")) {
						artists.add(artist);
					}
					MediaType cd = new CD(new CD(), protocol[2], Integer.parseInt(protocol[3]), protocol[4],
							Integer.parseInt(protocol[5]), artists);
					boolean createCD = cr.create(cd);

					return createCD == true ? "Successfully created CD" : "Failed";

				} else if (protocol[1].equals("DVD")) {
					MediaType dvd = new DVD(new DVD(), protocol[2], Integer.parseInt(protocol[3]), protocol[4],
							protocol[5], protocol[6]);
					boolean createDVD = cr.create(dvd);

					return createDVD == true ? "Successfully created DVD" : "Failed";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "Failed";
		}

		// *************************************************************************

		@Test
		public String retrieveAllResponse(String[] protocol) {
			try {
				if (protocol[1].equals("CD")) {
					StringBuilder sb1 = new StringBuilder();
					List<MediaType> retrieveAllOfTypeCD = cr.retrieveAllOfType(new CD());
					for (MediaType mediatype : retrieveAllOfTypeCD) {
						sb1.append(("CD" + "#" + ((CD) mediatype).getTitle() + "#" + ((CD) mediatype).getLength() + "#"
								+ ((CD) mediatype).getGenre() + "#" + ((CD) mediatype).getTracks() + "#"
								+ ((CD) mediatype).getArtists()));
						sb1.append("--");
					}
					return sb1.toString();
				} else if (protocol[1].equals("DVD")) {
					StringBuilder sb2 = new StringBuilder();
					List<MediaType> retrieveAllOfTypeDVD = cr.retrieveAllOfType((MediaType) new DVD());
					for (MediaType mediatype : retrieveAllOfTypeDVD) {
						sb2.append(("DVD" + "#" + ((DVD) mediatype).getTitle() + "#" + ((DVD) mediatype).getLength()
								+ "#" + ((DVD) mediatype).getGenre() + "#" + ((DVD) mediatype).getLeadActor() + "#"
								+ ((DVD) mediatype).getLeadActress()));
						sb2.append("--");
					}
					return sb2.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "Failed";
		}

		// *************************************************************************

		public String deleteRequest(String[] protocol) {
			try {
				if (protocol[1].equals("CD")) {
					MediaType cd = new CD(new CD(), protocol[2]);
					boolean deleteCD = cr.delete(cd);

					return deleteCD == true ? "Successfully deleted CD" : "Failed";

				} else if (protocol[1].equals("DVD")) {
					MediaType dvd = new DVD(new DVD(), protocol[2]);
					boolean deleteDVD = cr.delete(dvd);

					return deleteDVD == true ? "Successfully deleted DVD" : "Failed";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "Failed";
		}

		// *************************************************************************
	}
}
