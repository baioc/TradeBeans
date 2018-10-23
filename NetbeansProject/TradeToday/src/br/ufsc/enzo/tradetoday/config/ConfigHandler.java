/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.enzo.tradetoday.config;

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
        File cfgFile = 
    }
}
