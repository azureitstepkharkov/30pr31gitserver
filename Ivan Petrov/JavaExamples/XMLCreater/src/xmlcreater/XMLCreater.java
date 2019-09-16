package xmlcreater;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class XMLCreater {

     public static void main(String[] args) 
    {
        User customer1 = new User();
        customer1.setId(100);
        customer1.setName("Вася");

                User customer2 = new User();
        customer2.setId(101);
        customer2.setName("Петя");
        
        Users users = new Users();
        users.getUsers().add(customer1);
        users.getUsers().add(customer2);
        try {

            File file = new File("C:\\MyData\\file3.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(users, file);
            jaxbMarshaller.marshal(users, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
    }
    
}
