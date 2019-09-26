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
    int id_student;
    int age;
    double rating;
    String student_name;
    String group;

    @Override
    public String toString() {
        return "StudentsInfo{" + "STRUDENTS_NAME=" + student_name + ", AGE=" + age + ", AVERAGE_RATING=" + rating + ", STUDENTS_GROUP=" + group + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id_student;
        hash = 17 * hash + Objects.hashCode(this.student_name);
        hash = 17 * hash + this.age;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.rating) ^ (Double.doubleToLongBits(this.rating) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.group);
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
        if (this.id_student != other.id_student) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (Double.doubleToLongBits(this.rating) != Double.doubleToLongBits(other.rating)) {
            return false;
        }
        if (!Objects.equals(this.student_name, other.student_name)) {
            return false;
        }
        if (!Objects.equals(this.group, other.group)) {
            return false;
        }
        return true;
    }





    public StudentsInfo(int ID_STUDENT, String STRUDENTS_NAME, int AGE, float AVERAGE_RATING, String STUDENTS_GROUP) {
        this.id_student = ID_STUDENT;
        this.student_name = STRUDENTS_NAME;
        this.age = AGE;
        this.rating = AVERAGE_RATING;
        this.group = STUDENTS_GROUP;
    }

    public StudentsInfo() {
    }

    public int getID_STUDENT() {
        return id_student;
    }

    public void setID_STUDENT(int ID_STUDENT) {
        this.id_student = ID_STUDENT;
    }

    public String getSTRUDENTS_NAME() {
        return student_name;
    }

    public void setSTRUDENTS_NAME(String STRUDENTS_NAME) {
        this.student_name = STRUDENTS_NAME;
    }

    public int getAGE() {
        return age;
    }

    public void setAGE(int AGE) {
        this.age = AGE;
    }

    public double getAVERAGE_RATING() {
        return rating;
    }

    public void setAVERAGE_RATING(double AVERAGE_RATING) {
        this.rating = AVERAGE_RATING;
    }

    public String getSTUDENTS_GROUP() {
        return group;
    }

    public void setSTUDENTS_GROUP(String STUDENTS_GROUP) {
        this.group = STUDENTS_GROUP;
    }
}
