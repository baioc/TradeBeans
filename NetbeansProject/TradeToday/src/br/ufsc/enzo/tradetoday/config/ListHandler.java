/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.enzo.tradetoday.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Enzo Coelho Albornoz
 */
public class ListHandler {
    public static String DEFAULT_TYPE = "Cripto";
    public static String TYPE_CRIPTO = "Cripto";
    public static String TYPE_STOCK = "Stock";
    private static String[][] list = null;
    private static String selectedType = DEFAULT_TYPE;
    
    private static java.io.BufferedReader a;
    
    public static void updateList() {
        try {
            String path = "/home/100000000901519/";
            String name = "stocks.csv";
            java.io.Reader reader = java.nio.file.Files.newBufferedReader(
                    java.nio.file.Paths.get(path,name));
            CSVParser p = new CSVParser(reader,
                        CSVFormat.DEFAULT
                                .withHeader("symbol",
                                            "type",
                                            "name",
                                            "desc")
                                .withIgnoreHeaderCase()
                                .withTrim());
            ArrayList<String[]>aList = new ArrayList<String[]>();
            for(CSVRecord r : p){
                String[] line = new String[4];
                line[0] = r.get("symbol");
                line[1] = r.get("type");
                line[2] = r.get("name");
                line[3] = r.get("desc");
                aList.add(line);
            }
            list = new String[aList.size()][4];
            for(int z = 0;z < aList.size();z++){
                list[z] = aList.get(z);
            }
            for(int k = 1;k < list.length;k++){
                System.out.printf("Symbol: %s | Type : %s | Name : %s | "
                        + "Desc: %s \n",list[k][0],list[k][1],
                        list[k][2],list[k][3]);
            }
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    
    private static String[] getSymbolsCripto(){
        if(list == null){
            updateList();
        }
        ArrayList<String> symbols = new ArrayList<String>();
        for(int i = 0;i < list.length;i++){
            if(list[i][1].equals(TYPE_CRIPTO)){
                symbols.add(list[i][0]);
            }
        }
        return symbols.toArray(list[0]);
    }
    
    public static String[] getSymbols(){
        if(selectedType.equals(TYPE_CRIPTO)){
            return getSymbolsCripto();
        }
        return getSymbolsStock();
    }
    
    private static String[] getSymbolsStock(){
        if(list == null){
            updateList();
        }
        ArrayList<String> symbols = new ArrayList<String>();
        for(int i = 0;i < list.length;i++){
            if(list[i][1].equals(TYPE_STOCK)){
                symbols.add(list[i][0]);
                
            }
        }
        String[] r = new String[symbols.size()];
        for(int i = 0;i < r.length;i++){
            r[i] = symbols.get(i);
        }
        return r;
    }
    
    public static void main(String [] args) throws FileNotFoundException{
        String[] c = getSymbolsCripto();
        String[] s = getSymbolsStock();
        for(String l : c){
            System.out.println(l);
            System.out.println("a");
            
        }
        for(String l : s){
            System.out.println(l);
        }
    }
}
