
package javaetalonclass;
//
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.json.XML;
import org.json.JSONObject;
//
public class JavaEtalonClass {
//
      //отвечает за соединение с базой данных
    private Session session;
    //регистрирует объект, который нужно удалить из виртуальной
    //машины, сразу после закрытия приложения
    //или окна или сессии сервлета
    private ServiceRegistry serviceRegistry;

    
     void openSession() {
        System.out.println("openSession()");
        try {
            //если "эксепшн" здесь - значит
            //внутри hibernate.cfg.xml ошибка синтаксиса
            //например, не закрыты тэги...
            Configuration configuration = new Configuration().configure();
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            //если ошибка здесь:
            //значит отсутсвуют сами классы для Эмэппинга"
            //или jpa-тэги внутри классов
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            // obtains the session
            session = sessionFactory.openSession();
            session.beginTransaction();
        } catch (Exception ex) {
            String errmsg = ex.toString();
            System.out.println("Подробности ошибки: " + errmsg);
        }

    }

    void closeSession() {
        System.out.println("closeSession()");
        session.getTransaction().commit();
        session.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
      
//
    public static void main(String[] args) 
    {
        JavaEtalonClass tester = new JavaEtalonClass();
        tester.openSession();//f7
        tester.testListQuery();   
        tester.closeSession();
    }
    //
      void testListQuery() {
        System.out.println("testListQuery()");
        String hql = "from Category";//HQL = Hibernate Query Language
        //SQL= Stuctured Query Language 
        //select c from Category c
        //select * from category на sql
        //в HQL запрос делается НЕ к таблице. а к КЛАССУ
        //я не могу написать так from сategory (нет класса в нижнем регистре)
        Query query = session.createQuery(hql);
        List<Category> listCategories = query.list();
        int num =0;
        for (Category aCategory : listCategories) {
            System.out.println(aCategory.getName());
            num++;
            //
             try {
            String xmlFileName = "C:\\MyData\\file"+num+".xml";
            File file = new File(xmlFileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Category.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(aCategory, file);
            jaxbMarshaller.marshal(aCategory, System.out);
            //создание jsno-а из XML
            Path path = file.toPath();
            String tmps = Files.lines(path).collect(Collectors.joining("\n"));
            JSONObject xmlToJson = XML.toJSONObject(tmps);
            String jsonString = xmlToJson.toString();
            System.out.println(jsonString);
        } catch (JAXBException e) {
            e.printStackTrace();
        }   catch (IOException ex) {
               ex.printStackTrace(); 
            }
            
            //
            
        }
        
        //
        
        
        
        //
    }
    //
}
