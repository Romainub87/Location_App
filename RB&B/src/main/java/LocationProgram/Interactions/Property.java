/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LocationProgram.Interactions;

import LocationProgram.Users.Owner;

/**
 *
 * @author romai
 */
public class Property {

    private PropertyType type;
    private String adresse;
    private String ville;
    private Owner proprio;

    /**
     * Builder in order to make properties
     *
     * @param proprio : property owner
     * @param type : type of property (MAISON, APPARTEMENT, VILLA)
     * @param adresse : property adress
     * @param ville : property city
     */
    public Property(Owner proprio, String type, String adresse, String ville) {
        type = type.toUpperCase();
        switch (type) {
            case "HOUSE":
            case "MAISON":
                this.type = PropertyType.MAISON;
                break;
            case "APARTMENT":
            case "APPARTEMENT":
                this.type = PropertyType.APPARTEMENT;
                break;
            case "VILLA":
                this.type = PropertyType.VILLA;
                break;
            default:
                System.out.println("Property type not supported");
                break;
        }
        this.adresse = adresse;
        this.ville = ville;
        this.proprio = proprio;
        if (!type.equals(PropertyType.APPARTEMENT.name()) && !type.equals(PropertyType.VILLA.name()) && !type.equals(PropertyType.MAISON.name())) {
            System.err.println("Property type not supported, it has not been added");
        } else if (type.equals(PropertyType.APPARTEMENT.name()) || type.equals(PropertyType.VILLA.name()) || type.equals(PropertyType.MAISON.name())) {
            proprio.AjouterProperty(this);
        }
    }

    /**
     * Method that permit to get the property type
     *
     * @return type : property type
     */
    public PropertyType getType() {
        return type;
    }

    /**
     * Method that permit to get the property adress
     *
     * @return adresse : property adress
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Method that permit to get the property city
     *
     * @return ville : property city
     */
    public String getVille() {
        return ville;
    }
    
    /**
     * Method that permit to get the property owner
     *
     * @return proprio : property owner
     */
    public Owner getOwner(){
        return proprio;
    }
    
    /**
     * Override of the toString method that permit to display a property with all their informations
     *
     * @return
     */
    @Override 
    public String toString(){
        return type + " situé à " + ville + ", " + adresse;
    }
}
