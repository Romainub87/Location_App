/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LocationProgram.Interactions;

import LocationProgram.Users.Owner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import mainapp.MainAppLocation;

/**
 *
 * @author baril
 */
public class Location {

    private Property property;
    private String titreLocation;
    private int nominalPrice;
    private String description;
    private HashMap<String, ArrayList<Bid>> listEnchereLoc;
    private Owner owner;
    private int nbMaxPeople;
    private HashSet<String> validMonth;
    private final HashSet<String> validMonthFinal;
    private boolean open;
    private ArrayList<Reservation> listReservLoc;

    /**
     * Builder in order to make locations
     * 
     * @param pr : rental house owner
     * @param titre : title of the location
     * @param prix : price of the location for 1 night and 1 person
     * @param des : description of the location
     * @param nbpers : maximum number of persons allow in the rental home
     * @param ow : rental owner (the same as the rental home owner)
     * @param ms : the list of months where the rental is possible
     */
    public Location(Property pr, String titre, int prix, String des, int nbpers, Owner ow, HashSet ms) {
        listEnchereLoc = new HashMap<>();
        validMonthFinal = new HashSet<>();
        for (Month m : Month.values()) {
            if (ms.contains(m.name())) {
                validMonthFinal.add(m.name().trim());
                listEnchereLoc.put(m.name().trim(), new ArrayList<>());
            }
        }
        listReservLoc = new ArrayList<>();
        open = true;
        validMonth = ms;
        nbMaxPeople = nbpers;
        owner = ow;
        property = pr;
        titreLocation = titre;
        nominalPrice = prix;
        description = des;
        MainAppLocation.listLocation.add(this);
    }

    /**
     * Method that permit to get the rental house
     * 
     * @return property : the rental house
     */
    public Property getProperty() {
        return (Property) property;
    }

    /**
     * Change location status
     */
    public void CloseBid() {
        open = false;
    }

    /**
     * Method that permit to get the location status
     *
     * @return open : location status
     */
    public boolean getOpen() {
        return (boolean) open;
    }
    
    /**
     * Method that permit to get the reservation list for a location
     *
     * @return listReservLoc : reservation list
     */
    public ArrayList<Reservation> getListReservLoc(){
        return (ArrayList<Reservation>) listReservLoc.clone();
    }
    
    /**
     * Method that permit to add a reservation to the reservation list of a location 
     *
     * @param r : reservation added
     */
    public void addReservForLoc(Reservation r){
        listReservLoc.add(r);
    }

    /**
     * Method that permit to get the maximum number of persons for a location
     *
     * @return nbMaxPeople : maximum number of persons
     */
    public int getNbPersMax() {
        return (int) nbMaxPeople;
    }

    /**
     * Method that permit to get the location owner
     *
     * @return owner : the location owner
     */
    public Owner getOwner() {
        return (Owner) owner;
    }

    /**
     * Method that permit to get the fixed list of months when the location is available
     *
     * @return validMonthFinal : the fixed list of months
     */
    public HashSet<String> getMonthLoc() {
        return (HashSet<String>) validMonthFinal.clone();
    }

    /**
     * Method that permit to get the location title
     *
     * @return titreLocation : title of the location
     */
    public String getTitreLoc() {
        return (String) titreLocation;
    }

    /**
     * Method that permit to remove a month from the list of month when the location is available
     *
     * @param m : month to remove
     */
    public void EnleverMois(String m) {
        validMonth.remove(m);
    }

    /**
     * Method that permit to get the refreshed list of months when the location is available
     *
     * @return validMonth : refreshed list of months
     */
    public HashSet<String> getValidMonth() {
        return (HashSet<String>) validMonth.clone();
    }

    /**
     * Method that permit to get the location description
     *
     * @return description : location description
     */
    public String getDescription() {
        return (String) description;
    }

    /**
     * Method that permit to set the description of a location
     *
     * @param d : new description
     */
    public void setDescription(String d) {
        description = d;
    }

    /**
     * Method that permit to get the list of bids on a location
     *
     * @param m : the month that the location is for
     * 
     * @return listEnchereLoc.get(m) : list of bids on a location for a specified month
     */
    public ArrayList<Bid> getListEnchereLoc(String m) {
        return (ArrayList<Bid>) listEnchereLoc.get(m).clone();
    }

    /**
     * Method that permit to get the list of bids on a location sorted from the lesser to the bigger bid
     *
     * @param m : the month that the location is for
     * 
     * @return SortedBid : list of bids on a location sorted for a specified month
     */
    public TreeSet<Bid> getListEnchereLocSortedByBidAmount(String m) {
        Set<Bid> SortedBid = new TreeSet<>(new ComparatorBid());
        for (Bid bid : listEnchereLoc.get(m)) {
            SortedBid.add(bid);
        }
        return (TreeSet<Bid>) SortedBid;
    }

    /**
     * Method that permit to get the bigger bid on a location
     *
     * @param m : the month that the location is for
     * 
     * @return topBid : the bigger bid for a location
     */
    public Bid getCurrentBid(String m) {
        Bid topBid = listEnchereLoc.get(m).get(listEnchereLoc.get(m).size() - 1);
        return (Bid) topBid;
    }

    /**
     * Method that permit to get the nominal price for a location
     *
     * @return the nominal price for a location
     */
    public int getNominalPrice() {
        return (int) nominalPrice;
    }

    /**
     * Method that permit to calcul the starting price for the bids on a location
     *
     * @param nbPers : number of person specified by the person who make the bid
     * @param nbNuit : number of nights specified by the person who make the bid
     * 
     * @return price : starting price for the bids on a location
     */
    public int CalculRentPrice(int nbPers, int nbNuit) {
        double price = (nbNuit * nbPers * nominalPrice) / 10;
        if (price % 10 == 0) {
            return (int) price;
        } else {
            while (price % 10 != 0) {
                ++price;
            }
            return (int) price;
        }
    }

    /**
     * Method that permit to get the price expected by the owner
     *
     * @param nbPers : number of person specified by the person who make the bid
     * @param nbNuit : number of nights specified by the person who make the bid
     * 
     * @return price : price expected by the owner
     */
    public int getRealPrice(int nbPers, int nbNuit) {
        double price = nbNuit * nbPers * nominalPrice;
        return (int) price;
    }

    /**
     * Method that permit to add a bid on a location
     *
     * @param m : the month where the location is available for a bid
     * @param bid : the bid 
     */
    public void AddBid(String m, Bid bid) {
        listEnchereLoc.get(m).add(bid);
    }

    /**
     * Method that permit to remove a bid list for a location
     *
     * @param m : the month of the location where the bid list is remove
     */
    public void RemoveBidList(String m) {
        listEnchereLoc.remove(m);
    }

    /**
     * Override of the method toString that permit to display a location with all informations
     *
     * @return
     */
    @Override
    public String toString() {
        return titreLocation + ", " + description + ", number maximum : " + nbMaxPeople + ", price for 1 night : " + nominalPrice + ", Disponible en " + validMonth;
    }
}
