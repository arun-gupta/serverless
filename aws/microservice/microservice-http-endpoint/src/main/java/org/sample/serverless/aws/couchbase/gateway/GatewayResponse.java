package org.sample.serverless.aws.couchbase.gateway;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author arungupta
 */
public class GatewayResponse {

    private String body;
    private int status;
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
