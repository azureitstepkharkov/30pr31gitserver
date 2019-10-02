package Controller;

import Model.StudentsInfo;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;


public class StudentsInfoComboBoxModel extends DefaultComboBoxModel<StudentsInfo>
{
    public StudentsInfoComboBoxModel(Vector<StudentsInfo> studentsInfo)
    {
        super(studentsInfo);
    }

    @Override
    public StudentsInfo getSelectedItem()
    {
        StudentsInfo selectedUser = (StudentsInfo) super.getSelectedItem();
        return selectedUser;
    }
}
