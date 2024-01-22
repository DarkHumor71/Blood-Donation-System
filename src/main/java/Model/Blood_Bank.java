/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author moudy
 */
public class Blood_Bank {
    private String BBname,BBloc;
private int BBID;
    public int getBBID() {
        return BBID;
    }

    public void setBBID(int BBID) {
        this.BBID = BBID;
    }

    public String getBBname() {
        return BBname;
    }

    public void setBBname(String BBname) {
        this.BBname = BBname;
    }

    public String getBBloc() {
        return BBloc;
    }

    public void setBBloc(String BBloc) {
        this.BBloc = BBloc;
    }

    public Blood_Bank(int BBID, String BBname, String BBloc) {
        this.BBID = BBID;
        this.BBname = BBname;
        this.BBloc = BBloc;
    }

    @Override
    public String toString() {
        return  BBID + ",'" + BBname + ",'" + BBloc + "'";
    }
    
}
