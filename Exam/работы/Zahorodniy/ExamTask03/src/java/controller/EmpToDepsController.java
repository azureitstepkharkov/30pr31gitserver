
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.EmpToDepsDAO;
import model.pojo.EmpToDeps;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EmpToDepsController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("emptodeps");
        try
        {
            List<EmpToDeps> lst =  EmpToDepsDAO.layDS();
            mv.addObject("model", lst);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return mv;
    }
    
}