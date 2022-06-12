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
public class Tenant extends User {

    /**
     * Builder in order to make tenants
     *
     * @param name : tenant name
     * @param age : tenant age
     * @param mail : tenant mail
     */
    public Tenant(String name, int age, String mail) {
        this.name = name;
        this.age = age;
        this.email = mail;
        money = 0;
        MainAppLocation.listTenant.add(this);
        this.MyBids = new ArrayList<>();
        this.listReservationTn = new ArrayList<>();
    }

    /**
     * Method that permit to add money on the wallet  by multiple of five
     *
     * @param ajout : amount added to the wallet
     */
    public void AjouterArgent(int ajout) {
        if (ajout % 5 == 0 && ajout > 0) {
            this.money += ajout;
            this.virtualWallet += ajout;
            System.out.println("Money added !");
            System.out.println("Your balance is " + this.money + " €.");
        } else {
            System.err.println("The ammount is not a multiple of 5 or is a negative number");
        }
    }

}
