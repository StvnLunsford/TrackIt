package trackit.UI;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import trackit.*;

/**
 * @author Douglas
 * UI Layer: Handles all aspects of the Order panel. TODO: convert to JPanel.
 */
public class OrdersUI
        extends JPanel {
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final String WINDOW_NAME = "Orders";
    // </editor-fold>
    // <editor-fold defaultstate="expanded" desc="Private Fields">
    private final ArrayList<Order> orders = new ArrayList<>();
    private final Order bll = new Order();
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Components">
    JButton btnCreate, btnRemove, btnEdit;
    String[] ordersLabel = {"Order Date", "Order Number", "Supplier", "Status", "Total"};
    JTable ordersTable;
    OrderItemsUI details;
    int selectedRow;
    
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public OrdersUI() {
        setLayout(new BorderLayout());
        
        //add data to suppliers arraylist 
        Object[][] suppliersTestData = {{"12MAY2018", "019645232", "Walmart", "in transit", "$128.34"}, {"12MAY2018", "019645232", "Walmart", "in transit", "$128.34"}, {"12MAY2018", "019645232", "Walmart", "in transit", "$128.34"} };
        ordersTable = new JTable(suppliersTestData, ordersLabel);
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        ordersTable.setFillsViewportHeight(true);
        ordersTable.setDefaultEditor(Object.class, null);
        
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel btmSup = new JPanel();
        
        btnCreate = new JButton("Create");
        btnCreate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("create supply");
                details = new OrderItemsUI();
            }
        });
        
        btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Edit supply");
                //if list item selected edit item else select item
                selectedRow = ordersTable.getSelectedRow();
                if(selectedRow < 0){
                    JOptionPane.showMessageDialog(null, "Select item to edit");
                }else{
                    details = new OrderItemsUI();
                    //TODO: enter item info of selected item
                }
            }         
        });
        
        btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("remove supply");
                selectedRow = ordersTable.getSelectedRow();
                if(selectedRow < 0){
                    JOptionPane.showMessageDialog(null, "Select item to remove");
                }else{
                    //TODO: remove item from db
                    JOptionPane.showMessageDialog(null, "Item successfully removed");
                }
            }
        });
        
        btmSup.add(btnCreate);
        btmSup.add(btnEdit);
        btmSup.add(btnRemove);
        
        add(btmSup, BorderLayout.SOUTH);
        
    }
    
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    
    private void getValues() {
        if (bll.load()) {
            //this.orders.addAll(bll.getItems());
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    /**
     * Displays the frame.
     */
    public void display() {
        System.out.println(String.format("Displaying %s...", WINDOW_NAME));
        setVisible(true);
    }
    
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="SubClasses">
    /**
     * Handles all aspects of closing the program.
     */
    private class CloseQuery extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            JFrame frame = (JFrame) e.getSource();
            int result = JOptionPane.showConfirmDialog(frame,
                    "Do you want to save?", "Close Query",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                //TODO
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else {
                //TODO
            }
        }
    }
    // </editor-fold>
}