package org.sample.serverless.aws.couchbase.gateway;

import org.sample.serverless.aws.couchbase.CouchbaseUtil;
import org.sample.serverless.aws.couchbase.Book;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.java.document.JsonDocument;

/**
 * @author arungupta
 */
public class BucketPost implements RequestHandler<GatewayRequest, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(GatewayRequest request, Context context) {

        try {
            JsonDocument document = CouchbaseUtil.getBucket().upsert(Book.fromStringToJson(request.getBody()));
            return new GatewayResponse(200, document.content().toString(), GatewayResponse.HEADERS_JSON);
        } catch (Exception ex) {
            return new GatewayResponse(400, ex.getMessage(), GatewayResponse.HEADERS_TEXT);
        }
    }
}
