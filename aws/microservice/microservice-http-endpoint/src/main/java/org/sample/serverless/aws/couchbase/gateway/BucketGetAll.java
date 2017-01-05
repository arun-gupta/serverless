package org.sample.serverless.aws.couchbase.gateway;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.core.config.ConfigurationException;
import com.couchbase.client.java.query.N1qlQuery;
import static com.couchbase.client.java.query.dsl.Expression.i;
import org.sample.serverless.aws.couchbase.Book;
import org.sample.serverless.aws.couchbase.CouchbaseUtil;
import static com.couchbase.client.java.query.Select.select;

/**
 * @author arungupta
 */
public class BucketGetAll implements RequestHandler<Book, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(Book request, Context context) {
        try {
            N1qlQuery query = N1qlQuery
                    .simple(select("*")
                            .from(i(CouchbaseUtil.getBucketName()))
                            .limit(10));
//        N1qlQuery query = N1qlQuery.simple("SELECT * FROM " + CouchbaseUtil.getBucketName() + " LIMIT 10");

            String result = CouchbaseUtil.getBucket(null).query(query).allRows().toString();

            return new GatewayResponse(200, result, GatewayResponse.HEADERS_JSON);
        } catch (ConfigurationException e) {
            return new GatewayResponse(400, e.getMessage(), GatewayResponse.HEADERS_TEXT);
        }
    }
}
