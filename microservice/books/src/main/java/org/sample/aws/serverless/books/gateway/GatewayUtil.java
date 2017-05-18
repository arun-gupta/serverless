package org.sample.aws.serverless.books.gateway;

//import com.couchbase.client.java.document.json.JsonArray;
//import com.couchbase.client.java.document.json.JsonObject;
import java.util.Arrays;

import com.amazonaws.util.json.Jackson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author arungupta
 */
public class GatewayUtil {

    public static final String CONTENT_TYPE_JSON_HEADER = "Content-Type: application/json";
    public static final String CONTENT_TYPE_TEXT_HEADER = "Content-Type: text/plain";

    public static final String proxyIntegration(int statusCode, String headers, String body) {
        return proxyIntegration(statusCode, Arrays.asList(headers).toArray(new String[0]), body);
    }
    
    public static final String proxyIntegration(int statusCode, String[] headers, String body) {
    	ObjectMapper mapper = Jackson.getObjectMapper();
    	ObjectNode json = mapper.createObjectNode();
    	
        json.put("statusCode", statusCode);
        
        ArrayNode array = mapper.createArrayNode();
        Arrays.stream(headers).forEach(header -> array.add(header));
        json.put("headers", array);

        json.put("body", body);

        return json.toString();
    }
}
