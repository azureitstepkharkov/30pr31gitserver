package com.mycompany.shoptelegrambotoracle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@Entity
@Table(name = "ORDERS")
public class Orders implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_generator")
    @SequenceGenerator(name="orders_generator", sequenceName = "ORDERS_SEQUENCE", initialValue = 1, allocationSize=1)
    @Column(name ="ORDERID", updatable = false, nullable = false)
    private Long orderId;
    
    @Column(name ="NUMBERORDER", nullable = false, length = 25)
    private String numberOrder;
    
    @Column(name ="DATEORDER", nullable = true)
    private Date dateOrder;
    
    @Column(name ="IDCUSTOMER", nullable = true)
    private Long idCustomer;

    @Column(name ="ORDERPRICE", nullable = true)
    private Float orderPrice;
    
    @Column(name ="ORDERSTATUS", nullable = true)
    private Integer orderStatus;
    
    
    @ManyToOne
    @JoinColumn(name ="IDCUSTOMER", insertable = false, updatable = false)
    private Customers customers;
    
    @OneToMany(mappedBy = "orders")
    private List<OrderDetails> orderDetails;

    
    public Orders() {
        
    }

    public Orders(Long orderId, String numberOrder, Date dateOrder, Long idCustomer, Float orderPrice, Integer orderStatus) {
        this.orderId = orderId;
        this.numberOrder = numberOrder;
        this.dateOrder = dateOrder;
        this.idCustomer = idCustomer;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    @XmlJavaTypeAdapter(yyyyMMddDateAdapter.class)
    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setOrderPrice(Float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    @XmlJavaTypeAdapter(yyyyMMddDateAdapter.class)
    public Date getDateOrder() {
        return dateOrder;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public Float getOrderPrice() {
        return orderPrice;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }
    
    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", numberOrder=" + numberOrder + ", dateOrder=" + dateOrder + ", idCustomer=" + idCustomer + ", orderPrice=" + orderPrice + ", orderStatus=" + orderStatus + '}';
    }
}
