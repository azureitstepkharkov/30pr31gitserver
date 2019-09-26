package com.mycompany.shoptelegrambotoracle;

import java.io.Serializable;
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


@Entity
@Table(name = "PRODUCTS")
public class Products implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_generator")
    @SequenceGenerator(name="products_generator", sequenceName = "PRODUCTS_SEQUENCE")
    @Column(name ="PRODUCTID", updatable = false, nullable = false)
    private Long productId;
    
    @Column(name ="MODEL", nullable = false, length = 30)
    private String model;
    
    @Column(name ="IDTYPEPRODUCT", nullable = true)
    private Long idProductType;
    
    @Column(name ="IDMANUFACTURER", nullable = true)
    private Long idManufacturer;

    @Column(name ="PRICE", nullable = false)
    private Float price;
    
    @Column(name ="PRODUCTCOUNT", nullable = true)
    private Integer productCount;
    
    @ManyToOne
    @JoinColumn(name ="IDTYPEPRODUCT", insertable = false, updatable = false)
    private TypesProduct typesProduct;
    
    @ManyToOne
    @JoinColumn(name ="IDMANUFACTURER", insertable = false, updatable = false)
    private Manufacturers manufacturers;
    
    @OneToMany(mappedBy = "products")
    private List<OrderDetails> orderDetails;
    
    @OneToMany(mappedBy = "products")
    private List<Pictures> pictureses;

    
    public Products(Long productId, String model, Long idProductType, Long idManufacturer, Float price, Integer productCount) {
        this.productId = productId;
        this.model = model;
        this.idProductType = idProductType;
        this.idManufacturer = idManufacturer;
        this.price = price;
        this.productCount = productCount;
    }

    public Products() {
        
    }
    
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setIdProductType(Long idProductType) {
        this.idProductType = idProductType;
    }

    public void setIdManufacturer(Long idManufacturer) {
        this.idManufacturer = idManufacturer;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Long getProductId() {
        return productId;
    }

    public String getModel() {
        return model;
    }

    public Long getIdProductType() {
        return idProductType;
    }

    public Long getIdManufacturer() {
        return idManufacturer;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getProductCount() {
        return productCount;
    }

    
    @Override
    public String toString() {
        return "Products{" + "productId=" + productId + ", model=" + model + ", idProductType=" + idProductType + ", idManufacturer=" + idManufacturer + ", price=" + price + ", productCount=" + productCount + '}';
    }
    
}
