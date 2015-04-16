package ChatRoom;

import javax.swing.JOptionPane;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UI_Server extends javax.swing.JFrame {
    
    private int PORT;
    public myEchoServer eserv;
    String nama = null;
    
    int x;
    //private Paket paket;
    //private Vector<myEchoServer> Data = new Vector<myEchoServer>();
    //java.util.ArrayList<Paket> Paket_Data;
    
    public UI_Server() {
        initComponents();
        //this.Report_Area.append("Fill The blank form. and wait until server finish the searching\n"); 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        PORT_Area = new javax.swing.JTextField();
        Server_Btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Report_Area = new javax.swing.JTextArea();
        Exit_Btn = new javax.swing.JButton();
        DC_field = new javax.swing.JTextField();
        DC_but = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PORT");

        PORT_Area.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PORT_AreaActionPerformed(evt);
            }
        });

        Server_Btn.setText("Open");
        Server_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Server_BtnActionPerformed(evt);
            }
        });

        Report_Area.setEditable(false);
        Report_Area.setColumns(20);
        Report_Area.setRows(5);
        jScrollPane1.setViewportView(Report_Area);

        Exit_Btn.setText("Exit");
        Exit_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Exit_BtnActionPerformed(evt);
            }
        });

        DC_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DC_fieldActionPerformed(evt);
            }
        });

        DC_but.setText("Disconnect");
        DC_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DC_butActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("SERVER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Exit_Btn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DC_field, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(DC_but)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Server_Btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PORT_Area, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addGap(129, 129, 129)
                .addComponent(jLabel2)
                .addContainerGap(230, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PORT_Area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Server_Btn)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Exit_Btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DC_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DC_but))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PORT_AreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PORT_AreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PORT_AreaActionPerformed

    private void Server_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Server_BtnActionPerformed
    if(PORT_Area.getText().equals(""))JOptionPane.showMessageDialog(null, "Complete the form to make Server");
    else{    
        PORT = Integer.parseInt(PORT_Area.getText());
        //Waiting_Time = Integer.parseInt(Wait_Field.getText());
        x = 0;
        //paket = new Paket();
        try
        { 
            eserv = new myEchoServer(this, PORT);
            x++;
            //Data.add(eserv);
            //Server = new ServerSocket(PORT);
            //Server.setSoTimeout(Waiting_Time*1000);     
        }
        
        catch(Exception e)
        {
            System.out.println("Error Server");
        }
        /*do{
           try{
               client = Server.accept();
               Handle data = new Handle(this,client,x);
               Data.add(data); //= new process(this,client,x);
               //Data.setDaemon(true);
               //Data.start();
              // paket.NewPort(client.getLocalPort(),Data.getName());
               Report_Area.append(data.getName()+" has been connect\n");
               x++;
           }
            catch(SocketTimeoutException e){
            JOptionPane.showMessageDialog(null,"Searching is Done !!!");
            this.Report_Area.append("\n\n");
            break;
           }
           catch(IOException e){
               this.Report_Area.append("Terjadi Error\n");
               //System.exit(1);
           }
        }while(true);*/
    }
    }//GEN-LAST:event_Server_BtnActionPerformed

    private void Exit_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Exit_BtnActionPerformed
        System.exit(1);
    }//GEN-LAST:event_Exit_BtnActionPerformed

    private void DC_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DC_fieldActionPerformed
        // TODO add your handling code here:     
    }//GEN-LAST:event_DC_fieldActionPerformed

    private void DC_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DC_butActionPerformed
        this.nama = this.DC_field.getText();
        //String cut = nama.substring(0, nama.length()-1);
        try 
        {
            this.DC(this.nama);
            this.DC_field.setText("");
        } 
        catch (IOException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_DC_butActionPerformed

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
            java.util.logging.Logger.getLogger(UI_Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DC_but;
    private javax.swing.JTextField DC_field;
    private javax.swing.JButton Exit_Btn;
    private javax.swing.JTextField PORT_Area;
    private javax.swing.JTextArea Report_Area;
    private javax.swing.JButton Server_Btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    void writelog(String message) {
        Report_Area.append(message+"\n");
    }
    
        void sendToClient(String Message)throws java.io.IOException
        {   
            for(int i =0; i<eserv.clientCount; i++){
                eserv.clients[i].sendMessage(Message);
                //this.writelog(eserv.clients[i].getName());
            }
        }
        
        public void sendToAClient(String Message, String jeneng)throws IOException
        {
            for(int i =0; i<eserv.clientCount; i++)
            {
                String klien = eserv.clients[i].nama;
                if(klien.equalsIgnoreCase(jeneng))
                {
                    //System.out.println("mlebu");
                    eserv.clients[i].sendMessage("[PM] "+Message+"\n");
                }
            } 
        }
        
        public void DC(String jeneng)throws IOException
        {     
            for(int i =0; i<eserv.clientCount; i++)
                {
                    String klien = eserv.clients[i].nama;
                    if(klien.equalsIgnoreCase(jeneng))
                    {
                        //eserv.clients[i].output.writeUTF("QUIT");
                        eserv.clients[i].stop();
                        /*eserv.clientCount--;
                        break;*/
                    }
                } 
        }
}

