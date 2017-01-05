package org.sample.serverless.aws.couchbase.gateway;

import org.sample.serverless.aws.couchbase.CouchbaseUtil;
import org.sample.serverless.aws.couchbase.Book;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.java.document.JsonDocument;

/**
 * @author arungupta
 */
public class BucketPost implements RequestHandler<Book, GatewayResponse> {

    LambdaLogger logger;

    @Override
    public GatewayResponse handleRequest(Book request, Context context) {
        
        JsonDocument document;
        try {
            document = CouchbaseUtil.getBucket(logger).upsert(Book.toJson(request));
            return new GatewayResponse(200, document.toString(), GatewayResponse.HEADERS_JSON);
        } catch (JsonProcessingException ex) {
            return new GatewayResponse(400, ex.getMessage(), GatewayResponse.HEADERS_TEXT);
        }
    }
}
