package com.mycompany.shoptelegrambotoracle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TYPESPRODUCT")
public class TypesProduct implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "typesProduct_generator")
    @SequenceGenerator(name="typesProduct_generator", sequenceName = "TypesProduct_sequence")
    @Column(name ="TYPESPRODUCTID", updatable = false, nullable = false)
    private Long typesProductId;
    
    @Column(name ="NAME", nullable = false, length = 50)
    private String name;
    
    @OneToMany(mappedBy = "typesProduct")
    private List<Products> products;

    public TypesProduct(Long typesProductId, String name) {
        this.typesProductId = typesProductId;
        this.name = name;
    }

    public TypesProduct() {
        
    }
    
   public void setTypesProductId(Long typesProductId) {
        this.typesProductId = typesProductId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTypesProductId() {
        return typesProductId;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "TypesProduct{" + "typesProductId=" + typesProductId + ", name=" + name + '}';
    }
}
