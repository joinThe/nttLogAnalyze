package sample;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
//        public static File file = new File("C:\\Users\\Gleb\\IdeaProjects\\nttLogAnalyze", "AlmControlTest.csv");
//    private static File file = new File("C:\\Users\\Gleb\\IdeaProjects\\nttLogAnalyze", "AlmControl.csv");
//private static File file = new File("C:\\Users\\Gleb\\IdeaProjects\\nttLogAnalyze", "AlmControlForYear.csv");
private static File file = new File("C:\\Users\\Gleb\\IdeaProjects\\nttLogAnalyze", "AlmControlForYear10.csv");
//    private static File file = new File("C:\\Users\\Gleb\\IdeaProjects\\nttLogAnalyze", "AlmControlForYearT.csv");

    private static ArrayList crashList;

    public static void main(String[] args) throws ParseException {

        if (file.isFile()) {
            ArrayList<Step> stepArrayList;

            stepArrayList = new ParseFile(file, true).parse();
            ParseTime.parseDateTime(stepArrayList);
            System.out.println("Parse line: " + stepArrayList.size());

            StepAnalyze stepAnalyze = new StepAnalyze(stepArrayList);
            stepAnalyze.equipmentMessage="- пускатель включен";
            stepAnalyze.analyze();

            for ( excelLine el : stepAnalyze.getExcelLines() ) {
                System.out.println(el);
            }

        }

    }

    public void craetedCrashList() {
        String crash1 = "- сработал датчик";
        String crash2;
        String crash3;
        String crash4;
    }

}
