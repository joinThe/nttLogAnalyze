package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ParseFile {

    private File file;
    private ArrayList<Step> stepList;
    private ArrayList<Step> arrayListForReverse;
    private boolean reverse;
    private int tokenizerCounter = 0;
    private CharSequence tokenizerSymbol = ";";
    private int minLengthLine=55;
    private int litlLineCounter=0;

    public ParseFile(File file, boolean reverseLog) {
        this.file = file;
        reverse = reverseLog;
        stepList = new ArrayList<>();
        //stepList.ensureCapacity(11205+10);
        if (reverseLog)
            arrayListForReverse = new ArrayList<>();
    }

    public void setMinLengthLine(int minLengthLine) {
        this.minLengthLine = minLengthLine;
    }

    public ArrayList parse() {
        int counter = 0;
        ArrayList<String> list = new ArrayList<>();
        String val;
        String key;
        StringBuilder stringBuilder = new StringBuilder();

        try (Reader fileReader = new InputStreamReader(new FileInputStream(file), "Unicode")) {
            char c;
            String s;
            for ( int i = 0;i < file.length();i++ ) {
                c = (char) fileReader.read();
                if (c != '\n') {
                    stringBuilder.append(c);
                } else {
                    counter++;
                    s = stringBuilder.toString();
                    if (s.length()>minLengthLine) {
                        StringTokenizer st = new StringTokenizer(s, ";", true);
                        while (st.hasMoreTokens()) {
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
                            } else if (val.contains(tokenizerSymbol)&&!key.contains(tokenizerSymbol)) {
                                list.add("null");
                                list.add(key);

                                if (st.hasMoreTokens()) {
                                    st.nextToken();
                                }
                            }
                        }
                        if (list.size() == 8) {
                            stepList.add(new Step(list));
                        } else
                            //System.out.println("Error Parse line - "+counter);//todo zbs
                            tokenizerCounter = 0;
                        list.clear();
                        stringBuilder.delete(0, stringBuilder.length());
                    }else {
                        litlLineCounter++;
                    }
                }
            }
            //System.out.println(s);
        } catch (FileNotFoundException e) {
            System.out.println("File error " +  e.getMessage());
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("Error line/code: " + counter + ie.getMessage()            );
        } catch
        (IOException ie) {
            System.out.println(ie.getMessage());
        }

        if (reverse) {
            for ( int i = stepList.size() - 1;i >= 0;i-- ) {
                arrayListForReverse.add(stepList.get(i));
            }
            return arrayListForReverse;
        } else
            return stepList;
    }

}
