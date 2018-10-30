/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.enzo.tradetoday.config;

import java.io.IOException;
import java.util.ArrayList;
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
    
    public static void updateList() {
        try {
            java.io.Reader reader = null;
            CSVParser p = new CSVParser(reader,
                        CSVFormat.DEFAULT
                                .withHeader("symbol",
                                            "type",
                                            "name",
                                            "desc")
                                .withIgnoreHeaderCase()
                                .withTrim());
            list = new String[p.getRecords().size()][4];
            for(CSVRecord r : p){
                int i = (int) r.getRecordNumber();
                list[i][0] = r.get("symbol");
                list[i][1] = r.get("type");
                list[i][2] = r.get("name");
                list[i][3] = r.get("desc");
            }
            for(int i = 0;i < list.length;i++){
                System.out.printf("Symbol: %s | Type : %s | Name : %s | "
                        + "Desc: %s \n",list[i][0],list[i][1],
                        list[i][2],list[i][3]);
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
        return symbols.toArray(list[0]);
    }
    
    public static void main(String [] args){
        
    }
}
