package Model;

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
