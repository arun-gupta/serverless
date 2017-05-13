package org.sample.aws.serverless.books.gateway;

import org.sample.aws.serverless.books.DynamoDBUtil;

import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @author arungupta
 */
public class BookGetAll implements RequestHandler<GatewayRequest, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(GatewayRequest request, Context context) {
    	ScanRequest scanRequest = new ScanRequest().withTableName("Reply");
    	String result = DynamoDBUtil.getClient().scan(scanRequest).getItems().toString();

        return new GatewayResponse(200, result, GatewayResponse.HEADERS_JSON);
    }
}
