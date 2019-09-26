
package com.mycompany.shoptelegrambotoracle;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class OrderDetailsProduct {

    private Long orderDetailId;    
    private Long idOrder;
    private Long idProduct;
    private Integer countProduct;
    private Float price;
    private String productName;
    
    public OrderDetailsProduct(Long orderDetailId, Long idOrder, Long idProduct, Integer countProduct, Float price, String producyName) {
        this.orderDetailId = orderDetailId;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.countProduct = countProduct;
        this.price = price;
        this.productName = producyName;
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

    public String getProducyName() {
        return productName;
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

    public void setProducyName(String producyName) {
        this.productName = producyName;
    }
}
