/*
 * NewJFrame.java
 *
 * Created on 24 aprile 2007, 13.05
 */

/**
 *
 * @author  cdo_06
 */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import jnpout32.*;

public class NewJFrame extends javax.swing.JFrame {
             
    static short Base;
    static short Status;
    static short Control;
    static pPort lpt;
    
    /** Creates new form NewJFrame */
    
  
    public NewJFrame() {
        short ingresso;
        final NumberFormat formatter = new DecimalFormat("0.00");
        Thread nuovo;
        Base=(short) 0x378;
        Status=Base;
        Status++;
        Control=Status;
        Control++;
        lpt = new pPort();
        
        
        initComponents();
        
        lpt.output( Base, (short) 0 );
        
        nuovo = new Thread(
                        new Runnable () {               
                        public void run()
                        {
                            short risultato;
                            int valore;
                            
                            while (true) {
                                valore = 0;
                                try {
                                    
                                    lpt.output( Base, (short) 0 ); //Select                               
                                    Thread.currentThread().sleep(10);
                                    
                                    lpt.output( Base, (short) 1 ); // Primo clock su
                                    Thread.currentThread().sleep(10);
                                    lpt.output( Base, (short) 0 ); // Primo clock Giu
                                    Thread.currentThread().sleep(10);

                                    risultato = lpt.input(Status);
                                    //jLabel1.setText(Integer.toString((short) risultato));
                                    
                                    if ( risultato >= 128 ) {
                                        lpt.output( Base, (short) 1 ); // Secondo clock su
                                        Thread.currentThread().sleep(10);
                                        lpt.output( Base, (short) 0 ); // Secondo clock Giu
                                        Thread.currentThread().sleep(10);
                                
                                    //risultato = lpt.input(Status);
                                    //jLabel1.setText(Integer.toString((short) risultato));
                                    
                                    jLabel2.setText("");
                                        
                                    lpt.output( Base, (short) 1 ); // Terzo clock su
                                    Thread.currentThread().sleep(10);
                                    lpt.output( Base, (short) 0 ); // Terzo clock Giu
                                    Thread.currentThread().sleep(10);
 
                                    risultato = lpt.input(Status);
                                    if ( risultato < 128 ) {
                                        valore = 128;
                                    }
                                    
                                    lpt.output( Base, (short) 1 ); // Quarto clock su
                                    Thread.currentThread().sleep(10);
                                    lpt.output( Base, (short) 0 ); // Quarto clock Giu
                                    Thread.currentThread().sleep(10);
                                
                                    risultato = lpt.input(Status);
                                    if ( risultato < 128 ) {
                                        valore = valore + 64;
                                    }
                                    
                                    lpt.output( Base, (short) 1 ); // Quinto clock su
                                    Thread.currentThread().sleep(10);
                                    lpt.output( Base, (short) 0 ); // Quinto clock Giu
                                    Thread.currentThread().sleep(10);

                                    risultato = lpt.input(Status);
                                    if ( risultato < 128 ) {
                                        valore = valore + 32;
                                    }
                                    
                                    lpt.output( Base, (short) 1 ); // Sesto clock su
                                    Thread.currentThread().sleep(10);
                                    lpt.output( Base, (short) 0 ); // Sesto clock Giu
                                    Thread.currentThread().sleep(10);
                                    
                                    risultato = lpt.input(Status);
                                    if ( risultato < 128 ) {
                                        valore = valore + 16;
                                    }
                                    
                                    lpt.output( Base, (short) 1 ); // Settimo clock su
                                    Thread.currentThread().sleep(10);
                                    lpt.output( Base, (short) 0 ); // Settimo clock Giu
                                    Thread.currentThread().sleep(10);
                                    
                                    risultato = lpt.input(Status);
                                    if ( risultato < 128 ) {
                                        valore = valore + 8;
                                    }                                    
                                    
                                    lpt.output( Base, (short) 1 ); // Ottavo clock su
                                    Thread.currentThread().sleep(10);
                                    lpt.output( Base, (short) 0 ); // Ottavo clock Giu
                                    Thread.currentThread().sleep(10);
                                    
                                    risultato = lpt.input(Status);
                                    if ( risultato < 128 ) {
                                        valore = valore + 4;
                                    }
                                    
                                    lpt.output( Base, (short) 1 ); // Nono clock su
                                    Thread.currentThread().sleep(10);
                                    lpt.output( Base, (short) 0 ); // Nono clock Giu
                                    Thread.currentThread().sleep(10);

                                    risultato = lpt.input(Status);
                                    if ( risultato < 128 ) {
                                        valore = valore + 2;
                                    }                                    
                                    
                                    lpt.output( Base, (short) 1 ); // Decimo clock su
                                    Thread.currentThread().sleep(10);
                                    lpt.output( Base, (short) 0 ); // Decimo clock Giu
                                    Thread.currentThread().sleep(10);
                                    
                                    risultato = lpt.input(Status);
                                    if ( risultato < 128 ) {
                                        valore = valore + 1;
                                    }
                                                                        
                                    lpt.output( Base, (short) 1 ); // Undicesimo clock su
                                    Thread.currentThread().sleep(10);
                                    lpt.output( Base, (short) 0 ); // Undicesimo clock Giu
                                    Thread.currentThread().sleep(10);
                                    
                                    jProgressBar1.setValue(valore);
                                    jLabel1.setText(formatter.format( valore * 0.01 ));
                                    //jLabel1.setText(formatter.format(256 / 100.0));
                                    
                                    } else {
                                        jProgressBar1.setValue(0);
                                        jLabel1.setText("");
                                        jLabel2.setText("Connettere lo strumento!");
                                    }
                                    
                                    lpt.output( Base, (short) 8 ); //Select
                                    Thread.currentThread().sleep(250);
                                    
                                } catch (InterruptedException e) {
                                    System.out.println("Thread figlio interrotto");
                                }
                                
                            //jLabel1.setText(Integer.toHexString((short) Base) + "h");
                            //jLabel2.setText(Integer.toBinaryString((short) lpt.input(Base)));
                            //jLabel3.setText(Integer.toBinaryString((short) lpt.input(Status)));
                            //jLabel4.setText(Integer.toBinaryString((short) lpt.input(Control)));
                            //jLabel4.setText(Integer.toString((short) datum++));
                          }
                        }
                    }
                );
        nuovo.start();
       
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("adc0831");
        setResizable(false);
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 72));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jProgressBar1.setForeground(new java.awt.Color(0, 153, 0));
        jProgressBar1.setMaximum(255);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
                       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        }
        );
        
        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
    
}