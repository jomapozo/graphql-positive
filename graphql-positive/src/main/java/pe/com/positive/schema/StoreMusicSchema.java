package pe.com.positive.schema;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
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
				.type(TypeRuntimeWiring.newTypeWiring("Artista").build())
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
