package sample;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
//    public static File file = new File("C:\\Users\\Gleb\\IdeaProjects\\nttLogAnalyze", "AlmControlTestWorkTime.csv");
//    private static File file = new File("C:\\Users\\Gleb\\IdeaProjects\\nttLogAnalyze", "AlmControlTest.csv");
    private static File file = new File("C:\\Users\\Gleb\\IdeaProjects\\nttLogAnalyze", "AlmControl.csv");

    private static ArrayList crashList;

    public static void main(String[] args) throws ParseException {

        ArrayList<Step> stepArrayList;

//        ParseFile parseFile = new ParseFile(file, true);
//        stepArrayList = parseFile.parse();
        stepArrayList = new ParseFile(file, true).parse();

        ParseTime.parseDateTime(stepArrayList);


        StepAnalyze stepAnalyze = new StepAnalyze(stepArrayList);
        stepAnalyze.analyze();

        for ( excelLine el : stepAnalyze.getExcelLines() ) {
            System.out.println(el);
        }

        String st = "- сработал датчик схода ленты GS1";



    }

    public void craetedCrashList(){
        String crash1="- сработал датчик";
        String crash2;
        String crash3;
        String crash4;
    }

}
