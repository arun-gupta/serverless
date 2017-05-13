package org.sample.aws.serverless.books;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @author arungupta
 */
public class BookPost implements RequestHandler<Book, String> {

    @Override
    public String handleRequest(Book request, Context context) {
    	DynamoDBMapper mapper = new DynamoDBMapper(DynamoDBUtil.getClient());
    	mapper.save(request);

    	return "success";
    }
}
