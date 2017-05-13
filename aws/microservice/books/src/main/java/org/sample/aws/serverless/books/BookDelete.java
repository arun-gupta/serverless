package org.sample.aws.serverless.books;

import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @author arungupta
 */
public class BookDelete implements RequestHandler<Book, String> {

    @Override
    public String handleRequest(Book request, Context context) {
        
    	DeleteItemOutcome outcome = DynamoDBUtil.getTable().deleteItem(new PrimaryKey("id", request.getId()));
    	
    	String result = "Item deleted: " + outcome.getDeleteItemResult().getSdkHttpMetadata().getHttpStatusCode();
    	
    	return result;
    }
}
