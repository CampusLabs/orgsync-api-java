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

/**
 * Score for a submission.
 * 
 * @author steffyj
 * 
 */
public class SubmissionScore {

    private String notes;
    private int scorer;
    private int submissionScore;

    public final String getNotes() {
        return notes;
    }

    public final int getScorer() {
        return scorer;
    }

    public final int getSubmissionScore() {
        return submissionScore;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        result = prime * result + scorer;
        result = prime * result + submissionScore;
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
        SubmissionScore other = (SubmissionScore) obj;
        if (notes == null) {
            if (other.notes != null)
                return false;
        } else if (!notes.equals(other.notes))
            return false;
        if (scorer != other.scorer)
            return false;
        if (submissionScore != other.submissionScore)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SubmissionScore [notes=" + notes + ", scorer=" + scorer + ", submissionScore=" + submissionScore + "]";
    }

}
