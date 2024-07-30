/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DIneEaseModel;




public class AdminLoginModel {
   
    public boolean authenticate(String username, String password) {
        
        return "admin".equals(username) && "password".equals(password);
    }
}

