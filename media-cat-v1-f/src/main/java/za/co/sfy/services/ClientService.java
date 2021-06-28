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

	// XXX there is no need for this line of ******
	// *************************************************************************

	public static void main(String[] args) {
//		List<MediaTypeVO> receiveAllOfMediaType = new ClientService().receiveAllOfMediaType(new CDVO());
		List<MediaTypeVO> receiveAllOfMediaType = new ClientService().receiveAllOfMediaType(new DVDVO());
		// XXX The variable receiveAllOfMediaType is never used, so don't create it

		List<String> artists = new ArrayList<>();
		artists.add("Depp");
		artists.add("Dipp");
		CDVO mtcd = new CDVO("yes2");
		// XXX mtcd is not used
		DVDVO mtdvd = new DVDVO(new DVDVO(), "Titanic", 1, "genre", "leadactor", "leadactress");
		// XXX mtdvd is not used
		boolean createMediaType = new ClientService().createMediaType(mtcd);
		// XXX createMediaType is not used
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
				
				// XXX User a StringBuilder when concatenating String, because String in Java immutable
				// https://redfin.engineering/java-string-concatenation-which-way-is-best-8f590a7d22a8
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
		/*
		 * XXX none of the code nested in this try catch block throws any checked exceptions, so there is no need to wrap the code.
		 * Catch specific Exceptions rather than just catching a generic Exception.
		 * https://stackify.com/common-mistakes-handling-java-exception/
		 */
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
				/*
				 * XXX The code is dependent on the order of parameters, If I was to introduce a new parameter (at index 2) then all of this code would need to be re-factored and will fail at runtime.
				 * I would recommed using a key value pair in your String array. E.G "title=X;length=Y;tracks=Z"
				 */
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
			/*
			 * XXX none of the code nested in this try catch block throws any checked exceptions, so there is no need to wrap the code.
			 * Catch specific Exceptions rather than just catching a generic Exception.
			 * https://stackify.com/common-mistakes-handling-java-exception/
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * XXX a method that returns a List, should always return a list even if the
		 * list is empty. Avoid 'Null's at all cost and rather through specific
		 * exceptions than returning nulls
		 * 
		 * This will prevent the users of you method to always perform null checks, WRITE NULL SAFE CODE
		 * 
		 * E.G:
		 * return Collections.emptyList();
		 */
		return null;
	}

	// *************************************************************************

	public boolean update(MediaTypeVO type) {
		return false;
	}

	// *************************************************************************

	public boolean delete(MediaTypeVO type) {
		try {
			
			// XXX there is no need to cast the MediaTypeVO since they inherit from the same super class that as a method .getTitle().
			/* IF you only wrote the below the code would work:
				String title = type.getTitle().toString();
				request("4#CD#" + title);
				String response = nc.readProtocolFromServer();
				return response.equals("Successfully deleted CD") ? true : false;
			 */
			
			if (type instanceof CDVO) {
				CDVO mtcd = (CDVO) type;
				// XXX getTitle() already returns a String, Why are you calling .toString() on a String?
				String title = mtcd.getTitle().toString();
				request("4#CD#" + title);
				String response = nc.readProtocolFromServer();
				
				/*
				 * XXX Please avoid using ternary statements, And please take another look at this line of code.
				 * 
				 * Its saying if true then return true
				 * else if false then return false
				 * 
				 * just right: response.equals("Successfully deleted CD")
				 * 
				 */
				return response.equals("Successfully deleted CD") ? true : false;

			} else if (type instanceof DVDVO) {
				DVDVO mtdvd = (DVDVO) type;
				request("4#DVD#" + mtdvd.getTitle());
				String response = nc.readProtocolFromServer();
				return response.equals("Successfully deleted DVD") ? true : false;
			}
			
			
		// XXX Exception Handling
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	// *************************************************************************

}
