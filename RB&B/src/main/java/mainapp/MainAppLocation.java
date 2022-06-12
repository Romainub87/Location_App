/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainapp;

import LocationProgram.Interactions.Bid;
import LocationProgram.Interactions.Location;
import LocationProgram.Interactions.Month;
import LocationProgram.Interactions.Property;
import LocationProgram.Interactions.Reservation;
import LocationProgram.Users.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author romai
 */
public class MainAppLocation {

    private static boolean quit; // Permet de savoir si l'utilisateur a quitté l'application
    private static Scanner scan; // Permet de parcourir la sortie du terminal
    private static boolean waitingForString; // Permet de savoir si l'utilisateur a rentré une réponse en sortie
    private int IDNumber; // number referencing the account choosen
    private static String stringReading; // Permet de lire ce que l'utilisateur a rentré (texte)
    private static int numberReading; // Permet de lire ce que l'utilisateur a rentré (chiffre)
    private static String currentID; // Permet de recupérer le nom de l'utilisateur actif
    private static User currentUser; // Permet de récupérer l'utilisateur actif

    private static final HashSet<String> Mois = new HashSet<>();
    public static HashSet<Admin> listAdmin = new HashSet<>();
    public static HashSet<Owner> listOwner = new HashSet<>();
    public static HashSet<Tenant> listTenant = new HashSet<>();
    public static HashSet<Location> listLocation = new HashSet<>();
    public static ArrayList<Reservation> listReservation = new ArrayList<>();

    /**
     * Function that launches the application
     *
     * @param args
     */
    public static void main(String[] args) {
        scan = new Scanner(System.in);
        AppLibrary();
        MainAppLocation app;
        int IDNumber = ARR_connectionAccount();
        while (IDNumber == -1) {
            ARR_createAccount(stringReading);
            IDNumber = ARR_connectionAccount();
        }
        app = new MainAppLocation(IDNumber); // ARR_launch
        app.run();
    }

    private static int verifID(String s) {

        for (Owner ow : listOwner) {
            for (Tenant tn : listTenant) {
                if (ow.getName().toUpperCase().equals(s) && tn.getName().toUpperCase().equals(s)) {
                    System.out.println("Which account you want to go with ?");
                    System.out.println("1. Tenant");
                    System.out.println("2. Owner");
                    stringReading = scan.nextLine();
                    verifyInt();
                    switch (numberReading) {
                        case 1 -> {
                            currentUser = tn;
                            return 1;
                        }
                        case 2 -> {
                            currentUser = ow;
                            return 0;
                        }
                    }
                }
            }
        }
        for (Owner ow : listOwner) {
            if (ow.getName().toUpperCase().equals(s)) {
                currentUser = ow;
                return 0;
            }
        }

        for (Tenant tn : listTenant) {
            if (tn.getName().toUpperCase().equals(s)) {
                currentUser = tn;
                return 1;
            }
        }

        for (Admin ad : listAdmin) {
            if (ad.getPseudo().toUpperCase().equals(s)) {
                currentUser = ad;
                return 2;
            }
        }

        return -1;
    }

    private static int ARR_connectionAccount() {
        int IDNumber = -1;
        System.out.println("please, write your identifier");
        stringReading = scan.nextLine();
        currentID = stringReading.trim();
        return verifID(stringReading.toUpperCase());
    }

    private static void ARR_createAccount(String IDWrong) {
        System.out.println("the identifier is incorrect, would you like to create an account with this ID or retry ?");
        System.out.println("0 - Create account");
        System.out.println("1 - Retry");
        System.out.println("2 - Quit");
        stringReading = scan.nextLine();
        waitingForString = true;
        verifyInt();
        switch (numberReading) {
            case 0 -> {
                System.out.println("You will create an account with the identifier " + IDWrong + ", do you want to be an owner or a tenant ? ");
                System.out.println("0 - A tenant");
                System.out.println("1 - An owner");
                System.out.println("2 - tenant and owner");
                System.out.println("3 - Quit");
                stringReading = scan.nextLine();
                waitingForString = true;
                try {
                    numberReading = Integer.parseInt(stringReading);
                } catch (NumberFormatException nfe) {
                    System.err.println("Error: please enter an integer.");
                    ARR_createAccount(IDWrong);
                }
                switch (numberReading) {
                    case 0 -> {
                        System.out.println("Please enter your age");
                        stringReading = scan.nextLine();
                        verifyInt();
                        int age = numberReading;
                        while (!stringReading.contains("@") && !stringReading.contains(".")) {
                            System.out.println("Please enter a valid email");
                            stringReading = scan.nextLine();
                        }
                        String mail = stringReading;
                        listTenant.add(new Tenant(IDWrong, age, mail));
                    }

                    case 1 -> {
                        System.out.println("Please enter your age");
                        stringReading = scan.nextLine();
                        verifyInt();
                        int age = numberReading;
                        while (!stringReading.contains("@") && !stringReading.contains(".")) {
                            System.out.println("Please enter a valid email");
                            stringReading = scan.nextLine();
                        }
                        String mail = stringReading;
                        listOwner.add(new Owner(IDWrong, age, mail));
                    }
                    case 2 -> {
                        System.out.println("Please enter your age");
                        stringReading = scan.nextLine();
                        verifyInt();
                        int age = numberReading;
                        while (!stringReading.contains("@") && !stringReading.contains(".")) {
                            System.out.println("Please enter a valid email");
                            stringReading = scan.nextLine();
                        }
                        String mail = stringReading;
                        listTenant.add(new Tenant(IDWrong, age, mail));
                        listOwner.add(new Owner(IDWrong, age, mail));
                    }
                    case 3 -> {
                        ARR_Quit();
                    }
                    default ->
                        System.err.println("Error: no such menu item.");
                }

            }
            case 1 -> {
            }
            case 2 ->
                ARR_Quit();
            default -> {
                System.err.println("Error: no such menu item.");
                ARR_createAccount(IDWrong);
            }
        }
    }

