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
    // Static default values
    public static final String DEFAULT_REFRESH_RATE = "Daily";
    public static final String DEFAULT_RANKING_ALGORITHM = "MovingAverage";
    public static final String DEFAULT_CUSTOM_KEY = "";
    
    // Headers on CSV File | Configurations
    private String refreshRate;
    private String rankingAlgorithm;
    private String customKey;
    
    
    Config() {
        //Only for instancy on ConfigHandler
    }
    
    Config(String refreshRate,String rankingAlgorithm,String customKey){
        this.refreshRate = refreshRate;
        this.rankingAlgorithm = rankingAlgorithm;
        this.customKey = customKey;
    }
    
    void update(String refreshRate,String rankingAlgorithm,String customKey) {
        this.refreshRate = refreshRate;
        this.rankingAlgorithm = rankingAlgorithm;
        this.customKey = customKey;
    }
    
    public String getRefreshRate() {
        return refreshRate;
    }
    
    public String getRankingAlgorithm() {
        return rankingAlgorithm;
    }
    
    public String getCustomKey() {
        return customKey;
    }
}
