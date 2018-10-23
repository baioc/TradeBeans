/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.enzo.tradetoday.config;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;

/**
 *
 * @author 18100527
 */
public class ConfigHandler {
    private static final String saveFileName = "TradeToday/config.csv";
    private static java.io.File saveFile = getFilePath();
    
    private static Config cfg = null;
    
    private static String getDefaultDir() {
        if(OSUtils.isWindows()){
            return System.getenv("APPDATA");
        }
        if(OSUtils.isUnix()) {
            return System.getProperty("user.home");
        }
        return System.getProperty("file.separator");
    }
    
    private static java.io.File getFilePath() {
        java.io.File cfgFile = new java.io.File(getDefaultDir(), saveFileName);
        return cfgFile;
    }
    
    public static Config getConfig() {
        if(!getFilePath().exists()) {
            createConfigFile();
        }else if(cfg == null){
            cfg = new Config();
        }
        updateConfigFile();
        return cfg;
    }
    
    private static void createConfigFile() {
        
    }
    
    private static void updateConfigFile() {
        try {
            //Locate the file path and creates a reader for it
            java.io.Reader reader = Files.newBufferedReader(
                    Paths.get(getDefaultDir(),saveFileName));
            //Now parse that file to an CSV File
            CSVParser parser = new CSVParser(reader,
                    CSVFormat.DEFAULT
                            .withHeader("blabla")
                            .withIgnoreHeaderCase()
                            .withTrim());
            for(CSVRecord records : parser) {
                cfg.update();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConfigHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
        
    }
    
    
}
