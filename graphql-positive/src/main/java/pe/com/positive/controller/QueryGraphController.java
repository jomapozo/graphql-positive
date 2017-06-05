package pe.com.positive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.positive.business.IMusicStore;
import pe.com.positive.pojo.Response;

@RestController
public class QueryGraphController {

	@Autowired
	private IMusicStore iMusicStore;

	// private final AtomicLong count = new AtomicLong();

	@RequestMapping(value = { "graphql/query" })
	public Response initRest(@RequestParam(name = "id", defaultValue = "1") Long id) {

		Response response = null;

		try {
			response = iMusicStore.getArtistById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	@RequestMapping(value = { "graphql/add" })
	public Response addArtist() {

		Response response = null;

		try {
			iMusicStore.addArtistStore();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
}
