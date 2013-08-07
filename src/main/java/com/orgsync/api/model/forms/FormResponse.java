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
package com.orgsync.api.model.forms;

import java.util.List;
import java.util.Map;

/**
 * A response to a single form question. The data returned from the API is very dynamic, which causes problems for our
 * static types... for now it returns an Object that can be cast to the correct type based on the {@link FormQuestion}.
 * See {@link #getData()} for some more info.
 * 
 * @author steffyj
 * 
 */
public class FormResponse {

    private FormQuestion element;
    // TODO object here is not great... look into custom deserializer
    private Object data;

    public final FormQuestion getElement() {
        return element;
    }

    /**
     * Get the data for this form response. Depending on the type of the question, the returned {@link Object} could
     * either be a {@link String} (e.g. simple test field), a {@link Map} (e.g. in contact information), or a
     * {@link List} of {@link Map}s (e.g. in a checkbox response)
     * 
     * @return the form data
     */
    public final Object getData() {
        return data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((element == null) ? 0 : element.hashCode());
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
        FormResponse other = (FormResponse) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (element == null) {
            if (other.element != null)
                return false;
        } else if (!element.equals(other.element))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FormResponse [element=" + element + ", data=" + data + "]";
    }

}
