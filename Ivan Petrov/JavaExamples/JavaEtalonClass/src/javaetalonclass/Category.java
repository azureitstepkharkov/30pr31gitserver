package javaetalonclass;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;



@Entity
@Table(name = "CATEGORY")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Category implements Comparable<Category>, Serializable//+интерфейс Comparable<Product>
{
      @Override
    public int compareTo(Category t)
    {
        return t.name.compareTo(name); 
    }
    	@Id
	@Column(name = "CATEGORY_ID")
	@GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Category() {
    }

    public Category(String name, Set<Product> products) {
        this.name = name;
        this.products = products;
    }
        //поля для обращения к базе данных
    	private long id;
	private String name;
        //коллекция дочерних объектов
        //то, что заполнит Hibernate сам
        @XmlElement(name="products")
        private Set<Product> products;
}

