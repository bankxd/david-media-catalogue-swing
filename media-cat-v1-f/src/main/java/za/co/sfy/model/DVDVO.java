package za.co.sfy.model;

public class DVDVO extends MediaTypeVO {
	private String leadActor;
	private String leadActress;


	/*
	 * XXX Try to avoid using the constructor to initialize an entire object.
	 *  Constructors parameters are used to ensure objects cannot be created without certain values, for example a database connection
	 *  object cannot be created without a user-name or password.
	 *  In this case I should be able to create a DVD without a genre?
	 *  
	 *  Also avoid doing this, because if you were to add a new attribute to the DVD class, you would be forced to reactor 
	 *  all the places (in the code) where it is being instantiated.
	 *  
	 *  So rule of Thumb, only pass parameters into your constructor when an object cannot exist without a value
	 *  
	 */
	public DVDVO(MediaTypeVO type, String title, int length, String genre, String leadActor, String leadActress) {
		super(type, title, length, genre);
		this.leadActor = leadActor;
		this.leadActress = leadActress;
	}
	
	public DVDVO() {
	}
	
	

	public DVDVO(String title) {
		super(title);
	}

	@Override
	public MediaTypeVO getType() {
		return super.getType();
	}

	@Override
	public void setType(MediaTypeVO type) {
		super.setType(type);
	}

	@Override
	public String getTitle() {
		return super.getTitle();
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
	}

	@Override
	public int getLength() {
		return super.getLength();
	}

	@Override
	public void setLength(int length) {
		super.setLength(length);
	}

	@Override
	public String getGenre() {
		return super.getGenre();
	}

	@Override
	public void setGenre(String genre) {
		super.setGenre(genre);
	}

	public String getLeadActor() {
		return leadActor;
	}

	public void setLeadActor(String leadActor) {
		this.leadActor = leadActor;
	}

	public String getLeadActress() {
		return leadActress;
	}

	public void setLeadActress(String leadActress) {
		this.leadActress = leadActress;
	}

}
