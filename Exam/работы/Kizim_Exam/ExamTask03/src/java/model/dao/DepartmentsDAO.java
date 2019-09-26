package model.dao;

import java.util.List;
import model.pojo.Departments;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Artik
 */
public class DepartmentsDAO {
    public static List<Departments> layDS(){
        List<Departments> lst = null;
        try
        {
           Session  session = HibernateUtil.getSessionFactory().openSession();
           String hql = "from Departments";
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