    private MainAppLocation(int Id) {
        quit = false;
        IDNumber = Id;
        waitingForString = false;
    }

    private static void ARR_Quit() {
        System.exit(0);
    }

    private void run() {
        while (!quit) {
            switch (IDNumber) {
                case 0 ->
                    ownerAction();
                case 1 ->
                    tenantAction();
                case 2 ->
                    adminAction();
            }
        }
    }

    private void ownerAction() {
        boolean change = false;
        if (!waitingForString) {
            ownerPrompt();
        }
        stringReading = scan.nextLine();
        if (waitingForString) {
            try {
                numberReading = Integer.parseInt(stringReading);
            } catch (NumberFormatException nfe) {
                System.err.println("Error, please enter an integer");
                ownerAction();
            }
            switch (numberReading) {
                case 0 ->
                    ARR_Quit();
                case 1 ->
                    StartSession();
                case 2 -> {
                    DisplayPropertyList();
                }
                case 3 -> {
                    DisplayLocationList();
                }
                case 4 -> {
                    DisplayMoney();
                }
                case 5 -> {
                    CreateProperty();
                }
                case 6 -> {
                    CreateLocation();
                }
                case 7 -> {
                    DeleteProperty();
                    if (stringReading.equals("stop")) {
                        break;
                    }
                }
                case 8 -> {
                    System.out.println("Are you sure ?");
                    System.out.println("0. No");
                    System.out.println("1. Yes");
                    stringReading = scan.nextLine();
                    verifyInt();
                    switch (numberReading) {
                        case 0 -> {
                            break;
                        }
                        case 1 -> {
                            ChangeDescription();
                        }
                    }
                }
                case 9 -> {
                    DisplayBidsByLocation();
                }
                case 10 -> {
                    DisplayReservByProperty();
                }
                case 11 -> {
                    int amount = 0;
                    for (Tenant tn : listTenant) {
                        if (currentUser.getName().toUpperCase().equals(tn.getName().toUpperCase())) {
                            while (amount > currentUser.getMoney() || amount <= 0) {
                                System.out.println("Enter the amount of money you want to transfer to your tenant account");
                                stringReading = scan.nextLine();
                                verifyInt();
                                amount = numberReading;

                            }
                            tn.AddMoney(amount);
                            currentUser.RetireMoney(amount);
                        }
                    }
                }
                case 12 -> {
                    for (Tenant tn : listTenant) {
                        if (currentUser.getName().toUpperCase().equals(tn.getName().toUpperCase())) {
                            change = true;
                            System.out.println("You are on the tenant profile !");
                            currentUser = tn;
                            tenantPrompt();
                            tenantAction();
                        }
                    }
                    if (!change) {
                        System.out.println("Your tenant account is not link to a owner account");
                    }
                }
                default -> {
                    System.err.println("Error no more action");
                }
            }
            if (!change) {
                ownerPrompt();
            }
        }
    }

    private void tenantAction() {
        boolean change = false;
        if (!waitingForString) {
            tenantPrompt();
        }
        stringReading = scan.nextLine();
        if (waitingForString) {
            try {
                numberReading = Integer.parseInt(stringReading);
            } catch (NumberFormatException nfe) {
                System.err.println("Error, please enter an integer");
                tenantAction();
            }
            switch (numberReading) {
                case 0 ->
                    ARR_Quit();
                case 1 ->
                    StartSession();
                case 2 -> {
                    System.out.println("Enter the month you would like to see");
                    stringReading = scan.nextLine().toUpperCase();
                    DisplayAllLocationsByMonth(stringReading);
                }
                case 3 -> {
                    AddMoney();
                }
                case 4 -> {
                    DisplayMoney();
                }
                case 5 -> {
                    AddBidOnLoc();
                }
                case 6 -> {
                    ModifyProfile();
                }
                case 7 -> {
                    DisplayCurrentBids();
                }
                case 8 -> {
                    DisplayClosedBids();
                }
                case 9 -> {
                    if (currentUser.getReservation().isEmpty()) {
                        System.out.println("No reservations actually");
                    } else {
                        for (Reservation r : currentUser.getReservation()) {
                            System.out.println(r.toString());
                        }
                    }
                }
                case 10 -> {
                    for (Owner ow : listOwner) {
                        if (currentUser.getName().toUpperCase().equals(ow.getName().toUpperCase())) {
                            change = true;
                            System.out.println("You are on the owner profile !");
                            currentUser = ow;
                            ownerPrompt();
                            ownerAction();
                        }
                    }
                    if (!change) {
                        System.out.println("Your tenant account is not link to a owner account");
                    }
                }
                default -> {
                    System.err.println("Error no more action");
                }
            }
            if (!change) {
                tenantPrompt();
            }
        }
    }

