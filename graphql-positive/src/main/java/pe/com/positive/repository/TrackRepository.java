package pe.com.positive.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.positive.entity.Track;

@Repository
public interface TrackRepository extends CrudRepository<Track, Serializable> {

}
