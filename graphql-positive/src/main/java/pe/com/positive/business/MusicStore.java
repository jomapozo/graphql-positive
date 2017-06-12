
package pe.com.positive.business;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pe.com.positive.entity.Artista;
import pe.com.positive.pojo.Response;
import pe.com.positive.repository.ArtistaRepository;

@Service
public class MusicStore extends AbstractMusicStore {

    @Autowired
    private ArtistaRepository artistaRepo;

    @Override
    public Response getArtistById(Long id){
	  Artista artist = artistaRepo.findOne(id);
	  Response response = new Response(id , "getArtistById");
	  response.setObj(artist);
	  return response;

    }

    @Override
    public Response getAllArtist(){
	  Iterable<Artista> artists = artistaRepo.findAll();
	  Response response = new Response(0L , "getArtists");
	  response.setObj(artists);
	  return response;
    }

    @Override
    @Cacheable("musicStore")
    public Response getArtistByName(String name){
	  Artista artist = artistaRepo.getArtistByName(name);
	  Response response = new Response(new Random().nextLong() , "getArtistByName");
	  response.setObj(artist);
	  return response;
    }

    public Response addArtistStore(){
	  Response response = new Response(new Random().nextLong() , "addArtistStore");
	  this.addMockLedZepellin();
	  this.addMockMichaelJackson();
	  this.addMockLennyKravitz();

	  return response;
    }

}
