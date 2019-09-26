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
public class EmpToDeps {
    private int Id;

    private int EmpId;
    
    private int DepId;

    public EmpToDeps() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int EmpId) {
        this.EmpId = EmpId;
    }

    public int getDepId() {
        return DepId;
    }

    public void setDepId(int DepId) {
        this.DepId = DepId;
    }
    
    
    
}
