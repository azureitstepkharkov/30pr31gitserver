
package View;

import Controller.StudentsInfoComboBoxModel;
import Model.FillStudentsInfo;
import Model.StudentsInfo;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;  


public class MyForm extends JFrame implements ActionListener
{
    JComboBox<StudentsInfo> cbox;
    private JButton btnSelect;
    private JButton btnRemove;
    FillStudentsInfo fill = null;
    
    public MyForm()
    {
        fill = new FillStudentsInfo();
        
        
        // JComboBox 
        
        setTitle("Working with JComboBox");
        setSize(760, 320);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        cbox = new JComboBox<>();
        
        StudentsInfoComboBoxModel model = new StudentsInfoComboBoxModel(fill.getStudentsInfo());
        
        cbox.setModel(model);
        
        this.add(cbox);
        
        btnSelect = new JButton("Выбрать");
        this.add(btnSelect);
        btnRemove = new JButton("Удалить");
        this.add(btnRemove);
        //добавим кнопки выбора и удаления элементов в список
        btnSelect.addActionListener(this);
        btnRemove.addActionListener(this);
        
        
        // JList

        JList<StudentsInfo> list = new JList<>(fill.getStudentsInfo());
        list.setBounds(100, 100, 75, 75);
        this.add(list);
        this.setVisible(true);
        
        
        
        // JTable
             
        List<String[]> values = new ArrayList<String[]>();
        
        for (StudentsInfo item : fill.getStudentsInfo()) {
            values.add(new String[]{
                item.getId_student().toString(),
                item.getStudents_name(),
                item.getAge().toString(),
                item.getAverage_rating().toString(),
                item.getStudents_group().toString()
            });
        }
        
        List<String> columns = new ArrayList<String>();
        
        columns.add("ID");
        columns.add("NAME");
        columns.add("AGE");
        columns.add("RAITING");
        columns.add("GROUP");
        
        JTable jt = new JTable(values.toArray(new Object[][] {}), columns.toArray());
        jt.setBounds(30, 40, 200, 300);
        this.add(jt);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton btn = (JButton)ae.getSource();
        
        if (btn == btnSelect)
        {
             StudentsInfo selectedBook = (StudentsInfo)cbox.getSelectedItem();
             System.out.println("select ->"+selectedBook);
             JOptionPane.showMessageDialog(MyForm.this ,
                                           selectedBook);
        }
        if (btn == btnRemove)
        {
             StudentsInfo selectedItem = (StudentsInfo)cbox.getSelectedItem();
               cbox.removeItem(selectedItem);
               System.out.println("remove ->"+selectedItem);
              
               
               fill.getStudentsInfo().remove(selectedItem);
               System.out.println();
                for(StudentsInfo student : fill.getStudentsInfo())
                System.out.println(student); 
                System.out.println();
        }
    }


}
