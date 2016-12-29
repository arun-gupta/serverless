package org.sample.serverless.aws.couchbase;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.java.document.JsonDocument;

/**
 * @author arungupta
 */
public class BucketDelete implements RequestHandler<Book, String> {

    LambdaLogger logger;

    @Override
    public String handleRequest(Book request, Context context) {
        
        JsonDocument document;
        try {
            document = CouchbaseUtil.getBucket(logger).remove(Book.toJson(request));
            return document.content().toString();
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
