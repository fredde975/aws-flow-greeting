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
import com.amazonaws.services.simpleworkflow.flow.worker.LambdaFunctionClient;

/**
 * Implementation of the hello lambda workflow
 */
public class MyLambdaWorkflowImpl implements MyLambdaWorkflow {

    @Override
    public void myWorld(String name) throws Exception {
        DecisionContextProvider decisionProvider = new DecisionContextProviderImpl();
        DecisionContext decisionContext = decisionProvider.getDecisionContext();
        LambdaFunctionClient lambdaClient = decisionContext.getLambdaFunctionClient();

        //Promise<String> output =
        lambdaClient.scheduleLambdaFunction("helloLambda", name + "123454321");
        //Promise<String> output2 =
        lambdaClient.scheduleLambdaFunction("helloLambda2", "12321");
        //System.out.println("output2 = " + output2);


    }

}