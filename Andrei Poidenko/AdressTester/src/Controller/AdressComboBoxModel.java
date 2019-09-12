package Controller;

import Model.Adress;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

public class AdressComboBoxModel extends DefaultComboBoxModel<Adress>
{
    //задет выбор коллекции - хранилища
    public AdressComboBoxModel(Vector<Adress> adresses)
    {
        super(adresses);
    }
    //единственный обязательный, который должен реализовать
    //уточнить
    @Override
    public Adress getSelectedItem()
    {
        Adress selectedUser = (Adress) super.getSelectedItem();
        return selectedUser;
    }
}
