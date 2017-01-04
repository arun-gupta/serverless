package org.sample.serverless.aws.couchbase.apigateway;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.couchbase.client.core.config.ConfigurationException;
import org.sample.serverless.aws.couchbase.Book;

/**
 * @author arungupta
 */
public class BucketGetAll extends org.sample.serverless.aws.couchbase.BucketGetAll implements RequestHandler<Book, String> {

    @Override
    public String handleRequest(Book request, Context context) {
        try {
            return APIGatewayUtil.proxyIntegration(200, 
                    APIGatewayUtil.CONTENT_TYPE_JSON_HEADER, 
                    super.handleRequest(request, context));
        } catch (ConfigurationException e) {
            return APIGatewayUtil.proxyIntegration(400, APIGatewayUtil.CONTENT_TYPE_TEXT_HEADER, e.getMessage());
        }
    }
}
