package sample;

public class excelLine {
int day;
long Tw;
String stay;
String Tr;

    @Override
    public String toString() {
        return "excelLine{" +
                "day=" + day +
                ", Tw(s)=" + Tw/1000/60 +
                '}';
    }

    public excelLine(int day, long tw) {

        this.day = day;
        Tw = tw;

    }


}
