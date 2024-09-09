package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRentalListings {

    private RentalListings rentalListings;
    private RentalListing onCampusListing1;
    private RentalListing onCampusListing2;
    private RentalListing offCampusListing1;
    private RentalListing offCampusListing2;

    @BeforeEach
    void runBefore() {
        rentalListings = new RentalListings();
        onCampusListing1 = new RentalListing("Ponderosa Commons", "1000", "p@gmail.com", true);
        onCampusListing2 = new RentalListing("Walter Gage", "1200", "gage@email.com", true);
        offCampusListing1 = new RentalListing("Downtown", "3000", "m@gmail.com", false);
        offCampusListing2 = new RentalListing("North Van", "2500", "nvan@email.com", false);
    }

    @Test
    void testConstructor() {
        assertTrue(rentalListings.getAllListings().isEmpty());
    }

    @Test
    void testAddRentalListing() {

        rentalListings.addRentalListing(onCampusListing1);
        rentalListings.addRentalListing(onCampusListing2);
        rentalListings.addRentalListing(offCampusListing1);
        rentalListings.addRentalListing(offCampusListing2);

        assertEquals(4, rentalListings.getAllListings().size());
        assertEquals(onCampusListing1, rentalListings.getAllListings().get(0));
        assertEquals(onCampusListing2, rentalListings.getAllListings().get(1));
        assertEquals(offCampusListing1, rentalListings.getAllListings().get(2));
        assertEquals(offCampusListing2, rentalListings.getAllListings().get(3));
    }

    @Test
    void testDeleteRentalListing() {
        rentalListings.addRentalListing(onCampusListing1);
        rentalListings.addRentalListing(onCampusListing2);
        rentalListings.addRentalListing(offCampusListing1);
        rentalListings.addRentalListing(offCampusListing2);

        rentalListings.deletedRentalListing(onCampusListing1);
        rentalListings.deletedRentalListing(offCampusListing2);

        assertEquals(2, rentalListings.getAllListings().size());
        assertEquals(onCampusListing2, rentalListings.getAllListings().get(0));
        assertEquals(offCampusListing1, rentalListings.getAllListings().get(1));
    }

    @Test
    void testGetParticularListings() {
        rentalListings.addRentalListing(onCampusListing1);
        rentalListings.addRentalListing(onCampusListing2);
        rentalListings.addRentalListing(offCampusListing1);
        rentalListings.addRentalListing(offCampusListing2);

        List<RentalListing> onCampusListings = rentalListings.getParticularListings(true);
        assertEquals(2, onCampusListings.size());
        assertEquals(onCampusListing1, onCampusListings.get(0));
        assertEquals(onCampusListing2, onCampusListings.get(1));

        List<RentalListing> offCampusListings = rentalListings.getParticularListings(false);
        assertEquals(2, offCampusListings.size());
        assertEquals(offCampusListing1, offCampusListings.get(0));
        assertEquals(offCampusListing2, offCampusListings.get(1));
    }

    @Test
    void testAddRentalListingWithList() {
        List<RentalListing> listings = new ArrayList<>();
        listings.add(onCampusListing1);
        listings.add(onCampusListing2);
        listings.add(offCampusListing1);
        listings.add(offCampusListing2);
        rentalListings.addRentalListing(listings);
        assertEquals(listings, rentalListings.getAllListings());
    }
}
