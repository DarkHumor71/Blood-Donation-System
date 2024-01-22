/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author moudy
 */

public class Blood {
private int ID;
private String type,date,BBID,DID;

    public Blood(int ID, String type, String BBID, String DID, String date) {
        this.ID = ID;
        this.type = type;
        this.date = date;
        this.BBID = BBID;
        this.DID = DID;
    }
    

    @Override
    public String toString() {
        return ID + ",'" + type + ",'" + date + "," + BBID + "," + DID ;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBBID() {
        return BBID;
    }

    public void setBBID(String BBID) {
        this.BBID = BBID;
    }

    public String getDID() {
        return DID;
    }

    public void setDID(String DID) {
        this.DID = DID;
    }

}
