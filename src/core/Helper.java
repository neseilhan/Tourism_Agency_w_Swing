package core;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Helper {
    public static void setTheme() {
//        optionPaneTR();
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){ //added "Windows" theme.
            if("Windows".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            }
        }

    }
    public static int getLocationPoint(String type, Dimension size){
        return switch (type){
            case "x"  -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2 ;
            case "y"  -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2 ;
            default -> 0;
        };
    }
    public static void showMsg (String str){
//        optionPaneTR();
        String msg;
        String title;
        switch (str) {
            case "fill" -> {
                msg = "Please fill all the gaps.";
                title = "ERROR";
            }
            case "done" -> {
                msg = "Operation Successful.";
                title = "Result";
            }
            case "notFound" -> {
                msg = "No Records Found.";
                title = "Not Found";
            }
            case "error" -> {
                msg = "Error Occurred";
                title = "ERROR";
            }
            default -> {
                msg = str;
                title = "Message";
            }

        }
        JOptionPane.showMessageDialog(null,msg,title, JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean confirm(String str){ //Confirm operation for deleting.
//        optionPaneTR();
        String msg;
        if(str.equals("sure")){
            msg = "Are you sure you want to continue with this action?";
        }
        else{
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Confirm Delete", JOptionPane.YES_NO_OPTION) == 0;
    }

//    public static boolean isFieldEmpty(JTextField field){
//        return field.getText().trim().isEmpty(); // if its empty return true.
//    }
    public static boolean isFieldEmpty(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFieldListEmpty(JTextField[]... fieldlist){
        for(JTextField[] field : fieldlist){
            if(isFieldEmpty(field)) {
                return true;
            }
        }
        return false;

    }

    public static boolean isComboBoxEmpty(JComboBox comboBox) {
        return comboBox.getSelectedItem() == null;
    }
    public static boolean isComboBoxListEmpty(JComboBox[] fieldlist){
        for(JComboBox field : fieldlist){
            if(isComboBoxEmpty(field)) {
                return true;
            }
        }
        return false;
    }


    public static String changeBoolean(boolean entry){
        if(entry){
            return "Yes";
        }else{
            return "No";
        }
    }
    public static boolean isValidDate(String inputDate, String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        try {
            LocalDate date = LocalDate.parse(inputDate, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
