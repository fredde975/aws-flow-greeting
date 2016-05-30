/*
 * Copyright 2012-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package startWorkflow;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import common.ConfigHelper;
import hellolambda.HelloLambdaWorkflowImpl;
import simpleWorkflow.OrderActivitiesImpl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WorkflowHost {

    private static final String DECISION_TASK_LIST = "HelloLambdaWorkflow";
    private static String swfAccessId = "AKIAI27LYLNPH6GKZ4GQ";
    private static String swfSecretKey = "oA7JARv0qct/ITcaa5RVGNwvZS12cXKOHUg9AQGv";
    private static String swfEndpointName = "https://swf.eu-west-1.amazonaws.com";
    private static String domain = "Samples2";


    public static void main(String[] args) throws Exception {
        ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

        //create swf-service
        AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId, swfSecretKey);
        AmazonSimpleWorkflow swfService = new AmazonSimpleWorkflowClient(awsCredentials, config);
        swfService.setEndpoint(swfEndpointName);

        final WorkflowWorker worker = new WorkflowWorker(swfService, domain, DECISION_TASK_LIST);
        worker.addWorkflowImplementationType(MyLambdaWorkflowImpl.class);
        worker.start();

        System.out.println("Workflow Host Service Started...");

        ActivityWorker aw = new ActivityWorker(swfService, domain, DECISION_TASK_LIST);
        aw.addActivitiesImplementation(new HelloActivitiesImpl());
        aw.start();


        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {
                try {
                    worker.shutdownAndAwaitTermination(1, TimeUnit.MINUTES);
                    System.out.println("Workflow Host Service Terminated...");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Please press any key to terminate service.");

        try {
            System.in.read();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);

    }

}
