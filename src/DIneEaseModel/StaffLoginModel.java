/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DIneEaseModel;

/**
 *
 * @author roshankhadka
 */
public class StaffLoginModel {
    private final String validUsername = "Staff";
    private final String validPassword = "Password";
    private final String validPhoneNumber = "1234567890";

    public boolean validateLogin(String username, String password) {
        return validUsername.equals(username) && validPassword.equals(password);
    }

    public boolean resetPassword(String username, String phoneNumber, String newPassword) {
        return validUsername.equals(username) && validPhoneNumber.equals(phoneNumber);
    }
}
    
