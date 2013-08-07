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
package com.orgsync.api.model.events;

import java.util.Date;

/**
 * Parameters used to query an event.
 * 
 * @author steffyj
 * 
 */
public class EventQueryParams {

    private Date startDate;
    private Date endDate;
    private String keyword;

    public final Date getStartDate() {
        return startDate;
    }

    public final EventQueryParams setStartDate(final Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public final Date getEndDate() {
        return endDate;
    }

    public final EventQueryParams setEndDate(final Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public final String getKeyword() {
        return keyword;
    }

    public final EventQueryParams setKeyword(final String keyword) {
        this.keyword = keyword;
        return this;
    }

}