    private void adminAction() {
        if (!waitingForString) {
            adminPrompt();
        }
        stringReading = scan.nextLine();
        if (waitingForString) {
            try {
                numberReading = Integer.parseInt(stringReading);
            } catch (NumberFormatException nfe) {
                System.err.println("Error, please enter an integer");
                adminAction();
            }
            switch (numberReading) {
                case 0 -> {
                    ARR_Quit();
                }
                case 1 -> {
                    StartSession();
                }
                case 2 -> {
                    DisplayAllNameByType();
                }
                case 3 -> {
                    DisplayAllUsers();
                }
                case 4 -> {
                    DisplayMoney();
                }
                case 5 -> {
                    CreateAdmin();
                }
                case 6 -> {
                    DeleteUser();
                }
                case 7 -> {
                    ModifyProfile();
                }
                case 8 -> {
                    DisplayListLocationByMonth();
                }
                case 9 -> {
                    CloseLocation();
                }
                case 10 -> {
                    DisplayBidsByLocOrByTenant();
                }
                case 11 -> {
                    ModifyUser();
                }
                case 12 -> {
                    ModifyLocDescription();
                }
                case 13 -> {
                    DisplayAllReservation();
                }
                default -> {
                    System.err.println("Error no more action");
                }
            }
            adminPrompt();
        }
    }

    private void DisplayReservByProperty() {
        for (Location loc : currentUser.getListLocation()) {
            if (!loc.getListReservLoc().isEmpty()) {
                System.out.println("Reservation for " + loc.getTitreLoc());
                for (Reservation r : loc.getListReservLoc()) {
                    System.out.println("- " + r.toString() + " made by " + r.getTenant().getName() + ". Email : " + r.getTenant().email());
                }
            } else {
                System.out.println("No reservation for " + loc.getTitreLoc());
            }
        }
    }

    private void DisplayAllReservation() {
        System.out.println("Here the list of all reservations on the application :");
        for (Tenant tn : listTenant) {
            if (!tn.getReservation().isEmpty()) {
                System.out.println(tn.getName());
                for (Reservation r : tn.getReservation()) {
                    System.out.println("- " + r.toString());
                }
            } else {
                System.out.println(tn.getName() + " don't have any reservation for the moment");
            }
        }
    }

    private void DisplayBidsByLocOrByTenant() {
        System.out.println("You want to see by tenant or by location ?");
        System.out.println("0. By tenant");
        System.out.println("1. By location");
        System.out.println("2. Cancel");
        stringReading = scan.nextLine();
        verifyInt();
        switch (numberReading) {
            case 0 -> {
                boolean vide = false;
                System.out.println("List of tenants :");
                for (Tenant tn : listTenant) {
                    System.out.println("- " + tn.getName());
                }
                System.out.println("For which tenant you want to see the bids ?");
                stringReading = scan.nextLine().toUpperCase();
                for (Location loc : listLocation) {
                    for (String m : loc.getValidMonth()) {
                        for (Tenant tn : listTenant) {
                            if (tn.getName().toUpperCase().equals(stringReading)) {
                                if (tn.getBids().isEmpty()) {
                                    vide = true;
                                }
                                for (Bid bid : loc.getListEnchereLoc(m)) {
                                    if (bid.getTenant().equals(tn)) {
                                        System.out.println(bid.getMontant() + " on " + loc.getTitreLoc() + " for " + m);
                                    }
                                }
                            }
                        }
                    }
                }
                if (vide) {
                    System.out.println("No bids made by him for the moment");
                }
            }
            case 1 -> {
                boolean vide = false;
                System.out.println("List of locations :");
                for (Location loc : listLocation) {
                    System.out.println("- " + loc.getTitreLoc());
                }
                System.out.println("For which location you want to see the bids ?");
                stringReading = scan.nextLine().toUpperCase();
                for (Location loc : listLocation) {
                    if (stringReading.equals(loc.getTitreLoc().toUpperCase())) {
                        System.out.println("List of bids for " + loc.getTitreLoc() + " : ");
                        for (String m : loc.getValidMonth()) {
                            if (loc.getListEnchereLoc(m).isEmpty()) {
                                vide = true;
                            }
                            for (Bid bid : loc.getListEnchereLoc(m)) {
                                if (bid.equals(loc.getCurrentBid(m))) {
                                    System.out.println("- " + bid.getMontant() + " euros by " + bid.getTenant().getName() + " for " + m + " (He is the actual winner for " + m + ")");
                                } else {
                                    System.out.println("- " + bid.getMontant() + " euros by " + bid.getTenant().getName() + " for " + m);
                                }
                            }
                        }
                    }
                }
                if (vide) {
                    System.out.println("No bids on for the moment");
                }
            }
            case 2 -> {
                break;
            }
            default -> {
                break;
            }
        }
    }

    private void ModifyLocDescription() {
        for (Location loc : listLocation) {
            System.out.println("- " + loc.getTitreLoc() + " for " + loc.getValidMonth() + ", description : " + loc.getDescription());
        }
        System.out.println("Which location you want to modify ? (tap the name)");
        stringReading = scan.nextLine();
        for (Location loc : listLocation) {
            if (stringReading.equals(loc.getTitreLoc())) {
                System.out.println("Enter a new description or tap stop to cancel");
                stringReading = scan.nextLine();
                if (!stringReading.equals("stop")) {
                    loc.setDescription(stringReading);
                    System.out.println("New description for " + loc.getTitreLoc() + " added !");
                } else {
                    break;
                }
            }
        }
    }

