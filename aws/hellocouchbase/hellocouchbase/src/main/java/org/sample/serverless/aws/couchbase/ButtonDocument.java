package org.sample.serverless.aws.couchbase;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

/**
 * @author arungupta
 */
public class ButtonDocument {

    String id;
    String requestId;
    String installationId;
    String identityId;
    String timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getInstallationId() {
        return installationId;
    }

    public void setInstallationId(String installationId) {
        this.installationId = installationId;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    JsonDocument toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        return JsonDocument.create(getId(), JsonObject.fromJson(json));
    }
    
    @Override
    public String toString() {
        return this.toJson().content().toString();
    }

}
