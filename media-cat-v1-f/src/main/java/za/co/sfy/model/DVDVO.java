package za.co.sfy.model;

public class DVDVO extends MediaTypeVO {
	private String leadActor;
	private String leadActress;

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
