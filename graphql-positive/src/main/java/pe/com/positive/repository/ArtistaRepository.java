package pe.com.positive.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.positive.entity.Artista;

@Repository
public interface ArtistaRepository extends CrudRepository<Artista, Serializable> {

	Artista getArtistByName(String name);
}
