package Controller;

import Model.MYUSER_INFO;
import Model.MY_USER;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class LoginServlet extends HttpServlet {

    private ServiceRegistry serviceRegistry;
    
    public Session openSession() throws HibernateException {
        Configuration configuration = new Configuration().configure();
        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());
        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public void closeSession(Session session) throws HibernateException {
        session.getTransaction().commit();
        session.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
    
    public List<MY_USER> getMY_USER(Session session){
        String hqltext = "from MY_USER";
        
        Query q = session.createQuery(hqltext);
        List<MY_USER> my_users = q.list();
        return  my_users;
    }
    
    public void showMY_USERHtml(final PrintWriter out, List<MY_USER> my_users){
        out.println("<h2>MY_USER: </h2>");
        out.println("<table>");
        for (MY_USER m: my_users) {
            out.println("<tr><td>" + m.getUSER_NAME()+ "</tr></td>");
        }
        out.println("</table>");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            Session session = openSession();
            List<MY_USER> my_users = getMY_USER(session);

            if (!userName.isEmpty() && !password.isEmpty()) {
                if (my_users.stream().anyMatch(x -> x.getUSER_NAME().equals(userName))) {
                    MY_USER my_user = my_users.stream().filter(x -> x.getUSER_NAME().equals(userName)).findFirst().get();
                    if (my_user.getUSER_PASS().equals(password)) {
                        
                        HttpSession sessionInternet = request.getSession(true);
                        sessionInternet.setAttribute("name", userName);
                        sessionInternet.setAttribute("id", Long.toString(my_user.getId()));
                        closeSession(session);
                        response.sendRedirect("welcome.jsp");
                    } else {
                        out.println("Invalid password");
                    }
                } else {
                    out.println("Invalid user name");
                }
            } else {
                out.println("Empty User Name or password");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
