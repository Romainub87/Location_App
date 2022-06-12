/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LocationProgram.Users;

import java.util.ArrayList;
import mainapp.MainAppLocation;

/**
 *
 * @author romai
 */
public class Owner extends User {

    /**
     * Builder in order to make owners
     * 
     * @param n : name of the owner
     * @param a : age of the owner
     * @param mail : mail of the owner
     */
    public Owner(String n, int a, String mail) {
        name = n;
        age = a;
        this.money = 500;
        this.email = mail;
        MainAppLocation.listOwner.add(this);
        this.listLocationOw = new ArrayList<>();
        this.listPropertyOw = new ArrayList<>();
    }

}
