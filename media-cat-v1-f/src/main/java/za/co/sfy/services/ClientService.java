package za.co.sfy.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import za.co.sfy.model.CDVO;
import za.co.sfy.model.DVDVO;
import za.co.sfy.model.MediaTypeVO;

public class ClientService implements ClientServiceInterface {

	NetworkClientInterface nc;

	public ClientService() {
		nc = new NetworkClient();
	}

	public boolean request(String protocol) {
		return nc.writeProtocolToServer(protocol);
	}

	public boolean createMediaType(MediaTypeVO type) {
		if (type instanceof CDVO) {
			CDVO mtcd = (CDVO) type;
			StringBuilder stringBuilderCD = new StringBuilder();
			stringBuilderCD.append("1#CD#");
			stringBuilderCD.append(mtcd.getTitle());
			stringBuilderCD.append("#");
			stringBuilderCD.append(mtcd.getLength());
			stringBuilderCD.append("#");
			stringBuilderCD.append(mtcd.getGenre());
			stringBuilderCD.append("#");
			stringBuilderCD.append(mtcd.getTracks());
			stringBuilderCD.append("#");
			stringBuilderCD.append(mtcd.getArtists().toString().replaceAll("\\[|\\]", ""));
			request(stringBuilderCD.toString());
			nc.readProtocolFromServer();
			return true;

		} else if (type instanceof DVDVO) {
			DVDVO mtdvd = (DVDVO) type;
			StringBuilder stringBuilderDVD = new StringBuilder();
			stringBuilderDVD.append("1#DVD#");
			stringBuilderDVD.append(mtdvd.getTitle());
			stringBuilderDVD.append("#");
			stringBuilderDVD.append(mtdvd.getLength());
			stringBuilderDVD.append("#");
			stringBuilderDVD.append(mtdvd.getGenre());
			stringBuilderDVD.append("#");
			stringBuilderDVD.append(mtdvd.getLeadActor());
			stringBuilderDVD.append("#");
			stringBuilderDVD.append(mtdvd.getLeadActress());
			request(stringBuilderDVD.toString());
			nc.readProtocolFromServer();
			return true;
		}
		return false;
	}

	public List<MediaTypeVO> receiveAllOfMediaType(MediaTypeVO type) {
		if (type instanceof CDVO) {
			List<MediaTypeVO> CDlist = new ArrayList<>();
			request("2#CD");
			String CDListString = nc.readProtocolFromServer();
			String[] objectElements = CDListString.split("--");
			/*
			 * XXX The code is dependent on the order of parameters, If I was to introduce a
			 * new parameter (at index 2) then all of this code would need to be re-factored
			 * and will fail at runtime. I would recommed using a key value pair in your
			 * String array. E.G "title=X;length=Y;tracks=Z"
			 */
			for (String object : objectElements) {
				String[] objectValues = object.split("#");
				String[] artists = objectValues[5].split(", ");
				List<String> artistList = new ArrayList<>();
				for (String artist : artists) {
					String artistStr = artist.replaceAll("\\[|\\]", "");
					artistList.add(artistStr);
				}
				CDVO cd = new CDVO();
				cd.setType(cd);
				cd.setTitle(objectValues[1]);
				cd.setLength(Integer.parseInt(objectValues[2]));
				cd.setGenre(objectValues[3]);
				cd.setTracks(Integer.parseInt(objectValues[4]));
				cd.setArtists(artistList);
				CDlist.add(cd);
			}
			return CDlist;
		} else if (type instanceof DVDVO) {
			List<MediaTypeVO> DVDlist = new ArrayList<>();
			request("2#DVD");
			String DVDListString = nc.readProtocolFromServer();
			String[] objectElements = DVDListString.split("--");
			for (String object : objectElements) {
				String[] objectValues = object.split("#");
				DVDVO dvd = new DVDVO();
				dvd.setType(dvd);
				dvd.setTitle(objectValues[1]);
				dvd.setLength(Integer.parseInt(objectValues[2]));
				dvd.setGenre(objectValues[3]);
				dvd.setLeadActor(objectValues[4]);
				dvd.setLeadActress(objectValues[5]);
				DVDlist.add(dvd);
			}
			return DVDlist;
		}
		return Collections.emptyList();
	}

	public boolean delete(MediaTypeVO type) {
		if (type instanceof CDVO) {
			CDVO mtcd = (CDVO) type;
			String title = mtcd.getTitle();
			request("4#CD#" + title);
			nc.readProtocolFromServer();
			return true;
		} else if (type instanceof DVDVO) {
			DVDVO mtdvd = (DVDVO) type;
			request("4#DVD#" + mtdvd.getTitle());
			nc.readProtocolFromServer();
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		List<MediaTypeVO> receiveAllOfMediaType = new ClientService().receiveAllOfMediaType(new DVDVO());
System.out.println(receiveAllOfMediaType);
	}
}
