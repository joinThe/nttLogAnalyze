package sample;

import java.util.ArrayList;

public class CrashMessage {
    ArrayList<String> arrayList;

    public CrashMessage(ArrayList<String> crashMessageList) {
        this.arrayList = arrayList;
    }

    public CrashMessage() {
        this.arrayList = arrayList;
    }


    public void addMessage(String message){
        arrayList.add(message);
    }

}
