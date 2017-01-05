package org.sample.serverless.aws.couchbase.gateway;

import org.sample.serverless.aws.couchbase.CouchbaseUtil;
import org.sample.serverless.aws.couchbase.Book;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.java.document.JsonDocument;

/**
 * @author arungupta
 */
public class BucketPost implements RequestHandler<GatewayRequest, GatewayResponse> {

    LambdaLogger logger;

    @Override
    public GatewayResponse handleRequest(GatewayRequest request, Context context) {

        System.out.println("request body: " + request.getBody());
        System.out.println("json: " + Book.fromStringToJson(request.getBody()));
        try {
            JsonDocument document = CouchbaseUtil.getBucket(logger).upsert(Book.fromStringToJson(request.getBody()));
            System.out.println("document: " + document.content().toString());
            return new GatewayResponse(200, document.content().toString(), GatewayResponse.HEADERS_JSON);
        } catch (Exception ex) {
            return new GatewayResponse(400, ex.getMessage(), GatewayResponse.HEADERS_TEXT);
        }
    }
}
