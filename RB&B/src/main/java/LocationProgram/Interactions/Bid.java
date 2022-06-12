/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LocationProgram.Interactions;

import LocationProgram.Users.Tenant;

/**
 *
 * @author romai
 */
public class Bid {

    int montant;
    Tenant tenant;
    int pers;
    int nuit;

    /**
     * Builder in order to make bids
     * 
     * @param t : person who make the bid
     * @param m : amount of the bid
     * @param nbPers : number of person for the location where the bid is deposed
     * @param nbNuit : number of nights for the location where the bid is deposed
     */
    public Bid(Tenant t, int m, int nbPers, int nbNuit) {
        tenant = t;
        montant = m;
        pers = nbPers;
        nuit = nbNuit;
    }

    /**
     * Method that permit to get the amount of a bid
     * 
     * @return montant : the amount of the bid
     */
    public int getMontant() {
        return montant;
    }

    /**
     * Method that permit to get the person who make the bid
     * 
     * @return tenant : the person who make the bid
     */
    public Tenant getTenant() {
        return tenant;
    }
    
    /**
     * Method that permit to get the number of persons for the location the bid is for
     * 
     * @return pers : number of persons
     */
    public int getNbPersBid(){
        return pers;
    }
    
    /**
     * Method that permit to get the number of nights for the location the bid is for
     * 
     * @return nuit : number of nights
     */
    public int getNbNuitBid(){
        return nuit;
    }
    
    /**
     * Override of the method toString that permit to display a bid with her amount and her tenant name
     * 
     * @return
     */
    @Override
    public String toString(){
        return  montant + " euros by " + tenant.getName();
    }
}
