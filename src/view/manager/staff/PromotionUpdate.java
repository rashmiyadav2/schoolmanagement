/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.manager.staff;

import controller.GeneralManager;
import java.awt.Frame;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import model.Department;
import model.Position;
import model.Promotion;

/**
 *
 * @author luan
 */
public class PromotionUpdate extends javax.swing.JDialog {

    private Promotion promotion;
    private Promotion newPromotion;
    private GeneralManager manager;

    /**
     * Creates new form StudentMoreInfoDialog
     */
    public PromotionUpdate(Frame parent, boolean modal,  Vector data) {
        super(parent, modal);
        this.manager = new GeneralManager();
        initComponents();
        if (data != null) {
            this.promotion = new Promotion(Integer.parseInt(data.get(0).toString()), new Position((String) data.get(1), new Department((String) data.get(2))), manager.parseDate((String) data.get(3)));
        }
        initUI();
    }

    private void initUI() {
        setLocationRelativeTo(this);
        manager.loadCombobox("SELECT name FROM department", comboDepartment);
        if (promotion != null) {
            comboPosition.setSelectedItem(promotion.getPosition().getName());
            comboDepartment.setSelectedItem(promotion.getPosition().getDepartment().getName());
//            txtDate.setText(manager.formatDate(promotion.getTime()));
            dPic.setDate(promotion.getTime());
        }
    }

    private void save() {
        String SQL;
        String positionName = comboPosition.getSelectedItem().toString();
        if (positionName == null || positionName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Position is required !");
            return;
        }
        String department = comboDepartment.getSelectedItem().toString();

        Date date = dPic.getDate();
//        if (manager.parseDate(txtDate.getText()) == null) {
//            JOptionPane.showMessageDialog(this, "Date not wellform !");
//            return;
//        }
//        date = manager.parseDate(txtDate.getText());
        Department dpm = new Department(department);
        Position pos = new Position(positionName, dpm);
        SQL="SELECT position.ID FROM position "
                + "INNER JOIN department "
                + "ON department.ID=position.department_ID "
                + "WHERE department.name='"+dpm.getName()+"' "
                + "AND position.name='"+pos.getName()+"';";
        int ID_Position=manager.getIDEdentity(SQL);
        if(ID_Position==-1){
            JOptionPane.showMessageDialog(this, "Position not found !");
            dispose();
            return;
        }
        pos.setID(ID_Position);
        if (promotion == null) {
            promotion = new Promotion(pos, date);
        } else {
            promotion.setPosition(pos);
            promotion.setTime(date);
        }
        
        SQL = "SELECT 1 FROM position "
                + "INNER JOIN department "
                + "ON position.department_ID=department.ID "
                + "INNER JOIN promotion "
                + "ON position.ID=promotion.position_ID "
                + "WHERE promotion.date='" + manager.formatDate(promotion.getTime()) + "'";
        if (!manager.checkUnique(SQL)) {
            JOptionPane.showMessageDialog(this, "Time must be unique !");
            return;
        }

        newPromotion = promotion;
        dispose();
    }

    private void reset() {
        comboPosition.removeAllItems();
        initUI();
    }

    private void cancel() {
        dispose();
    }

    public Promotion getPromotion() {
        return newPromotion;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboPosition = new javax.swing.JComboBox();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCancle = new javax.swing.JButton();
        comboDepartment = new javax.swing.JComboBox();
        dPic = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Position");

        jLabel3.setText("Department");

        jLabel5.setText("Date");

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/20x20/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/20x20/reset.gif"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnCancle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/20x20/cancel.png"))); // NOI18N
        btnCancle.setText("Cancel");
        btnCancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancleActionPerformed(evt);
            }
        });

        comboDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboDepartmentItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancle))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(comboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5))
                            .addGap(49, 49, 49)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comboPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dPic, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(29, 29, 29))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(dPic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnReset)
                    .addComponent(btnCancle)))
        );

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel11.setText("Poromotion Detail");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(131, 131, 131))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboDepartmentItemStateChanged
        if (comboDepartment.getSelectedItem() != null) {
            String SQL = "SELECT position.name "
                    + "FROM position INNER JOIN department "
                    + "ON position.department_ID=department.ID "
                    + "WHERE department.name='" + comboDepartment.getSelectedItem().toString() + "'";
            manager.loadCombobox(SQL, comboPosition);
        }
    }//GEN-LAST:event_comboDepartmentItemStateChanged

    private void btnCancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancleActionPerformed
        cancel();
    }//GEN-LAST:event_btnCancleActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
    }//GEN-LAST:event_btnSaveActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancle;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox comboDepartment;
    private javax.swing.JComboBox comboPosition;
    private com.toedter.calendar.JDateChooser dPic;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
