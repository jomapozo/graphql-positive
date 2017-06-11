package pe.com.positive.controller;

import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQL.Builder;
import pe.com.positive.business.IMusicStore;
import pe.com.positive.pojo.Response;
import pe.com.positive.schema.StoreMusicSchema;

@RestController
public class QueryGraphController {

	@Autowired
	private IMusicStore iMusicStore;

	@Autowired
	private StoreMusicSchema storeMusicSchema;

	@RequestMapping(value = { "graphql/query" })
	public Response initRest(@RequestParam(name = "id", defaultValue = "1") Long id) {
		Response response = null;

		try {
			response = iMusicStore.getArtistById(id);
		} catch (Exception e) {
			response.setContent(e.getMessage());
		}
		
		return response;
	}

	@RequestMapping (value = {"graphql/add"})
	public Response addArtista () {
		
		Response response = null;
		
		try {
		response = iMusicStore.addArtistStore();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/graphql/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object executeOperation(@RequestBody Map body) throws URISyntaxException {
		String query = (String) body.get("query");
		Map<String, Object> variables = new LinkedHashMap<>();
		
		try {
			Gson gson = new Gson();
			variables = (Map<String,Object>)gson.fromJson((String)body.get("variables"), variables.getClass());
		} catch (ClassCastException e) {
			//TODO: ignore error
		}

		Builder graphql = GraphQL.newGraphQL(storeMusicSchema.getSchemaMusicStore());
		
		ExecutionResult executionResult = graphql.build().execute(query, (Object) null, variables);

		Map<String, Object> result = new LinkedHashMap<>();
		if (executionResult.getErrors().size() > 0) {
			result.put("errors", executionResult.getErrors());
			System.out.println("Errors_: {}" + executionResult.getErrors());
		}
		result.put("data", executionResult.getData());
		return result;
	}

}
