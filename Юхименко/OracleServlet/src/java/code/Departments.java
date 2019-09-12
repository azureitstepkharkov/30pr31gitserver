package code;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//обязательные тэги
//для класса
@Entity
@Table(name = "Departments") //имя проверить в базе данных
@SequenceGenerator(name = "Departments_Id_Seq_Gen", 
                   sequenceName = "DEPARTMENTS_SEQ", //имя проверить в базе данных
                   allocationSize = 50,
                   initialValue = 200)
public class Departments implements Serializable {
     //для поля класса, которое отвечает за работы с первичным ключом
    @Id
    @Column(name ="DEPARTMENT_ID", nullable = false)
    private Integer departmentId;
    @Column(name = "DEPARTMENT_NAME",nullable = false, length = 30 )
    private String departmentName;
    @Column(name = "LOCATION_ID" )
    private Integer locationId;
    @Column(name = "MANAGER_ID" )
    private Integer managerId;
    //отношение One-To-Many
    @OneToMany(mappedBy = "departments")//departments - имя свойства в классе
    //для описания подчиненной таблицы
    private List<Employees> employeeList;

    public Departments() {
    }

    public Departments(Integer departmentId, String departmentName, Integer locationId, Integer managerId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.locationId = locationId;
        this.managerId = managerId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public List<Employees> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employees> employeesList1) {
        this.employeeList = employeesList1;
    }

    public Employees addEmployees(Employees employees) {
        getEmployeeList().add(employees);
        employees.setDepartments(this);
        return employees;
    }

    public Employees removeEmployees(Employees employees) {
        getEmployeeList().remove(employees);
        employees.setDepartments(null);
        return employees;
    }
}

