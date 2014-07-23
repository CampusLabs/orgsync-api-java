/*
 * Copyright 2014 OrgSync
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package com.orgsync.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * @author steffyj
 */
/* package */ final class JsonSerializer {

    /** We use the naming policy to translate the json names to Java friendly names */
    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    private JsonSerializer() {
        // no implement...
    }

    /* package */  static final String toJson(Object any) {
        return GSON.toJson(any);
    }

    /* package */  static final <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    /* package */  static final <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }
}
