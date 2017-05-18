package org.sample.serverless.aws.helloworld;

/**
 * @author arungupta
 */
public class Response {

    String greetings;

    public Response() {
    }

    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }

    public Response(String greetings) {
        this.greetings = greetings;
    }
}
