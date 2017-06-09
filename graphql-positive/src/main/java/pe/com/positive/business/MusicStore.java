package pe.com.positive.business;

import java.util.LinkedHashSet;
import java.util.Random;
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
	public Response getArtistById(Long id) {

		Artista artist=  artistaRepo.findOne(id);
		
		Response response = new Response(id, "getArtistById");
		response.setObj(artist);
		return response;

	}

	public Response addArtistStore() {
		
		Response response = new Response(new Random().nextLong(), "addArtistStore");
		this.addMockLedZepellin();
		this.addMockMichaelJackson();
		
		return response;
	}
	
	@SuppressWarnings("unused")
	private void addMockLedZepellin() {
		Artista artista = new Artista();
		artista.setName("Led Zeppelin");
		artista.setImageUrl("https://www.google.com.pe/image/led-zeppelin");

		Set<Album> albums = new LinkedHashSet<>();
		Set<Artista> artists = new LinkedHashSet<>();
		Set<Track> tracks = new LinkedHashSet<>();
		artists.add(artista);

		Album album = new Album();
		album.setName("Led Zeppelin II");
		album.setImageUrl("https://www.google.com.pe/image/album/led-zeppelinII");
		album.setArtist(artista);

		Track track = new Track();
		track.setTrackNumber(1);
		track.setName("Whole Lotta Love");
		track.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinII/track/1");
		track.setAlbum(album);
		

		Track track1 = new Track();
		track1.setTrackNumber(2);
		track1.setName("What Is and What Should Never Be");
		track1.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinII/track/2");
		track1.setAlbum(album);
		

		Track track2 = new Track();
		track2.setTrackNumber(3);
		track2.setName("The Lemon Song");
		track2.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinII/track/3");
		track2.setAlbum(album);
		

		tracks.add(track);
		tracks.add(track1);
		tracks.add(track2);

		album.setTracks(tracks);

		// ***************************************

		Album album1 = new Album();
		album1.setName("Led Zeppelin III");
		album1.setImageUrl("https://www.google.com.pe/image/album/led-zeppelinIII");
		album1.setArtist(artista);

		Set<Track> tracks1 = new LinkedHashSet<>();

		Track tracka = new Track();
		tracka.setTrackNumber(1);
		tracka.setName("Immigrant Song");
		tracka.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinIII/track/1");
		tracka.setAlbum(album1);
		

		Track trackb = new Track();
		trackb.setTrackNumber(2);
		trackb.setName("Friends");
		trackb.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinIII/track/2");
		trackb.setAlbum(album1);
		

		Track trackc = new Track();
		trackc.setTrackNumber(3);
		trackc.setName("Celebration Day");
		trackc.setPreviewUrl("https://www.google.com.pe/led-zeppelin/led-zeppelinIII/track/3");
		trackc.setAlbum(album1);
		

		tracks1.add(tracka);
		tracks1.add(trackb);
		tracks1.add(trackc);

		album1.setTracks(tracks1);

		// ***************************************
		Album album2 = new Album();
		album2.setName("Houses of the Holy");
		album2.setImageUrl("https://www.google.com.pe/image/album/houses-of-the-holy");
		album2.setArtist(artista);

		Set<Track> tracks2 = new LinkedHashSet<>();

		Track track01 = new Track();
		track01.setTrackNumber(1);
		track01.setName("The Song Remains the Same");
		track01.setPreviewUrl("https://www.google.com.pe/led-zeppelin/houses-of-the-holy/track/1");
		track01.setAlbum(album2);
		

		Track track02 = new Track();
		track02.setTrackNumber(2);
		track02.setName("The Rain Song");
		track02.setPreviewUrl("https://www.google.com.pe/led-zeppelin/houses-of-the-holy/track/2");
		track02.setAlbum(album2);
		

		Track track03 = new Track();
		track03.setTrackNumber(3);
		track03.setName("Over the Hills and Far Away");
		track03.setPreviewUrl("https://www.google.com.pe/led-zeppelin/houses-of-the-holy/track/3");
		track03.setAlbum(album2);
		

		tracks2.add(track01);
		tracks2.add(track02);
		tracks2.add(track03);

		album2.setTracks(tracks2);
		// ***************************************

		albums.add(album);
		albums.add(album1);
		albums.add(album2);

		artista.setAlbums(albums);

		artistaRepo.save(artista);
	}
	
	@SuppressWarnings("unused")
	private void addMockMichaelJackson() {
		Artista artista = new Artista();
		artista.setName("Michael Jackson");
		artista.setImageUrl("https://www.google.com.pe/image/michael-jackson");

		Set<Album> albums = new LinkedHashSet<>();
		Set<Artista> artists = new LinkedHashSet<>();
		Set<Track> tracks = new LinkedHashSet<>();
		artists.add(artista);

		Album album = new Album();
		album.setName("Thriller");
		album.setImageUrl("https://www.google.com.pe/image/album/Thriller");
		album.setArtist(artista);

		Track track = new Track();
		track.setTrackNumber(1);
		track.setName("Thriller");
		track.setPreviewUrl("https://www.google.com.pe/michael-jackson/thriller/track/1");
		track.setAlbum(album);
		

		Track track1 = new Track();
		track1.setTrackNumber(2);
		track1.setName("Beat It");
		track1.setPreviewUrl("https://www.google.com.pe/michael-jackson/thriller/track/2");
		track1.setAlbum(album);
		

		Track track2 = new Track();
		track2.setTrackNumber(3);
		track2.setName("Billy Jean");
		track2.setPreviewUrl("https://www.google.com.pe/michael-jackson/thriller/track/3");
		track2.setAlbum(album);
		

		tracks.add(track);
		tracks.add(track1);
		tracks.add(track2);

		album.setTracks(tracks);

		// ***************************************

		Album album1 = new Album();
		album1.setName("Bad");
		album1.setImageUrl("https://www.google.com.pe/image/album/bad");
		album1.setArtist(artista);

		Set<Track> tracks1 = new LinkedHashSet<>();

		Track tracka = new Track();
		tracka.setTrackNumber(1);
		tracka.setName("Smooth Criminal");
		tracka.setPreviewUrl("https://www.google.com.pe/michael-jackson/bad/track/1");
		tracka.setAlbum(album1);
		

		Track trackb = new Track();
		trackb.setTrackNumber(2);
		trackb.setName("Man in the Mirror");
		trackb.setPreviewUrl("https://www.google.com.pe/michael-jackson/bad/track/2");
		trackb.setAlbum(album1);
		

		Track trackc = new Track();
		trackc.setTrackNumber(3);
		trackc.setName("The Way you make me feel");
		trackc.setPreviewUrl("https://www.google.com.pe/michael-jackson/bad/track/3");
		trackc.setAlbum(album1);
		

		tracks1.add(tracka);
		tracks1.add(trackb);
		tracks1.add(trackc);

		album1.setTracks(tracks1);

		// ***************************************
		Album album2 = new Album();
		album2.setName("Dangerous");
		album2.setImageUrl("https://www.google.com.pe/image/album/dangerous");
		album2.setArtist(artista);

		Set<Track> tracks2 = new LinkedHashSet<>();

		Track track01 = new Track();
		track01.setTrackNumber(1);
		track01.setName("Remenber the time");
		track01.setPreviewUrl("https://www.google.com.pe/michael-jackson/dangerous/track/1");
		track01.setAlbum(album2);
		

		Track track02 = new Track();
		track02.setTrackNumber(2);
		track02.setName("Black or white");
		track02.setPreviewUrl("https://www.google.com.pe/michael-jackson/dangerous/track/2");
		track02.setAlbum(album2);
		

		Track track03 = new Track();
		track03.setTrackNumber(3);
		track03.setName("Heal the world");
		track03.setPreviewUrl("https://www.google.com.pe/michael-jackson/dangerous/track/3");
		track03.setAlbum(album2);
		

		tracks2.add(track01);
		tracks2.add(track02);
		tracks2.add(track03);

		album2.setTracks(tracks2);
		// ***************************************

		albums.add(album);
		albums.add(album1);
		albums.add(album2);

		artista.setAlbums(albums);

		artistaRepo.save(artista);
	}
}
