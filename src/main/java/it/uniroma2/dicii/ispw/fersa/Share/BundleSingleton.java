package it.uniroma2.dicii.ispw.fersa.Share;

import java.util.*;

public class BundleSingleton {
    private static ResourceBundle myBundle;

    public static ResourceBundle getMyBundle(){
        if (myBundle == null) {
            myBundle = ResourceBundle.getBundle("Messages", Locale.getDefault());
        }
        return myBundle;
    }

    public static ResourceBundle getMyEnglishBundle(){
        myBundle = ResourceBundle.getBundle("Messages", Locale.forLanguageTag("en_US"));
        return myBundle;
    }
    public static ResourceBundle getMyItalianBundle(){
        myBundle = ResourceBundle.getBundle("Messages", Locale.forLanguageTag("it-IT"));
        return myBundle;
    }

    public static String getKey(String value){
        Iterator<String> keyList = myBundle.keySet().iterator();
        String key = null;
        while (keyList.hasNext()){
            key = keyList.next();
            String val = myBundle.getString(key);
            if (val == value){
                break;
            }
        }
        return key;
    }
}
