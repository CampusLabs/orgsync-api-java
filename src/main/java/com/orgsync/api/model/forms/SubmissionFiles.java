package com.orgsync.api.model.forms;

public class SubmissionFiles {

    private String fileName;
    private String fileLink;
    private String formElementName;

    public final String getFileName() {
        return fileName;
    }

    public final String getFileLink() {
        return fileLink;
    }

    public final String getFormElementName() {
        return formElementName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fileLink == null) ? 0 : fileLink.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((formElementName == null) ? 0 : formElementName.hashCode());
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
        SubmissionFiles other = (SubmissionFiles) obj;
        if (fileLink == null) {
            if (other.fileLink != null)
                return false;
        } else if (!fileLink.equals(other.fileLink))
            return false;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        if (formElementName == null) {
            if (other.formElementName != null)
                return false;
        } else if (!formElementName.equals(other.formElementName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SubmissionFiles [fileName=" + fileName + ", fileLink=" + fileLink + ", formElementName="
                + formElementName + "]";
    }

}
