package tests;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "TestWebService")
public class TestWebService {
    @WebMethod(operationName = "correctName")
    public String correctName(@WebParam(name = "name") String name) {
        return "My Name is " + name;
    }
    public List<String> getNames()
    {
        List<String> names = new ArrayList();
        names.add("Name 1");
        names.add("Name 2");
        names.add("Name 3");
        return names;
    }
    public List<User> getUsers()
    {
        List<User> users = new ArrayList();
        users.add(new User("user 1"));
        users.add(new User("user 2"));
        users.add(new User("user 3"));
        return users;
    }
}
