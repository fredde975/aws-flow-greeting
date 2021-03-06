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

import com.amazonaws.services.simpleworkflow.flow.annotations.Execute;
import com.amazonaws.services.simpleworkflow.flow.annotations.Workflow;
import com.amazonaws.services.simpleworkflow.flow.annotations.WorkflowRegistrationOptions;

/**
 * Contract of the hello lambda workflow
 */
@Workflow
// replace with the ARN of an IAM role that allows SWF to invoke your lambda functions to use it by default
// when calling lambda functions in this workflow
@WorkflowRegistrationOptions(defaultExecutionStartToCloseTimeoutSeconds = 120, defaultLambdaRole = "arn:aws:iam::487526570401:role/swf-lambda")
public interface MyLambdaWorkflow {

    @Execute(version = "3.0")
    void myWorld(String name) throws Exception;

}
