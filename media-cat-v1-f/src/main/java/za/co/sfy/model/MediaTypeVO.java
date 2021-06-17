package za.co.sfy.model;

public abstract class MediaTypeVO {
	private MediaTypeVO type;
	private String title;
	private int length;
	private String genre;

	public MediaTypeVO(MediaTypeVO type, String title, int length, String genre) {
		this.type = type;
		this.title = title;
		this.length = length;
		this.genre = genre;
	}
	
	public MediaTypeVO() {
	}
	
	

	public MediaTypeVO(String title) {
		this.title = title;
	}

	public MediaTypeVO getType() {
		return type;
	}

	public void setType(MediaTypeVO type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
