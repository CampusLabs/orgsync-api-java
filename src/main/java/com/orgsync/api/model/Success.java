/*
 * Copyright 2013 OrgSync
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
package com.orgsync.api.model;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

/**
 * A boolean response for whether or not the call succeeded.
 * 
 * @author steffyj
 * 
 */
public class Success {

    public static final Type TYPE = new TypeToken<Success>() {
    }.getType();

    private boolean success;

    public final boolean isSuccess() {
        return success;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (success ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Success other = (Success) obj;
        if (success != other.success)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Success [success=" + success + "]";
    }

}
