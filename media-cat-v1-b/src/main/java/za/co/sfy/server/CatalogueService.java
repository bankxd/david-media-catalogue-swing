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

	public CatalogueService(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException ex) {
			System.out.println("Error creating server: " + ex.getMessage());
			System.exit(0);
		}
		runServer();
	}

	private void runServer() {
		System.out.println("Waiting for client. . .");
		while (true) {
			try {
				new SocketThread(serverSocket.accept()).start();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	class SocketThread extends Thread {

		CatalogueResourceInterface catalogueResource;
		Socket socket;

		SocketThread(Socket socket) {
			this.socket = socket;
			catalogueResource = new CatalogueResource();
		}

		@Override
		public void run() {
			System.out.println("SERVER: Client Connected.");
			String response = processClientRequest();
			processServerResponse(response);
		}

		// Reader
		// CD - action | type | title | length | genre | tracks | artists
		// DVD - action | type | title | length | genre | leadactor | leadactress
		public String processClientRequest() {
			BufferedReader bufferedReader = null;
			String response = null;
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String request = bufferedReader.readLine();
				String[] protocol = request.split("#");
				int action = Integer.parseInt(protocol[0]);
				switch (action) {
				case 1: // create MediaType
					response = createRequest(protocol);
					break;
				case 2: // get MediaType List
					response = retrieveAllResponse(protocol);
					break;
				case 4: // delete MediaType
					response = deleteRequest(protocol);
					break;
				default:
					response = "Invalid request. Can only be (1,2,4)";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}

		// Writer
		public void processServerResponse(String response) {
			PrintWriter printWriter = null;
			OutputStream outputStream = null;
			try {
				outputStream = socket.getOutputStream();
				printWriter = new PrintWriter(outputStream, true);
				printWriter.println(response);
				System.out.println("SERVER: Message written");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public String createRequest(String[] protocol) {
			try {
				if (protocol[1].equals("CD")) {
					List<String> artists = new ArrayList<>();
					for (String artist : protocol[6].split(", ")) {
						artists.add(artist);
					}
					CD cd = new CD();
					cd.setType(new CD());
					cd.setTitle(protocol[2]);
					cd.setLength(Integer.parseInt(protocol[3]));
					cd.setGenre(protocol[4]);
					cd.setTracks(Integer.parseInt(protocol[5]));
					cd.setArtists(artists);
					boolean createCD = catalogueResource.create(cd);
					return createCD == true ? "Successfully created CD" : "Failed";
				} else if (protocol[1].equals("DVD")) {
					DVD dvd = new DVD();
					dvd.setType(new DVD());
					dvd.setTitle(protocol[2]);
					dvd.setLength(Integer.parseInt(protocol[3]));
					dvd.setGenre(protocol[4]);
					dvd.setLeadActor(protocol[5]);
					dvd.setLeadActress(protocol[6]);
					boolean createDVD = catalogueResource.create(dvd);
					return createDVD == true ? "Successfully created DVD" : "Failed";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "Failed";
		}

		public String retrieveAllResponse(String[] protocol) {
			try {
				if (protocol[1].equals("CD")) {
					StringBuilder sb1 = new StringBuilder();
					List<MediaType> retrieveAllOfTypeCD = catalogueResource.retrieveAllOfType(new CD());
					for (MediaType mediatype : retrieveAllOfTypeCD) {
						sb1.append(("CD" + "#" + ((CD) mediatype).getTitle() + "#" + ((CD) mediatype).getLength() + "#"
								+ ((CD) mediatype).getGenre() + "#" + ((CD) mediatype).getTracks() + "#"
								+ ((CD) mediatype).getArtists()));
						sb1.append("--");
					}
					return sb1.toString();
				} else if (protocol[1].equals("DVD")) {
					StringBuilder sb2 = new StringBuilder();
					List<MediaType> retrieveAllOfTypeDVD = catalogueResource.retrieveAllOfType((MediaType) new DVD());
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

		public String deleteRequest(String[] protocol) {
			try {
				if (protocol[1].equals("CD")) {
					CD cd = new CD();
					cd.setType(cd);
					cd.setTitle(protocol[2]);
					boolean deleteCD = catalogueResource.delete(cd);
					return deleteCD == true ? "Successfully deleted CD" : "Failed";
				} else if (protocol[1].equals("DVD")) {
					DVD dvd = new DVD();
					dvd.setType(dvd);
					dvd.setTitle(protocol[2]);
					boolean deleteDVD = catalogueResource.delete(dvd);
					return deleteDVD == true ? "Successfully deleted DVD" : "Failed";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "Failed";
		}
	}
}
