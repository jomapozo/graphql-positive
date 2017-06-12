
package pe.com.positive.business;


import pe.com.positive.pojo.Response;

/**
 * 
 * @Author: JomaPozo.
 * @Datecreation: 11 jun. 2017 20:04:19
 * @FileName: IMusicStore.java
 * @AuthorCompany: 
 * @version: 0.1
 * @Description: partial functionality
 */
public interface IMusicStore {
   /**
    * @date: 11 jun. 2017 20:08:44
    * @description: get artist by Id
    * @param id
    * @return
    */
    Response getArtistById(Long id);
    /**
     * @date: 11 jun. 2017 20:08:59
     * @description: get all artists
     * @return
     */
    Response getAllArtist();
    
    /**
     * @date: 11 jun. 2017 20:09:28
     * @description: insert data in db
     * @return
     */
    Response addArtistStore();
    
    /**
     * @date: 11 jun. 2017 20:09:41
     * @description: get artist by name
     * @param name
     * @return
     */
    Response getArtistByName(String name);

}
