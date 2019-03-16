package sample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StepAnalyze {

    int errors=0;

    private long tStart;
    private long tStop;
    private long tCrash;

    private int thisDay = 0;
    private int nextDay = 0;

    private boolean start;
    private boolean stop;
    private boolean crash;

    long workTime = 0;
    long repairTime = 0;
    int crashCounter = 0;

    public String equipmentMessage = "- ПП - запущен";
    private String startMessage = "ON";
    private String stopMessage = "OFF";
    private String crash1 = "- сработа";
    private String crash2 = "- Сработа";

    private String stepMessage;
    private String stepStatus;


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

        for ( Step st : stepsList ) {
            Date date = st.getDateTime();
            if (date != null) {
                int getDay = Integer.parseInt(returnDateFormat.format(date));
                stepMessage = st.getMessageText();
                stepStatus = st.getStatus();
                if (thisDay == 0)
                    thisDay = getDay;
                nextDay = getDay;
                if (thisDay == nextDay) {
                    if (isStartMessage()) {
                        tStart = date.getTime();
                        start = true;
                        stop = false;
                        if (crash)
                            repairTime += tStart - tCrash;
                        crash = false;
                    } else if (isStopMessage()) {
                        tStop = date.getTime();
                        stop = true;
                        start = false;
                        if (!start&&stop)
                            workTime += tStop - tStart;
                    } else if (isCrashMessage()) {
                        tCrash = date.getTime();
                        crash = true;
                        crashCounter++;
                    }
                } else {
                    if (isStartMessage()) {
                        tStart = date.getTime();
                        start = true;
                        stop = false;
                        excelLines.add(new excelLine(thisDay, workTime, crashCounter, repairTime, date));
                        thisDay = nextDay;
                        resetCounters();
                        continue;
                    }
                    if (start) {
                        tStop = beginThisDay(date).getTime();
                        workTime += tStop - tStart;
                        tStart = tStop;
                    }
                    crash = false;
                    excelLines.add(new excelLine(thisDay, workTime, crashCounter, repairTime, date));
                    thisDay = nextDay;
                    resetCounters();
                    if (isStopMessage()) {
                        tStop = date.getTime();
                        workTime += tStop - tStart;

                    }

                }
            }

            // System.out.println(crashCounter);
        }
        System.out.println("Crash "+errors);
    }

    private Date beginThisDay(Date date) throws ParseException {
        SimpleDateFormat ret = new SimpleDateFormat("dd/MM/yyyy");
        return ret.parse(ret.format(date));
    }

    private boolean isCrashMessage() {
        if (stepMessage.contains(crash1)||stepMessage.contains(crash2)){

            errors++;
            if (stepStatus.contains("+")){
                return true;
            }
        }
        return false;
    }

    private boolean isStartMessage() {
        if (stepMessage.contains(equipmentMessage))
            if (stepStatus.contains(startMessage))
                return true;
        return false;
    }

    private boolean isStopMessage() {
        if (stepMessage.contains(equipmentMessage))
            if (stepStatus.contains(stopMessage))
                return true;
        return false;
    }

    private void resetCounters() {
        workTime = 0;
        repairTime = 0;
        crashCounter = 0;
    }

}
