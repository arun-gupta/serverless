package org.sample.serverless.aws.couchbase.apigateway;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.core.config.ConfigurationException;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.i;
import org.sample.serverless.aws.couchbase.Book;
import org.sample.serverless.aws.couchbase.CouchbaseUtil;

/**
 * @author arungupta
 */
public class BucketGetAll implements RequestHandler<Book, JsonObject> {

    @Override
    public JsonObject handleRequest(Book request, Context context) {
        try {
            N1qlQuery query = N1qlQuery
                .simple(select("*")
                .from(i(CouchbaseUtil.getBucketName()))
                .limit(10));
//        N1qlQuery query = N1qlQuery.simple("SELECT * FROM " + CouchbaseUtil.getBucketName() + " LIMIT 10");

        String result = CouchbaseUtil.getBucket(null).query(query).allRows().toString();

            return APIGatewayUtil.proxyIntegration(200, 
                    APIGatewayUtil.CONTENT_TYPE_JSON_HEADER, 
                    result);
        } catch (ConfigurationException e) {
            return APIGatewayUtil.proxyIntegration(400, APIGatewayUtil.CONTENT_TYPE_TEXT_HEADER, e.getMessage());
        }
    }
}
