package code;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class OracleServlet extends HttpServlet {

    private Session session;
    private  ServiceRegistry serviceRegistry;   
    //
    //
    private static void testEmployeesEx() throws ExceptionInInitializerError, HibernateException {
                    //ORA-12705: Cannot access NLS data files or invalid environment specified
            java.util.Locale locale = java.util.Locale.getDefault();
            java.util.Locale.setDefault(java.util.Locale.ENGLISH);
        SessionFactory mFctory;
        try{
            mFctory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
               java.util.Locale.setDefault(locale);
        Session session = null;
        Transaction tx = null;
        //
        session = mFctory.openSession();
        System.out.println("--- Find all Employees ---");
        Query query = session.createQuery("SELECT e FROM Employees e");
	List<Employees> employees = query.list();
	for (Employees foundEmployee : employees) 
        {
	System.out.println(String.format("Found: %s\n", foundEmployee));
        
        //for (Departments dep : foundEmployee.getDepartments()) 
        Departments dep = foundEmployee.getDepartments();
        {
	System.out.println(String.format("deps info: %s\n", dep));
        }
        //
        }
        session.close();
        //
    }
    //
    //
    public Session openSession() throws HibernateException {
        // loads configuration and mappings
        Configuration configuration = new Configuration().configure();
        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());
                    java.util.Locale locale = java.util.Locale.getDefault();
            java.util.Locale.setDefault(java.util.Locale.ENGLISH);
        try {

            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            // builds a session factory from the service registry
            
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            // obtains the session
            //Session session = sessionFactory.openSession();
            session = sessionFactory.openSession();
            session.beginTransaction();

        } catch (Exception e) {
            
            String str = e.toString();
            String msg = e.getMessage();
            System.out.println(str);
            System.out.println(msg);
        }
        finally
        {
               java.util.Locale.setDefault(locale);
        }

//        SessionFactory mFctory;
//        try {
//            mFctory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Couldn't create session factory." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//        Session session = null;
//        Transaction tx = null;
//        //
//        session = mFctory.openSession();
        
        return session;
    }
    //закрытие сессии
    public void closeSession(Session session) throws HibernateException {
        session.getTransaction().commit();
        session.close();
        //отдал сборщику мусор - пометил на удаление
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
    
    public List<Departments> getDepartments(Session session){
        String hqltext = "from Departments";
        
        Query q = session.createQuery(hqltext);
        List<Departments> departments = q.list();
        return  departments;
    }
    
    public void addCategoryHtml(final PrintWriter out, List<Departments> departments){
        out.println("<h2>Category: </h2>");
        out.println("<table>");
        for (Departments d: departments) {
            out.println("<tr><td>" + d.getDepartmentName() + "</tr></td>");
        }
        out.println("</table>");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
        //testEmployeesEx();
        //
        Session session = openSession();
        List<Departments> departments = null;
        
        departments = getDepartments(session);
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HqlTestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            addCategoryHtml(out, departments);
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
