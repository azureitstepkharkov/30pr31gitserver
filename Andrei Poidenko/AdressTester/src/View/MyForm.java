package View;

import Controller.AdressComboBoxModel;
import Model.Adress;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyForm extends JFrame implements ActionListener
{
    
    Vector<Adress> adresses;
    JComboBox<Adress> cbox;
    private JButton btnSelect;
    private JButton btnRemove;
    
    public MyForm()
    {
        setTitle("Working with JComboBox");
        setSize(620, 200);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        cbox = new JComboBox<>();
        adresses = new Vector<>();
        Adress a1 = new Adress("Харьков","проспект","Московский, 256Б","6100");
        Adress a2 = new Adress("Харьков","улица","Академика Павлова, 44-Б","6100");
        Adress a3 = new Adress("Харьков","улица","Вернадского, 2/літА3","6100");
        Adress a4 = new Adress("Харьков","улица","Полтавский Шлях , 56","6100");
        Adress a5 = new Adress("Харьков","проспект","Тракторостроителей , 59/56","6100");
        adresses.add(a1);
        adresses.add(a2);
        adresses.add(a3);
        adresses.add(a4);
        adresses.add(a5);
        
        AdressComboBoxModel model = new AdressComboBoxModel(adresses);
        
        cbox.setModel(model);
        
        this.add(cbox);
        
        btnSelect = new JButton("Выбрать");
        this.add(btnSelect);
        btnRemove = new JButton("Удалить");
        this.add(btnRemove);
        btnSelect.addActionListener(this);
        btnRemove.addActionListener(this);
        
        //
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
         JButton btn = (JButton)ae.getSource();
        if (btn == btnSelect)
        {
             Adress selectedBook = (Adress)cbox.getSelectedItem();
             System.out.println("select ->"+selectedBook);
             JOptionPane.showMessageDialog(MyForm.this ,
                                           selectedBook);
        }
        if (btn == btnRemove)
        {
             Adress selectedItem = (Adress)cbox.getSelectedItem();
               cbox.removeItem(selectedItem);
               System.out.println("remove ->"+selectedItem);
               
               adresses.remove(selectedItem);
               System.out.println();
                for(Adress adress : adresses)
                System.out.println(adress); 
                System.out.println();
        }
    }
    
    
}
