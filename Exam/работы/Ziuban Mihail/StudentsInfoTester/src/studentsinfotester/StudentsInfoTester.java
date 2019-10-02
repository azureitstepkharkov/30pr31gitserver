/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsinfotester;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author merkyr
 */
public class StudentsInfoTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Vector<StudentsInfo> list = new Vector<>();
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
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(800,400);
        JComboBox comboBox = new JComboBox();
        DefaultComboBoxModel model = new DefaultComboBoxModel( list.toArray());
        comboBox.setModel( model ); 
        jFrame.add(comboBox);
        
        JList jList = new JList();
        jList.setModel(new DefaultComboBoxModel(list.toArray()));
        jFrame.add(jList);

        
        List<String[]> values = new ArrayList<String[]>();
        for (StudentsInfo item : list) {
            values.add(new String[]{
                Integer.toString(item.getID_STUDENT()),
                item.getSTRUDENTS_NAME(),
                Integer.toString(item.getAGE()),
                Double.toString(item.getAVERAGE_RATING()),
                item.getSTUDENTS_GROUP()
            });
        }
        
        List<String> columns = new ArrayList<String>();
        
        columns.add("ID_STUDENT");
        columns.add("STRUDENTS_NAME");
        columns.add("AGE");
        columns.add("AVERAGE_RATING");
        columns.add("STUDENTS_GROUP");
        
        JTable jt = new JTable(values.toArray(new Object[][] {}), columns.toArray());
        jFrame.add(jt);
        
        
        jFrame.setVisible(true);

    }
    
}
