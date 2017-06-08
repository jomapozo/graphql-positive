package pe.com.positive.schema;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import pe.com.positive.business.IMusicStore;
import pe.com.positive.pojo.Response;
import pe.com.positive.util.Constant;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.io.File;
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

	public GraphQLSchema getSchemaMusicStore() {
		SchemaParser schemaParser = new SchemaParser();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		File schemaFile = loadSchema();
		TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaFile);
		GraphQLSchema graphQlSchema =  schemaGenerator.makeExecutableSchema(typeRegistry, null); // TODO: create wiring
		return graphQlSchema;
	}

	/**
	 * get schema
	 * @return
	 */
	private File loadSchema() {
		Path path = Paths.get(Constant.SCHEMA_FILE);
		File localFile = path.toFile();
		return localFile.exists() == Boolean.TRUE  ? localFile : null; 
	}

}
