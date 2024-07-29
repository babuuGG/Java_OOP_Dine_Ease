/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DineEaseController;





import DIneEaseModel.AdminLoginModel;
import DineEaseVIew.AdminLoginView;

public class AdminLoginController {
    private AdminLoginView view;

    public AdminLoginController(AdminLoginModel adminModel, AdminLoginView view) {
        this.view = view;
    }

    public static void main(String[] args) {
        AdminLoginView view = new AdminLoginView();
        AdminLoginModel adminModel = new AdminLoginModel();
        new AdminLoginController(adminModel, view);
    }
}
