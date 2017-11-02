package com.damien.interviewing;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by Damien on 11/2/2017.
 */
public class Main {

    //Overall Thoughts -- XML is inherently 3-dimensional in nature, with elements being able to nest, where csv seems to be limited to 2 dimensions
    //I am not certain of how to represent xml nesting in csv data, though that's something I could also research for future revisions
    public static void main (String Args[]){
        //This is hard coded, in a future version I would consider stripping this from a command-line arg.
        BufferedReader bufferedCsvFile = HelperMethods.getCsvFileBuffer("test.csv");
        ArrayList<String> csvFileStrings = new ArrayList<String>();
        HelperMethods.readCsvToStrings(csvFileStrings, bufferedCsvFile);
        /*  Debug for checking contents of ArrayList
            for(String s : csvFileStrings){
            System.out.println(s);
        }*/
        String[] columnHeaders = HelperMethods.setHeaders(csvFileStrings);
        csvFileStrings.remove(0); // Remove headers from the csvStrings Arraylist
        HelperMethods.writeXmlFile(columnHeaders, csvFileStrings);
    }
}
