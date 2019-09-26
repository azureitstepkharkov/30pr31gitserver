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


@Entity
@Table(name = "ORDERDETAILS")
public class OrderDetails implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderDetails_generator")
    @SequenceGenerator(name="orderDetails_generator", sequenceName = "ORDERDETAILS_SEQUENCE", initialValue = 1, allocationSize=1)
    @Column(name ="ORDERDETAILID", updatable = false, nullable = false)
    private Long orderDetailId;
    
    @Column(name ="IDORDER", nullable = false, length = 25)
    private Long idOrder;
    
    @Column(name ="IDPRODUCT", nullable = false)
    private Long idProduct;
    
    @Column(name ="COUNTPRODUCT", nullable = true)
    private Integer countProduct;

    @Column(name ="PRICE", nullable = false)
    private Float price;
    
    @ManyToOne
    @JoinColumn(name ="IDORDER", insertable = false, updatable = false)
    private Orders orders;
    
    @ManyToOne
    @JoinColumn(name ="IDPRODUCT", insertable = false, updatable = false)
    private Products products;
    

    public OrderDetails(Long orderDetailId, Long idOrder, Long idProduct, Integer countProduct, Float price) {
        this.orderDetailId = orderDetailId;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.countProduct = countProduct;
        this.price = price;
    }

    public OrderDetails() {
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public Integer getCountProduct() {
        return countProduct;
    }

    public Float getPrice() {
        return price;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public void setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderDetailId=" + orderDetailId + ", idOrder=" + idOrder + ", idProduct=" + idProduct + ", countProduct=" + countProduct + ", price=" + price + '}';
    }
}
