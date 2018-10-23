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
public class Config {
    /*/ Config Enumerations
    public enum refreshRates {
        Daily,Weekly,Monthly
    }
    
    public enum rankingAlgorithms {
        movingAverage,BollingerBand
    }
    */
    
    // Headers on CSV File | Configurations
    private String refreshRate;
    private String rankingAlorithm;
    private String customKey;
    
    
    Config() {
        //Only for instancy on ConfigHandler
    }
    
    Config(String refreshRate,String rankingAlorithm,String customKey){
        this.refreshRate = refreshRate;
        this.rankingAlorithm = rankingAlorithm;
        this.customKey = customKey;
    }
    
    void update(/* TODO : ADD HEADERS*/) {
        
    }
    
    public String getRefreshRate() {
        return refreshRate;
    }
    
    public String rankingAlorithm() {
        return rankingAlorithm;
    }
    
    public String customKey() {
        return customKey;
    }
}
