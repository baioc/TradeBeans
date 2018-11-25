/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.tradetoday.config;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import br.ufsc.tradetoday.backend.*;
import java.io.Writer;
import org.apache.commons.csv.CSVPrinter;

/**
 *
 * @author Enzo Coelho Albornoz
 */
public class ListHandler {
    public final static String TYPE_CRYPTO = "CRYPTO";
    public final static String TYPE_STOCK = "STOCK";
    public final static String TYPE_DEFAULT = TYPE_STOCK;
    
    private static String[][] list = null;
    private static String selectedType = TYPE_DEFAULT;
    private static String gdocURL = "spreadsheets/d/e/2PACX"
                                  + "-1vTthBZd6gPf6Ak8R0cXz"
                                  + "xvoB-_9SkMcEyNqmaCjPNT"
                                  + "CXLnc-rIz_e1VF55iGS7SB"
                                  + "1ECQUjmYDg2Mk8F/pub?ou"
                                  + "tput=csv";
    private static long lastUpdt = 0;
    
    public static void updateList() {
    	String path = ConfigHandler.getFileDirPath().toString();
        String name = "/stocks.csv";
        
        java.io.Reader reader = null;
        try {
            long temp = System.currentTimeMillis();
            long delta = temp - lastUpdt;
            lastUpdt = temp;
            
            if (!Files.exists(Paths.get(path,name)) || delta > 300000) {
                createList(Paths.get(path,name));
            }
            
            reader = java.nio.file.Files.newBufferedReader(java.nio.file.Paths.get(path,name));
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        try {
            CSVParser p = new CSVParser(reader,
                        CSVFormat.DEFAULT
                                .withHeader("symbol",
                                            "type",
                                            "name",
                                            "desc")
                                .withIgnoreHeaderCase()
                                .withTrim());
            
            ArrayList<String[]>aList = new ArrayList<String[]>();
            
            for(CSVRecord r : p) {
                String[] line = new String[4];
                line[0] = r.get("symbol");
                line[1] = r.get("type");
                line[2] = r.get("name");
                line[3] = r.get("desc");
                aList.add(line);
            }
            
            list = new String[aList.size()][4];
            for (int z = 0; z < aList.size(); z++) {
                list[z] = aList.get(z);
            }
            
            p.close();
            
        } catch(IOException e) {
        	e.printStackTrace();
        }
    }
    
    private static String[] getSymbolsCrypto() {
        if (list == null) {
            updateList();
        }
        
        List<String> symbols = new ArrayList<>();
        
        for (int i = 1; i < list.length; i++) {
            if (list[i][1].equals(TYPE_CRYPTO)) {
                symbols.add(list[i][0]);
            }
        }
        
        String[] r = new String[symbols.size()];
        for (int i = 0; i < r.length; i++) {
            r[i] = symbols.get(i);
        }
        
        return r;
    }
    
    public static String[] getSymbols() {
        if (selectedType.equals(TYPE_CRYPTO)) {
            return getSymbolsCrypto();
        }
        return getSymbolsStock();
    }
    
    private static String[] getSymbolsStock() {
        if (list == null) {
            updateList();
        }
        
        List<String> symbols = new ArrayList<>();
        for (int i = 1; i < list.length; i++) {
            if (list[i][1].equals(TYPE_STOCK)) {
                symbols.add(list[i][0]);
            }
        }
        
        String[] r = new String[symbols.size()];
        for (int i = 0;i < r.length;i++) {
            r[i] = symbols.get(i);
        }
        
        return r;
    }
    
    public static void changeSelectedType() {
        if (selectedType.equals(TYPE_CRYPTO)) {
            selectedType = TYPE_STOCK;
        } else {
            selectedType = TYPE_CRYPTO;
        }
    }
    
    public static String getSelectedType() {
        return selectedType;
    }
    
    private static void createList(java.nio.file.Path pathToWrite) {
        final GoogleDocsAPI gdoc = new GoogleDocsAPI();
        
        try {
        	new File(pathToWrite.getParent().toString()).mkdirs();	// creates directory if it doesn't yet exist
            Reader reader = gdoc.get(gdocURL);
            Writer wr = Files.newBufferedWriter(pathToWrite);
            CSVParser r = new CSVParser(reader, CSVFormat.DEFAULT);
            CSVPrinter printer = new CSVPrinter(wr, CSVFormat.DEFAULT);
            for (CSVRecord line : r) {
                printer.printRecord(line);
            }
            wr.flush();
            printer.close();
            r.close();
            reader.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getDescOf(String stockSymbol){
        for (int i = 0; i < list.length; i++) {
            if (list[i][0].equals(stockSymbol)) {
                return list[i][3];
            }
        }
        return null;
    }    
    
    public static String getNameOf(String stockSymbol){
        for (int i = 0; i < list.length; i++) {
            if (list[i][0].equals(stockSymbol)) {
                return list[i][2];
            }
        }
        return null;
    }   
    
    
    public static void main(String[] args) {
        String[] c = getSymbolsCrypto();
        System.out.print("\nOnCryptoSymbolsAcquired()");
        System.out.println("\n-----------");
        for (String l : c) {
            System.out.println(l);
            System.out.println("-----------");
        }
        
        String[] s = getSymbolsStock();
        System.out.print("\nOnStockSymbolsAcquired()");
        System.out.println("\n===========");
        for (String l : s) {
            System.out.println(l);
            System.out.println("===========");
        }
    }
}
