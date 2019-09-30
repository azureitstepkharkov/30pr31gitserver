package Model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "MY_USER")
public class MY_USER {

    private long id;
    private String USER_NAME;
    private String USER_PASS;

    private Set<MYUSER_INFO> myuser_info;

    
    public MY_USER() {

    }

    public MY_USER(long id, String USER_NAME, String USER_PASS, Set<MYUSER_INFO> myuser_info) {
        this.id = id;
        this.USER_NAME = USER_NAME;
        this.USER_PASS = USER_PASS;
        this.myuser_info = myuser_info;
    }
    

    @Id
    @Column(name = "ID")
    @GeneratedValue
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public void setUSER_PASS(String USER_PASS) {
        this.USER_PASS = USER_PASS;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public String getUSER_PASS() {
        return USER_PASS;
    }
    
    @OneToMany(mappedBy = "my_user", cascade = CascadeType.ALL)
    public Set<MYUSER_INFO> getMyuser_info() {
        return myuser_info;
    }

    public void setMyuser_info(Set<MYUSER_INFO> myuser_info) {
        this.myuser_info = myuser_info;
    }

}
