package com.orgsync.api.model.forms;

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
