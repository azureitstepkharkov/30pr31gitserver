/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojo;

/**
 *
 * @author asp
 */
public class Employee {
    private int Id;
    
    private String Employees_Name;

    private int IdCode;

    public Employee() {
    }

    public Employee(String fname) {
      this.Employees_Name = fname;
    }

    public int getId() {
      return this.Id;
    }

    public void setId(int id) {
      this.Id = id;
    }
    
    public int getIdCode() {
      return this.IdCode;
    }

    public void setIdCode(int id) {
      this.IdCode = id;
    }

    public String getEmployees_Name() {
      return Employees_Name;
    }

    public void setEmployees_Name(String name) {
      this.Employees_Name = name;
    }
}
