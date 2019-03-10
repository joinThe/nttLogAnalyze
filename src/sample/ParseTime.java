package sample;

import jdk.nashorn.internal.runtime.ParserException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseTime {

    private SimpleDateFormat simpleDateFormat;
    private final byte length = 1;

    public ParseTime(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    public Date getDate(String stringTimeDate) {
        Date dateObject;
        try {
            if (stringTimeDate.length() >= length) {
                 dateObject = simpleDateFormat.parse(stringTimeDate);
            }else return null;

            return dateObject;
        } catch (ParseException pe) {
            return null;
        }
    }

}
