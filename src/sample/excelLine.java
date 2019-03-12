package sample;

public class excelLine {
int day;
long Tw;
int stay;
long Tr;

    @Override
    public String toString() {
        return "excelLine{" +
                "day=" + day +
                ", Tw(m)=" + Tw/1000/60 +
                ", stay=" + stay +
                ", Tr(m)=" + Tr/1000/60 +
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