    private void ModifyUser() {
        System.out.println("Which type of user you want to modify ?");
        System.out.println("0. Tenant");
        System.out.println("1. Owner");
        stringReading = scan.nextLine();
        verifyInt();
        switch (numberReading) {
            case 0 -> {
                for (Tenant tn : listTenant) {
                    System.out.println("- " + tn.getName());
                }
                System.out.println("Which tenant you want to modify ?");
                stringReading = scan.nextLine();
                for (Tenant tn : listTenant) {
                    if (stringReading.toUpperCase().equals(tn.getName().toUpperCase())) {
                        System.out.println("Enter a new pseudo for " + tn.getName() + " or stop to cancel");
                        stringReading = scan.nextLine();
                        while (stringReading.toUpperCase().equals(tn.getName().toUpperCase())) {
                            System.out.println("Incorrect pseudo, enter a new pseudo");
                            stringReading = scan.nextLine();
                        }
                        tn.setName(stringReading);
                        System.out.println("Pseudo changed !");
                    }

                }
            }
            case 1 -> {
                for (Owner ow : listOwner) {
                    System.out.println("- " + ow.getName());
                }
                System.out.println("Which owner you want to modify ?");
                stringReading = scan.nextLine().toUpperCase();
                for (Owner ow : listOwner) {
                    if (stringReading.toUpperCase().equals(ow.getName().toUpperCase())) {
                        System.out.println("Enter a new pseudo for " + ow.getName() + " or stop to cancel");
                        stringReading = scan.nextLine();
                        while (stringReading.toUpperCase().equals(ow.getName().toUpperCase())) {
                            System.out.println("Incorrect pseudo, enter a new pseudo");
                            stringReading = scan.nextLine();
                        }
                        ow.setName(stringReading);
                        System.out.println("Pseudo changed !");
                    }
                }
            }
        }
    }

    private void DisplayListLocationByMonth() {
        HashSet<String> MoisLoc = new HashSet<>();
        for (Month m : Month.values()) {
            Mois.add(m.name());
        }
        for (String m : Mois) {
            for (Location loc : listLocation) {
                if (loc.getValidMonth().contains(m)) {
                    MoisLoc.add(m);
                }
            }
        }
        for (String m : MoisLoc) {
            System.out.println("Liste of locations for " + m);
            for (Location loc : listLocation) {
                if (loc.getValidMonth().contains(m)) {
                    System.out.println("- " + loc.getTitreLoc());
                }
            }
        }
    }

    private void AddMoney() {
        System.out.println("How much do you want to add to your account?");
        stringReading = scan.nextLine();
        waitingForString = true;
        verifyInt();
        AjouterArgent(numberReading);
    }

    private void CloseLocation() {
        int benefice = 0;
        int realPrice = 0;
        int topBid = 0;
        System.out.println("Liste des locations en cours :");
        for (Location loc : listLocation) {
            if (!loc.getValidMonth().isEmpty()) {
                System.out.println(loc.toString());
                for (String month : loc.getValidMonth()) {
                    for (Bid bid : loc.getListEnchereLoc(month)) {
                        if (bid.equals(loc.getCurrentBid(month))) {
                            topBid = loc.getCurrentBid(month).getMontant();
                            realPrice = loc.getRealPrice(bid.getNbPersBid(), bid.getNbNuitBid());
                            benefice = loc.getCurrentBid(month).getMontant() - loc.getRealPrice(bid.getNbPersBid(), bid.getNbNuitBid());
                        }
                    }
                }
            } else {
                System.out.println("Aucune location à fermer");
                break;
            }
        }
        System.out.println("Enter the name of the location you want to close");
        stringReading = scan.nextLine().toUpperCase();
        String name = stringReading;
        for (Location loc : listLocation) {
            if (name.trim().equals(loc.getTitreLoc().toUpperCase())) {
                System.out.println("Enter for what month you want to close");
                stringReading = scan.nextLine();
                String mois = stringReading;
                for (String month : loc.getValidMonth()) {
                    if (mois.toUpperCase().equals(month)) {
                        for (Bid bid : loc.getListEnchereLoc(month)) {
                            if (bid.equals(loc.getCurrentBid(month))) {
                                bid.getTenant().AddReservation(new Reservation(loc.getTitreLoc(), bid.getTenant(), month, bid.getNbPersBid(), bid.getNbNuitBid()));
                                loc.addReservForLoc(new Reservation(loc.getTitreLoc(), bid.getTenant(), month, bid.getNbPersBid(), bid.getNbNuitBid()));
                                bid.getTenant().RetireBidMoney(bid);
                            } else {
                                bid.getTenant().ActionFrais();
                            }

                        }
                        System.out.println("Location for " + month + " closed ");
                        for (Owner ow : listOwner) {
                            if (ow.getListLocation().contains(loc)) {
                                for (Bid bid : loc.getListEnchereLoc(month)) {
                                    if (bid.equals(loc.getCurrentBid(month))) {

                                        if (topBid < realPrice) {
                                            ow.AddMoney(topBid * 75 / 100);
                                        } else {
                                            ow.AddMoney(realPrice);
                                            ow.AddMoney(benefice * 75 / 100);
                                        }
                                    }
                                }
                            }
                        }
                        for (Admin am : listAdmin) {
                            if (!loc.getListEnchereLoc(month).isEmpty()) {
                                for (Bid bid : loc.getListEnchereLoc(month)) {
                                    am.ActionFrais();
                                    if (bid.equals(loc.getCurrentBid(month))) {
                                        if (topBid < realPrice) {
                                            am.AddMoney(topBid * 25 / 100);
                                        } else {
                                            am.RetireMoney(benefice * 75 / 100);
                                        }
                                    }
                                }
                            } else {
                                am.AddMoney(10);
                            }
                        }
                        if (loc.getListEnchereLoc(month).isEmpty()) {
                            loc.getOwner().RetireMoney(10);
                        }
                        loc.EnleverMois(month);
                        loc.CloseBid();
                    }
                }
            }
        }
    }

    private void DeleteProperty() {
        if (currentUser.getListProperty().isEmpty()) {
            System.out.println("You don't have any property !");
        } else {
            for (Property pr : currentUser.getListProperty()) {
                System.out.println(pr.toString());
            }
            System.out.println("Enter the property you want to delete or tap stop to cancel");
            stringReading = scan.nextLine();
            for (Property pr : currentUser.getListProperty()) {
                if (pr.getAdresse().equals(stringReading)) {
                    currentUser.RemoveProperty(pr);
                    for (Location loc : currentUser.getListLocation()) {
                        if (loc.getProperty().equals(pr)) {
                            currentUser.RemoveLocation(loc);
                        }
                    }
                }
            }
        }
    }

