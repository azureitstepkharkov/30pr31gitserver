/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DepartmentDAO;
import model.pojo.Department;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DepartmentController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("department");
        try
        {
            List<Department> lst =  DepartmentDAO.layDS();
            mv.addObject("model", lst);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return mv;
    }
    
}