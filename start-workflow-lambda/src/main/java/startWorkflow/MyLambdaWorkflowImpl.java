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

import com.amazonaws.services.simpleworkflow.flow.DecisionContext;
import com.amazonaws.services.simpleworkflow.flow.DecisionContextProvider;
import com.amazonaws.services.simpleworkflow.flow.DecisionContextProviderImpl;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.worker.LambdaFunctionClient;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of the hello lambda workflow
 */
public class MyLambdaWorkflowImpl implements MyLambdaWorkflow {
    HelloActivitiesClient operations = new HelloActivitiesClientImpl();

    @Override
    public void myWorld(String name) throws Exception {
        System.out.println("Input to workflow, name = " + name);
        DecisionContextProvider decisionProvider = new DecisionContextProviderImpl();
        DecisionContext decisionContext = decisionProvider.getDecisionContext();
        LambdaFunctionClient lambdaClient = decisionContext.getLambdaFunctionClient();

        //Promise<String> output =
        lambdaClient.scheduleLambdaFunction("helloLambda", "123454321");
        //Promise<String> output2 =
        lambdaClient.scheduleLambdaFunction("helloLambda2", "12321");
        //System.out.println("output2 = " + output2);

        Promise<String> output3 = lambdaClient.scheduleLambdaFunction("helloLambda3", "1234567654321");
        System.out.println("output3 = " + output3);

        Promise<Float> sum = operations.calculateSum(generateOrderList());
        operations.printResult(sum);
    }


    private static List<HelloOrder> generateOrderList(){
        int minNumber = 1;
        int minAmount = 1;
        int maxAmount = 99;
        int maxNumber = 99;
        float minPrice = 1;
        float maxPrice = 100;

        List<HelloOrder> orderList = new ArrayList<>();
        int randomNumber = minNumber + (int)(Math.random() * ((maxNumber - minNumber) + 1));

        for(int i = 0; i < randomNumber; i++) {
            int randomAmount = minAmount + (int)(Math.random() * ((maxAmount - minAmount) + 1));
            Float randomPrice = new Float(minPrice + (int)(Math.random() * ((maxPrice - minPrice) + 1)));
            orderList.add(new HelloOrder(randomAmount, randomPrice));
        }

        return orderList;
    }
}
