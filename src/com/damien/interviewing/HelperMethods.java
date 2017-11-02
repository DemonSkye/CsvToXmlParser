package com.damien.interviewing;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Damien on 11/2/2017.
 */
public class HelperMethods {

    //Attempts to initialize a buffered reader with the contents of the file at the working directory\fileName then return it to main
    public static BufferedReader getCsvFileBuffer(String fileName){
        File csvFile = new File(fileName);
        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new FileReader(csvFile));
        }catch(FileNotFoundException fnf) {
            System.err.println("GetCsv Method could not find file at " + System.getProperty("user.dir") + "\\" + fileName);
            fnf.printStackTrace();
        }catch(Exception e) { System.err.println("Unknown error occurred in GetCsvFile: "); e.printStackTrace(); }
        return buff;
    }

    //Create an arrayList of strings from the csv file
    public static ArrayList<String> readCsvToStrings (ArrayList<String> stringsFromCsv, BufferedReader buff){
        String line = null;
        try{
            while ((line = buff.readLine()) != null) {
                stringsFromCsv.add(line);
            }
        }catch (java.io.IOException ioe){ System.err.println("IO Exception while trying to parse buffered reader to strings: "); ioe.printStackTrace(); }
        return stringsFromCsv;
    }

    //Explode headers into an array
    public static String[] setHeaders(ArrayList <String> stringsFromCsv){
        String[] columnHeaders = stringsFromCsv.get(0).split(",");
        return columnHeaders;
    }

    //Write the strings to csv
    //This makes some assumptions that each line represents a "person", this could be changed via command-line args in the future.
    public static void writeXmlFile(String[] headers, ArrayList <String> stringsFromCsv){
        try {
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("xmlOutput.xml"));
            buffWrite.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
            buffWrite.write("<people>\r\n");
            //For each string in the csv file
            for(String s:stringsFromCsv){
                buffWrite.write("\t<person>\r\n");

                //Split the line into an array of strings
                String fields[] = s.split(",");

                //For each item in that array of strings
                for(int i=0; i<fields.length; i++){
                    //Write the corresponding header to the file, as well as the value from the array 'fields'
                    buffWrite.write("\t\t<" + headers[i] +">"+ fields[i] + "</" + headers[i] +">\n");
                }
                buffWrite.write("\t</person>\n");
            }
            buffWrite.write("</people>");
            buffWrite.close();
        }catch (IOException ioe){ System.err.println("Error while writing to xml file in writeXmlFile: "); ioe.printStackTrace(); }
    }
}
