package com.orgsync.api.model.timesheets;

/**
 * Account information on a timesheet.
 * 
 * @author steffyj
 * 
 */
public class TimesheetAccount {

    private int id;
    private String firstName;
    private String lastName;

    public final int getId() {
        return id;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + id;
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
        TimesheetAccount other = (TimesheetAccount) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id != other.id)
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TimesheetAccount [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

}
