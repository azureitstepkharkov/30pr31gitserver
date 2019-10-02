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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "MYUSER_INFO")
public class MYUSER_INFO {
    
    private long id;
    private String USER_MAIL;
    private String USER_PHONE;
    private String USER_RULE;
    private MY_USER my_user;
    
    
    public MYUSER_INFO() {
    }

    @Override
    public String toString() {
        return "MYUSER_INFO{" + "id=" + id + ", USER_MAIL=" + USER_MAIL + ", USER_PHONE=" + USER_PHONE + ", USER_RULE=" + USER_RULE + ", my_user=" + my_user + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 73 * hash + Objects.hashCode(this.USER_MAIL);
        hash = 73 * hash + Objects.hashCode(this.USER_PHONE);
        hash = 73 * hash + Objects.hashCode(this.USER_RULE);
        hash = 73 * hash + Objects.hashCode(this.my_user);
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
        final MYUSER_INFO other = (MYUSER_INFO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.USER_MAIL, other.USER_MAIL)) {
            return false;
        }
        if (!Objects.equals(this.USER_PHONE, other.USER_PHONE)) {
            return false;
        }
        if (!Objects.equals(this.USER_RULE, other.USER_RULE)) {
            return false;
        }
        if (!Objects.equals(this.my_user, other.my_user)) {
            return false;
        }
        return true;
    }

    
    
    public MYUSER_INFO(long id, String USER_MAIL, String USER_PHONE, String USER_RULE, MY_USER my_user) {
        this.id = id;
        this.USER_MAIL = USER_MAIL;
        this.USER_PHONE = USER_PHONE;
        this.USER_RULE = USER_RULE;
        this.my_user = my_user;
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
    
    @ManyToOne
    @JoinColumn(name = "ID_MYUSER")
    public MY_USER getMy_user() {
        return my_user;
    }

    public void setUSER_MAIL(String USER_MAIL) {
        this.USER_MAIL = USER_MAIL;
    }

    public void setUSER_PHONE(String USER_PHONE) {
        this.USER_PHONE = USER_PHONE;
    }

    public void setUSER_RULE(String USER_RULE) {
        this.USER_RULE = USER_RULE;
    }

    public void setMy_user(MY_USER my_user) {
        this.my_user = my_user;
    }

    public String getUSER_MAIL() {
        return USER_MAIL;
    }

    public String getUSER_PHONE() {
        return USER_PHONE;
    }

    public String getUSER_RULE() {
        return USER_RULE;
    }
    
}
