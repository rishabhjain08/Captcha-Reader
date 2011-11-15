package Learner;

import java.awt.Component;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TagID extends javax.swing.JFrame {

    /** Creates new form TagID */
    boolean BUTTON;
    public TagID() {
        //        this.setUndecorated(true);
        TagIDModel tagIDModel = new TagIDModel();
        initComponents();
        table.setModel(tagIDModel);
        JCheckBox check_box = new JCheckBox();
        DefaultCellEditor cell_editor=new DefaultCellEditor(check_box);

        table.getColumnModel().getColumn(4).setCellRenderer(new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                boolean selected=(Boolean)value;
                JCheckBox box=new JCheckBox();
                if(selected)
                    box.setSelected(true);
                return box;
            }
        });
        table.getColumnModel().getColumn(4).setCellEditor(cell_editor);

        table.getColumnModel().getColumn(5).setCellRenderer(new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                boolean selected=(Boolean)value;
                JCheckBox box=new JCheckBox();
                if(selected)
                    box.setSelected(true);
                return box;
            }
        });
        table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        table.getColumnModel().getColumn(6).setCellRenderer(new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                boolean selected=(Boolean)value;
                JCheckBox box=new JCheckBox();
                if(selected)
                    box.setSelected(true);
                return box;
            }
        });
        table.getColumnModel().getColumn(6).setCellEditor(cell_editor);

        table.setColumnSelectionAllowed(true);
        table.setRowSelectionAllowed(true);
    }

    public void setExtractions(List[] l){
        if(l==null){
            return;
        }
        TagIDModel model = (TagIDModel) table.getModel();
        int i=model.getRowCount();
        while(i>0){
            model.removeRow(0);
            i--;
        }
        i=0;
        while(i<l[0].size()){
            model.addRow(new Object[]{i+1,l[0].get(i),l[1].get(i),Character.toString(Character.toChars(Character.codePointAt(new char[]{'A'}, 0)+i)[0]),true,false,true});
            i++;
        }
//        jScrollPane1.setSize(this.getWidth(), 100);
        this.setSize(this.getWidth(), Math.min(i*table.getRowHeight(),10*table.getRowHeight())+105);
    }

    public JTable getResult(){
        return table;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No.", "Extracted", "Respective Length", "ID", "Remember lengths", "At Strech", "Consider"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setMinWidth(300);
        table.getColumnModel().getColumn(3).setMaxWidth(100);
        table.getColumnModel().getColumn(4).setMinWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(15);
        table.getColumnModel().getColumn(4).setMaxWidth(50);
        table.getColumnModel().getColumn(5).setMinWidth(80);
        table.getColumnModel().getColumn(5).setMaxWidth(80);
        table.getColumnModel().getColumn(6).setMinWidth(80);
        table.getColumnModel().getColumn(6).setMaxWidth(80);

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(682, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-886)/2, (screenSize.height-346)/2, 886, 346);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i=0;
        boolean blankid=false;
        while(i<table.getRowCount()){
            if(table.getValueAt(i, 2).equals("")&&table.getValueAt(i, 4).equals(true)){
                new Message("ID at position "+(i+1)+" is blank").setVisible(true);
                return;
            }
            if(((String)table.getValueAt(i, 3)).indexOf(',')!=-1){
                new Message("Comma is not allowed").setVisible(true);
                return;
            }
            i++;
        }
        BUTTON=true;
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        BUTTON=false;
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TagID().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
