package edu.psu.cjr5662.blt;

        import java.util.ArrayList;

public class Lab {
    private int labNumber = 0;
    private int computersAvailable = 0;     // number of computers in the lab
    private String software = " ";
    int PK;

    public Lab(){}

    public Lab(int pk, int labNum, int computers, String software){
        this.PK = pk;
        this.labNumber = labNum;
        this.computersAvailable = computers;
        this.software = software;
    }

    public void setLabNumber(int num){
        this.labNumber = num;
    }
    public int getLabNumber(){
        return this.labNumber;
    }

    public void setPK(int num){
        this.PK= num;
    }
    public int getPK(){
        return this.PK;
    }

    public void setComputers(int computers){
        this.computersAvailable = computers;
    }
    public int getComputers(){
        return this.computersAvailable;
    }

    public void setAvailableSoftware(String software){
        this.software = software;
    }
    public String getAvailabileSoftware(){
        return this.software;
    }
}