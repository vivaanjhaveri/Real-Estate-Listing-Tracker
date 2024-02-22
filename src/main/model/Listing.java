package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a real estate listing having a name, location, size, and price
public class Listing implements Writable {

    private int listingID;                   // listing ID
    private String listingName;              // name of listing
    private String listingType;              // type of listing
    private double listingSize;              // size of the listing (in square feet)
    private double listingPrice;            // price of the listing
    private boolean isListingInDemand;      // is the listing in demand
    private boolean isListingSold;          // listing status (sold or not) of the listing

    // Constructs new listing with provided fields. price and size must be positive numbers
    public Listing(int id, String name, String type, double size, double price, boolean demand, boolean status) {
        listingID = id;
        listingName = name;
        listingType = type;
        listingSize = size;
        listingPrice = price;
        isListingInDemand = demand;
        isListingSold = status;
    }

    public int getListingID() {
        return listingID;
    }

    public String getListingName() {

        return listingName;
    }

    public String getListingType() {
        return listingType;
    }

    public boolean getListingDemand() {

        return isListingInDemand;
    }

    public double getListingSize() {

        return listingSize;
    }

    public double getListingPrice() {

        return listingPrice;
    }

    public boolean getListingStatus() {

        return isListingSold;
    }

    // EFFECTS: creates and returns json object
    // CITATION: JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", listingID);
        json.put("name", listingName);
        json.put("type", listingType);
        json.put("size", listingSize);
        json.put("price", listingPrice);
        json.put("demand", isListingInDemand);
        json.put("status", isListingSold);
        return json;
    }
}