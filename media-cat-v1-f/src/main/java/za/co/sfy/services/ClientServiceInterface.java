package za.co.sfy.services;

import java.util.List;

import za.co.sfy.model.MediaTypeVO;

public interface ClientServiceInterface {

    boolean createMediaType(MediaTypeVO type);

    boolean request(String protocol);

    List<MediaTypeVO> receiveAllOfMediaType(MediaTypeVO type);

    boolean update(MediaTypeVO type);

    boolean delete(MediaTypeVO type);
}
