/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.tradetoday.config;

/**
 *
 * @author 18100527
 */
public class OSUtils {
    
    private static String realOS = null;
    
    
    public static String getOsRealName() {
        if (realOS == null) realOS = System.getProperty("os.name");
        return realOS;
    }
    
    public static boolean isWindows() {
        return getOsRealName().startsWith("Windows");
    }
    
    public static boolean isUnix() {
        return getOsRealName().startsWith("Linux");
    }
    
    public static boolean isMac() {
        return getOsRealName().startsWith("Mac");
    }
    
    public static String getOsName() {
        if (isWindows()) {
            return "Windows";
        } else if (isUnix()) {
            return "Linux";
        } else if (isMac()) {
            return "Mac";
        }
        return "Others";
    }
}
