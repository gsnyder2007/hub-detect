/**
 * hub-detect
 *
 * Copyright (C) 2018 Black Duck Software, Inc.
 * http://www.blackducksoftware.com/
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.blackducksoftware.integration.hub.detect.workflow.hub;

import com.blackducksoftware.integration.hub.detect.configuration.DetectConfigWrapper;
import com.blackducksoftware.integration.hub.detect.configuration.DetectProperty;
import com.blackducksoftware.integration.hub.detect.workflow.project.DetectProject;
import com.blackducksoftware.integration.hub.service.model.ProjectRequestBuilder;

public class DetectProjectRequestBuilder extends ProjectRequestBuilder {

    public DetectProjectRequestBuilder(final DetectConfigWrapper detectConfigWrapper, final DetectProject detectProject) {
        setProjectName(detectProject.getProjectName());
        setVersionName(detectProject.getProjectVersion());

        setProjectLevelAdjustments(detectConfigWrapper.getBooleanProperty(DetectProperty.DETECT_PROJECT_LEVEL_ADJUSTMENTS));
        setPhase(detectConfigWrapper.getProperty(DetectProperty.DETECT_PROJECT_VERSION_PHASE));
        setDistribution(detectConfigWrapper.getProperty(DetectProperty.DETECT_PROJECT_VERSION_DISTRIBUTION));
        setProjectTier(detectConfigWrapper.getIntegerProperty(DetectProperty.DETECT_PROJECT_TIER));
        setDescription(detectConfigWrapper.getProperty(DetectProperty.DETECT_PROJECT_DESCRIPTION));
        setReleaseComments(detectConfigWrapper.getProperty(DetectProperty.DETECT_PROJECT_VERSION_NOTES));

    }
}
