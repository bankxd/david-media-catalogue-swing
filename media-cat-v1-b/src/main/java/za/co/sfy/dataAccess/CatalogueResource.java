package za.co.sfy.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	// *************************************************************************

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

	// *************************************************************************

	public boolean create(MediaType type) {
		if (type instanceof CD) {
			StringBuilder sb = new StringBuilder();
			CD cd = (CD) type;
			int checkCD = 0;
			try {
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
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException ex) {
					}
				}
			}
			return checkCD > 0 ? true : false;
		} else if (type instanceof DVD) {
			DVD dvd = (DVD) type;
			int checkDVD = 0;
			try {
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
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException ex) {
					}
				}
			}
			return checkDVD > 0 ? true : false;
		}
		return false;
	}

	public MediaType retrieve(String Typetitle) {
		return null;
	}

	// *************************************************************************

	public ArrayList<MediaType> retrieveAllOfType(MediaType type) {
		if (type instanceof CD) {
			ArrayList<MediaType> CDList = new ArrayList<>();
			try {
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
					CDList.add(new CD(((CD) type), rs.getString("TITLE"), rs.getInt("LENGTH"), rs.getString("GENRE"),
							rs.getInt("TRACKS"), artistList));
				}
			} catch (SQLException ex) {
				System.out.println("Error retrieving all CDs.");
				ex.printStackTrace();
			}
			return CDList;
		} else if (type instanceof DVD) {
			ArrayList<MediaType> DVDList = new ArrayList<>();
			try {
				ps = conn.prepareStatement(
						"SELECT TITLE, LENGTH, GENRE, TYPE, LEADACTOR, LEADACTRESS FROM DVDTABLE WHERE ACTIVITY = 'ACTIVE'");
				rs = ps.executeQuery();
				while (rs.next()) {
					DVDList.add(new DVD(((DVD) type), rs.getString("TITLE"), rs.getInt("LENGTH"), rs.getString("GENRE"),
							rs.getString("LEADACTOR"), rs.getString("LEADACTRESS")));
				}
			} catch (SQLException ex) {
				System.out.println("Error retrieving all DVDs.");
				ex.printStackTrace();
			}
			return DVDList;
		}
		return null;
	}

//	public ArrayList<Product> getProducts() {
//		ArrayList<Product> listOfProducts = new ArrayList();
//
//		try {
//			ps = myCon9.prepareStatement(
//					"SELECT PRODUCTID, PRODUCTNAME, PHOTO, PRODUCTDESCRIPTION, PRODUCTWARNINGS, PRICE, DISCOUNT, CATEGORYID  FROM PRODUCTSTABLE WHERE ACTIVITY = 'ACTIVE'");
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				listOfProducts.add(new Product(rs.getInt("PRODUCTID"), rs.getString("PRODUCTNAME"),
//						rs.getString("PHOTO"), rs.getString("PRODUCTDESCRIPTION"), rs.getString("PRODUCTWARNINGS"),
//						rs.getDouble("PRICE"), rs.getInt("DISCOUNT"), rs.getInt("CATEGORYID")));
//			}
//
//			for (int i = 0; i < listOfProducts.size(); i++) {
//				ps1 = myCon9.prepareStatement(
//						"SELECT PRODUCTINGREDIENTTABLE.INGREDIENTID, PRODUCTINGREDIENTTABLE.QUANTITY, INGREDIENTTABLE.INGREDIENTNAME, INGREDIENTTABLE.UNIT FROM PRODUCTINGREDIENTTABLE INNER JOIN INGREDIENTTABLE ON PRODUCTINGREDIENTTABLE.INGREDIENTID = INGREDIENTTABLE.INGREDIENTID WHERE PRODUCTINGREDIENTTABLE.PRODUCTID = ?");
//				ps.setInt(1, listOfProducts.get(i).getProductID());
//				rs = ps1.executeQuery();
//
//				ArrayList<Ingredient> listOfIngredients = new ArrayList();
//				while (rs.next()) {
//					listOfIngredients.add(new Ingredient(rs.getInt("INGREDIENTID"), rs.getString("INGREDIENTNAME"),
//							rs.getInt("QUANTITY"), rs.getString("UNIT")));
////                    listOfIngredients.add(new Ingredient(rs.getInt("INGREDIENTID"), rs.getString("INGREDIENTNAME"), rs.getInt("QUANTITY")));
//				}
//				listOfProducts.get(i).setRecipeArr(listOfIngredients);
//			}
//		} catch (SQLException ex) {
//			Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//		} finally {
//			closeStreams();
//		}
//
//		return listOfProducts;
//	}

	public boolean update(MediaType type) {
		return false;

	}

	// *************************************************************************

	public boolean delete(MediaType type) {
		if (type instanceof CD) {
			CD cd = (CD) type;
			int checkCD = 0;
			try {
				ps = conn.prepareStatement("UPDATE CDTABLE SET ACTIVITY = 'INACTIVE' WHERE TITLE = ?");
				ps.setString(1, cd.getTitle());
				checkCD = ps.executeUpdate();
			} catch (SQLException ex) {
				System.out.println("Error updating CD.");
				ex.printStackTrace();
			}
			return checkCD > 0 ? true : false;
		} else if (type instanceof DVD) {
			DVD dvd = (DVD) type;
			int checkDVD = 0;
			try {
				ps = conn.prepareStatement("UPDATE DVDTABLE SET ACTIVITY = 'INACTIVE' WHERE TITLE = ?");
				ps.setString(1, dvd.getTitle());
				checkDVD = ps.executeUpdate();
			} catch (SQLException ex) {
				System.out.println("Error updating DVD.");
				ex.printStackTrace();
			}
			return checkDVD > 0 ? true : false;
		}
		return false;
	}

	// *************************************************************************

//	public static void main(String[] args) {
//		List<MediaType> retrieveAllOfType = new CatalogueResource().retrieveAllOfType(new CD());
//		System.out.println(retrieveAllOfType);
//	}
}
