package edu.psu.cjr5662.blt;

        import java.util.ArrayList;

public class Lab {
    private int labNumber = 0;
    private int computersAvailable = 0;     // number of computers in the lab
    private ArrayList<String> software = new ArrayList<String>();

    public Lab(){}

    public Lab(int labNum, int computers, ArrayList<String> arr){
        this.labNumber = labNum;
        this.computersAvailable = computers;
        this.software = arr;
    }

    public void setLabNumber(int num){
        this.labNumber = num;
    }
    public int getLabNumber(){
        return this.labNumber;
    }

    // COMPUTER AVAILABILITY
    public void setComputers(int computers){
        this.computersAvailable = computers;
    }
    public int getComputers(){
        return this.computersAvailable;
    }

    // SOFTWARE AVAILABILITY
    public void setAvailableSoftware(ArrayList<String> arr){
        this.software.addAll(arr);
    }
    public ArrayList<String> getAvailabileSoftware(){
        return this.software;
    }
}