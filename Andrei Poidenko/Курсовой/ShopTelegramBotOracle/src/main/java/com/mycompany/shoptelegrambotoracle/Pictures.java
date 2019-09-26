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
@Table(name = "PICTURES")
public class Pictures implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pictures_generator")
    @SequenceGenerator(name="pictures_generator", sequenceName = "PICTURES_SEQUENCE", initialValue = 1, allocationSize=1)
    @Column(name ="PICTUREID", updatable = false, nullable = false)
    private Long picturesId;
    
    @Column(name ="PHOTO", nullable = false)
    private byte[] photo;
    
    @Column(name ="IDPRODUCT", nullable = true)
    private Long idProduct;
    
    @ManyToOne
    @JoinColumn(name ="IDPRODUCT", insertable = false, updatable = false)
    private Products products;

    public Pictures() {
    }

    public Pictures(Long picturesId, byte[] photo, Long idProduct) {
        this.picturesId = picturesId;
        this.photo = photo;
        this.idProduct = idProduct;
    }

    public void setPicturesId(Long picturesId) {
        this.picturesId = picturesId;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getPicturesId() {
        return picturesId;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Long getIdProduct() {
        return idProduct;
    }
}
