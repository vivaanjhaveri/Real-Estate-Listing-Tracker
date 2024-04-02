package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Maintains a portfolio of real estate listings
public class Portfolio implements Writable {

    private ArrayList<Listing> allInDemandListings;
    private ArrayList<Listing> allUnsoldListings;

    // Constructs portfolio
    public Portfolio() {
        allInDemandListings = new ArrayList<Listing>();
        allUnsoldListings = new ArrayList<Listing>();
        EventLog.getInstance().logEvent(new Event("Portfolio created"));
    }

    // MODIFIES: this
    // EFFECTS: adds provided listing to list of unsold listings, if listing has true in demand status,
    //          also adds it to list of in-demand listings
    // CITATION: Code obtained from AlarmSystem
    //           URL: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/main/ca/ubc/cpsc210/alarm/model/Event.java
    public void addListingToPortfolio(Listing listing) {
        allUnsoldListings.add(listing);
        if (listing.getListingDemand()) {
            allInDemandListings.add(listing);
        }
        EventLog.getInstance().logEvent(new Event("New listing with ID " + listing.getListingID() + " created."));
    }

    // REQUIRES: listing with provided listingIDToSell exists in list of unsold listings
    // MODIFIES: this
    // EFFECTS: sells the listing associated with the provided listingIDToSell by removing it from portfolio
    public void sellListing(Integer listingIDToSell) {
        ArrayList<Listing> toRemove = new ArrayList<Listing>();
        ArrayList<Listing> toRemoveWithDemand = new ArrayList<Listing>();
        for (Listing l : allUnsoldListings) {
            if (listingIDToSell == l.getListingID() && !l.getListingStatus()) {
                toRemove.add(l);
            }
            if (listingIDToSell == l.getListingID() && !l.getListingStatus() && l.getListingDemand()) {
                toRemoveWithDemand.add(l);
            }
        }
        allUnsoldListings.removeAll(toRemove);
        allInDemandListings.removeAll(toRemoveWithDemand);
        EventLog.getInstance().logEvent(new Event("Listing " + listingIDToSell + " sold."));
    }

    // EFFECTS: calculates sum of all unsold listings as value of portfolio
    public double checkPortfolioValue() {
        double portfolioValue = 0;
        for (int i = 0; i < allUnsoldListings.size(); i++) {
            portfolioValue += allUnsoldListings.get(i).getListingPrice();
        }
        EventLog.getInstance().logEvent(new Event("Current portfolio value is " + portfolioValue));
        return portfolioValue;
    }

    // EFFECTS: returns true if the portfolio contains the given listing id
    public boolean portfolioContainsListing(int listingID) {
        for (Listing l: getAllUnsoldListings()) {
            if (l.getListingID() == listingID) {
                EventLog.getInstance().logEvent(new Event("Portfolio contains listing ID " + listingID));
                return true;
            }
        }
        EventLog.getInstance().logEvent(new Event("Portfolio does not contain listing ID " + listingID));
        return false;
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

    // EFFECTS: creates and returns json object
    // CITATION: JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listingsInPortfolio", unsoldToJson());
        return json;
    }

    // EFFECTS: returns unsold listings in this portfolio as a JSON array
    // CITATION: JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private JSONArray unsoldToJson() {
        JSONArray jsonUnsoldArray = new JSONArray();
        for (Listing l : allUnsoldListings) {
            jsonUnsoldArray.put(l.toJson());
        }
        return jsonUnsoldArray;
    }
}