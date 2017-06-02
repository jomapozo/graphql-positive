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
import javax.persistence.SequenceGenerator;

@Entity
public class Track {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "track_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
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
	
	@ManyToMany(mappedBy = "Track")
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

}
