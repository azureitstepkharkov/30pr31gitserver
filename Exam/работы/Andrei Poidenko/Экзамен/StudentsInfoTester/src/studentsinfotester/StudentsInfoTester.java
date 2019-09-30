package studentsinfotester;

import Model.FillStudentsInfo;
import Model.StudentsInfo;
import View.MyForm;
import java.util.Collections;
import java.util.Vector;

public class StudentsInfoTester {

    
    
    public static void main(String[] args) {
        
        FillStudentsInfo fill = new FillStudentsInfo();
        
        System.out.println(fill.toString());
        
        
        MyForm form = new MyForm();
        form.setVisible(true);
        
    }
}
