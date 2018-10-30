/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.enzo.tradetoday.config;

import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Enzo Coelho Albornoz
 */
public class ListHandler {
    
    private static String[][] list;
    
    public void updateList() {
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
                System.out.printf("Symbol: %s | Type : %s | Name : %s | Desc: %s");
            }
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    
    
}
