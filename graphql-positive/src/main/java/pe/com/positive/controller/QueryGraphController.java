package pe.com.positive.controller;

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

import graphql.ExecutionResult;
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

	@RequestMapping(value = "/graphql", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object executeOperation(@RequestBody Map body) {
		String query = (String) body.get("query");
		Map<String, Object> variables = (Map<String, Object>) body.get("variables");
		if (variables == null) {
			variables = new LinkedHashMap<>();
		}
		ExecutionResult executionResult = storeMusicSchema.getInstance().execute(query, (Object) null, variables);
		Map<String, Object> result = new LinkedHashMap<>();
		if (executionResult.getErrors().size() > 0) {
			result.put("errors", executionResult.getErrors());
			// log.error("Errors: {}", executionResult.getErrors());
		}
		result.put("data", executionResult.getData());
		return result;
	}

}
