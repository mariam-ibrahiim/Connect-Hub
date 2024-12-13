
package connecthub.frontend;

import javax.swing.SwingUtilities;

import connecthub.backend.Admin;
import connecthub.backend.Group;
import connecthub.backend.Newsfeed;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author NEXT STORE
 */
public class GroupStatusWindow extends javax.swing.JFrame {

    /**
     * Creates new form GroupStatusWindow
     * 
     */
    private String userId;
    private String groupId;
    public GroupStatusWindow(String userId,String groupId) {
        initComponents();
        this.userId = userId;
        this.groupId = groupId;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ViewButton = new javax.swing.JButton();
        IgnoreButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Group Status");

        ViewButton.setText("View");
        ViewButton.setFocusPainted(false);
        ViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewButtonActionPerformed(evt);
            }
        });

        IgnoreButton.setText("Ignore");
        IgnoreButton.setFocusPainted(false);
        IgnoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IgnoreButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(ViewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(IgnoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ViewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IgnoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewButtonActionPerformed
           SwingUtilities.invokeLater(()->{
                dispose();
                Platform.runLater(()->{
                    Stage stage = App.getPrimaryStage();
                    Scene previousScene = stage.getScene();
                    Group group = Newsfeed.groupManager.searchGroupById(groupId);
                    Admin admin = group.getAdmin(userId);
                    if(group.getPrimaryAdmin().getUserId().equals(userId)) {
                        PrimaryAdminGroupProfile.show(group,group.getPrimaryAdmin(),stage,previousScene);
                    }
                    else if(admin!=null){
                        AdminGroupProfile.show(group,admin,stage,previousScene);
                    }
                    else {
                        UserGroupProfile.show(group,App.userAccountManager.searchById(userId),stage,previousScene);
                    }
                });
            });
            
    }

    private void IgnoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IgnoreButtonActionPerformed
        dispose();
    }

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IgnoreButton;
    private javax.swing.JButton ViewButton;
    // End of variables declaration//GEN-END:variables
}
