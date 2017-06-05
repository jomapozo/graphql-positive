package pe.com.positive.business;

import pe.com.positive.pojo.Response;

/**
 * 
 * @author Jonatan
 *
 */
@FunctionalInterface
public interface IMusicStore {
	/**
	 * 
	 * @param id
	 * @return
	 */
	Response getArtistById(Long id);

}
