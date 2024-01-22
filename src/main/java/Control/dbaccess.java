package Control;

import Model.Blood;
import Model.Blood_Bank;
import Model.Donor;
import Model.Hospital;
import Model.Order;
import Model.Patient;
import Model.deliver;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author moudy
 */
public class dbaccess {
    Connection connection;
    Statement statement;
    private void connect() {
        try {
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/blood","root","");
        
        statement=connection.createStatement();
 
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void disconnect(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Patient> readP() {
        connect();
        ArrayList<Patient> arr=new ArrayList<>();
       try { 
           Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM patient;");
        
            while(rs.next()){
                arr.add(new Patient(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        return arr; 
    }
    public ArrayList<Patient> readhospitalP(String h) {
        connect();
        ArrayList<Patient> arr=new ArrayList<>();
       try { 
           Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM patient where hospital_name='"+h+"';");
        
            while(rs.next()){
                arr.add(new Patient(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        return arr; 
    }
    public String[][] join(){
    connect();
        ArrayList<String> arr=new ArrayList<>();
       try { 
           Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT donor_id,patient_id,donor_name,patient_name FROM (patient natural join bloodbank natural join delivers natural join donor natural join blood);");
        
            while(rs.next()){
                arr.add(""+rs.getInt(1)+","+rs.getInt(2)+","+rs.getString(3)+","+rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        String[][] o=new String[arr.size()][4];
        for(int i=0;i<arr.size();i++){
            o[i]=arr.get(i).split(",");
            }
        return o;
}
    
    public ArrayList<Donor> readD() {
        connect();
        ArrayList<Donor> arr=new ArrayList<>();
       try { 
           Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM donor;");
        
            while(rs.next()){
                arr.add(new Donor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        return arr; 
    }
    public void insertdonor(String in) throws SQLException{
        connect();
        Statement stmt=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.execute("use blood;");//use db
        String q="INSERT INTO donor ( Btype, donor_name, donor_dob, donor_number) VALUES ("+ in+");";
        
        stmt.execute(q);
        disconnect();
    } 
    public void addO(Order o){
        try {
            connect();
            Statement stmt=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.execute("use blood;");//use db
            String q="INSERT INTO orders ( blood_bank_id,hospital_name, order_date) VALUES ("+ o.toString()+");";
            
            stmt.execute(q);
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void adddel(deliver o){
        try {
            connect();
            Statement stmt=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.execute("use blood;");//use db
            String q="INSERT INTO delivers ( hospital_name, patient_id) VALUES ("+ o.toString()+");";
            
            stmt.execute(q);
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addB(String type,String bb,Donor p) throws SQLException{
        connect();
        Date date = new Date();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       String dt = formatter.format(date);
        Statement stmt =connection.createStatement();
        stmt.execute("use blood;");
       String q="insert into blood (blood_type,blood_bank_id,donor_id,date) values('"+type+"',(select blood_bank_id from bloodbank where blood_bank_name='"+bb+"'),"+"(select donor_id from donor where donor_number="+p.getNumber()+")"+",'"+dt+"');"
               ;
        stmt.execute(q
       );
        disconnect();
    }
     public void addorder(Order o) throws SQLException{
         connect();
        Statement stmt =connection.createStatement();
        stmt.execute("use blood;");
        stmt.execute(
       "insert into order (blood_bank_id,hospital_name,order_date,) values("+o.toString()+")"
               );
        disconnect();
     }
     public void adddeliver(deliver o) throws SQLException{
         connect();
        Statement stmt =connection.createStatement();
        stmt.execute("use blood;");
        stmt.execute(
       "insert into delivers (hospital_name,patient_id) values("+o.toString()+")"
               );
        disconnect();
     }
    public void insertpatient(String in) throws SQLException{
            connect();
        Statement stmt=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
        String q="use blood;";
        
        stmt.execute(q);
        stmt.execute("INSERT INTO patient ( Btype, patient_name, patient_number, patient_disease, hospital_name) VALUES ("+in+");");
        disconnect();
    }
    public  ArrayList<Blood> readblood() {
        try {
            connect();
            ArrayList<Blood> arr=new ArrayList<>();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM blood;");
            while(rs.next()){
                arr.add(new Blood(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            disconnect();
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }return null;
    }
    public void deleteP(int r){
        try {
            connect();
            Statement stmt=connection.createStatement();
            stmt.execute("use blood;");
            stmt.execute("delete from patient where patient_id="+r+";");
            disconnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteD(int r){
        try {
            connect();
            Statement stmt=connection.createStatement();
            stmt.execute("use blood;");
            stmt.execute("delete from donor where donor_id="+r+";");
            disconnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Blood_Bank> readbloodinbb(String B) {
        try {
            connect();
            ArrayList<Blood_Bank> arr=new ArrayList<>();
            Statement stmt=connection.createStatement();
            ResultSet rs=
                    stmt.executeQuery(
                            "SELECT blood_bank_id,blood_bank_name,blood_bank_location FROM (blood natural join bloodbank)where blood_type='"+B+"'group by blood_bank_id;"
                    );
            while(rs.next()){
                arr.add(new Blood_Bank(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            disconnect();
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }return null;
    }
    public ArrayList<Order> readorder() throws SQLException{
        connect();
        ArrayList<Order> arr=new ArrayList<>();
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM orders;");
        while(rs.next()){
            arr.add(new Order(rs.getInt(1),rs.getString(2),rs.getString(3)));
                    }
        disconnect();
        return arr; 
    }
     public ArrayList<deliver> readdel() throws SQLException{
        connect();
        ArrayList<deliver> arr=new ArrayList<>();
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM delivers;");
        while(rs.next()){
            arr.add(new deliver(rs.getString(1),rs.getInt(2)));
                    }
        disconnect();
        return arr; 
    }
    
    public ArrayList<Hospital> readhospital() throws SQLException{
        connect();
        ArrayList<Hospital> arr=new ArrayList<>();
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM hospital;");
        while(rs.next()){
            arr.add(new Hospital(rs.getString(1),rs.getString(2),rs.getInt(3)));
        }
        disconnect();
        return arr;
    }
    public ArrayList<Blood_Bank> readbank() throws SQLException{
        connect();
        ArrayList<Blood_Bank> arr=new ArrayList<>();
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM bloodbank;");
        while(rs.next()){
            arr.add(new Blood_Bank(rs.getInt(1),rs.getString(2),rs.getString(3)));
        }
        disconnect();
        return arr;
    }
    public int getbankid(String name) throws SQLException{
        connect();
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT blood_bank_id FROM bloodbank where blood_bank_name='"+name+"';");int arr=-1;
        while(rs.next()){
             arr=rs.getInt(1);
        }
        disconnect();
        return arr;
    }
     public int patid(String name) throws SQLException{
        connect();
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT patient_id FROM patient where patient_name='"+name+"';");
        int arr=-1;
        while(rs.next()){
             arr=rs.getInt(1);
        }
        disconnect();
        return arr;
    }

    public void upP(String update,String colup,String colidn,String id) {
        try {
            connect();
            Statement stmt=connection.createStatement();
            stmt.executeUpdate("UPDATE `patient` SET `"+colup+"` = '"+update+"' WHERE `patient`.`"+colidn+"` ="+ id+";");
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void upD(String update,String colup,String colidn,String id) {
        try {
            connect();
            Statement stmt=connection.createStatement();
            stmt.executeUpdate("UPDATE `donor` SET `"+colup+"` = '"+update+"' WHERE `donor`.`"+colidn+"` ="+ id+";");
        } catch (SQLException ex) {
            Logger.getLogger(dbaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
