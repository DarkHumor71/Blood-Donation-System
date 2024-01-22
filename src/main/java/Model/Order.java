/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author moudy
 */
public class Order {
    private int id;
    private String hname,date;

    public Order(int id, String hname) {
        this.id = id;
        this.hname = hname;
        Date ti = new Date();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       String dt = formatter.format(ti);
       this.date =dt;
    }

    public Order(int id, String hname, String date) {
        this.id = id;
        this.hname = hname;
        this.date = date;
    }
    

    @Override
    public String toString() {
        return  id + ",'" + hname + "','" + date + "'";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
