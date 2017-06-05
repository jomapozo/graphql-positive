package pe.com.positive.schema;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import pe.com.positive.business.IMusicStore;
import pe.com.positive.pojo.Response;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreMusicSchema {
	
	@Autowired
	private IMusicStore iMusicStore;

	public GraphQL getInstance() {
		
		Response response = iMusicStore.getArtistById(2L);
		
		GraphQLObjectType queryType = newObject().name("queryArtist")
				.field(newFieldDefinition()
						.type(GraphQLString)
						.name("id")
						.staticValue(response)).build();

		GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType).build();

		return GraphQL.newGraphQL(schema).build();

	}

}
