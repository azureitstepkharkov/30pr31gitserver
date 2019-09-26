package com.mycompany.shoptelegrambotoracle;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@Entity
@Table(name = "NEWUSERS")
//@SequenceGenerator(name = "Newusers_Id_Seq_Gen", 
//                   sequenceName = "NEWUSERS_SEQUENCE",
//                   allocationSize = 50,
//                   initialValue = 200)
public class Newusers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "newusers_generator")
    @SequenceGenerator(name="newusers_generator", sequenceName = "NEWUSERS_SEQUENCE", initialValue = 1, allocationSize=1)
    @Column(name ="NewUsersId", updatable = false, nullable = false)
    private Long newusersId;
    
    @Column(name ="BOTNUMBER", nullable = false)
    private Integer botNumber;
    
    @Column(name ="NAME", nullable = false, length = 45)
    private String name;
    
    @Column(name ="PHONENUMBER", nullable = false, length = 55)
    private String phoneNumber;
    
    @Column(name ="STATUS", nullable = false)
    private Integer status;
    
    @Column(name ="IDCUSTOMER")
    private Integer idCustomer;
    
    @Temporal(TemporalType.DATE)
    @Column(name ="CREATEDAT", nullable = false)
    private Date createdAt;
    
    
    @ManyToOne
    @JoinColumn(name ="IDCUSTOMER", insertable = false, updatable = false)
    private Customers customers;

       
    public Newusers() 
    {
        
    }

    public Newusers(Long id, Integer botNumber, String name, String phoneNumber, Integer status, Integer idCustomer, Date createdAt, Customers customers) {
        this.newusersId = id;
        this.botNumber = botNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.idCustomer = idCustomer;
        this.createdAt = createdAt;
        this.customers = customers;
    }
    
   
    
    public Long getId() {
        return newusersId;
    }

    public Integer getBotNumber() {
        return botNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }
    
    @XmlJavaTypeAdapter(yyyyMMddDateAdapter.class)
    public Date getCreatedAt() {
        return createdAt;
    }

    public Customers getCustomers() {
        return customers;
    }
    
    public void setId(Long id) {
        this.newusersId = id;
    }

    public void setBotNumber(Integer botNumber) {
        this.botNumber = botNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    @XmlJavaTypeAdapter(yyyyMMddDateAdapter.class)
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }
     
    @Override
    public String toString() {
        return "Newusers{" + "id=" + newusersId + ", botNumber=" + botNumber + ", name=" + name + ", phoneNumber=" + phoneNumber + ", status=" + status + ", idCustomer=" + idCustomer + ", createdAt=" + createdAt + ", customers=" + customers + '}';
    }
    
}
