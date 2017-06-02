package pe.com.positive.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Album {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "album_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "image_url")
	private String imageUrl;

	@ManyToOne
	@JoinColumn(name = "id_artista")
	private Artista artist;
	
	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	private Set<Track> tracks;

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Artista getArtist() {
		return artist;
	}

	public void setArtist(Artista artist) {
		this.artist = artist;
	}

	public Set<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + ", artist=" + artist + "]";
	}

}
