package com.mycompany.shoptelegrambotoracle;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "MANUFACTURERS")
public class Manufacturers implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manufacturers_generator")
    @SequenceGenerator(name="manufacturers_generator", sequenceName = "MANUFACTURER_SEQUENCE")
    @Column(name ="MANUFACTURERID", updatable = false, nullable = false)
    private Long manufacturerId;

    @Column(name ="NAME", nullable = false, length = 40)
    private String name;
    
    @OneToMany(mappedBy = "manufacturers")
    private List<Products> products;
    
    public Manufacturers() {
    }

    public Manufacturers(Long manufacturerId, String name) {
        this.manufacturerId = manufacturerId;
        this.name = name;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Manufacturers{" + "manufacturerId=" + manufacturerId + ", name=" + name + '}';
    }
    
}
