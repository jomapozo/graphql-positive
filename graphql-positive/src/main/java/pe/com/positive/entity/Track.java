package pe.com.positive.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Track {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "preview_url")
	private String previewUrl;

	@Column(name = "track_number")
	private long trackNumber;

	@ManyToOne
	@JoinColumn(name = "id_album")
	private Album album;
	
	@ManyToMany(mappedBy = "tracks")
	private Set<Artista> artists;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public long getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(long trackNumber) {
		this.trackNumber = trackNumber;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Set<Artista> getArtists() {
		return artists;
	}

	public void setArtists(Set<Artista> artists) {
		this.artists = artists;
	}

}
