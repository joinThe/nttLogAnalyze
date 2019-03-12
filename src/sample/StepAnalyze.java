package sample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StepAnalyze {

    private long tStart;
    private long tStop;
    private long tCrash;

    private int thisDay = 0;
    private int nextDay = 0;

    private boolean start;
    private boolean stop;
    private boolean crash;

    private String equipmentMessage = "- ПП - запущен";
    private String startMessage = "ON";
    private String stopMessage;
    private String crash1 = "- сработал датчик";

    ArrayList<excelLine> excelLines;
    ArrayList<Step> stepsList;
   private SimpleDateFormat returnDateFormat;

    public ArrayList<excelLine> getExcelLines() {
        return excelLines;
    }

    public StepAnalyze(ArrayList<Step> stepsList) {
        this.excelLines = new ArrayList<>();
        returnDateFormat = new SimpleDateFormat("D");
        this.stepsList = stepsList;
    }

    public void analyze() throws ParseException {
        long workTime = 0;
        long repairTime = 0;
        int crashCounter = 0;
        for ( Step st : stepsList ) {
            Date date = st.getDateTime();
            String message = st.getMessageText();
            String status = st.getStatus();
            if (date == null)
                continue;
            int getDay = Integer.parseInt(returnDateFormat.format(date));
            if (thisDay == 0)
                thisDay = getDay;
            nextDay = getDay;
            if (thisDay == nextDay) {
                if (message.contains(equipmentMessage)) {
                    if (status.contains(startMessage)) {
                        tStart = date.getTime();
                        start = true;
                        stop = false;
                        if (crash)
                            repairTime += tStart - tCrash;
                        crash = false;
                    } else {
                        tStop = date.getTime();
                        stop = true;
                        start = false;
                    }

                    if (!start&&stop)//todo reverse
                        workTime += tStop - tStart;
                } else if (message.contains(crash1))
                    if (status.contains("+")) {
                        tCrash = date.getTime();
                        crash = true;
                        crashCounter++;
                    }
            } else {
                if (start) {
                    tStop = beginThisDay(date).getTime();
                    workTime += tStop - tStart;
                    tStart = beginThisDay(date).getTime();
                }
                crash = false;
                excelLines.add(new excelLine(thisDay, workTime, crashCounter, repairTime));
                thisDay = nextDay;
                workTime = 0;
                crashCounter = 0;
            }
        }

        System.out.println(crashCounter);
    }

    private Date beginThisDay(Date date) throws ParseException {
        SimpleDateFormat ret = new SimpleDateFormat("dd/MM/yyyy");
        return ret.parse(ret.format(date));
    }

    private boolean isCrashMessage(String s) {

        return true;
    }

}
