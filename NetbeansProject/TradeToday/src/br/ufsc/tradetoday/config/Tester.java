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
public class Tester {
    public static void main(String[] args) {
        Config c = ConfigHandler.getConfig();
        System.out.printf("Taxa de Atualização : %s\n"
                + "Algoritmo de Ranking : %s\n"
                + "Key Customizada : %s",
                c.getRefreshRate(),
                c.getRankingAlgorithm(),
                c.getCustomKey());
    }
}
