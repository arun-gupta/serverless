package org.sample.serverless.aws.couchbase;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.java.document.JsonDocument;

/**
 * @author arungupta
 */
public class BucketDelete implements RequestHandler<Book, String> {

    @Override
    public String handleRequest(Book request, Context context) {
        
        JsonDocument document;
        try {
            return CouchbaseUtil
                    .getBucket()
                    .remove(request.toJson())
                    .content()
                    .toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
