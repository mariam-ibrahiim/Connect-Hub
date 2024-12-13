
package connecthub.frontend;

import javax.swing.*;

import connecthub.backend.FriendManagement;
import connecthub.backend.Newsfeed;
import connecthub.backend.User;
//import connecthub.backend.App.userAccountManager;

import java.util.ArrayList;
import java.util.List;

public class FriendManagementFront extends javax.swing.JFrame {

    private javax.swing.JButton jButtonAccept;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonBlock;
    private javax.swing.JButton jButtonDecline;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonUnblock;
    private javax.swing.JComboBox<String> jComboBoxBlocked;
    private javax.swing.JComboBox<String> jComboBoxFriends;
    private javax.swing.JComboBox<String> jComboBoxRequests;
    private javax.swing.JComboBox<String> jComboBoxSuggested;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTableBlocked;
    private javax.swing.JTable jTableBlocked1;
    private javax.swing.JTable jTableFriends;
    private javax.swing.JTable jTableSuggested;
    private FriendManagement friendManagement;
    //private UserAccountManager userAccountManager;


    public FriendManagementFront(FriendManagement friendManagement) {
        initComponents();
        this.friendManagement = friendManagement;
      //  this.userAccountManager = userAccountManager;
        this.friendManagement.load();
    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

        // Component Initialization
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFriends = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableBlocked = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableSuggested = new javax.swing.JTable();
        jComboBoxFriends = new javax.swing.JComboBox<>();
        jButtonBlock = new javax.swing.JButton();
        jComboBoxSuggested = new javax.swing.JComboBox<>();
        jButtonAdd = new javax.swing.JButton();
        jComboBoxBlocked = new javax.swing.JComboBox<>();
        jButtonUnblock = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableBlocked1 = new javax.swing.JTable();
        jComboBoxRequests = new javax.swing.JComboBox<>();
        jButtonAccept = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButtonDecline = new javax.swing.JButton();


        // Labels
        jLabel1.setText("Friends");
        jLabel2.setText("Suggested");
        jLabel3.setText("Blocked");
        jLabel4.setText("Requests");

        // Tables - Set empty default models to avoid null data issues
        jTableFriends.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Id"}
        ));
        jScrollPane1.setViewportView(jTableFriends);

        jTableSuggested.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Id"}
        ));
        jScrollPane5.setViewportView(jTableSuggested);

        jTableBlocked.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Id"}
        ));
        jScrollPane4.setViewportView(jTableBlocked);

        jTableBlocked1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Id"}
        ));
        jScrollPane6.setViewportView(jTableBlocked1);

        // ComboBoxes - Set default models
        jComboBoxFriends.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Friend1", "Friend2"}));
        jComboBoxSuggested.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Suggested1", "Suggested2"}));
        jComboBoxBlocked.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Blocked1", "Blocked2"}));
        jComboBoxRequests.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Request1", "Request2"}));

        // Buttons
        jButtonBlock.setText("Block");
        jButtonAdd.setText("Add");
        jButtonUnblock.setText("Unblock");
        jButtonRemove.setText("Remove");
        jButtonAccept.setText("Accept");
        jButtonDecline.setText("Decline");

        // Event Listeners - Debugging added for troubleshooting
        jButtonBlock.addActionListener(evt -> {
            System.out.println("Block button clicked!");
            jButtonBlockActionPerformed(evt);
        });

        jButtonAdd.addActionListener(evt -> {
            System.out.println("Add button clicked!");
            jButtonAddActionPerformed(evt);
        });

        jButtonUnblock.addActionListener(evt -> {
            System.out.println("Unblock button clicked!");
            jButtonUnblockActionPerformed(evt);
        });

        jButtonRemove.addActionListener(evt -> {
            System.out.println("Remove button clicked!");
            jButtonRemoveActionPerformed(evt);
        });

        jButtonAccept.addActionListener(evt -> {
            System.out.println("Accept button clicked!");
            jButtonAcceptActionPerformed(evt);
        });

        jButtonDecline.addActionListener(evt -> {
            System.out.println("Decline button clicked!");
            jButtonDeclineActionPerformed(evt);
        });

        // Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxFriends, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonBlock)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonRemove))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxSuggested, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonAdd))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxBlocked, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonUnblock))
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxRequests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonAccept)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonDecline))
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jComboBoxFriends, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonBlock)
                                        .addComponent(jButtonRemove))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jComboBoxSuggested, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonAdd))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jComboBoxBlocked, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonUnblock))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jComboBoxRequests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonAccept)
                                        .addComponent(jButtonDecline))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }




    public void putFriends(FriendManagement friendManagement) {

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableFriends.getModel();

        model.setRowCount(0);
        jComboBoxFriends.removeAllItems();

        if (friendManagement.getFriends().getFriendsIds() != null) {
            for(String i : friendManagement.getFriends().getFriendsIds()) {
                User user = App.userAccountManager.searchById(i);
                if (user != null) {
                    model.addRow(new Object[]{user.getUsername(), user.getUserId()});
                    jComboBoxFriends.addItem(user.getUsername());
                }else {
                    System.out.println("User not found!");
                }
            }
        }
    }

    public void putSuggested(FriendManagement friendManagement) {

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableSuggested.getModel();
        model.setRowCount(0);
        jComboBoxSuggested.removeAllItems();

        friendManagement.getFriendRequestManagement().load();
        ArrayList<String> receivedRequests = friendManagement.getFriendRequestManagement().getRecivedRequests(friendManagement.getUserId());


        // Debugging: Print if getSuggested is null
        if (friendManagement.getSuggested() == null) {
            System.out.println("FriendManagement.getSuggested() is null!");
            return; // Exit the function early
        }

        // Debugging: Print if getSuggestedIds is null or empty
        List<String> suggestedIds = friendManagement.getSuggested().getSuggestedIds();
        if (suggestedIds == null) {
            System.out.println("Suggested IDs list is null!");
            return; // Exit the function early
        } else if (suggestedIds.isEmpty()) {
            System.out.println("Suggested IDs list is empty!");
            return; // Exit the function early
        }

        // Process suggested IDs if the list is not null or empty
        for (String i : suggestedIds) {

            if(!receivedRequests.contains(i)) {
                User user = App.userAccountManager.searchById(i);
                if (user != null) {
                    model.addRow(new Object[]{user.getUsername(), user.getUserId()});
                    jComboBoxSuggested.addItem(user.getUsername());
                }
            }
        }
    }

    public void putBlocked(FriendManagement friendManagement) {

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableBlocked.getModel();

        model.setRowCount(0);
        jComboBoxBlocked.removeAllItems();

        if(friendManagement.getBlocked().getBlockedIds() != null) {
            for(String i : friendManagement.getBlocked().getBlockedIds()) {
                User user = App.userAccountManager.searchById(i);
                if (user != null) {
                    model.addRow(new Object[]{user.getUsername(), user.getUserId()});
                    jComboBoxBlocked.addItem(user.getUsername());
                }
            }
        }
    }

    public void putRequests(FriendManagement friendManagement) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableBlocked1.getModel();

        // Clear the table and combo box
        model.setRowCount(0);
        jComboBoxRequests.removeAllItems();

        // Load the friend requests database
        friendManagement.getFriendRequestManagement().load();
        ArrayList<String> receivedRequests = friendManagement.getFriendRequestManagement().getRecivedRequests(friendManagement.getUserId());

        if (receivedRequests != null && !receivedRequests.isEmpty()) {
            for (String senderId : receivedRequests) {
                // Find the user by sender ID
                User user = App.userAccountManager.searchById(senderId);

                // Check if the user exists
                if (user != null) {
                    model.addRow(new Object[]{user.getUsername(), user.getUserId()});
                    jComboBoxRequests.addItem(user.getUsername());
                }
            }
        }
    }


    private void jComboBoxFriendsActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jComboBoxSuggestedActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jComboBoxBlockedActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    private void jComboBoxRequestsActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButtonBlockActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (jComboBoxFriends.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Please select friend");
        } else {
            String userName = jComboBoxFriends.getSelectedItem().toString();
            User user = App.userAccountManager.searchByUsername(userName);
            friendManagement.block(user.getUserId());
            friendManagement.update();


            jTableBlocked.removeAll();
            putBlocked(friendManagement);
            jTableFriends.removeAll();
            putFriends(friendManagement);
        }
    }

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (jComboBoxSuggested.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Please select suggested user");
        } else {
            String userName = jComboBoxSuggested.getSelectedItem().toString();
            User user = App.userAccountManager.searchByUsername(userName);
            friendManagement.addFriend(user.getUserId());
            friendManagement.update();
            jTableFriends.removeAll();
            putFriends(friendManagement);
            jTableSuggested.removeAll();
            putSuggested(friendManagement);
        }
    }

    private void jButtonUnblockActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (jComboBoxBlocked.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Please select blocked user!");
        } else {
            String userName = jComboBoxBlocked.getSelectedItem().toString();
            User user = App.userAccountManager.searchByUsername(userName);
            friendManagement.unblock(user.getUserId());
            jTableBlocked.removeAll();
            putBlocked(friendManagement);
            jTableSuggested.removeAll();
            putSuggested(friendManagement);
        }
    }

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(jComboBoxFriends.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null,"Please select friend");
        }else{
            String userName = jComboBoxFriends.getSelectedItem().toString();
            User user = App.userAccountManager.searchByUsername(userName);
            friendManagement.removeFriend(user.getUserId());
            jTableFriends.removeAll();
            putFriends(friendManagement);
            jTableSuggested.removeAll();
            putSuggested(friendManagement);
        }

    }

    private void jButtonAcceptActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        if (jComboBoxRequests.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Please select a request");
        } else {
            String userName = jComboBoxRequests.getSelectedItem().toString();
            User user = App.userAccountManager.searchByUsername(userName);
            friendManagement.getFriendRequestManagement().load();
            friendManagement.acceptFriend(user.getUserId());
          //  Newsfeed.notficationSystem.searchForNotification(friendManagement.getUserId(), user.getUserId());
            jTableFriends.removeAll();
            putFriends(friendManagement);
            jTableBlocked1.removeAll();
            putRequests(friendManagement);
        }

    }

    private void jButtonDeclineActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (jComboBoxRequests.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Please select a request");
        }else{
            String userName = jComboBoxRequests.getSelectedItem().toString();
            User user = App.userAccountManager.searchByUsername(userName);
            friendManagement.getFriendRequestManagement().load();
            friendManagement.declineFriend(user.getUserId());


            jTableBlocked1.removeAll();
            putRequests(friendManagement);
            jTableSuggested.removeAll();
            putSuggested(friendManagement);
        }
    }
}
