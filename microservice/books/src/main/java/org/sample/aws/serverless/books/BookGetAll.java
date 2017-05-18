package org.sample.aws.serverless.books;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

import org.sample.aws.serverless.books.gateway.GatewayUtil;

/**
 * @author arungupta
 */
public class BookGetAll implements RequestHandler<Book, String> {

    @Override
    public String handleRequest(Book request, Context context) {
    	ScanRequest scanRequest = new ScanRequest().withTableName("Books");
    	ScanResult result = DynamoDBUtil.getClient().scan(scanRequest);
        System.out.println("-- books listing start --");
    	for (Map<String, AttributeValue> item : result.getItems()){
    	    System.out.println(item);
    	}
        System.out.println("-- books listing end --");
        return result.getItems().toString();
    }

    public static void main(String[] args) {
        String result = new BookGetAll().handleRequest(null, null);
        System.out.println("Lambda main(): " + result);
        System.out.println("API Gateway: " + GatewayUtil.proxyIntegration(200, GatewayUtil.CONTENT_TYPE_JSON_HEADER, result));
    }
}
