package Control;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author moudy
 */
public class Controller {
    dbaccess da=new dbaccess();
    public void addP(String B,String n,int nb, String dis,String hop) throws SQLException{
        Patient p=new Patient(B,n,nb,dis,hop);
        da.insertpatient(p.toString());
        
    }
    public int getstockblood() throws SQLException{
        ArrayList<Blood> bb=da.readblood();
        return bb.size();
    }
     public int getorders() throws SQLException{
        ArrayList<Order> bb=da.readorder();
        return bb.size();
    }
     public String[] getallpatientnames(){
         ArrayList<Patient> p=da.readP();
         String[] o=new String[p.size()];
         for(int i=0;i<p.size();i++){
             o[i]=p.get(i).getName();
         }
         return o;
     }
     public String[] getalldonornames(){
         ArrayList<Donor> p=da.readD();
         String[] o=new String[p.size()];
         for(int i=0;i<p.size();i++){
             o[i]=p.get(i).getName();
         }
         return o;
     }
     public int[] getallpatientid(){
         ArrayList<Patient> p=da.readP();
         int [] o=new int [p.size()];
         for(int i=0;i<p.size();i++){
             o[i]=p.get(i).getID();
         }
         return o;
     }
     public String[][] disP(){
         ArrayList<Patient> p=da.readP();
         String [][] o=new String [p.size()][6];
         for(int i=0;i<p.size();i++){
             String t=p.get(i).toString();
             
             StringBuilder stringBuilder = new StringBuilder(t.length());
        for (char c : t.toCharArray()) {
            if (c != '\'') { // Exclude the single quote character
                stringBuilder.append(c);
            }
        }
         t = stringBuilder.toString();
            
             o[i]=t.split(",");
         }
         return o;
     }
     public  String[][] disB(){
         ArrayList<Blood> p=da.readblood();
         String [][] o=new String [p.size()][6];
         for(int i=0;i<p.size();i++){
             String t=p.get(i).toString();
                     StringBuilder stringBuilder = new StringBuilder(t.length());
        for (char c : t.toCharArray()) {
            if (c != '\'') { // Exclude the single quote character
                stringBuilder.append(c);
            }
        }
         t = stringBuilder.toString();
                   o[i]=t.split(",");
         }
         return o;
     }
     public String[][] disD(){
         ArrayList<Donor> p=da.readD();
         String [][] o=new String [p.size()][6];
         for(int i=0;i<p.size();i++){
            String t=p.get(i).toString();
                     StringBuilder stringBuilder = new StringBuilder(t.length());
        for (char c : t.toCharArray()) {
            if (c != '\'') { // Exclude the single quote character
                stringBuilder.append(c);
            }
        }
         t = stringBuilder.toString();
                   o[i]=t.split(",");
         }
         return o;
     }
      public int[] getalldonorid(){
         ArrayList<Donor> p=da.readD();
         int [] o=new int [p.size()];
         for(int i=0;i<p.size();i++){
             o[i]=p.get(i).getID();
         }
         return o;
     }
      
    
    public String[] check_available_blood_in_banks(String b){
        ArrayList<Blood_Bank> out=da.readbloodinbb(b);
        String[] o=new String[out.size()];
        for(int i=0;i<out.size();i++){
            o[i]=out.get(i).getBBname();
        }
        return o;
    }
    public String[] readhospitalP(String h,String blood){
         ArrayList<Patient> out=da.readhospitalP(h);
        String[] o=new String[out.size()];
        for(int i=0;i<out.size();i++){
            if(out.get(i).getType().equals(blood)) 
                o[i]=out.get(i).getName();
           
        }
        return o;
    }
    public boolean has(String p){
        try {
            ArrayList<deliver> d=da.readdel();
            for(int i=0;i<d.size();i++){
            if(d.get(i).getP()==da.patid(p))
            return true;   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }return false;
    }
    public void addO(int bbid,String hop,String pat){
        
        try {
            Order o=new Order(bbid,hop);
            da.addO(o);
            
            deliver d=new deliver(hop,da.patid(pat));
            da.adddeliver(d);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addB(String type,String bb,Donor p) throws SQLException{
        da.addB(type,bb,p);
    }
    public String[] gethops_name() throws SQLException {
      String[] na=new String[da.readhospital().size()];
        for(int i=0;i<da.readhospital().size();i++){
          na[i]=da.readhospital().get(i).getName();
      }
    return na;
    }
    
    public String[] getbbs_name() throws SQLException{
      String[] na=new String[da.readbank().size()];
        for(int i=0;i<da.readbank().size();i++){
          na[i]=da.readbank().get(i).getBBname();
      }
    return na;
    }

    public void addD(String B, String n, int nb, String dob,String bb) throws SQLException {
    Donor p=new Donor(B,n,dob,nb);
        da.insertdonor(p.toString());
        addB(B,bb,p);
       
    }

    public void addD(String B, String bb) throws SQLException {
    Donor p=new Donor(B);
        da.insertdonor(p.toString());
        addB(B,bb,p);    
    }
}
