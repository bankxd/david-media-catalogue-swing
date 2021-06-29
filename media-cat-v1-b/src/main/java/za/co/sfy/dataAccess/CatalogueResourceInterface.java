package za.co.sfy.dataAccess;

import java.util.List;

import za.co.sfy.domain.MediaType;

public interface CatalogueResourceInterface {
	public boolean create(MediaType type);

	public List<MediaType> retrieveAllOfType(MediaType type);

	public boolean delete(MediaType type);
}
