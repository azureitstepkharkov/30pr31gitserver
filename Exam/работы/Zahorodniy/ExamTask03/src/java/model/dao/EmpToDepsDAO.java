
package model.dao;

import java.util.List;
import model.pojo.EmpToDeps;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class EmpToDepsDAO {
    public static List<EmpToDeps> layDS(){
        List<EmpToDeps> lst = null;
        try
        {
           Session  session = HibernateUtil.getSessionFactory().openSession();
           String hql = "from EmpToDeps";
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
