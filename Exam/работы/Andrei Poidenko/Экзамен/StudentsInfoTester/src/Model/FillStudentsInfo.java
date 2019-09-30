
package Model;

import java.util.Collections;
import java.util.Vector;

public class FillStudentsInfo {
    
    private Vector<StudentsInfo> studentsInfo;
    
    
    public FillStudentsInfo() {
        
        studentsInfo = new Vector<>();
        StudentsInfo s1 = new StudentsInfo(1, "Иванов Иван", 18, 9.7f, "30ПР31");
        StudentsInfo s2 = new StudentsInfo(2, "Петров Петр", 17, 10.1f, "31ПР32");        
        StudentsInfo s3 = new StudentsInfo(3, "Каменский Андрей", 17, 8.6f, "31ПР32");
        StudentsInfo s4 = new StudentsInfo(4, "Шарапова Мария", 18, 7.7f, "30ПР31");
        StudentsInfo s5 = new StudentsInfo(5, "Сычёва Дарья", 18, 11.0f, "30ПР31");
        
        studentsInfo.add(s1);
        studentsInfo.add(s2);
        studentsInfo.add(s3);
        studentsInfo.add(s4);
        studentsInfo.add(s5);
        
        Collections.sort(studentsInfo);
    }
   
    public void setStudentsInfo(Vector<StudentsInfo> studentsInfo) {
        this.studentsInfo = studentsInfo;
    }

    public Vector<StudentsInfo> getStudentsInfo() {
        return studentsInfo;
    }

    @Override
    public String toString() {
        int count = 1;
        String str = "";
        
        str += "List of students:\n";
        for (StudentsInfo item: studentsInfo) {
            str += count++ + ". " + item.getStudents_name() + " Age: " + item.getAge() + " Group: " 
                    + item.getStudents_group() + " Average rating: " + item.getAverage_rating() + "\n";
        }
        
        return str;
    }
}
