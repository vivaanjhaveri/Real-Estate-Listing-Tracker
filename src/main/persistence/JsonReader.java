package persistence;

// CITATION: JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import org.json.*;

import model.Listing;
import model.Portfolio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads the portfolio from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads portfolio from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Portfolio read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePortfolio(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Portfolio parsePortfolio(JSONObject jsonObject) {
        Portfolio p = new Portfolio();
        addListingsInPortfolio(p, jsonObject);
        return p;
    }

    // MODIFIES: p
    // EFFECTS: parses listings from JSON object and adds them to portfolio
    private void addListingsInPortfolio(Portfolio p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listingsInPortfolio");
        for (Object json : jsonArray) {
            JSONObject nextListing = (JSONObject) json;
            addListing(p, nextListing);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses listing from JSON object and adds it to portfolio
    private void addListing(Portfolio p, JSONObject jsonObject) {
        int listingID = jsonObject.getInt("id");
        String listingName = jsonObject.getString("name");
        String listingType = jsonObject.getString("type");
        double listingSize = jsonObject.getDouble("size");
        double listingPrice = jsonObject.getDouble("price");
        boolean isListingInDemand = jsonObject.getBoolean("demand");
        boolean isListingSold = jsonObject.getBoolean("status");

        Listing l = new Listing(listingID, listingName, listingType, listingSize,
                                listingPrice, isListingInDemand, isListingSold);
        p.addListingToPortfolio(l);
    }
}
