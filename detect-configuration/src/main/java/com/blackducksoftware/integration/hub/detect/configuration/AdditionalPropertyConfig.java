/**
 * detect-configuration
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
package com.blackducksoftware.integration.hub.detect.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import com.blackducksoftware.integration.hub.configuration.HubServerConfigBuilder;

public class AdditionalPropertyConfig {
    public static final String DETECT_PROPERTY_PREFIX = "detect.";
    public static final String DOCKER_PROPERTY_PREFIX = "detect.docker.passthrough.";
    public static final String PHONE_HOME_PROPERTY_PREFIX = "detect.phone.home.passthrough.";
    public static final String DOCKER_ENVIRONMENT_PREFIX = "DETECT_DOCKER_PASSTHROUGH_";

    private final ConfigurableEnvironment configurableEnvironment;

    private final Set<String> allDetectPropertyKeys = new HashSet<>();
    private final Set<String> allBlackduckHubPropertyKeys = new HashSet<>();
    private final Set<String> additionalDockerPropertyNames = new HashSet<>();
    private final Set<String> additionalPhoneHomePropertyNames = new HashSet<>();

    public AdditionalPropertyConfig(final ConfigurableEnvironment configurableEnvironment) {
        this.configurableEnvironment = configurableEnvironment;
    }

    public void init() {
        final MutablePropertySources mutablePropertySources = configurableEnvironment.getPropertySources();
        for (final PropertySource<?> propertySource : mutablePropertySources) {
            if (propertySource instanceof EnumerablePropertySource) {
                final EnumerablePropertySource<?> enumerablePropertySource = (EnumerablePropertySource<?>) propertySource;
                for (final String propertyName : enumerablePropertySource.getPropertyNames()) {
                    if (StringUtils.isNotBlank(propertyName)) {
                        if (propertyName.startsWith(DETECT_PROPERTY_PREFIX)) {
                            allDetectPropertyKeys.add(propertyName);
                            if (propertyName.startsWith(DOCKER_PROPERTY_PREFIX)) {
                                additionalDockerPropertyNames.add(propertyName);
                            } else if (propertyName.startsWith(PHONE_HOME_PROPERTY_PREFIX)) {
                                additionalPhoneHomePropertyNames.add(propertyName);
                            }
                        }
                        if (propertyName.startsWith(HubServerConfigBuilder.HUB_SERVER_CONFIG_ENVIRONMENT_VARIABLE_PREFIX) || propertyName.startsWith(HubServerConfigBuilder.HUB_SERVER_CONFIG_PROPERTY_KEY_PREFIX)) {
                            allBlackduckHubPropertyKeys.add(propertyName);
                        }
                    }
                }
            }
        }
    }

    public String getDetectProperty(final String key) {
        return configurableEnvironment.getProperty(key);
    }

    public Map<String, String> getBlackduckHubProperties() {
        final Map<String, String> allBlackduckHubProperties = new HashMap<>();
        allBlackduckHubPropertyKeys.forEach(key -> {
            final String value = configurableEnvironment.getProperty(key);
            if (StringUtils.isNotBlank(value)) {
                allBlackduckHubProperties.put(key, value);
            }
        });

        return allBlackduckHubProperties;
    }

    public Set<String> getAllDetectPropertyKeys() {
        return allDetectPropertyKeys;
    }

    public Set<String> getAllBlackduckHubPropertyKeys() {
        return allBlackduckHubPropertyKeys;
    }

    public Set<String> getAdditionalDockerPropertyNames() {
        return additionalDockerPropertyNames;
    }

    public Set<String> getAdditionalPhoneHomePropertyNames() {
        return additionalPhoneHomePropertyNames;
    }
}
