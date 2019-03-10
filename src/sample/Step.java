package sample;

import java.util.ArrayList;
import java.util.Date;

public class Step {
    private String Number;
    private String Time;
    private String Date;
    private String CompName;
    private String UserName;
    private String PointOfError;
    private String MessageText;
    private String Status;
    private Date dateTime;


    public String getNumber() {
        return Number;
    }

    public String getTime() {
        return Time;
    }

    public String getDate() {
        return Date;
    }

    public String getCompName() {
        return CompName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPointOfError() {
        return PointOfError;
    }

    public String getMessageText() {
        return MessageText;
    }

    public String getStatus() {
        return Status;
    }

    public java.util.Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(java.util.Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Step{" +
                "Number='" + Number + '\'' +
                ", Time='" + Time + '\'' +
                ", Date='" + Date + '\'' +
                ", CompName='" + CompName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", PointOfError='" + PointOfError + '\'' +
                ", MessageText='" + MessageText + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }

    public Step(ArrayList<String> list) {
        Number = list.get(0);
        Time = list.get(1);
        Date = list.get(2);
        CompName = list.get(3);
        UserName = list.get(4);
        PointOfError = list.get(5);
        MessageText = list.get(6);
        Status = list.get(7);
    }


}
