package org.sample.serverless.aws.couchbase;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import static com.couchbase.client.java.query.dsl.Expression.i;
import static com.couchbase.client.java.query.Select.select;

/**
 * @author arungupta
 */
public class BucketGetAll implements RequestHandler<Book, String> {

    LambdaLogger logger;

    @Override
    public String handleRequest(Book request, Context context) {
        N1qlQuery query = N1qlQuery
                .simple(select("*")
                .from(i(CouchbaseUtil.getBucketName()))
                .limit(10));
        
        N1qlQueryResult result = CouchbaseUtil.getBucket(logger).query(query);
        return result.allRows().toString();
    }
}
