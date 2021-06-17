package za.co.sfy.dataAccess;

import java.util.ArrayList;

import za.co.sfy.domain.MediaType;

public interface CatalogueResourceInterface {
    boolean create(MediaType type);
    MediaType retrieve(String Typetitle);
    ArrayList<MediaType> retrieveAllOfType(MediaType type);
    boolean update(MediaType type);
    boolean delete(MediaType type);
}
