/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.Department;
import model.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;


public class DepartmentDAO {
    public static List<Department> layDS(){
        List<Department> lst = null;
        try
        {
           Session  session = HibernateUtil.getSessionFactory().openSession();
           String hql = "from Department";
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
