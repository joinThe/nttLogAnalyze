package sample;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StepAnalyze {

    long tStart;
    long tStop;
    int thisDay = 0;
    int nextDay = 0;
    boolean start;
    boolean stop;


    ArrayList<excelLine> excelLines;
    ArrayList<Step> stepsList;
    SimpleDateFormat returnDateFormat;

    public ArrayList<excelLine> getExcelLines() {
        return excelLines;
    }

    public StepAnalyze(ArrayList<Step> stepsList) {
        this.excelLines = new ArrayList<>();
        returnDateFormat = new SimpleDateFormat("D");
        this.stepsList = stepsList;
    }

    public void analyze() {
        long workTime = 0;
        for ( Step st : stepsList ) {
            Date date = st.getDateTime();
            if (date==null)
                continue;
            int getDay = Integer.parseInt(returnDateFormat.format(date));
            if (thisDay == 0)
                thisDay = getDay;
            nextDay = getDay;
            if (thisDay == nextDay) {
                if (st.getMessageText().contains("- ПП - запущен")) {
                    if (st.getStatus().contains("ON")) {
                        tStart = date.getTime();
                        start = true;
                        stop = false;
                    } else {
                        tStop = date.getTime();
                        stop = true;
                        start = false;
                    }
                    if (!stop&&start)
                        workTime += tStop - tStart;


                    /*if (!start&&stop) reverse
                        workTime += tStop - tStart;
                */}
            } else {
                tStop=date.getTime();
                excelLines.add(new excelLine(thisDay,workTime));
                System.out.println("new day");
                thisDay=nextDay;
                workTime = 0;
            }

        }
    }

}
