package pe.com.positive.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Artista {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "artista_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	private Set<Album> albums;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ArtistaTrack", joinColumns = @JoinColumn(name = "id_track", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_artista", referencedColumnName = "id"))
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

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "Artista [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + ", albums=" + albums + "]";
	}

}
