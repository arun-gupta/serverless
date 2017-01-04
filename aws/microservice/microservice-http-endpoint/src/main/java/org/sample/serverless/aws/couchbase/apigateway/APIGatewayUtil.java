package org.sample.serverless.aws.couchbase.apigateway;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import java.util.Arrays;

/**
 * @author arungupta
 */
public class APIGatewayUtil {

    public static final String CONTENT_TYPE_JSON_HEADER = "Content-Type: application/json";
    public static final String CONTENT_TYPE_TEXT_HEADER = "Content-Type: text/plain";

    public static final JsonObject proxyIntegration(int statusCode, String headers, String body) {
        return proxyIntegration(statusCode, Arrays.asList(headers).toArray(new String[0]), body);
    }
    
    public static final JsonObject proxyIntegration(int statusCode, String[] headers, String body) {
        JsonObject json = JsonObject.create();

        json.put("statusCode", statusCode);
        JsonArray array = JsonArray.create();
        Arrays.stream(headers).forEach(header -> array.add(header));
        json.put("headers", array);

        json.put("body", body);

        return json;
    }
}
