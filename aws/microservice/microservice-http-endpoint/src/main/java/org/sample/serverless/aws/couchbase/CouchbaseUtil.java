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
    
    public static CouchbaseCluster getCluster() {
        if (null == cluster) {
            String host = System.getProperty("COUCHBASE_HOST");
            if (host == null) {
                host = System.getenv("COUCHBASE_HOST");
            }
            if (host == null)
                throw new RuntimeException("Hostname is null");
            System.out.println("env: " + host);
            cluster = CouchbaseCluster.create(host);
        }
        return cluster;
    }
    
    public static Bucket getBucket() {
        while (null == bucket) {
            System.out.println("Trying to connect to the database");
            bucket = getCluster().openBucket(getBucketName(), 2L, TimeUnit.MINUTES);

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
        return "default";
    }
}
