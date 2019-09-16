package xmlcreater;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement//класс сохраняемый в xml
@XmlAccessorType(XmlAccessType.NONE)
//сохранять, только явно отмеченные
//@XmlAccessorType(XmlAccessType.FIELD )
public class User 
{
    @XmlElement
    private String name;
    @XmlElement
    public int age;
    //@XmlAttribute(name = "IDENTITY", required = true)
    @XmlElement
    public int id;

    public User(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }
    public User() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    
}
