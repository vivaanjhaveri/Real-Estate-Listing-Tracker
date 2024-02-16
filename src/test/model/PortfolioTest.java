package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PortfolioTest {

    private Portfolio testPortfolio;
    private Listing testListing1;
    private Listing testListing2;
    private Listing testListing3;

    private ArrayList<Listing> testUnsoldList1;
    private ArrayList<Listing> testUnsoldList2;
    private ArrayList<Listing> testUnsoldList3;
    private ArrayList<Listing> testDemandList;

    @BeforeEach
    public void setUp() {
        testListing1 = new Listing(90, "6110 Sunset Boulevard", "Apartment", 1000, 150000,
                true, false);
        testListing2 = new Listing(10, "626 Agronomy", "Villa", 1500, 450000,
                false, false);
        testListing3 = new Listing(55, "Totem", "Villa", 900, 120000,
                false, true);
        testPortfolio = new Portfolio();

        testUnsoldList1 = new ArrayList<Listing>();
        testUnsoldList1.add(testListing1);

        testUnsoldList2 = new ArrayList<Listing>();
        testUnsoldList2.add(testListing1);
        testUnsoldList2.add(testListing2);

        testUnsoldList3 = new ArrayList<Listing>();
        testUnsoldList3.add(testListing3);

        testDemandList = new ArrayList<Listing>();
        testDemandList.add(testListing1);
    }

    @Test
    public void testEmptyPortfolio() {
        assertTrue(testPortfolio.isPortfolioEmpty());
        testPortfolio.addListingToPortfolio(testListing1);
        assertFalse(testPortfolio.isPortfolioEmpty());
    }

    @Test
    public void testAddListingToPortfolio1() {
        assertTrue(testPortfolio.getAllUnsoldListings().isEmpty());
        testPortfolio.addListingToPortfolio(testListing1);
        assertEquals(testUnsoldList1, testPortfolio.getAllUnsoldListings());
    }

    @Test
    public void testAddListingToPortfolio2() {
        assertTrue(testPortfolio.getAllUnsoldListings().isEmpty());
        testPortfolio.addListingToPortfolio(testListing1);
        testPortfolio.addListingToPortfolio(testListing2);
        assertEquals(testUnsoldList2, testPortfolio.getAllUnsoldListings());
    }

    @Test
    public void testSellListing1() {
        testPortfolio.addListingToPortfolio(testListing1);
        testPortfolio.sellListing(90);
        assertTrue(testPortfolio.isPortfolioEmpty());
    }

    @Test
    public void testSellListing2() {
        testPortfolio.addListingToPortfolio(testListing1);
        testPortfolio.addListingToPortfolio(testListing2);
        testPortfolio.sellListing(9);
        assertEquals(testUnsoldList2, testPortfolio.getAllUnsoldListings());
        testPortfolio.sellListing(10);
        assertEquals(testUnsoldList1, testPortfolio.getAllUnsoldListings());
        testPortfolio.sellListing(90);
        assertTrue(testPortfolio.isPortfolioEmpty());

        testPortfolio.addListingToPortfolio(testListing3);
        testPortfolio.sellListing(55);
        assertEquals(testUnsoldList3, testPortfolio.getAllUnsoldListings());
    }

    @Test
    public void testPortfolioValue() {
        testPortfolio.addListingToPortfolio(testListing1);
        testPortfolio.addListingToPortfolio(testListing2);
        assertEquals(600000, testPortfolio.checkPortfolioValue());
    }

    @Test
    public void testGetUnsoldListings() {
        testPortfolio.addListingToPortfolio(testListing1);
        testPortfolio.addListingToPortfolio(testListing2);
        assertEquals(testUnsoldList2, testPortfolio.getAllUnsoldListings());
    }

    @Test
    public void testGetInDemandListings() {
        testPortfolio.addListingToPortfolio(testListing1);
        testPortfolio.addListingToPortfolio(testListing2);
        assertEquals(testUnsoldList1, testPortfolio.getAllInDemandListings());
    }
}