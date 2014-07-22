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

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.orgsync.api.model.orgs.OrgSnippet;

/**
 * A single form submission.
 * 
 * @author steffyj
 * 
 */
public class FormSubmission {

    public static final Type TYPE = new TypeToken<FormSubmission>() {
    }.getType();

    private int id;
    private int submitterId;
    private Date submittedAt;
    private String status;
    private List<SubmissionScore> scores;
    private List<SubmissionFiles> files;
    private List<FormResponse> responses;
    private OrgSnippet onBehalfOf;

    public final int getId() {
        return id;
    }

    public final int getSubmitterId() {
        return submitterId;
    }

    public final Date getSubmittedAt() {
        return submittedAt;
    }

    public final String getStatus() {
        return status;
    }

    public final List<SubmissionScore> getScores() {
        return scores;
    }

    public final List<SubmissionFiles> getFiles() {
        return files;
    }

    public final List<FormResponse> getResponses() {
        return responses;
    }

    public OrgSnippet getOnBehalfOf() {
        return onBehalfOf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormSubmission)) return false;

        FormSubmission that = (FormSubmission) o;

        if (id != that.id) return false;
        if (submitterId != that.submitterId) return false;
        if (files != null ? !files.equals(that.files) : that.files != null) return false;
        if (onBehalfOf != null ? !onBehalfOf.equals(that.onBehalfOf) : that.onBehalfOf != null) return false;
        if (responses != null ? !responses.equals(that.responses) : that.responses != null) return false;
        if (scores != null ? !scores.equals(that.scores) : that.scores != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (submittedAt != null ? !submittedAt.equals(that.submittedAt) : that.submittedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + submitterId;
        result = 31 * result + (submittedAt != null ? submittedAt.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (scores != null ? scores.hashCode() : 0);
        result = 31 * result + (files != null ? files.hashCode() : 0);
        result = 31 * result + (responses != null ? responses.hashCode() : 0);
        result = 31 * result + (onBehalfOf != null ? onBehalfOf.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FormSubmission{");
        sb.append("id=").append(id);
        sb.append(", submitterId=").append(submitterId);
        sb.append(", submittedAt=").append(submittedAt);
        sb.append(", status='").append(status).append('\'');
        sb.append(", scores=").append(scores);
        sb.append(", files=").append(files);
        sb.append(", responses=").append(responses);
        sb.append(", onBehalfOf=").append(onBehalfOf);
        sb.append('}');
        return sb.toString();
    }
}
