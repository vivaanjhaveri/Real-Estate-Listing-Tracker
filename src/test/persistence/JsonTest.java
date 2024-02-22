package persistence;

import model.Listing;

import static org.junit.jupiter.api.Assertions.assertEquals;

// CITATION: JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {

    protected void checkListing(int id, String name, String type, double size, double price, boolean demand, boolean status, Listing listing) {
        assertEquals(id, listing.getListingID());
        assertEquals(name, listing.getListingName());
        assertEquals(type, listing.getListingType());
        assertEquals(size, listing.getListingSize());
        assertEquals(price, listing.getListingPrice());
        assertEquals(demand, listing.getListingDemand());
        assertEquals(status, listing.getListingStatus());
    }
}
