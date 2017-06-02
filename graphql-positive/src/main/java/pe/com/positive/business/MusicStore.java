package pe.com.positive.business;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.positive.entity.Album;
import pe.com.positive.entity.Artista;
import pe.com.positive.entity.Track;
import pe.com.positive.pojo.Response;
import pe.com.positive.repository.ArtistaRepository;

@Service
public class MusicStore implements IMusicStore {

	@Autowired
	private ArtistaRepository artistaRepo;
	
	@Override
	public Response mockMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMusicStore() throws Exception {
		
		Set<Album> albums = new LinkedHashSet<>();
		
		Set<Track> tracks =  new LinkedHashSet<>();
		Set<Track> tracks1 =  new LinkedHashSet<>();
		Set<Track> tracks2 =  new LinkedHashSet<>();
		
		Track track = new Track();
		track.setTrackNumber(1);
		track.setName("Whole Lotta Love");
		track.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinII/track/1");
		
		Track track1 = new Track();
		track1.setTrackNumber(2);
		track1.setName("What Is and What Should Never Be");
		track1.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinII/track/2");
		
		Track track2 = new Track();
		track1.setTrackNumber(3);
		track1.setName("The Lemon Song");
		track1.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinII/track/3");
		
		tracks.add(track);
		tracks.add(track1);
		tracks.add(track2);
		
		Track tracka = new Track();
		track.setTrackNumber(1);
		track.setName("Immigrant Song");
		track.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinIII/track/1");
		
		Track trackb = new Track();
		track1.setTrackNumber(2);
		track1.setName("Friends");
		track1.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinIII/track/2");
		
		Track trackc = new Track();
		track1.setTrackNumber(3);
		track1.setName("Celebration Day");
		track1.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinIII/track/3");
		
		tracks1.add(tracka);
		tracks1.add(trackb);
		tracks1.add(trackc);
		
		Track track01 = new Track();
		track.setTrackNumber(1);
		track.setName("The Song Remains the Same");
		track.setPreviewUrl("https://www.google.com.pe/led-zeppelin/houses-of-the-holy/track/1");
		
		Track track02 = new Track();
		track1.setTrackNumber(2);
		track1.setName("The Rain Song");
		track1.setPreviewUrl("https://www.google.com.pe/led-zeppelin/houses-of-the-holy/track/2");
		
		Track track03 = new Track();
		track1.setTrackNumber(3);
		track1.setName("Over the Hills and Far Away");
		track1.setPreviewUrl("https://www.google.com.pe/led-zeppelin/houses-of-the-holy/track/3");
		
		tracks2.add(track01);
		tracks2.add(track02);
		tracks2.add(track03);
		
		Album album = new Album();
		album.setName("Led Zeppelin II");
		album.setImageUrl("https://www.google.com.pe/image/album/led-zeppelinII");
		album.setTracks(tracks);
		
		Album album1 = new Album();
		album1.setName("Led Zeppelin III");
		album1.setImageUrl("https://www.google.com.pe/image/album/led-zeppelinIII");
		album1.setTracks(tracks1);
		
		Album album2 = new Album();
		album2.setName("Houses of the Holy");
		album2.setImageUrl("https://www.google.com.pe/image/album/houses-of-the-holy");
		album2.setTracks(tracks2);
		
		albums.add(album);
		albums.add(album1);
		albums.add(album2);
		
		Artista artista = new Artista();
		artista.setName("Led Zeppelin");
		artista.setImageUrl("https://www.google.com.pe/image/led-zeppelin");
		artista.setAlbums(albums);
		
		artistaRepo.save(artista);
	}

}
