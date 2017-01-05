package org.sample.serverless.aws.couchbase.gateway;

import java.util.HashMap;
import java.util.Map;

/**
 * @author arungupta
 */
public class GatewayResponse {

    private String body;
    private int status;
    private Map<String, String> headers;

    public static Map<String, String> HEADERS_JSON = new HashMap<>();
    public static Map<String, String> HEADERS_TEXT = new HashMap<>();
    
    public GatewayResponse() {
        HEADERS_JSON.put("Content-Type", "application/json");
        HEADERS_TEXT.put("Content-Type", "text/plain");
    }

    public GatewayResponse(int status, String body) {
        this(status, body, null);
    }

    public GatewayResponse(int status, String body, Map<String, String> headers) {
        this.body = body;
        this.status = status;
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
