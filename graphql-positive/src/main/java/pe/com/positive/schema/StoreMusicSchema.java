package pe.com.positive.schema;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import pe.com.positive.business.IMusicStore;
import pe.com.positive.entity.Artista;
import pe.com.positive.pojo.Response;
import pe.com.positive.util.Constant;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreMusicSchema {

	@Autowired
	private IMusicStore iMusicStore;

	public GraphQL getInstance() {

		Response response = iMusicStore.getArtistById(2L);

		GraphQLObjectType queryType = newObject().name("queryArtist")
				.field(newFieldDefinition().type(GraphQLString).name("id").staticValue(response)).build();

		GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType).build();

		return GraphQL.newGraphQL(schema).build();

	}

	
	public GraphQLSchema getSchemaMusicStore() throws URISyntaxException {
		SchemaParser schemaParser = new SchemaParser();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		File schemaFile = this.loadSchema();
		TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaFile);
		GraphQLSchema graphQlSchema =  schemaGenerator.makeExecutableSchema(typeRegistry, this.buildWiring()); // TODO: create wiring
		return graphQlSchema;
	}

	
	/**
	 * 
	 * @return
	 */
	private RuntimeWiring buildWiring () {
		Response response = iMusicStore.getArtistById(2L);
		
		return RuntimeWiring.newRuntimeWiring()
				.type("QueryType",typeWiring -> typeWiring
						.dataFetcher("artist", (Artista)response.getObj()))
				.type("Album", typeWiring -> typeWiring
						.dataFetcher("name", new StaticDataFetcher("hbye"))
						.dataFetcher("image_url", new StaticDataFetcher("3")))
				.build();									
	}
	
	/**
	 * get schema	
	 * @return
	 * @throws URISyntaxException 
	 */
	private File loadSchema() throws URISyntaxException {
		Path path = Paths.get(ClassLoader.getSystemResource(Constant.SCHEMA_FILE).toURI());
		//Path path = Paths.get(Constant.SCHEMA_FILE);
		File localFile = path.toFile();
		return localFile.exists() == Boolean.TRUE  ? localFile : null; 
	}

}
