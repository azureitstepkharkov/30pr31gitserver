package testrestfullservice;

import test.ResponseList;
import test.User;
import test.WebServiceRESTfullServerJerseyClient;

public class TestRESTfullService {

    public static void main(String[] args) {
        WebServiceRESTfullServerJerseyClient client = new WebServiceRESTfullServerJerseyClient();
        ResponseList response = client.getUsers(ResponseList.class) ;
        for(User user : response.getUsers())
        {
            System.out.println(user.getName());
        }
        client.close();
    }
    
}
