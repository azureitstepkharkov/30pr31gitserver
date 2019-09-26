/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsinfotester;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author merkyr
 */
public class StudentsInfoTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<StudentsInfo> list = new ArrayList<>();
        list.add(new StudentsInfo(1,"Студент1",20,78.3f,"30PR31"));
        list.add(new StudentsInfo(2,"Студент2",22,70.2f,"30PR31"));
        list.add(new StudentsInfo(3,"Студент3",25,91.3f,"30PR31"));
        list.add(new StudentsInfo(4,"Студент4",18,67.8f,"30PR31"));
        list.add(new StudentsInfo(5,"Студент5",40,99.3f,"30PR31"));
        
        for (StudentsInfo student : list) {
            System.out.println(student.toString());
        }
        

        
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(600,200);
        JComboBox comboBox = new JComboBox();
        comboBox.setSize(50,50);
        DefaultComboBoxModel model = new DefaultComboBoxModel( list.toArray());
        comboBox.setModel( model ); 
        //comboBox.setSize(50,0);
        jFrame.add(comboBox);
        jFrame.setVisible(true);
        
    }
    
}
