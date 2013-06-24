package com.orgsync.api.model.forms;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import com.google.gson.reflect.TypeToken;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((files == null) ? 0 : files.hashCode());
        result = prime * result + id;
        result = prime * result + ((responses == null) ? 0 : responses.hashCode());
        result = prime * result + ((scores == null) ? 0 : scores.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((submittedAt == null) ? 0 : submittedAt.hashCode());
        result = prime * result + submitterId;
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
        FormSubmission other = (FormSubmission) obj;
        if (files == null) {
            if (other.files != null)
                return false;
        } else if (!files.equals(other.files))
            return false;
        if (id != other.id)
            return false;
        if (responses == null) {
            if (other.responses != null)
                return false;
        } else if (!responses.equals(other.responses))
            return false;
        if (scores == null) {
            if (other.scores != null)
                return false;
        } else if (!scores.equals(other.scores))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (submittedAt == null) {
            if (other.submittedAt != null)
                return false;
        } else if (!submittedAt.equals(other.submittedAt))
            return false;
        if (submitterId != other.submitterId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FormSubmission [id=" + id + ", submitterId=" + submitterId + ", submittedAt=" + submittedAt
                + ", status=" + status + ", scores=" + scores + ", files=" + files + ", responses=" + responses + "]";
    }

}
