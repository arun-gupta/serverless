package org.sample.aws.serverless.books.gateway;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author arungupta
 */
public class GatewayResponse {

    private String body;
    private int statusCode;
    private Map<String, String> headers;

    public static final Map<String, String> HEADERS_JSON = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("Content-Type", "application/json");
        }
    });

    public static final Map<String, String> HEADERS_TEXT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("Content-Type", "text/plain");
        }
    });
    
    public GatewayResponse() {
    }

    public GatewayResponse(int statusCode, String body) {
        this(statusCode, body, null);
    }

    public GatewayResponse(int statusCode, String body, Map<String, String> headers) {
        this.body = body;
        this.statusCode = statusCode;
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
    public Map<String, String> getHeaders() {
        return headers;
    }
}
