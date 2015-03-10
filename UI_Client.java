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


public class UI_Client extends javax.swing.JFrame {
    private Socket socket = null;
    private InetAddress host = null;
    private int PORT = 0;
    private String nama = null;
    private Handle data;
    DataInputStream input = null;
    DataOutputStream output = null;
    JFileChooser cus;
    
    public UI_Client() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        PORT_Field = new javax.swing.JTextField();
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
        browse_but = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PORT");

        PORT_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PORT_FieldActionPerformed(evt);
            }
        });

        Connect_btn.setText("Connect");
        Connect_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Connect_btnActionPerformed(evt);
            }
        });

        Exit_Btn.setText("Exit");
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

        browse_but.setText("Browse");
        browse_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browse_butActionPerformed(evt);
            }
        });

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
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IP_Field, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(PORT_Field)
                            .addComponent(Name_Field))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Connect_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Text_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(browse_but, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Send_btn)
                                .addGap(18, 18, 18)
                                .addComponent(Exit_Btn)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(IP_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PORT_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Name_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(Connect_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Send_btn)
                    .addComponent(Exit_Btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(browse_but)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>                        

    private void PORT_FieldActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void Connect_btnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String message = "null";
        if(PORT_Field.getText().equals("") || IP_Field.getText().equals("") || Name_Field.getText().equals(""))JOptionPane.showMessageDialog(null,"Complete the blank Form first");
        else    {
        nama = this.Name_Field.getText();
        try{
            this.Connect_btn.setEnabled(false);    
            host= InetAddress.getByName(IP_Field.getText());
            PORT = Integer.parseInt(PORT_Field.getText());
            socket = new Socket(host,PORT);
            //if(socket == null)this.Connect_btn.setEnabled(true);
            //else this.Connect_btn.setEnabled(false);
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());
            data = new Handle(input,this);
            data.setDaemon(true);
            data.nama = nama;
            data.start();
            output.writeUTF(nama);
            this.Chat_Area.append(data.nama +" has connected to server\n");
        }
        catch(IOException e){System.out.println("Failed to get Inet Address");}
    }
    }                                           

    private void Exit_BtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
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
    
    private void IP_FieldActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void Send_btnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String message=null;
        String tugel[] = null;
        String ke = null;
        String tujuan = null;
        message = nama+" : "+this.Text_Field.getText()+"\n";
        
        if (message.contains("@"))
        {
            tugel = this.Text_Field.getText().split("@");
            tujuan = tugel[tugel.length-1];
            ke = nama + " ke " + tujuan + " : " + tugel[0] +"\n";
            
            try{
                //this.Chat_Area.append(message);
                output.writeUTF(message);
                this.writelog(ke);
                this.Text_Field.setText("");
            }
            catch(Exception e){}
        }
        else      
            try{
                //this.Chat_Area.append(message);
                output.writeUTF(message);
                this.Text_Field.setText("");
            }
            catch(Exception e){}        
    }                                        

    private void browse_butActionPerformed(java.awt.event.ActionEvent evt) {                                           
        cus = new JFileChooser();
        cus.setCurrentDirectory(new java.io.File("."));
        //cus.setDialogTitle(choosertitle);
        cus.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        cus.setAcceptAllFileFilterUsed(false);
        
        if (cus.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
            
           try {
            String anu = cus.getSelectedFile().getCanonicalPath();
            File f = new File (anu);
            ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
            
                for (String file : names)
                {
                    //System.out.println(anu);
                    //System.out.println("file: " + file);
                    output.writeUTF(data.nama+" file: " + file + "\n");
                }
       }
         catch (IOException ex) {
           ex.getMessage();
       }
        }
        
        else{
            System.out.println("No Selection ");
        }
        
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

    // Variables declaration - do not modify                     
    private javax.swing.JTextArea Chat_Area;
    private javax.swing.JButton Connect_btn;
    private javax.swing.JButton Exit_Btn;
    private javax.swing.JTextField IP_Field;
    private javax.swing.JTextField Name_Field;
    private javax.swing.JTextField PORT_Field;
    private javax.swing.JButton Send_btn;
    private javax.swing.JTextField Text_Field;
    private javax.swing.JToggleButton browse_but;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration                   

    void writelog(String message) {
    Chat_Area.append(message);
    }

}
