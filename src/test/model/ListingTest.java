package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListingTest {

    private Listing testListing;

    @BeforeEach
    public void setUp() {
        testListing = new Listing(900, "6110 Sunset Boulevard", "Apartment", 1000, 150000,
                true, false);
    }

    @Test
    public void testGetListingID() {
        assertEquals(900, testListing.getListingID());
    }

    @Test
    public void testGetListingName() {
        assertEquals("6110 Sunset Boulevard", testListing.getListingName());
    }

    @Test
    public void testGetListingType() {
        assertEquals("Apartment", testListing.getListingType());
    }

    @Test
    public void testGetListingSize() {
        assertEquals(1000, testListing.getListingSize());
    }

    @Test
    public void testGetListingPrice() {
        assertEquals(150000, testListing.getListingPrice());
    }

    @Test
    public void testGetListingDemand() {
        assertTrue(testListing.getListingDemand());
    }

    @Test
    public void testGetListingStatus() {
        assertFalse(testListing.getListingStatus());
    }
}