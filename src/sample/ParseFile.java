package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ParseFile {

    File file;
    ArrayList<Step> arrayListForReverse;
    boolean reverse;
    int tokenizerCounter = 0;

    public ParseFile(File file, boolean reverseLog) {
        this.file = file;
        reverse = reverseLog;
        if (reverseLog)
            arrayListForReverse = new ArrayList<>();
    }

    public ArrayList parse() {

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


        if (reverse){
            for ( int i = stepList.size()-1;i >=0 ;i-- ) {
                arrayListForReverse.add(stepList.get(i));
            }
            return arrayListForReverse;
        }
        else
            return stepList;
    }

}
