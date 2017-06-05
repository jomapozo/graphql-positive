package pe.com.positive.business;

import pe.com.positive.pojo.Response;

/**
 * 
 * @author Jonatan
 *
 */
public interface IMusicStore {
	/**
	 * 
	 * @param id
	 * @return
	 */
	Response getArtistById(Long id);

	/**
	 * 
	 * @throws Exception
	 */
	void addArtistStore() throws Exception;
}
