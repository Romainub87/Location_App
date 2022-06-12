/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LocationProgram.Interactions;

import java.util.Comparator;

/**
 *
 * @author romai
 */
public class ComparatorBid implements Comparator<Bid>{

    /**
     * Method that permit to sort the bid from the lesser to the bigger in a bid list
     *
     * @param o1 : first bid
     * @param o2 : second bid
     * @return int negative, positive or null
     */
    @Override
    public int compare(Bid o1, Bid o2) {
        return o2.getMontant() - o1.getMontant();
    }
    
    
}
