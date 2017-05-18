package org.sample.aws.serverless.books;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @author arungupta
 */
public class BookGetOne implements RequestHandler<Book, String> {

    LambdaLogger logger;

    @Override
    public String handleRequest(Book request, Context context) {
    	
    	Item outcome = DynamoDBUtil.getTable().getItem(new PrimaryKey("id", request.getId()));
    	if (null != outcome) {
    		return outcome.toJSONPretty();
    	}
		return null;
    }
}
