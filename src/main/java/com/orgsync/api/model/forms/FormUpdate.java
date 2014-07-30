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
package com.orgsync.api.model.forms;

/**
 * A model to hold a form/profile update.  It simply has
 * an element id and the new {@link java.lang.String}.
 *
 * @author steffyj
 */
public class FormUpdate {
    private final int elementId;
    private final String elementValue;

    public FormUpdate(int elementId, String elementValue) {
        this.elementId = elementId;
        this.elementValue = elementValue;
    }

    public int getElementId() {
        return elementId;
    }

    public String getElementValue() {
        return elementValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormUpdate)) return false;

        FormUpdate that = (FormUpdate) o;

        if (elementId != that.elementId) return false;
        if (!elementValue.equals(that.elementValue)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = elementId;
        result = 31 * result + elementValue.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FormUpdate{");
        sb.append("elementId=").append(elementId);
        sb.append(", elementValue='").append(elementValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
