package sample;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static File file;
    static ParseTime parseTime;

    public static void main(String[] args) throws FileNotFoundException {
        file = new File("C:\\Users\\Gleb\\IdeaProjects\\nttLogAnalyze\\out\\production\\nttLogAnalyze", "AlmControl.csv");


//        System.out.println(file.isFile());
        String[] array = new String[8];
        int tokenizerCounter = 0;


        //todo write only SS status and errors herna

        ArrayList<Step> stepList = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        String val;
        String key;
        StringBuilder stringBuilder = new StringBuilder();

        try (Reader fileReader = new InputStreamReader(new FileInputStream(file), "Unicode")) {
            char c;
            String s;

            for ( int i = 0;i < file.length();i++ ) {
                c = (char) fileReader.read();
                //System.out.print(c);

                if (c != '\n') {
                    stringBuilder.append(c);
                } else {
                    s = stringBuilder.toString();
                    StringTokenizer st = new StringTokenizer(s, ";", true);
                    //System.out.println("tokenizer "+st.countTokens());
                    while (st.hasMoreTokens()) {
                        //System.out.println("tcounter "+tokenizerCounter++);
                        val = st.nextToken();
                        if (st.hasMoreTokens())
                            key = st.nextToken();//todo has more tokenizer
                        else
                            key = "null";//todo has more tokenizer
                        if ((!val.contains(";"))&&key.contains(";") | key.contains("null"))
                            list.add(val);
                        else if (val.contains(";")&&key.contains(";") | key.contains("null")) {

                            list.add("null");
                            list.add("null");
                        }
                    }
                    tokenizerCounter = 0;
                    stepList.add(new Step(list));
                    list.clear();
                    stringBuilder.delete(0, stringBuilder.length());

                }
            }
            //System.out.println(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a dd/MM/yy"); //parse
//        SimpleDateFormat returnDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        SimpleDateFormat returnDateFormat = new SimpleDateFormat("D");//return year day

        parseTime = new ParseTime(simpleDateFormat);


        for ( Step s : stepList ) {
            String st = s.getTime() + " " + s.getDate();
            s.setDateTime(parseTime.getDate(st));
          System.out.println(s.getTime()+" "+s.getDateTime());
//            if (s.getDateTime() != null)
//                System.out.println(returnDateFormat.format(s.getDateTime()));
//            else System.out.println("null");
        }

       StepAnalyze stepAnalyze = new StepAnalyze(stepList);
        stepAnalyze.analyze();

        for ( excelLine el : stepAnalyze.getExcelLines() ) {
            System.out.println(el);
        }
    }

}
