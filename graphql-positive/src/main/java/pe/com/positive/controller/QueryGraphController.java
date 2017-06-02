package pe.com.positive.controller;

import java.util.concurrent.atomic.AtomicLong;

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
	
	private final AtomicLong count = new AtomicLong();

	@RequestMapping(value = { "graphql/init" })
	public Response initRest(@RequestParam(name = "name", defaultValue = "Wordl") String name) {
		
		try {
			iMusicStore.addMusicStore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(count.getAndIncrement(), name);
	}
}
