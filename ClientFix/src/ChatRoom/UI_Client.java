package ChatRoom;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.FileReader;
//library untuk enkripsi
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;

public class UI_Client extends javax.swing.JFrame {
    private Socket socket = null;
    private InetAddress host = null;
    private int PORT = 0;
    private String nama = null;
    private Handle data;
    DataInputStream input = null;
    DataOutputStream output = null;
    JFileChooser cus;
    //keperluan enkripsi
    static String IV = "AAAAAAAAAAAAAAAA";
    static String encryptionKey = "0123456789abcdef";
    public UI_Client() {
        initComponents();
        this.Exit_Btn.setEnabled(false);
        this.Send_btn.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Connect_btn = new javax.swing.JButton();
        Exit_Btn = new javax.swing.JButton();
        IP_Field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Text_Field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Send_btn = new javax.swing.JButton();
        Name_Field = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Chat_Area = new javax.swing.JTextArea();
        jlabel1 = new javax.swing.JLabel();
        tujuanpesan = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Connect_btn.setText("Connect");
        Connect_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Connect_btnActionPerformed(evt);
            }
        });

        Exit_Btn.setText("Disconnect");
        Exit_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Exit_BtnActionPerformed(evt);
            }
        });

        IP_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IP_FieldActionPerformed(evt);
            }
        });

        jLabel2.setText("IP Address");

        jLabel3.setText("Message");

        Send_btn.setText("Send");
        Send_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send_btnActionPerformed(evt);
            }
        });

        jLabel4.setText("Name");

        Chat_Area.setEditable(false);
        Chat_Area.setColumns(20);
        Chat_Area.setRows(5);
        jScrollPane2.setViewportView(Chat_Area);

        jlabel1.setText("Destination");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IP_Field, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(Name_Field))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Connect_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Send_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Exit_Btn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jlabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Text_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tujuanpesan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(IP_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Name_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Connect_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tujuanpesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(Text_Field)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Send_btn)
                    .addComponent(Exit_Btn))
                .addContainerGap())
        );

        Exit_Btn.getAccessibleContext().setAccessibleName("jtextfield");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Connect_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Connect_btnActionPerformed
        String message = "null";
        if(IP_Field.getText().equals("") || Name_Field.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Complete the blank Form first");
        }
        else
        {
            nama = this.Name_Field.getText();
            try{
                this.Connect_btn.setEnabled(false);    
                host= InetAddress.getByName(IP_Field.getText());
                PORT = 1234;
                socket = new Socket(host,PORT);
                this.input = new DataInputStream(socket.getInputStream());
                this.output = new DataOutputStream(socket.getOutputStream());
                data = new Handle(input,this);
                data.setDaemon(true);
                data.nama = nama;
                data.start();
                output.writeUTF(nama);
                this.Chat_Area.append(data.nama +" has connected to server\n");
                this.Connect_btn.setEnabled(false);
                this.Exit_Btn.setEnabled(true);
                this.Send_btn.setEnabled(true);
            }
            catch(IOException e)
            {
                System.out.println("Failed to get Inet Address");
            }
        }
    }//GEN-LAST:event_Connect_btnActionPerformed
 
    public void cabut()
    {
       if(socket==null)System.exit(1);
        int flag = data.Disconnect();
        String Dis = "++**Exit++**";
        
        try
        {
            output.writeUTF(Dis);
            socket.close();            
        }
        
        catch(IOException e){}
        
        System.exit(1); 
    }
    
    private void IP_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IP_FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IP_FieldActionPerformed

    private void Send_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Send_btnActionPerformed
        String message;
        String ke;
        if(tujuanpesan.getSelectedIndex() != 0)
        {
            message = nama+" :  "+this.Text_Field.getText()+"@"+tujuanpesan.getSelectedItem().toString();
        }
        else
        {
            message = nama+" :  "+this.Text_Field.getText();
        }
        if (message.contains("@"))
        {
            ke = nama + " ke " + tujuanpesan.getSelectedItem().toString() + " : " + Text_Field.getText();
            
            try{
                //this.Chat_Area.append(message);
                output.writeUTF(message);
                this.writelog(ke);
                this.Text_Field.setText("");
            }
            catch(Exception e){}
        }
        else 
        {
            try{
                //this.Chat_Area.append(message);
                output.writeUTF(message);
                this.Text_Field.setText("");
            }
            catch(Exception e)
            {}
        }        
    }//GEN-LAST:event_Send_btnActionPerformed

    private void Exit_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Exit_BtnActionPerformed
        if(socket==null)System.exit(1);
        int flag = data.Disconnect();
        String Dis = "++**Exit++**";

        try
        {
            output.writeUTF(Dis);
            socket.close();
            data.stop();
            this.Connect_btn.setEnabled(true);
            this.Exit_Btn.setEnabled(false);
            this.Send_btn.setEnabled(false);
        }

        catch(IOException e){}
    }//GEN-LAST:event_Exit_BtnActionPerformed

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
            java.util.logging.Logger.getLogger(UI_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Chat_Area;
    private javax.swing.JButton Connect_btn;
    private javax.swing.JButton Exit_Btn;
    private javax.swing.JTextField IP_Field;
    private javax.swing.JTextField Name_Field;
    private javax.swing.JButton Send_btn;
    private javax.swing.JTextField Text_Field;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlabel1;
    public javax.swing.JComboBox tujuanpesan;
    // End of variables declaration//GEN-END:variables

    void writelog(String message) {
        Chat_Area.append(message+"\n");
    }

}
