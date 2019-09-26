package com.mycompany.shoptelegrambotoracle;


import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
@SequenceGenerator(name = "Customers_Id_Seq_Gen", 
                   sequenceName = "CUSTOMERS_SEQUENCE",
                   allocationSize = 50,
                   initialValue = 200)
public class Customers implements Serializable {

    @Id
    @Column(name ="CUSTOMERID", nullable = false) 
    private Integer customersId;
    @Column(name = "FN", nullable = false, length = 30 )
    private String firstName;
    @Column(name = "LN", nullable = false, length = 30 )
    private String lastName;
    @Column(name = "MN", nullable = false, length = 30 )
    private String middleName;
    @Column(name = "PHONENUMBER" ,nullable = true, length = 35 )
    private String phoneNumber;
    @Column(name = "EMAIL" ,nullable = false, length = 35 )
    private String email;
    @Column(name = "AGE")
    private Integer age;
    
    
    @OneToMany(mappedBy = "customers")
    private List<Newusers> newusersList;
    
//    OneToMany(mappedBy = "customers")
//    private List<Orders> orders;
    
    
    public Customers() {
    }

    public Customers(Integer Id, String firstName, String lastName, String middleName, String email, Integer age) {
        this.customersId = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.age = age;
    }

    public Integer getId() {
        return customersId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
    
    public void setId(Integer Id) {
        this.customersId = Id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public void setNewusersList(List<Newusers> newusersList) {
        this.newusersList = newusersList;
    }

    public List<Newusers> getNewusersList() {
        return newusersList;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    @Override
    public String toString() {
        return  firstName + " " + lastName + " " + middleName + ", email: " + email + ", age: " + age + '}';
    }
    
}



