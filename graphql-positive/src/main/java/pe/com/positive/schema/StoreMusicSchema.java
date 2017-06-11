package pe.com.positive.schema;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import pe.com.positive.business.IMusicStore;
import pe.com.positive.entity.Artista;
import pe.com.positive.pojo.Response;
import pe.com.positive.util.Constant;

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

	DataFetcher<Artista> artistDataFetcher = environment -> {
        // environment.getSource() is the value of the surrounding
        // object. In this case described by objectType
        Response value = iMusicStore.getArtistByName(environment.getArgument("name")); // Perhaps getting from a DB or whatever
        return (Artista)value.getObj();
	};
	
	/**
	 * 
	 * @return
	 */
	private RuntimeWiring buildWiring () {
		
		return RuntimeWiring.newRuntimeWiring()
				.type("QueryType",typeWiring -> typeWiring
						.dataFetcher("artist", artistDataFetcher ))
				.type("Album", typeWiring -> typeWiring
						.dataFetcher("name", new StaticDataFetcher("hbye"))
						.dataFetcher("image_url", new StaticDataFetcher("3")))
				.type(TypeRuntimeWiring.newTypeWiring("Character").build())
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
