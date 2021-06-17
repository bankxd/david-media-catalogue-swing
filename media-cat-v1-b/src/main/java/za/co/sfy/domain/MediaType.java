package za.co.sfy.domain;

public abstract class MediaType {
	private MediaType type;
	private String title;
	private int length;
	private String genre;

	public MediaType(MediaType type, String title, int length, String genre) {
		this.type = type;
		this.title = title;
		this.length = length;
		this.genre = genre;
	}
	
	public MediaType(MediaType type, String title) {
		this.type = type;
		this.title = title;
	}

	public MediaType() {
	}

	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
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
