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
    public static final String DEFAULT_RANKING_ALGORITHM = AlphaVantageAPI.INDICATOR_OSC_BOLLINGER;
    public static final String DEFAULT_CUSTOM_KEY = "demo";
    
    
    // Headers on CSV File | Configurations
    private String refreshRate = DEFAULT_REFRESH_RATE;
    private String rankingAlgorithm = DEFAULT_RANKING_ALGORITHM;
    private String customKey = DEFAULT_CUSTOM_KEY;
    
    
    Config() {} // only for instancing on ConfigHandler
    
    Config(String refreshRate, String rankingAlgorithm, String customKey) {
        update(refreshRate, rankingAlgorithm, customKey);
    }
    
    void update(String refreshRate, String rankingAlgorithm, String customKey) {
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
       String[] r = {
    	   AlphaVantageAPI.TIME_DAILY,
    	   AlphaVantageAPI.TIME_WEEKLY,
    	   AlphaVantageAPI.TIME_MONTHLY,
       };
       return r;
    }
    
    public static String[] getRankingAlgorithms() {
        String[] r = {
        	AlphaVantageAPI.INDICATOR_OSC_BOLLINGER,
        	AlphaVantageAPI.INDICATOR_OSC_ULTIMATE,
        	AlphaVantageAPI.INDICATOR_OSC_STOCHASTIC,
        };
        return r;
    }
     
}
