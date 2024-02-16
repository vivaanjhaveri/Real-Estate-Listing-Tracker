package model;

import java.util.ArrayList;

// Maintains a portfolio of real estate listings
public class Portfolio {

    private ArrayList<Listing> allInDemandListings;
    private ArrayList<Listing> allUnsoldListings;

    // Constructs portfolio
    public Portfolio() {
        allInDemandListings = new ArrayList<Listing>();
        allUnsoldListings = new ArrayList<Listing>();
    }

    // MODIFIES: this
    // EFFECTS: adds provided listing to list of unsold listings, if listing has true in demand status,
    //          also adds it to list of in-demand listings
    public void addListingToPortfolio(Listing listing) {
        allUnsoldListings.add(listing);
        if (listing.getListingDemand()) {
            allInDemandListings.add(listing);
        }
    }

    // REQUIRES: listing with provided listingIDToSell exists in list of unsold listings
    // MODIFIES: this
    // EFFECTS: sells the listing associated with the provided listingIDToSell by removing it from portfolio
    public void sellListing(Integer listingIDToSell) {
        ArrayList<Listing> toRemove = new ArrayList<Listing>();
        for (Listing l : allUnsoldListings) {
            if (listingIDToSell == l.getListingID() && !l.getListingStatus()) {
                toRemove.add(l);
            }
        }
        allUnsoldListings.removeAll(toRemove);
    }

    // EFFECTS: calculates sum of all unsold listings as value of portfolio
    public double checkPortfolioValue() {
        double portfolioValue = 0;
        for (int i = 0; i < allUnsoldListings.size(); i++) {
            portfolioValue += allUnsoldListings.get(i).getListingPrice();
        }
        return portfolioValue;
    }

    public ArrayList<Listing> getAllInDemandListings() {
        return allInDemandListings;
    }

    public ArrayList<Listing> getAllUnsoldListings() {
        return allUnsoldListings;
    }

    public boolean isPortfolioEmpty() {
        return (allUnsoldListings.size() == 0);
    }
}