package za.co.sfy.model;

import java.util.List;

public class CDVO extends MediaTypeVO {
	private int tracks;
	private List<String> artists;

	public CDVO(MediaTypeVO type, String title, int length, String genre, int tracks, List<String> artists) {
		super(type, title, length, genre);
		this.tracks = tracks;
		this.artists = artists;
	}
	public CDVO() {
	}
	
	

	public CDVO(String title) {
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

	public int getTracks() {
		return tracks;
	}

	public void setTracks(int tracks) {
		this.tracks = tracks;
	}

	public List<String> getArtists() {
		return artists;
	}

	public void setArtists(List<String> artists) {
		this.artists = artists;
	}

}
