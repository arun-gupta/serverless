package org.sample.serverless.aws.couchbase;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @author arungupta
 */
public class BucketPost implements RequestHandler<Book, String> {

    @Override
    public String handleRequest(Book request, Context context) {
        
        try {
            return CouchbaseUtil
                    .getBucket()
                    .upsert(request.toJson())
                    .content()
                    .toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
