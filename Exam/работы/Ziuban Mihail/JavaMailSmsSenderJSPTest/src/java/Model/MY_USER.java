/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author merkyr
 */
import java.util.Objects;
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

    @Override
    public String toString() {
        return "MY_USER{" + "id=" + id + ", USER_NAME=" + USER_NAME + ", USER_PASS=" + USER_PASS + ", myuser_info=" + myuser_info + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.USER_NAME);
        hash = 29 * hash + Objects.hashCode(this.USER_PASS);
        hash = 29 * hash + Objects.hashCode(this.myuser_info);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MY_USER other = (MY_USER) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.USER_NAME, other.USER_NAME)) {
            return false;
        }
        if (!Objects.equals(this.USER_PASS, other.USER_PASS)) {
            return false;
        }
        if (!Objects.equals(this.myuser_info, other.myuser_info)) {
            return false;
        }
        return true;
    }

    
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
