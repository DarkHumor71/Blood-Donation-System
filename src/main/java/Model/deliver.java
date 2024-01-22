/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author moudy
 */
public class deliver {
    private String h;private int p;

    public deliver(String h, int p) {
        this.h = h;
        this.p = p;
    }

    @Override
    public String toString() {
        return "'" + h + "'," + p;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }
}
