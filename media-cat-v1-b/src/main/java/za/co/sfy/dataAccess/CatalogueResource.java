package za.co.sfy.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import za.co.sfy.domain.CD;
import za.co.sfy.domain.DVD;
import za.co.sfy.domain.MediaType;

public class CatalogueResource implements CatalogueResourceInterface {

	private String JDBC_DRIVER;
	private String DB_URL;
	private String USER;
	private String PASS;
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private final String ACTIVITY;

	public CatalogueResource() {
		JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		DB_URL = "jdbc:mysql://localhost:3306/media_cat_v1?autoReconnect=true&useSSL=false";
		USER = "root";
		PASS = "root";
		ACTIVITY = "ACTIVE";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("[Connected Successfully to JDB]\n");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error: Cannot connect to the database!" + e.getMessage());
			System.exit(0);
		}
	}

	public boolean create(MediaType type) {
		try {
			if (type instanceof CD) {
				StringBuilder sb = new StringBuilder();
				CD cd = (CD) type;
				int checkCD = 0;
				ps = conn.prepareStatement(
						"INSERT INTO CDTABLE(TITLE,LENGTH,GENRE,TYPE,TRACKS,ARTISTS,ACTIVITY) VALUES (?,?,?,?,?,?,?)");
				ps.setString(1, cd.getTitle());
				ps.setInt(2, cd.getLength());
				ps.setString(3, cd.getGenre());
				ps.setString(4, cd.getClass().getSimpleName());
				ps.setInt(5, cd.getTracks());
				for (String artist : cd.getArtists()) {
					sb.append(artist + ", ");
				}
				ps.setString(6, sb.toString());
				ps.setString(7, ACTIVITY);
				checkCD = ps.executeUpdate();
				System.out.println(checkCD > 0 ? "Success saving CD." : "Failed saving CD.");
				return checkCD > 0 ? true : false;
			} else if (type instanceof DVD) {
				DVD dvd = (DVD) type;
				int checkDVD = 0;
				ps = conn.prepareStatement(
						"INSERT INTO DVDTABLE(TITLE,LENGTH,GENRE,TYPE,LEADACTOR, LEADACTRESS, ACTIVITY) VALUES (?,?,?,?,?,?,?)");
				ps.setString(1, dvd.getTitle());
				ps.setInt(2, dvd.getLength());
				ps.setString(3, dvd.getGenre());
				ps.setString(4, dvd.getClass().getSimpleName());
				ps.setString(5, dvd.getLeadActor());
				ps.setString(6, dvd.getLeadActress());
				ps.setString(7, ACTIVITY);
				checkDVD = ps.executeUpdate();
				System.out.println(checkDVD > 0 ? "Success saving DVD." : "Failed saving DVD.");
				return checkDVD > 0 ? true : false;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return false;
	}

	public List<MediaType> retrieveAllOfType(MediaType type) {
		try {
			if (type instanceof CD) {
				List<MediaType> CDList = new ArrayList<>();
				ps = conn.prepareStatement(
						"SELECT TITLE, LENGTH, GENRE, TYPE, TRACKS, ARTISTS FROM CDTABLE WHERE ACTIVITY = 'ACTIVE'");
				rs = ps.executeQuery();
				while (rs.next()) {
					List<String> artistList = new ArrayList<>();
					String artists = rs.getString("ARTISTS");
					String[] split = artists.split(", ");
					for (String artist : split) {
						artistList.add(artist);
					}
					CD cd = new CD();
					cd.setType((CD) type);
					cd.setTitle(rs.getString("TITLE"));
					cd.setLength(rs.getInt("LENGTH"));
					cd.setGenre(rs.getString("GENRE"));
					cd.setTracks(rs.getInt("TRACKS"));
					cd.setArtists(artistList);
					CDList.add(cd);
				}
				return CDList;
			} else if (type instanceof DVD) {
				List<MediaType> DVDList = new ArrayList<>();
				ps = conn.prepareStatement(
						"SELECT TITLE, LENGTH, GENRE, TYPE, LEADACTOR, LEADACTRESS FROM DVDTABLE WHERE ACTIVITY = 'ACTIVE'");
				rs = ps.executeQuery();
				while (rs.next()) {
					DVD dvd = new DVD();
					dvd.setType((DVD) type);
					dvd.setTitle(rs.getString("TITLE"));
					dvd.setLength(rs.getInt("LENGTH"));
					dvd.setGenre(rs.getString("GENRE"));
					dvd.setLeadActor(rs.getString("LEADACTOR"));
					dvd.setLeadActress(rs.getString("LEADACTRESS"));
					DVDList.add(dvd);
				}
				return DVDList;
			}
		} catch (SQLException ex) {
			System.out.println("Error retrieving.");
			ex.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return Collections.emptyList();
	}

	public boolean delete(MediaType type) {
		try {
			if (type instanceof CD) {
				CD cd = (CD) type;
				int checkCD = 0;
				ps = conn.prepareStatement("UPDATE CDTABLE SET ACTIVITY = 'INACTIVE' WHERE TITLE = ?");
				ps.setString(1, cd.getTitle());
				checkCD = ps.executeUpdate();
				return checkCD > 0 ? true : false;
			} else if (type instanceof DVD) {
				DVD dvd = (DVD) type;
				int checkDVD = 0;
				ps = conn.prepareStatement("UPDATE DVDTABLE SET ACTIVITY = 'INACTIVE' WHERE TITLE = ?");
				ps.setString(1, dvd.getTitle());
				checkDVD = ps.executeUpdate();
				return checkDVD > 0 ? true : false;
			}
		} catch (SQLException ex) {
			System.out.println("Error updating.");
			ex.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return false;
	}
}
