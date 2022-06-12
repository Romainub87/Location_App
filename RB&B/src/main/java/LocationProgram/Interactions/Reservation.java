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
public class Reservation {
    
    private String title;
    private Tenant tn;
    private String month;
    private int nbP;
    private int nbN;
    
    /**
     * Builder in order to make reservations
     *
     * @param titre : reservation title
     * @param t : reservation tenant
     * @param m : reservation month
     * @param nbPers : reservation's number of person
     * @param nbNuit : reservation's number of night
     */
    public Reservation(String titre, Tenant t, String m, int nbPers, int nbNuit){
        title = titre;
        tn = t;
        month = m;
        nbN = nbNuit;
        nbP = nbPers;
    }
    
    /**
     * Method that permit to get the reservation title
     *
     * @return title : reservation title
     */
    public String getTitle(){
        return title;
    }
    
    /**
     * Method that permit to get the reservation's number of person
     *
     * @return nbP : number of persons
     */
    public int getnbPersReserv(){
        return nbP;
    }
    
    /**
     * Methos that permit to get the reservation's number of nights
     *
     * @return nbN : number of nights
     */
    public int getNbNuitReserv(){
        return nbN;
    }
    
    /**
     * Method that permit to get the reservation tenant
     *
     * @return tn : reservation tenant
     */
    public Tenant getTenant(){
        return tn;
    }
    
    /**
     * Method that permit to get the reservation month
     *
     * @return month : reservation month
     */
    public String getMonth(){
        return month;
    }
    
    /**
     * Override of the toString method to display a reservation with all their informations
     *
     * @return
     */
    @Override
    public String toString(){
        return title + " for " + month + " for " + nbP + " person(s) and " + nbN + " night(s)";
    }
    
}
