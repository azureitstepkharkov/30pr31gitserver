
package javaetalonclass;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "PRODUCT")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Product implements Comparable<Product>, Serializable
{       
    @Override
    public int compareTo(Product t)
    {
        return t.name.compareTo(name); 
    }

    	@Id
	@Column(name = "PRODUCT_ID")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    public Category getCategory() {
        return category;
    }

    //поля из базы данных - один в один
    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {
    }

    public Product(String name, String description, float price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
    @XmlElement
    	private long id;
    @XmlElement
	private String name;
    @XmlElement
	private String description;
    @XmlElement
	private float price;
	
        //ссылка в таблице - здесь это класс, 
        //который представляет Главную таблицу
	private Category category;
    
}
