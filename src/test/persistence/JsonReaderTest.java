package persistence;

// CITATION: JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Listing;
import model.Portfolio;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Portfolio p = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPortfolio.json");
        try {
            Portfolio p = reader.read();
            assertTrue(p.isPortfolioEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPortfolio.json");
        try {
            Portfolio p = reader.read();
            List<Listing> testListings = p.getAllUnsoldListings();
            assertEquals(2, testListings.size());
            checkListing(123, "Sunset", "Apartment", 1000, 190000, true, false,
                    testListings.get(0));
            checkListing(100, "Agronomy", "Villa", 1800, 2000000, false, false,
                    testListings.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
