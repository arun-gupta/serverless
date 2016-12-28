package org.sample.serverless.aws.couchbase;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import static com.couchbase.client.java.query.dsl.Expression.i;
import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.x;

/**
 * @author arungupta
 */
public class BucketGetOne implements RequestHandler<Book, String> {

    LambdaLogger logger;

    @Override
    public String handleRequest(Book request, Context context) {
        
        N1qlQuery query = N1qlQuery
                .simple(select("*")
                .from(i(CouchbaseUtil.getBucketName()))
                .where(x("id").eq(request.getId())));        
        N1qlQueryResult result = CouchbaseUtil.getBucket(logger).query(query);
        if (result.finalSuccess() && !result.allRows().isEmpty()) {
            return result.allRows().get(0).toString();
        }
        return null;
    }
}
