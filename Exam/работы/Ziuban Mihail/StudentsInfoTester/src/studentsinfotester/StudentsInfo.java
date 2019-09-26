/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsinfotester;

import java.util.Objects;

/**
 *
 * @author merkyr
 */
public class StudentsInfo {
    int ID_STUDENT;
    String STRUDENTS_NAME;
    int AGE;
    double AVERAGE_RATING;
    String STUDENTS_GROUP;

    @Override
    public String toString() {
        return "StudentsInfo{" + "STRUDENTS_NAME=" + STRUDENTS_NAME + ", AGE=" + AGE + ", AVERAGE_RATING=" + AVERAGE_RATING + ", STUDENTS_GROUP=" + STUDENTS_GROUP + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.ID_STUDENT;
        hash = 17 * hash + Objects.hashCode(this.STRUDENTS_NAME);
        hash = 17 * hash + this.AGE;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.AVERAGE_RATING) ^ (Double.doubleToLongBits(this.AVERAGE_RATING) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.STUDENTS_GROUP);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentsInfo other = (StudentsInfo) obj;
        if (this.ID_STUDENT != other.ID_STUDENT) {
            return false;
        }
        if (this.AGE != other.AGE) {
            return false;
        }
        if (Double.doubleToLongBits(this.AVERAGE_RATING) != Double.doubleToLongBits(other.AVERAGE_RATING)) {
            return false;
        }
        if (!Objects.equals(this.STRUDENTS_NAME, other.STRUDENTS_NAME)) {
            return false;
        }
        if (!Objects.equals(this.STUDENTS_GROUP, other.STUDENTS_GROUP)) {
            return false;
        }
        return true;
    }





    public StudentsInfo(int ID_STUDENT, String STRUDENTS_NAME, int AGE, float AVERAGE_RATING, String STUDENTS_GROUP) {
        this.ID_STUDENT = ID_STUDENT;
        this.STRUDENTS_NAME = STRUDENTS_NAME;
        this.AGE = AGE;
        this.AVERAGE_RATING = AVERAGE_RATING;
        this.STUDENTS_GROUP = STUDENTS_GROUP;
    }

    public StudentsInfo() {
    }

    public int getID_STUDENT() {
        return ID_STUDENT;
    }

    public void setID_STUDENT(int ID_STUDENT) {
        this.ID_STUDENT = ID_STUDENT;
    }

    public String getSTRUDENTS_NAME() {
        return STRUDENTS_NAME;
    }

    public void setSTRUDENTS_NAME(String STRUDENTS_NAME) {
        this.STRUDENTS_NAME = STRUDENTS_NAME;
    }

    public int getAGE() {
        return AGE;
    }

    public void setAGE(int AGE) {
        this.AGE = AGE;
    }

    public double getAVERAGE_RATING() {
        return AVERAGE_RATING;
    }

    public void setAVERAGE_RATING(double AVERAGE_RATING) {
        this.AVERAGE_RATING = AVERAGE_RATING;
    }

    public String getSTUDENTS_GROUP() {
        return STUDENTS_GROUP;
    }

    public void setSTUDENTS_GROUP(String STUDENTS_GROUP) {
        this.STUDENTS_GROUP = STUDENTS_GROUP;
    }
}
