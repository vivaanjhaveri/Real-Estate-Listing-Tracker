package ui;

import model.Portfolio;
import model.Listing;

import java.util.ArrayList;
import java.util.Scanner;

// Real estate tracker app
public class RealEstateApp {

    private Portfolio propertyPortfolio;
    private Scanner input;

    // EFFECTS: runs the real estate tracker application
    public RealEstateApp() {
        runRealEstateApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runRealEstateApp() {
        boolean appStatus = true;
        String userInput;

        startPortfolio();
        System.out.println("\nWelcome to Vivaan's Real Estate Portfolio Tracker");

        while (appStatus) {
            displayAppMenu();
            userInput = input.next();
            userInput = userInput.toLowerCase();

            if (userInput.equals("quit")) {
                appStatus = false;
            } else {
                processCommand(userInput);
            }
        }
        System.out.println("\nThank you for using the application!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            addNewListing();
        } else if (command.equals("2")) {
            sellAListing();
        } else if (command.equals("3")) {
            seeAllListings();
        } else if (command.equals("4")) {
            seeAllListingsInDemand();
        } else if (command.equals("5")) {
            givePortfolioValue();
        } else {
            System.out.println("\nPlease enter a valid choice");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes portfolio
    private void startPortfolio() {
        propertyPortfolio = new Portfolio();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: prints menu of options to user
    private void displayAppMenu() {
        System.out.println("\nPlease select one of the following options:");
        System.out.println("\t1 -> Add new listing to portfolio");
        System.out.println("\t2 -> Sell a listing from portfolio");
        System.out.println("\t3 -> View all listings");
        System.out.println("\t4 -> View all listings currently in demand");
        System.out.println("\t5 -> Check portfolio value");
        System.out.println("\tquit -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a new listing to the portfolio
    private void addNewListing() {
        System.out.print("Create new listing ID: ");
        Integer newListingID = input.nextInt();
        System.out.print("Enter name of listing: ");
        String newListingName = input.next();
        System.out.print("Enter type of listing: ");
        String newListingType = input.next();
        System.out.print("Enter size of listing (in sq ft.): ");
        Double newListingSize = input.nextDouble();
        System.out.print("Enter price of listing (in $): ");
        Double newListingPrice = input.nextDouble();
        System.out.print("Is the listing in demand? ('y' or 'n'): ");
        String newListingDemandReturn = input.next();

        boolean newListingDemand;
        String demandYes = "y";
        if (newListingDemandReturn.equals(demandYes)) {
            newListingDemand = true;
        } else {
            newListingDemand = false;
        }

        Listing propertyListing = new Listing(newListingID, newListingName, newListingType,
                newListingSize, newListingPrice, newListingDemand, false);
        propertyPortfolio.addListingToPortfolio(propertyListing);
        System.out.println("\nListing added successfully!");
    }

    // REQUIRES: propertyPortfolio.getAllUnsoldListings() contains listing with given listingIDToSell
    // MODIFIES: this
    // EFFECTS: if valid listing ID given, sells the listing and remove it from the portfolio
    private void sellAListing() {
        if (propertyPortfolio.isPortfolioEmpty()) {
            System.out.println("\nThere are no properties to sell.");
        } else {
            System.out.println("\nThere are currently " + propertyPortfolio.getAllUnsoldListings().size()
                    + " listings in portfolio.");
            printListingsFrom(propertyPortfolio.getAllUnsoldListings());
            System.out.print("Enter listing ID to sell: ");
            Integer listingIDToSell = input.nextInt();
            System.out.print("Enter price sold at: ");
            Double listingSellPrice = input.nextDouble();

            propertyPortfolio.sellListing(listingIDToSell);
            System.out.println("\nListing sold successfully!");
        }
    }

    // EFFECTS: prints all unsold listings in portfolio
    private void seeAllListings() {
        System.out.println("\nThere are currently " + propertyPortfolio.getAllUnsoldListings().size()
                    + " listings in portfolio.");
        printListingsFrom(propertyPortfolio.getAllUnsoldListings());
    }

    // EFFECTS: prints all unsold listings in portfolio that are in demand
    private void seeAllListingsInDemand() {
        System.out.println("\nThere are currently " + propertyPortfolio.getAllInDemandListings().size()
                + " in-demand listings in portfolio.");
        printListingsFrom(propertyPortfolio.getAllInDemandListings());
    }

    // EFFECTS: prints sum of all unsold listings as value of portfolio
    private void givePortfolioValue() {
        System.out.println("\nCurrent value of the portfolio is $"
                + propertyPortfolio.checkPortfolioValue());
    }

    // REQUIRES: !listings.isEmpty()
    // EFFECTS: prints listings from provided ArrayList<Listing>
    private void printListingsFrom(ArrayList<Listing> listings) {
        for (Listing l: listings) {
            System.out.println("Listing ID: " + l.getListingID()
                    + "\nListing name: " + l.getListingName()
                    + "\nListing type: " + l.getListingType()
                    + "\nListing price: $ " + l.getListingPrice()
                    + "\nListing size: " + l.getListingSize() + " sq.ft"
                    + "\nListing in demand? " + l.getListingDemand()
                    + "\nListing sold? " + l.getListingStatus() + "\n");
        }
    }
}