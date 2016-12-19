package org.sample.serverless.aws.couchbase;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author arungupta
 */
public class HelloCouchbase implements RequestHandler<String, String> {

    CouchbaseCluster cluster;
    Bucket bucket;

    @Override
    public String handleRequest(String request, Context context) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String timestamp = dateFormat.format(Calendar.getInstance().getTime());

        context.getLogger().log("Request received: %s" + timestamp);
        ButtonDocument buttonDocument = new ButtonDocument();
        buttonDocument.setId(context.getAwsRequestId());
        buttonDocument.setRequestId(context.getAwsRequestId());
        buttonDocument.setTimestamp(String.valueOf(timestamp));
//        buttonDocument.setInstallationId(context.getClientContext().getClient().getInstallationId());
//        buttonDocument.setIdentityId(context.getIdentity().getIdentityId());
        
        getBucket().upsert(buttonDocument.toJson());

        return buttonDocument.toString();
    }

    public CouchbaseCluster getCluster() {
        if (null == cluster) {
            System.out.println("env: " + System.getenv("COUCHBASE_HOST"));
            cluster = CouchbaseCluster.create(System.getenv("COUCHBASE_HOST"));
        }
        return cluster;
    }

    public Bucket getBucket() {
        while (null == bucket) {
            System.out.println("Trying to connect to the database");
//            String bucketName = System.getenv("BUCKET");
            String bucketName = "serverless";
            bucket = getCluster().openBucket(bucketName, 2L, TimeUnit.MINUTES);

            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println("Thread sleep Exception: " + e.toString());
                throw new RuntimeException(e);
            }
        }

        return bucket;
    }
}
