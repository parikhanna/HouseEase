package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestConsumer {

    private Consumer testConsumer;
    private RentalListing onCampusListing1;
    private RentalListing offCampusListing1;
    
    @BeforeEach
    void runBefore() {
        testConsumer = new Consumer();
        onCampusListing1 = new RentalListing("Ponderosa Commons", "1000", "p@gmail.com", true);
        offCampusListing1 = new RentalListing("Downtown", "3000", "m@gmail.com", false);
    }


    @Test
    void testConstructor() {
        assertTrue(testConsumer.getFavourites().isEmpty());
        assertTrue(testConsumer.getMyListings().isEmpty());
    }


    @Test
    void testAddToMyFavouritesOnCampus() {
        testConsumer.addToFavourites(onCampusListing1);
        assertEquals(1,testConsumer.getFavourites().size());
        assertEquals(onCampusListing1,testConsumer.getFavourites().get(0));
    }

    @Test
    void testAddToMyFavouritesOffCampus() {
        testConsumer.addToFavourites(offCampusListing1);
        assertEquals(1,testConsumer.getFavourites().size());
        assertEquals(offCampusListing1,testConsumer.getFavourites().get(0));
    }

    @Test
    void testAddToMyFavouritesBoth() {
        testConsumer.addToFavourites(onCampusListing1);
        testConsumer.addToFavourites(offCampusListing1);
        assertEquals(2,testConsumer.getFavourites().size());
        assertEquals(onCampusListing1,testConsumer.getFavourites().get(0));
        assertEquals(offCampusListing1,testConsumer.getFavourites().get(1));
    }

    @Test
    void testAddToMyFavouritesSameTwice() {
        testConsumer.addToFavourites(onCampusListing1);
        testConsumer.addToFavourites(onCampusListing1);
        assertEquals(1,testConsumer.getFavourites().size());
        assertEquals(onCampusListing1,testConsumer.getFavourites().get(0));
    }

    @Test
    void testAddListingOnCampus() {
        testConsumer.addToMyListings(onCampusListing1);
        assertEquals(1,testConsumer.getMyListings().size());
        assertEquals(onCampusListing1,testConsumer.getMyListings().get(0));
    }
    
    @Test
    void testAddListingOffCampus() {
        testConsumer.addToMyListings(offCampusListing1);
        assertEquals(1,testConsumer.getMyListings().size());
        assertEquals(offCampusListing1,testConsumer.getMyListings().get(0));
    }

    @Test
    void testAddListingBoth() {
        testConsumer.addToMyListings(offCampusListing1);
        testConsumer.addToMyListings(onCampusListing1);
        assertEquals(2,testConsumer.getMyListings().size());
        assertEquals(offCampusListing1,testConsumer.getMyListings().get(0));
        assertEquals(onCampusListing1,testConsumer.getMyListings().get(1));
    }

    @Test
    void testAddListingSameTwice() {
        testConsumer.addToMyListings(offCampusListing1);
        testConsumer.addToMyListings(offCampusListing1);
        assertEquals(2,testConsumer.getMyListings().size());
        assertEquals(offCampusListing1,testConsumer.getMyListings().get(0));
        assertEquals(offCampusListing1,testConsumer.getMyListings().get(1));
    }

    @Test
    void testAddToFavourites() {
        List<RentalListing> listings = new ArrayList<>();
        listings.add(onCampusListing1);
        listings.add(offCampusListing1);
        testConsumer.addToFavourites(listings);

        assertEquals(listings, testConsumer.getFavourites());  
    }
}