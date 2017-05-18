package org.sample.aws.serverless.books;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

/**
 * @author arungupta
 */
public class DynamoDBUtil {

    private static AmazonDynamoDB dynamodbClient;
    private static Table table;

    public static AmazonDynamoDB getClient() {
        if (null != dynamodbClient) {
            return dynamodbClient;
        }

        String region = System.getenv("DYNAMODB_REGION");
        if (null == region) {
            System.err.println("Region is null, using default \"" + Regions.US_WEST_1 + "\"");
            region = Regions.US_WEST_1.name();
        }
        System.out.println("DynamoDB region: " + region);

        dynamodbClient = AmazonDynamoDBClientBuilder.standard()
                .withRegion(region)
                .build();

        return dynamodbClient;
    }

    public static Table getTable() {
        if (null != table) {
            return table;
        }

        table = new DynamoDB(getClient()).getTable("Books");
        return table;
    }
}
