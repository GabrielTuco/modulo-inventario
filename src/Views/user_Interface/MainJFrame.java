/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.user_Interface;

import java.awt.CardLayout;
import Views.userinterface.CVSadmin.ListaProductos;



/**
 *
 * @author Neha Ghate
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    public ListaProductos adminWorkArea;

        
    public MainJFrame() {
        initComponents();      
        userProcessContainer.removeAll();
        adminWorkArea =  new ListaProductos(userProcessContainer);
        userProcessContainer.add("adminWorkAreaJPanel",adminWorkArea);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        CVSAdminJButton = new javax.swing.JButton();
        pharmaAdminJButton = new javax.swing.JButton();
        userProcessContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setBackground(new java.awt.Color(0, 51, 51));

        jPanel1.setBackground(new java.awt.Color(7, 7, 32));

        CVSAdminJButton.setBackground(new java.awt.Color(24, 20, 52));
        CVSAdminJButton.setForeground(new java.awt.Color(255, 255, 255));
        CVSAdminJButton.setLabel("PRODUCTOS");
        CVSAdminJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CVSAdminJButtonActionPerformed(evt);
            }
        });

        pharmaAdminJButton.setBackground(new java.awt.Color(24, 20, 52));
        pharmaAdminJButton.setForeground(new java.awt.Color(255, 255, 255));
        pharmaAdminJButton.setText("Pharma admin");
        pharmaAdminJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pharmaAdminJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pharmaAdminJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CVSAdminJButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(CVSAdminJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(pharmaAdminJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        userProcessContainer.setBackground(new java.awt.Color(7, 7, 32));
        userProcessContainer.setLayout(new java.awt.CardLayout());
        jSplitPane1.setRightComponent(userProcessContainer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pharmaAdminJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pharmaAdminJButtonActionPerformed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_pharmaAdminJButtonActionPerformed

    private void CVSAdminJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CVSAdminJButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.removeAll();
        userProcessContainer.add("adminWorkAreaJPanel",adminWorkArea);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_CVSAdminJButtonActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton CVSAdminJButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    public javax.swing.JButton pharmaAdminJButton;
    public javax.swing.JPanel userProcessContainer;
    // End of variables declaration//GEN-END:variables
}
