/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Views.userinterface.CVSadmin;

import javax.swing.*;
/**
 *
 * @author J3R3MY
 */
public class DialogueBoxPopUp {
    public static void main() {
       
    }
         
    public void mensaje(int num){
        if(num == 3 ){
            JOptionPane.showMessageDialog(null,
                "Tienes 3 intentos más",
                "PopUp Dialog",
                JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(num == 2){
                JOptionPane.showMessageDialog(null,
                "Tienes 2 intentos más",
                "PopUp Dialog",
                JOptionPane.WARNING_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null,
                "Tienes 1 intentos más",
                "PopUp Dialog",
                JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
