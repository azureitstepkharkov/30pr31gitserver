/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.EmployeeDAO;
import model.pojo.Employee;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Artik
 */
public class EmployeeController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("employee");//employee.jsp
        try
        {
            List<Employee> lst =  EmployeeDAO.layDS();
            mv.addObject("model", lst);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return mv;
    }
    
}
