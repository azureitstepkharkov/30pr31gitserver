
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DepartmentsDAO;
import model.pojo.Departments;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class DepartmentsController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("departments");//employee.jsp
        try
        {
            List<Departments> lst =  DepartmentsDAO.layDS();
            mv.addObject("departments", lst);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return mv;
    }
    
}