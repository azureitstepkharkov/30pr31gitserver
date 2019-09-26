package model.dao;

import java.util.List;
import model.pojo.Employee;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Artik
 */
public class EmployeeDAO {
    public static List<Employee> layDS(){
        List<Employee> lst = null;
        try
        {
           Session  session = HibernateUtil.getSessionFactory().openSession();
           String hql = "from Employee";
           Query query = session.createQuery(hql);
           lst = query.list();
        }
        catch(Exception ex)
        {
           ex.printStackTrace();
        }
        return lst;
    }
}
