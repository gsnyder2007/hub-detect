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
package com.blackducksoftware.integration.hub.detect.extraction.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.blackducksoftware.integration.hub.detect.util.DetectFileFinder;

public class ExtractionContext {
    public Map<String, Object> keyMap = new HashMap<>();

    public final static String DIRECTORY_KEY = "directory";
    public ExtractionContext(final File directory) {
        keyMap.put(DIRECTORY_KEY, directory);
    }

    public File getDirectory() {
        return getFileKey(DIRECTORY_KEY);
    }

    public File getFileKey(final String key) {
        return (File) keyMap.get(key);
    }

    public void addFileKey(final String key, final File file) {
        keyMap.put(key, file);
    }

    public boolean findFile(final DetectFileFinder fileFinder, final File directory, final String key, final String filePattern) {
        final File found = fileFinder.findFile(directory, filePattern);
        if (found != null) {
            this.addFileKey(key, found);
            return true;
        }

        return false;
    }


}
