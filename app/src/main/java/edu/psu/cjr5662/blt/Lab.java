package edu.psu.cjr5662.blt;

        import java.util.ArrayList;

public class Lab {
    private int labNumber = 0;
    private int computersAvailable = 0;     // number of computers in the lab
    private String software = " ";
    private String day = " ";
    private String times = " ";
    int PK;


    public Lab(){}

    public Lab(int pk, int labNum, int computer, String software, String day, String times){
        this.PK = pk;
        this.labNumber = labNum;
        this.computersAvailable = computer;
        this.software = software;
        this.day = day;
        this.times = times;
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

    public void setDay(String day){
        this.day = day;
    }
    public String getDay(){
        return this.day;
    }

    public void setTimes(String times){
        this.times = times;
    }
    public String getTimes(){ return this.times; }
}