package org.sample.aws.serverless.books.gateway;

import org.sample.aws.serverless.books.Book;
import org.sample.aws.serverless.books.DynamoDBUtil;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @author arungupta
 */
public class BucketPost implements RequestHandler<GatewayRequest, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(GatewayRequest request, Context context) {
    	DynamoDBMapper mapper = new DynamoDBMapper(DynamoDBUtil.getClient());
    	mapper.save(request.getBody());

        return new GatewayResponse(200, request.getBody(), GatewayResponse.HEADERS_JSON);
    }
}
