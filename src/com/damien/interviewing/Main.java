package com.damien.interviewing;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by Damien on 11/2/2017.
 */
public class Main {
    public static void main (String Args[]){
        BufferedReader bufferedCsvFile = HelperMethods.getCsvFileBuffer("test.csv");
        ArrayList<String> csvFileStrings = new ArrayList();
        HelperMethods.readCsvToStrings(csvFileStrings, bufferedCsvFile);
        /*  Debug for checking contents of ArrayList
            for(String s : csvFileStrings){
            System.out.println(s);
        }*/
        String[] columnHeaders = HelperMethods.setHeaders(csvFileStrings);
        csvFileStrings.remove(0);
        HelperMethods.writeXmlFile(columnHeaders, csvFileStrings);
    }
}
