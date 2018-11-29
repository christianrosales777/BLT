package edu.psu.cjr5662.blt;

public class Lab {
    private int labNumber = 0;
    private int computersAvailable = 0;     // number of computers in the lab
    private String[] software = {"Creo", "Vivado", "Minitab", "Visual Studio", "Alice", "Matlab", "Moldflow Synergy", "MathCad", "Inventor", "Java JDK", "Moldflow Insight", "Netbeans"};
    private int[] softwareAvailable = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int initTime = 0;
    private int endTime = 0;
    private int status = 0;
    private String dayOfWeek;

    public Lab(){}

    //public Lab(int num, int computers){
      //  this.labNumber = num;
      //  this.computersAvailable = computers;
    //}

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
    public void setAvailable(String software){
        int index = getIndex(software);
        this.softwareAvailable[index] = 1;
    }
    public int getAvailability(){
        return this.labNumber;
    }

    public int getIndex(String software) {
        int index = 0;
        for(int i = 0; i < this.software.length; i++){
            if(this.software[i].equals(software))
                index = i;
        }
        return index;
    }

    // SCHEDULE STUFF
    public void setInitTime(int time){
        this.initTime = time;
    }
    public int getInitTime(){
        return this.initTime;
    }

    public void setEndTime(int time){
        this.endTime = time;
    }
    public int getEndTime(){
        return this.endTime;
    }

    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }

    public void setDay(String day){
        this.dayOfWeek = day;
    }
    public String getDay(){
        return this.dayOfWeek;
    }
}
