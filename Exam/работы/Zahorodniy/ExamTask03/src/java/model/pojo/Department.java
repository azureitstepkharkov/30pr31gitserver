/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojo;

/**
 *
 * @author staro
 */
public class Department {
    private int Id;
    
    private String Department_Name;

    public Department() {
    }

    public Department(String fname) {
      this.Department_Name = fname;
    }

    public int getId() {
      return this.Id;
    }

    public void setId(int id) {
      this.Id = id;
    }

    public String getDepartment_Name() {
      return this.Department_Name;
    }

    public void setDepartment_Name(String name) {
      this.Department_Name = name;
    }
}