    private void DisplayBidsByLocation() {
        if (currentUser.getListLocation().isEmpty()) {
            System.out.println("You don't have any location !");
        } else {
            for (Location loc : currentUser.getListLocation()) {
                System.out.println("Historic of bids for " + loc.toString());
                for (String m : loc.getValidMonth()) {
                    System.out.println("List of bids for " + m);
                    for (Bid bid : loc.getListEnchereLocSortedByBidAmount(m)) {
                        if (bid.getMontant() == loc.getCurrentBid(m).getMontant()) {
                            System.out.println("- " + bid.getMontant() + " euros by " + bid.getTenant().getName() + " (It's the top bid !)");
                        } else {
                            System.out.println("- " + bid.getMontant() + " euros by " + bid.getTenant().getName());
                        }
                    }
                }
            }
        }
    }

    private void DisplayClosedBids() {
        for (Location loc : listLocation) {
            if (!loc.getOpen()) {
                for (String m : loc.getMonthLoc()) {
                    System.out.println(m);
                    if (!loc.getListEnchereLoc(m).isEmpty()) {
                        if (loc.getCurrentBid(m).getTenant().equals(currentUser)) {
                            System.out.println(loc.getCurrentBid(m).getMontant() + " euros on " + loc.getTitreLoc() + " for " + m + " (You are the winner !)");
                        } else {
                            for (Bid bid : loc.getListEnchereLoc(m)) {
                                if (bid.getTenant().equals(currentUser)) {
                                    System.out.println(loc.getCurrentBid(m).getMontant() + " euros on " + loc.getTitreLoc() + " for " + m + " (You have lost this location...)" + " Your top bid : " + bid.getMontant() + " euros");
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    private void DisplayCurrentBids() {
        if (currentUser.getBids().isEmpty()) {
            System.out.println("No bids for the moment");
        } else {
            for (Location loc : listLocation) {
                for (String m : loc.getValidMonth()) {
                    if (!loc.getListEnchereLoc(m).isEmpty()) {
                        if (loc.getCurrentBid(m).getTenant().equals(currentUser)) {
                            System.out.println(loc.getCurrentBid(m).getMontant() + " euros on " + loc.getTitreLoc() + " for " + m + " (You are the top bid actually !)");
                        } else {
                            for (Bid bid : loc.getListEnchereLoc(m)) {
                                if (bid.getTenant().equals(currentUser)) {
                                    System.out.println(loc.getTitreLoc() + " for " + m + " (You are not the top bid actually !) The best is " + loc.getCurrentBid(m).getMontant() + " euros by " + loc.getCurrentBid(m).getTenant().getName());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void CreateProperty() {
        System.out.println("What is the type of your property ?");
        stringReading = scan.nextLine();
        String type = stringReading;
        System.out.println("What is the address of your property ?");
        stringReading = scan.nextLine();
        String adresse = stringReading.trim();
        System.out.println("In which city is your property located ?");
        stringReading = scan.nextLine();
        String ville = stringReading;
        AjouterPropriete(new Property((Owner) currentUser, type, adresse, ville));
        System.out.println("Your property has been added to your list of properties !");
    }

    private void ChangeDescription() {
        if (currentUser.getListLocation().isEmpty()) {
            System.out.println("You don't have any location !");
        } else {
            DisplayLocationList();
            System.out.println("Enter the name of the location you want to modify the description");
            stringReading = scan.nextLine();
            String loc = stringReading;
            for (Location location : currentUser.getListLocation()) {
                if (location.getTitreLoc().equals(loc)) {
                    System.out.println("Enter a new description for your location offer");
                    stringReading = scan.nextLine();
                    location.setDescription(stringReading);
                    System.out.println("New description added for this location : " + location.getTitreLoc());
                }
            }
        }
    }

    private static void verifyInt() {
        try {
            numberReading = Integer.parseInt(stringReading);
        } catch (NumberFormatException nfe) {
            System.err.println("Error, please enter an integer");
        }
    }

    private void DisplayLocationList() {
        System.out.println("Votre liste de locations :");
        if (!currentUser.getListLocation().isEmpty()) {
            for (Location loc : currentUser.getListLocation()) {
                System.out.println(loc.toString());
            }
        } else {
            System.out.println("You don't have any location offers");
        }
    }

    private void CreateLocation() {
        System.out.println("Enter the property address you want to rent out");
        stringReading = scan.nextLine();
        String adresse = stringReading.trim();
        if (verifyProperty(adresse)) {
            System.out.println("Enter the rental title");
            stringReading = scan.nextLine();
            String titre = stringReading;
            System.out.println("Enter the rental terms");
            stringReading = scan.nextLine();
            String modalités = stringReading;
            System.out.println("Enter the starting price of the rental");
            stringReading = scan.nextLine();
            verifyInt();
            int prix = numberReading;
            System.out.println("Enter the maximum number of tenants");
            stringReading = scan.nextLine();
            verifyInt();
            int nbMaxPers = numberReading;
            HashSet<String> MoisValid = new HashSet<>();
            System.out.println("Enter at least 1 month to rent your location");
            boolean arret = false;
            stringReading = scan.nextLine().toUpperCase();
            for (Month m : Month.values()) {
                Mois.add(m.name());
            }
            System.out.println(Mois);
            if (!arret) {
                while (!stringReading.equals("stop")) {
                    if (Mois.contains(stringReading)) {
                        MoisValid.add(stringReading);
                        System.out.println(MoisValid);
                        System.out.println("Add an other month or enter stop to quit");
                        stringReading = scan.nextLine().toUpperCase();
                    } else if (stringReading.toLowerCase().equals("stop")) {
                        break;
                    } else if (!Mois.contains(stringReading)) {
                        System.out.println("Unvalid month");
                        System.out.println("Add an other month or enter stop to quit");
                        stringReading = scan.nextLine().toUpperCase();
                    }
                }
                AjouterLocation(new Location(recupProperty(adresse), titre, prix, modalités, nbMaxPers, (Owner) currentUser, MoisValid));
                System.out.println("Location added !");
            }
        } else {
            System.out.println("You do not own this property.");
        }
    }

    private void ModifyProfile() {
        System.out.println("What info do you want to change ?");
        System.out.println("0. Your login");
        System.out.println("1. Your age");
        stringReading = scan.nextLine();
        verifyInt();
        switch (numberReading) {
            case 0 -> {
                System.out.println("Enter the new login");
                stringReading = scan.nextLine();
                currentUser.setName(stringReading.trim());
            }
            case 1 -> {
                System.out.println("Enter your new age");
                stringReading = scan.nextLine();
                verifyInt();
                currentUser.setAge(numberReading);
            }
        }
    }

    private void DeleteUser() {
        System.out.println("Which type of user you want to delete ?");
        System.out.println("0 - A tenant");
        System.out.println("1 - An owner");
        waitingForString = true;
        stringReading = scan.nextLine();
        verifyInt();
        switch (numberReading) {
            case 0 -> {
                DeleteTenant();
            }
            case 1 -> {
                DeleteOwner();
            }
        }
    }

    private void AddBidOnLoc() {
        for (Location loc : listLocation) {
            if (!loc.getValidMonth().isEmpty()) {
                System.out.println(loc.toString());
            }
        }
        System.out.println("Which Location would you bid ? (enter his name)");
        stringReading = scan.nextLine();
        String titre = stringReading;
        boolean trouve = false;
        String moisChoisi = null;
        for (Location loc : listLocation) {
            if (loc.getTitreLoc().equals(titre) && !trouve) {
                System.out.println("Which month ?");
                stringReading = scan.nextLine();
                while (moisChoisi == null) {
                    if (loc.getValidMonth().contains(stringReading.toUpperCase())) {
                        moisChoisi = stringReading;
                        trouve = true;
                    } else {
                        System.out.println("Unvalid month");
                        System.out.println("Enter an other month");
                        stringReading = scan.nextLine();
                    }

                }
            }
            if (trouve) {
                System.out.println("Enter the number of people or -1 to quit");
                stringReading = scan.nextLine();
                verifyInt();
                if (numberReading == -1) {
                    break;
                }
                while (numberReading >= loc.getNbPersMax() || numberReading < 1) {
                    System.out.println("Unvalid number for this location !");
                    System.out.println("Enter a new number or tap -1 to quit");
                    stringReading = scan.nextLine();
                    verifyInt();
                    if (numberReading == -1) {
                        break;
                    }
                }
                if (numberReading == -1) {
                    break;
                }
                int nbPers = numberReading;
                System.out.println("Enter the number of night or -1 to quit");
                stringReading = scan.nextLine();
                verifyInt();
                if (numberReading == -1) {
                    break;
                }
                while (numberReading > 10 || numberReading < 1 || numberReading == -1) {
                    System.out.println("Number of night is unvalid");
                    System.out.println("Enter a number of nights between 1 and 10");
                    stringReading = scan.nextLine();
                    verifyInt();
                    if (numberReading == -1) {
                        break;
                    }
                }
                if (numberReading == -1) {
                    break;
                }
                int nbNuit = numberReading;
                System.out.println("Here is the minimum price you can offer for this location.");
                System.out.println(loc.CalculRentPrice(nbPers, nbNuit));
                System.out.println("Enter your bid amount ");
                stringReading = scan.nextLine();
                verifyInt();
                int newBid = numberReading;
                if (!loc.getListEnchereLoc(moisChoisi.toUpperCase()).isEmpty()) {
                    if (loc.getCurrentBid(moisChoisi.toUpperCase()).getTenant().equals(currentUser)) {
                        currentUser.RetireBidOnVirtualWallet(newBid);
                    }
                }
                if (newBid < currentUser.getVirtualWallet()) {
                    int price = loc.CalculRentPrice(nbPers, nbNuit) + 9;
                    if (newBid < price) {
                        while (newBid < price) {
                            System.out.println("Please enter an amount bigger than " + (price) + " or cancel writing -1");
                            stringReading = scan.nextLine();
                            verifyInt();
                            newBid = numberReading;
                            if (newBid == -1) {
                                System.out.println("Bid canceled");
                                break;
                            }
                        }
                    }
                    if (!loc.getListEnchereLoc(moisChoisi.toUpperCase()).isEmpty()) {
                        if (newBid != -1 && newBid > loc.getCurrentBid(moisChoisi.toUpperCase()).getMontant()) {
                            loc.AddBid(moisChoisi.toUpperCase(), new Bid((Tenant) currentUser, newBid, nbPers, nbNuit));
                            currentUser.AddBidToMyList(new Bid((Tenant) currentUser, newBid, nbPers, nbNuit));
                            System.out.println("Your bid has been added !");

                        } else {
                            System.out.println("A bigger bid exist for this location !");
                        }
                    } else {
                        loc.AddBid(moisChoisi.toUpperCase(), new Bid((Tenant) currentUser, newBid, nbPers, nbNuit));
                        currentUser.AddBidToMyList(new Bid((Tenant) currentUser, newBid, nbPers, nbNuit));
                        System.out.println("Your bid has been added !");
                    }
                    break;
                } else {
                    System.out.println("You don't have enought money or you are top bid on others locations, would you want to add the amount of your bid ?");
                    System.out.println("0. No");
                    System.out.println("1. Yes");
                    stringReading = scan.nextLine();
                    verifyInt();
                    switch (numberReading) {
                        case 0 -> {
                            System.out.println("Bid canceled");
                            break;
                        }
                        case 1 ->
                            currentUser.AddMoney(newBid);
                    }
                    break;
                }

            }
        }
        if (!trouve) {
            System.out.println("This location doesn't exist or is closed");
        }
    }

    private void DeleteTenant() {
        for (Tenant tn : listTenant) {
            System.out.println(tn.getName());
        }
        System.out.println("Which user you want to remove ? (tap his name)");
        stringReading = scan.nextLine();
        for (Tenant tn : listTenant) {
            if (stringReading.toUpperCase().contains(tn.getName().toUpperCase())) {
                listTenant.remove(tn);
                System.out.println("This user has been removed from the application");
                break;
            }
        }

    }

    private void DeleteOwner() {
        for (Owner ow : listOwner) {
            System.out.println(ow.getName());
        }
        System.out.println("Which user you want to remove ? (tap his name)");
        stringReading = scan.nextLine();
        for (Owner ow : listOwner) {
            if (stringReading.toUpperCase().contains(ow.getName().toUpperCase())) {
                listOwner.remove(ow);
                System.out.println("This user has been removed from the application");
                break;
            }
        }
    }

    private void AjouterLocation(Location loc) {
        if (IDNumber == 0) {
            for (Owner ow : listOwner) {
                if (ow.getName().equals(currentID)) {
                    ow.AjouterLocation(loc);
                }
            }
        }
    }

    private Property recupProperty(String adresse) {
        Property recupPr = null;
        for (Owner ow : listOwner) {
            if (ow.getName().equals(currentID)) {
                for (Property pr : ow.getListProperty()) {
                    if (pr.getAdresse().equals(adresse)) {
                        recupPr = pr;
                    }
                }
            }
        }
        return recupPr;
    }

    private boolean verifyProperty(String adresse) {
        for (Owner ow : listOwner) {
            if (ow.getName().equals(currentID)) {
                for (Property pr : ow.getListProperty()) {
                    if (pr.getAdresse().equals(adresse)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void DisplayAllLocationsByMonth(String m) {
        HashSet<String> presentMonth = new HashSet<>();
        for (Location loc : listLocation) {
            for (String mois : loc.getValidMonth()) {
                presentMonth.add(mois);
            }
        }
        if (!presentMonth.contains(m)) {
            System.out.println("No locations for this month");
        } else {
            System.out.println("List of location offers for " + m + " : ");
            for (Location loc : listLocation) {
                if (loc.getValidMonth().contains(m)) {
                    if (loc.getListEnchereLoc(m).isEmpty()) {
                        System.out.println(loc.getTitreLoc() + ", no bid actually.");
                    } else {
                        System.out.println(loc.getTitreLoc() + ", Current bid : " + loc.getCurrentBid(m).getMontant() + " euros by " + loc.getCurrentBid(m).getTenant());
                    }
                }
            }
        }

    }

    private void DisplayPropertyList() {
        if (currentUser.getListProperty().isEmpty()) {
            System.out.println("You don't have any properties");
        }
        for (Owner ow : listOwner) {
            if (ow.getName().equals(currentID) && !currentUser.getListProperty().isEmpty()) {
                System.out.println("Your property list :");
                for (Property pr : ow.getListProperty()) {
                    System.out.println(pr.getType() + " located at " + pr.getVille() + ", " + pr.getAdresse());
                }
            }
        }
    }

    private void AjouterPropriete(Property property) {
        for (Owner ow : listOwner) {
            if (ow.getName().equals(currentID)) {
                ow.AjouterProperty(property);
            }
        }
    }

    private void DisplayAllUsers() {
        System.out.println("Administrators : ");
        for (Admin am : listAdmin) {
            System.out.println(am.getPseudo() + ", " + am.getAge() + " years old");
        }
        System.out.println("Owners : ");
        for (Owner ow : listOwner) {
            System.out.println(ow.getName() + ", " + ow.getAge() + " years old");
        }
        System.out.println("Tenants : ");
        for (Tenant tn : listTenant) {
            System.out.println(tn.getName() + ", " + tn.getAge() + " years old");
        }
    }

    private void DisplayAllNameByType() {
        switch (IDNumber) {
            case 0 -> {
                for (Owner ow : listOwner) {
                    System.out.println(ow.getName());
                }
            }
            case 1 -> {
                for (Tenant tn : listTenant) {
                    System.out.println(tn.getName());
                }
            }
            case 2 -> {
                for (Admin am : listAdmin) {
                    System.out.println(am.getPseudo());
                }
            }
            default -> {
            }
        }

    }

    private static void StartSession() {
        MainAppLocation app;
        int IDNumber = ARR_connectionAccount();
        while (IDNumber == -1) {
            ARR_createAccount(stringReading);
            IDNumber = ARR_connectionAccount();
        }
        app = new MainAppLocation(IDNumber); // ARR_launch
        app.run();
    }

    private void CreateAdmin() {
        System.out.println("Are you sure ?");
        System.out.println("0. Quit");
        System.out.println("1. Yes");
        waitingForString = true;
        stringReading = scan.nextLine();
        verifyInt();
        switch (numberReading) {
            case 0 -> {
                break;
            }
            case 1 -> {
                System.out.println("Enter the name and the surname of the new admin");
                waitingForString = true;
                stringReading = scan.nextLine();
                String prenomNom = stringReading.trim();
                System.out.println("Enter the pseudo of the new admin");
                waitingForString = true;
                stringReading = scan.nextLine();
                String pseudo = stringReading.trim();
                boolean used = false;
                for (Admin ad : listAdmin) {
                    if (ad.getPseudo().toUpperCase().equals(stringReading.toUpperCase())) {
                        System.err.println("Pseudo already use !");
                        used = true;
                    }
                }
                if (!used) {
                    System.out.println("Entrer his age");
                    waitingForString = true;
                    stringReading = scan.nextLine();
                    verifyInt();
                    int age = numberReading;
                    System.out.println("Enter his email");
                    stringReading = scan.nextLine();
                    String mail = stringReading;
                    while (!mail.contains("@") && !mail.contains(".")) {
                        System.out.println("Incorrect email, email have to have a @ and a .");
                        System.out.println("Enter an other email");
                        stringReading = scan.nextLine();
                        mail = stringReading;
                    }
                    listAdmin.add(new Admin(prenomNom, pseudo, age, mail));
                    System.out.println("A new admin has been added !");
                }
            }
        }

    }

    private void DisplayMoney() {
        System.out.println("your current balance :" + " (" + currentID + ")");
        System.out.println(currentUser.getMoney());
    }

    private void AjouterArgent(int money) {
        for (Tenant tn : listTenant) {
            if (tn.getName().equals(currentID)) {
                tn.AjouterArgent(money);
            }
        }
    }

    private void ownerPrompt() {
        waitingForString = true;
        System.out.println("Hi " + currentUser.getName());
        System.out.println("What do you want to do?");
        System.out.println("0. Quit.");
        System.out.println("1. Disconnect");
        System.out.println("2. See your property list.");
        System.out.println("3. See your location offers.");
        System.out.println("4. Display your money count.");
        System.out.println("5. Add a property");
        System.out.println("6. Add a location.");
        System.out.println("7. Delete a property from your list");
        System.out.println("8. Modify a location from your list");
        System.out.println("9. See the historic of bids on your locations");
        System.out.println("10. See reservations by location offer");
        System.out.println("11. Tranfer money to the tenant account");
        System.out.println("12. Switch profile");
    }

    private void tenantPrompt() {
        waitingForString = true;
        System.out.println("Hi " + currentUser.getName());
        System.out.println("What do you want to do?");
        System.out.println("0. Quit.");
        System.out.println("1. Disconnect");
        System.out.println("2. Display all locations.");
        System.out.println("3. Add money on my account.");
        System.out.println("4. Display my money count.");
        System.out.println("5. Add a bid on a location.");
        System.out.println("6. Modify my profile");
        System.out.println("7. See my historic of actuals bids");
        System.out.println("8. See my historic of bids on finished locations");
        System.out.println("9. See my reservations");
        System.out.println("10. Switch profile");
    }

    private void adminPrompt() {
        waitingForString = true;
        System.out.println("Hi " + currentUser.getPseudo());
        System.out.println("What do you want to do?");
        System.out.println("0. Quit.");
        System.out.println("1. Disconnect");
        System.out.println("2. Display all admins.");
        System.out.println("3. Display all users.");
        System.out.println("4. Display my money count.");
        System.out.println("5. Create admin");
        System.out.println("6. Delete User");
        System.out.println("7. Modify my profile");
        System.out.println("8. Display list of locations on the application by month");
        System.out.println("9. Close a location");
        System.out.println("10. See the bid's historic by tenant or by location");
        System.out.println("11. Modify a profile");
        System.out.println("12. Modify a location");
        System.out.println("13. Display all reservations by tenant");
    }

    private static void AppLibrary() {
        // Users
        Admin admin2 = new Admin("admin", "add", 19, "naro@gloc.col");
        Admin admin1 = new Admin("Romain", "Roms", 19, "nara@gloc.col");
        Owner owner2 = new Owner("Margaux", 18, "bonjour@gloc.col");
        Owner owner1 = new Owner("owner", 19, "bonjouy@gloc.col");
        Owner owner3 = new Owner("tenant", 19, "oui@gloc.col");
        Tenant tenant1 = new Tenant("tenant", 19, "oui@gloc.col");
        Tenant tenant2 = new Tenant("Jean", 55, "non@gloc.col");

        // Locations and properties
        HashSet<String> m = new HashSet<>();
        m.add(Month.MAY.name());
        m.add(Month.DECEMBER.name());
        m.add(Month.JULY.name());
        Location loc = new Location(new Property(owner1, "MAISON", "36 rue des Cotteaux", "Aigrefeuille"), "belle maison", 100, "aucune", 5, owner1, m);
        HashSet<String> m2 = new HashSet<>();
        m2.add(Month.JULY.name());
        m2.add(Month.OCTOBER.name());
        m2.add(Month.SEPTEMBER.name());
        Location loc2 = new Location(new Property(owner2, "VILLA", "37 rue du love", "Bordeaux"), "Villa de fou", 160, "piscine, 5 chambres, 3 salles de bain, 1 garage", 15, owner2, m2);

        // Ajout dans les listes
        listAdmin.add(admin1);
        listAdmin.add(admin2);
        listOwner.add(owner1);
        listOwner.add(owner2);
        listOwner.add(owner3);
        listTenant.add(tenant1);
        listTenant.add(tenant2);
        tenant1.AddMoney(2500);
        tenant2.AddMoney(2500);
        owner1.AjouterLocation(loc);
        owner2.AjouterLocation(loc2);
    }

}
