package sample;

import java.text.SimpleDateFormat;
import java.util.Date;

public class excelLine {

    public void setDate(Date date) {
        this.date = date;
    }


    int day;
    long Tw;
    int stay;
    long Tr;

    public excelLine(int day, long tw, int stay, long tr, Date date) {
        this.day = day;
        Tw = tw;
        this.stay = stay;
        Tr = tr;
        this.date = date;
    }

    Date date;

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
        return "excelLine{" +
                simpleDateFormat.format(date)+
                ", Tw(m)=" + Tw / 1000 / 60 +
                ", stay=" + stay +
                ", Tr(m)=" + Tr / 1000 / 60 +
                '}';
    }

    public excelLine(int day, long tw, int stay, long tr) {
        this.day = day;
        Tw = tw;
        this.stay = stay;
        Tr = tr;
    }

    public excelLine(int day, long tw) {

        this.day = day;
        Tw = tw;

    }


}
