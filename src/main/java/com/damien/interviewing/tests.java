package com.damien.interviewing;

import org.junit.*;

import java.io.*;
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
                    break;
                }
            }
        }
        else{
            equal = false;
        }
        assert(equal);
    }
    @Test
    public void testWriteToFile(){
        Strings.add("First_Name,Last_Name");
        Strings.add("Damien,Bell");
        Strings.add("Test,McTest");
        String[] Headers = HelperMethods.setHeaders(Strings);
        Strings.remove(0);
        HelperMethods.writeXmlFile(Headers,Strings,"xmlTestOutput.xml");

        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("xmlTestOutput.xml"));
        }catch(FileNotFoundException fnf) {
            System.err.println("GetCsv Method could not find file at " + System.getProperty("user.dir") + "\\xmlTestOutput.xml");
            fnf.printStackTrace();
        }catch(Exception e) { System.err.println("Unknown error occurred in GetCsvFile: "); e.printStackTrace(); }

        assert(br != null);
    }

}
