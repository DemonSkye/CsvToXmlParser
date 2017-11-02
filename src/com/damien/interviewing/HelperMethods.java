package com.damien.interviewing;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Damien on 11/2/2017.
 */
public class HelperMethods {
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

    public static ArrayList<String> readCsvToStrings (ArrayList<String> stringsFromCsv, BufferedReader buff){
        String line = null;
        try{
            while ((line = buff.readLine()) != null) {
                stringsFromCsv.add(line);
            }
        }catch (java.io.IOException ioe){ System.err.println("IO Exception while trying to parse buffered reader to strings: "); ioe.printStackTrace(); }
        return stringsFromCsv;
    }

    public static String[] setHeaders(ArrayList <String> stringsFromCsv){
        String[] columnHeaders = stringsFromCsv.get(0).split(",");
        return columnHeaders;
    }

    public static void writeXmlFile(String[] headers, ArrayList <String> stringsFromCsv){
        try {
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("xmlOutput.xml"));
            buffWrite.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
            for(String s:stringsFromCsv){
                buffWrite.write("<person>\r\n");
                String fields[] = s.split(",");
                for(int i=0; i<fields.length; i++){
                    buffWrite.write("\t<" + headers[i] +">"+ fields[i] + "</" + headers[i] +">\n");
                }
                buffWrite.write("</person>\n");
            }
            buffWrite.close();
        }catch (IOException ioe){ System.err.println("Error while writing to xml file in writeXmlFile: "); ioe.printStackTrace(); }
    }
}
