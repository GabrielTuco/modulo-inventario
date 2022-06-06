/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Views.user_Interface;

import javax.swing.*;
/**
 *
 * @author J3R3MY
 */
public class DialogueBoxPopUp {
    public void mensaje(String mensaje){
        JOptionPane.showMessageDialog(null,
        mensaje,
        "PopUp Dialog",
        JOptionPane.INFORMATION_MESSAGE);
        
    }
}
