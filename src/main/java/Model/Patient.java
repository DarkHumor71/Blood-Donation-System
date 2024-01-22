package Model;

/**
 *
 * @author moudy
 */
public class Patient {
    private int ID=-1,number;
    private String type,name,disease,hospital;

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public Patient(int ID, String type, String name, int number, String disease,String hospital) {
        this.ID = ID;
        this.number = number;
        this.type = type;
        this.name = name;
        this.disease = disease;
        this.hospital=hospital;
    }
     public Patient( String type, String name, int number, String disease,String hospital) {
        this.number = number;
        this.type = type;
        this.name = name;
        this.disease = disease;
        this.hospital=hospital;
    }

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

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Override
    public String toString() {
        if(ID!=-1)
        return  ID + ",'" + type + "','" +name  + "'," + number + ",'" + disease + "','" + hospital+"'";
        else 
            return "'"+type  + "','" + name + "'," + number + ",'" + disease + "','" + hospital+"'";
    }
    
}
