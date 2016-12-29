package org.sample.serverless.aws.couchbase;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import java.util.concurrent.TimeUnit;

/**
 * @author arungupta
 */
public class CouchbaseUtil {
    private static CouchbaseCluster cluster;
    private static Bucket bucket;
    
    public static CouchbaseCluster getCluster(LambdaLogger logger) {
        if (null == cluster) {
            System.out.println("env: " + System.getenv("COUCHBASE_HOST"));
            cluster = CouchbaseCluster.create(System.getenv("COUCHBASE_HOST"));
        }
        return cluster;
    }
    
    public static Bucket getBucket(LambdaLogger logger) {
        while (null == bucket) {
            System.out.println("Trying to connect to the database");
//            logger.log("");
            bucket = getCluster(logger).openBucket(getBucketName(), 2L, TimeUnit.MINUTES);

            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println("Thread sleep Exception: " + e.toString());
                throw new RuntimeException(e);
            }
        }

        return bucket;
    }
    
    public static String getBucketName() {
        return "books";
    }
}
