package org.sample.serverless.aws.couchbase;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author arungupta
 */
public class Book {

    private String id;
    private String name;
    private String isbn;
    private String cost;

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public JsonDocument toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("toJson: " + toString());
        
        JsonDocument document = JsonDocument.create(getId(), JsonObject.fromJson(json));
        
        return document;
    }

    public static Book fromJson(JsonDocument json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json.content().toString(), Book.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static Book fromString(String book) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(book, Book.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static JsonDocument fromStringToJson(String book) {
        return fromString(book).toJson();
    }
    
    public String toJsonString() {
        return toJson().content().toString();
    }

    @Override
    public String toString() {
        return "id: " + getId()
                + ", name: " + getName()
                + ", isbn: " + getIsbn()
                + ", cost: " + getCost();
    }

}
