package sample;

import jdk.nashorn.internal.runtime.ParserException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ParseTime {

    private static SimpleDateFormat simpleDateFormat= new SimpleDateFormat("hh:mm:ss a dd/MM/yy");
    private static final byte length = 1;

    public ParseTime() {
    }

    public ParseTime(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    public Date getDate(String stringTimeDate) {
        Date dateObject;
        try {
            if (stringTimeDate.length() >= length) {
                dateObject = simpleDateFormat.parse(stringTimeDate);
            } else return null;

            return dateObject;
        } catch (ParseException pe) {
            return null;
        }
    }


    public static boolean parseDateTime(ArrayList<Step> list) {
        for ( Step s : list ) {
            Date dateObject;
            String stringTimeDate=s.getTime()+" "+s.getDate();
            try {
                if (stringTimeDate.length() >= length) {
                    dateObject = simpleDateFormat.parse(stringTimeDate);
                } else dateObject= null;
            } catch (ParseException pe) {
//                System.out.println(pe.getMessage());
                dateObject=null;
            }
            s.setDateTime(dateObject);
        }
        return true;
    }

}
