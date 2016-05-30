/*
 * Copyright 2012 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not
 * use this file except in compliance with the License. A copy of the License is
 * located at
 * 
 * http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package flow.swf.greeter;


import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.ManualActivityCompletionClient;
import com.amazonaws.services.simpleworkflow.flow.ManualActivityCompletionClientFactory;
import com.amazonaws.services.simpleworkflow.flow.ManualActivityCompletionClientFactoryImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Utility to manually complete an activity task. Run this utility and enter the
 * task token and the result of the task to complete.
 */
public class HumanTaskConsole {

    /**
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //get task token and result from user 
        String taskToken = getTaskToken();
        String result = getResult();

        //complete the activity task
        AmazonSimpleWorkflow swfService = Common.createSWFClient();
        ManualActivityCompletionClientFactory manualCompletionClientFactory = new ManualActivityCompletionClientFactoryImpl(swfService);
        ManualActivityCompletionClient manualCompletionClient = manualCompletionClientFactory.getClient(taskToken);
        manualCompletionClient.complete(result);
    }

    static String getTaskToken() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the token of the task to compelete: ");
        String token = br.readLine();
        return token;
    }

    static String getResult() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter result of the task: ");
        String result = br.readLine();
        return result;
    }


    public void completeGetNameActivity(String taskToken) {

        AmazonSimpleWorkflow swfClient = Common.createSWFClient();

        ManualActivityCompletionClientFactory manualCompletionClientFactory = new ManualActivityCompletionClientFactoryImpl(swfClient);
        ManualActivityCompletionClient manualCompletionClient = manualCompletionClientFactory.getClient(taskToken);
        String result = "Done!";
        manualCompletionClient.complete(result);
    }

    public void failGetNameActivity(String taskToken, Throwable failure) {
        AmazonSimpleWorkflow swfClient = Common.createSWFClient();

        ManualActivityCompletionClientFactory manualCompletionClientFactory = new ManualActivityCompletionClientFactoryImpl(swfClient);
        ManualActivityCompletionClient manualCompletionClient = manualCompletionClientFactory.getClient(taskToken);
        manualCompletionClient.fail(failure);
    }
}
