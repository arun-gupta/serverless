package org.sample.serverless.aws.couchbase;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.java.query.N1qlQuery;
import static com.couchbase.client.java.query.dsl.Expression.i;
import org.sample.serverless.aws.couchbase.gateway.GatewayUtil;
import static com.couchbase.client.java.query.Select.select;

/**
 * @author arungupta
 */
public class BucketGetAll implements RequestHandler<Book, String> {

    @Override
    public String handleRequest(Book request, Context context) {
        N1qlQuery query = N1qlQuery
                .simple(select("*")
                .from(i(CouchbaseUtil.getBucketName()))
                .limit(10));
//        N1qlQuery query = N1qlQuery.simple("SELECT * FROM " + CouchbaseUtil.getBucketName() + " LIMIT 10");

        String result = CouchbaseUtil.getBucket().query(query).allRows().toString();
        System.out.println("Lambda: " + result);

        return result;
    }

    public static void main(String[] args) {
        String result = new BucketGetAll().handleRequest(null, null);
        System.out.println("Lambda main(): " + result);
        System.out.println("API Gateway: " + GatewayUtil.proxyIntegration(200, GatewayUtil.CONTENT_TYPE_JSON_HEADER, result));
    }
}
