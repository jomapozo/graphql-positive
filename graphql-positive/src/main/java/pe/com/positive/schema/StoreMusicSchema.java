package pe.com.positive.schema;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import pe.com.positive.business.IMusicStore;
import pe.com.positive.entity.Artista;
import pe.com.positive.pojo.Response;
import pe.com.positive.util.Constant;

/**
 * @Author: JomaPozo.
 * @Datecreation: 11 jun. 2017 20:01:49
 * @FileName: StoreMusicSchema.java
 * @AuthorCompany: 
 * @version: 0.1
 * @Description: Schema wiring with extern schema and Db
 */
@Component
public class StoreMusicSchema {

	@Autowired
	private IMusicStore iMusicStore;
	
	/**
	 * @date: 11 jun. 2017 20:15:14
	 * @description: generate schema grapqhl
	 * @return
	 * @throws URISyntaxException
	 */
	public GraphQLSchema getSchemaMusicStore() throws URISyntaxException {
		SchemaParser schemaParser = new SchemaParser();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		File schemaFile = this.loadSchema();
		TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaFile);
		GraphQLSchema graphQlSchema =  schemaGenerator.makeExecutableSchema(typeRegistry, this.buildWiring());
		return graphQlSchema;
	}

	DataFetcher<Artista> artistDataFetcher = environment -> {
	  Response value = iMusicStore.getArtistByName(environment.getArgument("name")); // Perhaps getting from a DB or whatever
        return (Artista)value.getObj();
	};
	
	/**
	 * @date: 11 jun. 2017 20:14:54
	 * @description: wiring with db
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
		File localFile = path.toFile();
		return localFile.exists() == Boolean.TRUE  ? localFile : null; 
	}

}
