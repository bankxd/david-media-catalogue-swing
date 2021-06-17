package za.co.sfy.domain;

public class DVD extends MediaType {
	private String leadActor;
	private String leadActress;

	public DVD(MediaType type, String title, int length, String genre, String leadActor, String leadActress) {
		super(type, title, length, genre);
		this.leadActor = leadActor;
		this.leadActress = leadActress;
	}

	public DVD(MediaType type, String title) {
		super(type, title);
	}

	public DVD() {
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

	@Override
	public MediaType getType() {
		return super.getType();
	}

	@Override
	public void setType(MediaType type) {
		super.setType(type);
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
