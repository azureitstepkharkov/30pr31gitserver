import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import test.TestWebService_Service;


@ManagedBean
@RequestScoped
public class CallWS {

    private String name;
    private String correctedName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorrectedName() {
        return correctedName;
    }

    public void setCorrectedName(String correctedName) {
        this.correctedName = correctedName;
    }

    public void correctName() {
       TestWebService_Service service = new TestWebService_Service();
       test.TestWebService port = service.getTestWebServicePort();
       correctedName = port.correctName(name);
    }
     
}
