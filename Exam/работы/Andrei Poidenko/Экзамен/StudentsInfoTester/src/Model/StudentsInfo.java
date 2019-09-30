package Model;

import java.util.Objects;


public class StudentsInfo implements Comparable<StudentsInfo>
{
    private Integer id_student;
    private String students_name;
    private Integer age;
    private Float average_rating;
    private String students_group;

   
    public StudentsInfo() {
          
    }
     
     public StudentsInfo(Integer id_student, String students_name, Integer age, Float average_rating, String students_group) {
        this.id_student = id_student;
        this.students_name = students_name;
        this.age = age;
        this.average_rating = average_rating;
        this.students_group = students_group;
    }

   
    public void setId_student(Integer id_student) {
        this.id_student = id_student;
    }

    public void setStudents_name(String students_name) {
        this.students_name = students_name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAverage_rating(Float average_rating) {
        this.average_rating = average_rating;
    }

    public void setStudents_group(String students_group) {
        this.students_group = students_group;
    }

    public Integer getId_student() {
        return id_student;
    }

    public String getStudents_name() {
        return students_name;
    }

    public Integer getAge() {
        return age;
    }

    public Float getAverage_rating() {
        return average_rating;
    }

    public String getStudents_group() {
        return students_group;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id_student);
        hash = 71 * hash + Objects.hashCode(this.students_name);
        hash = 71 * hash + Objects.hashCode(this.age);
        hash = 71 * hash + Objects.hashCode(this.average_rating);
        hash = 71 * hash + Objects.hashCode(this.students_group);
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
        if (!Objects.equals(this.students_name, other.students_name)) {
            return false;
        }
        if (!Objects.equals(this.students_group, other.students_group)) {
            return false;
        }
        if (!Objects.equals(this.id_student, other.id_student)) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        if (!Objects.equals(this.average_rating, other.average_rating)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudentsInfo{" + "id_student=" + id_student + ", students_name=" + students_name + ", age=" + age + ", average_rating=" + average_rating + ", students_group=" + students_group + '}';
    }

    @Override
    public int compareTo(StudentsInfo t) 
    {
        return compare(t.students_name, this.students_name);
    }
    
    public int compare(String a, String b) 
    {
        return b.compareTo(a);
    }  
    
}
