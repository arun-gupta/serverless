package org.sample.serverless.aws.couchbase.apigateway;

import com.couchbase.client.java.document.json.JsonObject;

/**
 * @author arungupta
 */
public class APIGatewayUtil {
    public static final String CONTENT_TYPE_JSON_HEADER = "Content-Type: application/json";
    public static final String CONTENT_TYPE_TEXT_HEADER = "Content-Type: text/plain";
    
    public static final String proxyIntegration(int statusCode, String headers, String body) {
        return JsonObject.create()
                .put("statusCode", statusCode)
                .put("headers", headers)
                .put("body", body)
                .toString();
    }
}
