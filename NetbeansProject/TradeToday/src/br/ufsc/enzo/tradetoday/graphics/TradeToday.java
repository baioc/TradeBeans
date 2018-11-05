/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.enzo.tradetoday.graphics;

import br.ufsc.enzo.tradetoday.config.ConfigHandler;
import br.ufsc.enzo.tradetoday.config.ListHandler;
import java.awt.event.ItemEvent;

/**
 *
 * @author Enzo Coelho Albornoz
 */
public class TradeToday extends javax.swing.JFrame {

    /**
     * Creates new form TradeTodat
     */
    public TradeToday() {
        initComponents();
        /* Puts this object invisible in init */
        menuPanel.setVisible(false);
        menuPanel.setEnabled(false);
        /*=====================================*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        leftPanel = new javax.swing.JPanel();
        alertButton = new javax.swing.JToggleButton();
        listStocks = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        typeMenu = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        menuButton = new javax.swing.JButton();
        analyzePanel1 = new br.ufsc.enzo.tradetoday.graphics.AnalyzePanel();
        stockInfoPanel1 = new br.ufsc.enzo.tradetoday.graphics.StockInfoPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trade Today");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setName("mainFrame"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(900, 600));

        mainPanel.setBackground(new java.awt.Color(40, 40, 40));
        mainPanel.setMinimumSize(new java.awt.Dimension(900, 600));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuPanel.setBackground(new java.awt.Color(76, 76, 76));
        menuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPanelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPanelMouseEntered(evt);
            }
        });

        jButton1.setText("Opções");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button1MouseEntered(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openConfigMenu(evt);
            }
        });

        jButton2.setText("Sair");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button2MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainPanel.add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 50, 100, -1));

        leftPanel.setBackground(new java.awt.Color(50, 50, 50));
        leftPanel.setPreferredSize(new java.awt.Dimension(150, 600));

        alertButton.setBackground(new java.awt.Color(204, 0, 0));
        alertButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/ufsc/enzo/tradetoday/res/AlertButtonUnlight.png"))); // NOI18N
        alertButton.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        alertButton.setBorderPainted(false);
        alertButton.setFocusPainted(false);
        alertButton.setMaximumSize(new java.awt.Dimension(150, 90));
        alertButton.setMinimumSize(new java.awt.Dimension(150, 90));
        alertButton.setPreferredSize(new java.awt.Dimension(150, 90));
        alertButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/ufsc/enzo/tradetoday/res/AlertButtonLight.png"))); // NOI18N
        alertButton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/ufsc/enzo/tradetoday/res/AlertButtonBackLight.png"))); // NOI18N
        alertButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/ufsc/enzo/tradetoday/res/AlertButtonBackUnlight.png"))); // NOI18N
        alertButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                alertButtonStateChanged(evt);
            }
        });

        listStocks.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jList1.setModel(new javax.swing.DefaultComboBoxModel<>(ListHandler.getSymbols()));
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        listStocks.setViewportView(jList1);

        typeMenu.setText(ListHandler.TYPE_DEFAULT);
        typeMenu.setBorderPainted(false);
        typeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(alertButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(listStocks, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(alertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typeMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listStocks, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 49, Short.MAX_VALUE))
        );

        mainPanel.add(leftPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        topPanel.setBackground(new java.awt.Color(50, 50, 50));
        topPanel.setPreferredSize(new java.awt.Dimension(750, 60));

        menuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/ufsc/enzo/tradetoday/res/MenuButtonUnlight.png"))); // NOI18N
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/ufsc/enzo/tradetoday/res/MenuButtonLighted.png"))); // NOI18N
        menuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuButtonMouseEntered(evt);
            }
        });
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap(656, Short.MAX_VALUE)
                .addComponent(menuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(menuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(558, 558, 558))
        );

        mainPanel.add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 750, -1));

        analyzePanel1.setBackground(new java.awt.Color(242, 42, 42));
        analyzePanel1.setEnabled(false);

        javax.swing.GroupLayout analyzePanel1Layout = new javax.swing.GroupLayout(analyzePanel1);
        analyzePanel1.setLayout(analyzePanel1Layout);
        analyzePanel1Layout.setHorizontalGroup(
            analyzePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        analyzePanel1Layout.setVerticalGroup(
            analyzePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        mainPanel.add(analyzePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        stockInfoPanel1.setBackground(new java.awt.Color(42, 42, 242));
        stockInfoPanel1.setEnabled(false);

        javax.swing.GroupLayout stockInfoPanel1Layout = new javax.swing.GroupLayout(stockInfoPanel1);
        stockInfoPanel1.setLayout(stockInfoPanel1Layout);
        stockInfoPanel1Layout.setHorizontalGroup(
            stockInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        stockInfoPanel1Layout.setVerticalGroup(
            stockInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        mainPanel.add(stockInfoPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(900, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanelMouseEntered
        openMenu(evt);    
    }//GEN-LAST:event_menuPanelMouseEntered

    private void menuPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPanelMouseExited
        menuPanel.setVisible(false);
        menuPanel.setEnabled(false);
        if(!inMenuBtn){
            menuOpened = false;
        }
    }//GEN-LAST:event_menuPanelMouseExited

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        openMenu(null);
    }//GEN-LAST:event_menuButtonActionPerformed

    private void menuButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuButtonMouseExited
        closeMenu(evt);
    }//GEN-LAST:event_menuButtonMouseExited

    private void menuButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuButtonMouseEntered
        if(menuOpened){
            openMenu(evt);
        }
    }//GEN-LAST:event_menuButtonMouseEntered

    private void typeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeMenuActionPerformed
        // TODO add your handling code here:
        ListHandler.changeSelectedType();
        typeMenu.setText(ListHandler.getSelectedType());
        updateSymbolsList();
    }//GEN-LAST:event_typeMenuActionPerformed

    private void updateSymbolsList(){
        jList1.setModel(new javax.swing.DefaultComboBoxModel<>(ListHandler.getSymbols()));
    }
    
    private void button1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseEntered
        // TODO add your handling code here:
        openMenu(evt);
    }//GEN-LAST:event_button1MouseEntered

    private void button2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button2MouseEntered
        // TODO add your handling code here:
        openMenu(evt);
    }//GEN-LAST:event_button2MouseEntered

    private void openConfigMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openConfigMenu
        // TODO add your handling code here:
        if(cfgMenu == null){
            cfgMenu = new ConfigMenu();
            cfgMenu.setVisible(true);
        }
        if(!cfgMenu.isOpened()){
            cfgMenu = new ConfigMenu();
            cfgMenu.setVisible(true);
        }
        
        closeMenu(null);
    }//GEN-LAST:event_openConfigMenu

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        // :: TODO :: CALLS GRAPHICAL MENU UPDATE
        alertButton.setSelected(false);
        
    }//GEN-LAST:event_jList1ValueChanged

    private void alertButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_alertButtonStateChanged
        // TODO add your handling code here:
        // TODO OPEN ANALIZE PANEL
        if(alertButton.isSelected()){
            analyzePanel1.setVisible(true);
            analyzePanel1.setEnabled(true);
            //analyzePanel1.startAnalysis();
        }else{
            analyzePanel1.setVisible(false);
            analyzePanel1.setEnabled(false);
        }
    }//GEN-LAST:event_alertButtonStateChanged
    
    private void openMenu(java.awt.event.MouseEvent evt) {
        menuPanel.setVisible(true);
        menuPanel.setEnabled(true);
        menuOpened = true;
    }
    
    private void closeMenu(java.awt.event.MouseEvent evt) {
        menuPanel.setVisible(false);
        menuPanel.setEnabled(false);
        menuOpened = false;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TradeToday.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TradeToday.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TradeToday.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TradeToday.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TradeToday().setVisible(true);
                ConfigHandler.getConfig();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton alertButton;
    private br.ufsc.enzo.tradetoday.graphics.AnalyzePanel analyzePanel1;
    private boolean cfgMenuOpened = false;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JScrollPane listStocks;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton menuButton;
    /* Defines custom variable for menu*/
    private boolean menuOpened = false;
    private boolean inMenuBtn = false;
    private boolean inMenuPnl = false;
    /*==================================*/
    private javax.swing.JPanel menuPanel;
    private br.ufsc.enzo.tradetoday.graphics.StockInfoPanel stockInfoPanel1;
    private javax.swing.JPanel topPanel;
    private javax.swing.JButton typeMenu;
    // End of variables declaration//GEN-END:variables

    //Custom Manual Variables
    //private String menuType = ConfigHandler.getConfig().getMenuType();
    private ConfigMenu cfgMenu = null;
    
    //Custom Manual Methods
    //private void changeType()
}
