package za.co.sfy.services;

import java.util.ArrayList;
import java.util.List;

import za.co.sfy.model.CDVO;
import za.co.sfy.model.DVDVO;
import za.co.sfy.model.MediaTypeVO;

public class ClientService implements ClientServiceInterface {

	NetworkClientInterface nc;

	public ClientService() {
		nc = new NetworkClient();
	}

	// *************************************************************************

	public static void main(String[] args) {
//		List<MediaTypeVO> receiveAllOfMediaType = new ClientService().receiveAllOfMediaType(new CDVO());
		List<MediaTypeVO> receiveAllOfMediaType = new ClientService().receiveAllOfMediaType(new DVDVO());

		List<String> artists = new ArrayList<>();
		artists.add("Depp");
		artists.add("Dipp");
		CDVO mtcd = new CDVO("yes2");
		DVDVO mtdvd = new DVDVO(new DVDVO(), "Titanic", 1, "genre", "leadactor", "leadactress");
		boolean createMediaType = new ClientService().createMediaType(mtcd);
//		System.out.println(createMediaType);
//		CDVO mtcd1 = new CDVO("yes");
//		new ClientService().delete(mtcd);
//		System.out.println(receiveAllOfMediaType);
	}

	// *************************************************************************

	public boolean createMediaType(MediaTypeVO type) {
		try {
			if (type instanceof CDVO) {
				CDVO mtcd = (CDVO) type;
				request("1#CD#" + mtcd.getTitle() + "#" + mtcd.getLength() + "#" + mtcd.getGenre() + "#"
						+ mtcd.getTracks() + "#" + mtcd.getArtists().toString().replaceAll("\\[|\\]", ""));
				String response = nc.readProtocolFromServer();
				return response.equals("Successfully created CD") ? true : false;

			} else if (type instanceof DVDVO) {
				DVDVO mtdvd = (DVDVO) type;
				request("1#DVD#" + mtdvd.getTitle() + "#" + mtdvd.getLength() + "#" + mtdvd.getGenre() + "#"
						+ mtdvd.getLeadActor() + "#" + mtdvd.getLeadActress());
				String response = nc.readProtocolFromServer();
				return response.equals("Successfully created DVD") ? true : false; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// *************************************************************************

	public boolean request(String protocol) {
		return nc.writeProtocolToServer(protocol);
	}

	// *************************************************************************

	public List<MediaTypeVO> receiveAllOfMediaType(MediaTypeVO type) {
		try {
			if (type instanceof CDVO) {
				List<MediaTypeVO> list = new ArrayList<>();
				CDVO mtcd = (CDVO) type;
				request("2#CD");
				String CDListString = nc.readProtocolFromServer();
				String[] objectElements = CDListString.split("--");
				for (String object : objectElements) {
					String[] objectValues = object.split("#");
					String[] artists = objectValues[5].split(", ");
					List<String> artistList = new ArrayList<>();
					for (String artist : artists) {
						String artistStr = artist.replaceAll("\\[|\\]", "");
						artistList.add(artistStr);
					}
					CDVO cd = new CDVO(mtcd, objectValues[1], Integer.parseInt(objectValues[2]), objectValues[3],
							Integer.parseInt(objectValues[4]), artistList);
					list.add((MediaTypeVO) cd);
				}
				return list;
			} else if (type instanceof DVDVO) {
				List<MediaTypeVO> list = new ArrayList<>();
				DVDVO mtdvd = (DVDVO) type;
				request("2#DVD");
				String DVDListString = nc.readProtocolFromServer();
				String[] objectElements = DVDListString.split("--");
				for (String object : objectElements) {
					String[] objectValues = object.split("#");
					DVDVO dvd = new DVDVO(mtdvd, objectValues[1], Integer.parseInt(objectValues[2]), objectValues[3],
							objectValues[4], objectValues[5]);
					list.add((MediaTypeVO) dvd);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// *************************************************************************

	public boolean update(MediaTypeVO type) {
		return false;
	}

	// *************************************************************************

	public boolean delete(MediaTypeVO type) {
		try {
			if (type instanceof CDVO) {
				CDVO mtcd = (CDVO) type;
				String title = mtcd.getTitle().toString();
				request("4#CD#" + title);
				String response = nc.readProtocolFromServer();
				return response.equals("Successfully deleted CD") ? true : false;

			} else if (type instanceof DVDVO) {
				DVDVO mtdvd = (DVDVO) type;
				request("4#DVD#" + mtdvd.getTitle());
				String response = nc.readProtocolFromServer();
				return response.equals("Successfully deleted DVD") ? true : false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	// *************************************************************************

}
