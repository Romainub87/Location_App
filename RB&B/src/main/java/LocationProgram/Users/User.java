/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LocationProgram.Users;

import LocationProgram.Interactions.Bid;
import LocationProgram.Interactions.Location;
import LocationProgram.Interactions.Property;
import LocationProgram.Interactions.Reservation;
import java.util.ArrayList;

/**
 *
 * @author romai
 */
public abstract class User {

    protected String pseudo;
    protected String email;
    protected String name;
    protected int age;
    protected int money;
    protected int virtualWallet;
    protected ArrayList<Location> listLocationOw;
    protected ArrayList<Property> listPropertyOw;
    protected ArrayList<Reservation> listReservationTn;
    protected ArrayList<Bid> MyBids;

    /**
     * Method that permit to get the email of a user
     *
     * @return email : email of a user
     */
    public String email() {
        return email;
    }

    /**
     * Method that permit to get the name of a user
     *
     * @return name : name of a user
     */
    public String getName() {
        return name;
    }

    /**
     * Method that permit to get the bid list of a user (tenant)
     *
     * @return MyBids : bid list of a user (tenant)
     */
    public ArrayList<Bid> getBids() {
        return (ArrayList<Bid>) MyBids.clone();
    }

    /**
     * Method that permit to add a bid in the bid list of a user (tenant)
     *
     * @param bid : bid added in the bid list
     */
    public void AddBidToMyList(Bid bid) {
        MyBids.add(bid);
    }

    /**
     * Method that permit to add a reservation in a reservation list
     *
     * @param r : reservation add in the list
     */
    public void AddReservation(Reservation r) {
        listReservationTn.add(r);
    }

    /**
     * Method that permit to get a reservation list
     *
     * @return listReservationTn : reservation list
     */
    public ArrayList<Reservation> getReservation() {
        return (ArrayList<Reservation>) listReservationTn.clone();
    }

    /**
     * Method that permit to get the age of a user
     *
     * @return age : age of a user
     */
    public int getAge() {
        return age;
    }

    /**
     * Method that permit to get the money (see on his account) a user have
     *
     * @return money : money of a user
     */
    public int getMoney() {
        return money;
    }

    /**
     * Method that permit to set the name of a user
     *
     * @param n : new name
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Method that permit to set the age of a user
     *
     * @param a : new age
     */
    public void setAge(int a) {
        age = a;
    }

    /**
     * Method that permit to get the virtual wallet (to simulate the bid amount)
     *
     * @return virtualWallet : virtual money of a user (with bid in less, etc)
     */
    public int getVirtualWallet() {
        return virtualWallet;
    }

    /**
     * Override of the toString method that permit to display the name of a user
     *
     * @return
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Method that permit to add a property in a owner property list
     *
     * @param property : property add
     */
    public void AjouterProperty(Property property) {
        listPropertyOw.add(property);
    }

    /**
     * Method that permit to remove a property from a owner property list
     *
     * @param property : property remove
     */
    public void RemoveProperty(Property property) {
        listPropertyOw.remove(property);
    }

    /**
     * Method that permit to get a owner property list
     *
     * @return listPropertyOw : owner property list
     */
    public ArrayList<Property> getListProperty() {
        return (ArrayList< Property>) listPropertyOw.clone();
    }

    /**
     * Method that permit to add a location in a owner property list
     *
     * @param loc : location add
     */
    public void AjouterLocation(Location loc) {
        listLocationOw.add(loc);
    }

    /**
     * Method that permit to remove a location in a owner property list
     *
     * @param loc : location remove
     */
    public void RemoveLocation(Location loc) {
        listLocationOw.remove(loc);
    }

    /**
     * Method that permit to get a owner location list
     *
     * @return listLocationOw : owner location list
     */
    public ArrayList<Location> getListLocation() {
        return (ArrayList< Location>) listLocationOw.clone();
    }

    /**
     * Method that permit to add money from the money of a user
     *
     * @param m : amount add on the money of the user
     */
    public void AddMoney(int m) {
        money += m;
        virtualWallet += m;
    }

    /**
     * Method that permit to retire 1 on the money of a user
     */
    public void ActionFrais() {
        money -= 1;
    }

    /**
     * Method that permit to retire money from the money of a user
     *
     * @param m : amount retire on the money of the user
     */
    public void RetireMoney(int m) {
        money -= m;
    }

    /**
     * Method that permit to retire bid amount on the money of a user
     *
     * @param bid : bid whose the amount is retire on the money of a user
     */
    public void RetireBidMoney(Bid bid) {
        money -= bid.getMontant();
    }

    /**
     * Method that permit to retire a bid amount on the virtual wallet
     *
     * @param bid : bid whose the amount is retire on the virtual wallet
     */
    public void RetireBidOnVirtualWallet(int bid) {
        virtualWallet -= bid;
    }

    /**
     * Method that permit to return the admin pseudo
     *
     * @return pseudo : admin pseudo
     */
    public String getPseudo() {

        return pseudo;
    }
}
