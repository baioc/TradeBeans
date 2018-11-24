/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.tradetoday.config;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;

/**
    Configuration Handler for TradeToday
Inteface :
    @param  setRefreshRate(String refreshRate)
    @param  setRankingAlgorithm(String rankingAlgorithm)
    @param  setCustomKey(String customKey)
    @param  getConfig()
        
*/
public class ConfigHandler {
	
    private static final String saveFileName = "TradeToday/config.csv";
    
    private static Config cfg = new Config();
    
    private static String getDefaultDir() {
        if (OSUtils.isWindows()) {
            return System.getenv("APPDATA");
        } else if (OSUtils.isUnix()) {
            return System.getProperty("user.home");
        }
        return System.getProperty("user.dir");
    }
    
    private static java.io.File getFilePath() {
        java.io.File cfgFile = new java.io.File(getDefaultDir(), saveFileName);
        return cfgFile;
    }
    
    static java.io.File getFileDirPath() {
        java.io.File cfgDir = new java.io.File(getDefaultDir(), "TradeToday");
        return cfgDir;
    }
    
    public static Config getConfig() {
        if (!getFilePath().exists()) {
            createConfigFile();
        }
        updateConfigFile();
        return cfg;
    }
    
    private static void createConfigFile() {
    	System.out.println("Creating config file.");
    	
        try {
            // Create the dir and file
            getFileDirPath().mkdirs();
            getFilePath().createNewFile();
            
            // Now write on it
            java.io.Writer writer = Files.newBufferedWriter(
                    Paths.get(getDefaultDir(),saveFileName));
            CSVPrinter p = new CSVPrinter(writer, CSVFormat.DEFAULT
                                .withHeader("refreshRate",
                                            "rankingAlgorithm",
                                            "customKey")
                                );
            p.printRecord(Config.DEFAULT_REFRESH_RATE,
                          Config.DEFAULT_RANKING_ALGORITHM,
                          Config.DEFAULT_CUSTOM_KEY);
            p.flush();
            p.close();
            
        } catch(IOException e) {
            System.err.println("Can't create file");
            System.err.println(e.getMessage());
        }
    }
    
    private static void updateConfigFile() {
        try {
            //Locate the file path and creates a reader for it
            java.io.Reader reader = Files.newBufferedReader(
                    Paths.get(getDefaultDir(),saveFileName));
            
            //Now parse that file to an CSV File
            CSVParser parser = new CSVParser(reader,
                    CSVFormat.DEFAULT
                            .withHeader("refreshRate",
                                        "rankingAlgorithm",
                                        "customKey")
                            .withIgnoreHeaderCase()
                            .withTrim());
            for(CSVRecord r : parser) {
                cfg.update(r.get("refreshRate"),
                           r.get("rankingAlgorithm"),
                           r.get("CustomKey"));
            }
            parser.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ConfigHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
    }
    
    private static void writeOnConfigFile(String refreshRate,String rankingAlgorithm,String customKey){
        try {
            java.io.Writer writer = Files.newBufferedWriter(
                    Paths.get(getDefaultDir(),saveFileName));
            CSVPrinter p = new CSVPrinter(writer, CSVFormat.DEFAULT
                                .withHeader("refreshRate",
                                            "rankingAlgorithm",
                                            "customKey")
                                );
            p.printRecord(refreshRate,rankingAlgorithm,customKey);
            p.flush();
            p.close();
            
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        
        updateConfigFile();
    }
    
    public static void setRefreshRate(String refreshRate) {
        writeOnConfigFile(refreshRate, cfg.getRankingAlgorithm(), cfg.getCustomKey());
    }
    
    public static void setRankingAlgorithm(String rankingAlgorithm) {
        writeOnConfigFile(cfg.getRefreshRate(), rankingAlgorithm, cfg.getCustomKey());
    }
    
    public static void setCustomKey(String customKey) {
        writeOnConfigFile(cfg.getRefreshRate(), cfg.getRankingAlgorithm(), customKey);
    }
    
}
