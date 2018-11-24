/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.tradetoday.config;

import br.ufsc.tradetoday.backend.AlphaVantageAPI;
/**
    Configuration Handler for TradeToday

Inteface :
    @param setRefreshRate(String refreshRate)
    @param setRankingAlgorithm(String rankingAlgorithm)
    @param setCustomKey(String customKey)
    @param getConfig()
        
*/
public class Config {
	
    // Static default values
    public static final String DEFAULT_REFRESH_RATE = AlphaVantageAPI.TIME_DAILY;
    public static final String DEFAULT_RANKING_ALGORITHM = AlphaVantageAPI.INDICATOR_AVERAGE_SIMPLE;
    public static final String DEFAULT_CUSTOM_KEY = "demo";
    public static final String REFRESH_DAILY = AlphaVantageAPI.TIME_DAILY;
    public static final String REFRESH_WEEKLY = AlphaVantageAPI.TIME_WEEKLY;
    public static final String REFRESH_MONTHLY = AlphaVantageAPI.TIME_MONTHLY;
    public static final String RANKING_AVERAGE = AlphaVantageAPI.INDICATOR_AVERAGE_SIMPLE;
    public static final String RANKING_BOLLINGER = AlphaVantageAPI.INDICATOR_OSC_BOLLINGER;
    
    
    // Headers on CSV File | Configurations
    private String refreshRate;
    private String rankingAlgorithm;
    private String customKey;
    
    
    Config() {} // only for instancing on ConfigHandler
    
    Config(String refreshRate,String rankingAlgorithm,String customKey) {
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
    
    public static String[] getRefreshTimes() {
       String[] r = {REFRESH_DAILY,REFRESH_MONTHLY,REFRESH_WEEKLY};
       return r;
    }
    
    public static String[] getRankingAlgorithms() {
        String[] r = {RANKING_AVERAGE,RANKING_BOLLINGER};
        return r;
    }
     
}
