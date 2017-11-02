package com.damien.interviewing;

import org.junit.*;
import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by Damien on 11/2/2017.
 */
public class tests {
    String testFile = "test.csv";
    ArrayList<String> Strings = new ArrayList<String>();
    @Test
    public void testGetCsvFileBuffer(){
        BufferedReader br = HelperMethods.getCsvFileBuffer(testFile);
        assert(br != null);
    }

    @Test
    public void testReadCsvToStrings(){
        BufferedReader br = HelperMethods.getCsvFileBuffer(testFile);
        Strings = HelperMethods.readCsvToStrings(Strings, br);
        assert(!Strings.isEmpty());
    }

    @Test
    public void testSetHeaders(){
        Strings.add("This,Is,A,Test,To,See,That,We,Get,Headers,Properly");
        Strings.add("This,Is,Making,Sure,We,Dont,Get,The,Wrong,Line");
        String[] headers = HelperMethods.setHeaders(Strings);
        String[] correctHeaders = Strings.get(0).split(",");

        boolean equal = true;
        if(headers.length == correctHeaders.length){
            for(int i=0; i< headers.length; i++){
                if(!headers[i].equals(correctHeaders[i])){
                    equal = false;
                }
            }
        }
        else{
            equal = false;
        }
        assert(equal);
}

}
