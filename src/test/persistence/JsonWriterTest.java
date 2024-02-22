package persistence;

// CITATION: JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Listing;
import model.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    private Listing testListing1;
    private Listing testListing2;

    @BeforeEach
    void setUp() {
        testListing1 = new Listing(558, "Dreat", "Building", 956, 900000, true, false);
        testListing2 = new Listing(9019, "Blake", "Villa", 2000, 150000, false, false);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            Portfolio p = new Portfolio();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPortfolio() {
        try {
            Portfolio p = new Portfolio();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPortfolio.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPortfolio.json");
            p = reader.read();
            assertTrue(p.isPortfolioEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPortfolio() {
        try {
            Portfolio p = new Portfolio();
            p.addListingToPortfolio(testListing1);
            p.addListingToPortfolio(testListing2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPortfolio.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPortfolio.json");
            p = reader.read();
            assertFalse(p.isPortfolioEmpty());
            List<Listing> testListings = p.getAllUnsoldListings();
            assertEquals(2, testListings.size());
            checkListing(558, "Dreat", "Building", 956, 900000, true, false, testListings.get(0));
            checkListing(9019, "Blake", "Villa", 2000, 150000, false, false, testListings.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
