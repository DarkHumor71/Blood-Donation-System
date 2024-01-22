/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author moudy
 */
public class Donor {
    private int ID,number;
    private String type,name, dob;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Donor(int ID, String type,String name,String dob, int number) {//general
        this.ID = ID;
        this.number = number;
        this.type = type;
        this.name = name;
        this.dob = dob;
    }
     public Donor(String type,String name,String dob, int number) {//add donor with info
        this.ID = -1;
        this.number = number;
        this.type = type;
        this.name = name;
        this.dob = dob;
    }
    public Donor(String type) { //entry without info
        this.ID = -1;
        this.number = -1;
        this.type = type;
        this.name = null;
        this.dob = null;
    }
    public Donor(int ID,String type) {//read without info
        this.ID = ID;
        this.number = -1;
        this.type = type;
        this.name = null;
        this.dob = null;
    }

    @Override
    public String toString() {
        if(number==-1 && ID==-1){
            return "'"+type  + "','" + name + "','" + dob + "'," + null;
        }else if(ID==-1){
            return "'"+type  + "','" + name + "','" + dob + "'," + number;
        }else if(number==-1){
            return ID + ",'" +type  + "','" + name + "','" + dob + "'," + null;
        }else
        return ID + ",'" +type  + "','" + name + "','" + dob + "'," + number;
        
    }

    
}
