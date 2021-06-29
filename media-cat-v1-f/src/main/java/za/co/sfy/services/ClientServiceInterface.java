package za.co.sfy.services;

import java.util.List;

import za.co.sfy.model.MediaTypeVO;

public interface ClientServiceInterface {
	public boolean request(String protocol);

	public boolean createMediaType(MediaTypeVO type);

	public List<MediaTypeVO> receiveAllOfMediaType(MediaTypeVO type);

	public boolean delete(MediaTypeVO type);
}
