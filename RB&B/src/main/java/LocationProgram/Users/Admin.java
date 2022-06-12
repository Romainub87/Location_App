/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LocationProgram.Users;

import mainapp.MainAppLocation;

/**
 *
 * @author romai
 */
public class Admin extends User {

    /**
     * Builder in order to make admins
     *
     * @param name : admin name
     * @param pseudo : admin pseudo
     * @param age : admin age
     * @param mail : admin mail
     */
    public Admin(String name, String pseudo, int age, String mail) {
        this.name = name;
        this.pseudo = pseudo;
        this.age = age;
        this.email = mail;
        this.money = 0;
        MainAppLocation.listAdmin.add(this);
    }
}
