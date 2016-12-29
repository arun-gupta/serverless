package org.sample.serverless.aws.couchbase;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import java.io.IOException;

/**
 * @author arungupta
 */
public class Book {

    private String id;
    private String bookname;
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

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
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

    static JsonDocument toJson(Book bean) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bean);

        return JsonDocument.create(bean.getId(), JsonObject.fromJson(json));
    }

    static Book fromJson(JsonDocument json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json.content().toString(), Book.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        try {
            return toJson(this).content().toString();
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
