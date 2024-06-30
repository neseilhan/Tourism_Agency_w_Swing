package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {
    public void guiInitialize(int width, int height){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Vin Tourism");
        this.setSize(width,height); // setting size and title of the container.
        this.setLocation(Helper.getLocationPoint("x", this.getSize()),Helper.getLocationPoint("y", this.getSize())); // container centered.
        this.setVisible(true);
    }
    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows ){
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        if(rows == null){
            rows = new ArrayList<>();
        }

        for(Object[] row : rows){
            model.addRow(row);
        }
    }
    public int getTableSelectedRow(JTable table, int index){
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(),index).toString());
    }
    public void tableRowSelect(JTable table,JPopupMenu menu){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selectedRow, selectedRow);
                if (SwingUtilities.isRightMouseButton(e)) {
                    menu.show(table, e.getX(), e.getY());
                }
            }
        });
    }
    public void selectRow(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }
    public void resizeTable(JTable table, int tableWidth, int tableHeight, double... percentages) {
        table.setPreferredSize(new Dimension(tableWidth, tableHeight));

        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setMaxWidth((int) (tableWidth * (percentages[i] / total)));
        }
    }
}
