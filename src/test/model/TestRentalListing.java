package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRentalListing {

    private RentalListing onCampusListing1;
    private RentalListing offCampusListing1;

    @BeforeEach
    void runBefore() {
        onCampusListing1 = new RentalListing("Ponderosa Commons", "1000", "p@gmail.com", true);
        offCampusListing1 = new RentalListing("Downtown", "3000", "m@gmail.com", false);

    }

    @Test
    void testConstructor1() {
        assertEquals("Ponderosa Commons", onCampusListing1.getAddress());
        assertEquals("1000", onCampusListing1.getRent());
        assertEquals("p@gmail.com", onCampusListing1.getContactInfo());
        assertTrue(onCampusListing1.isOnCampus());
    }

    @Test
    void testConstructor2() {
        assertEquals("Downtown", offCampusListing1.getAddress());
        assertEquals("3000", offCampusListing1.getRent());
        assertEquals("m@gmail.com", offCampusListing1.getContactInfo());
        assertFalse(offCampusListing1.isOnCampus());
    }

    @Test
    void testSetAddressEmpty() {
        onCampusListing1.setAddress("");
        assertEquals("Ponderosa Commons", onCampusListing1.getAddress());
    }

    @Test
    void testSetAddress() {
        onCampusListing1.setAddress("Orchard Commons");
        assertEquals("Orchard Commons", onCampusListing1.getAddress());
    }

    @Test
    void testSetContactInfoEmpty() {
        onCampusListing1.setContactInfo("");
        assertEquals("p@gmail.com", onCampusListing1.getContactInfo());
    }

    @Test
    void testSetContactInfo() {
        onCampusListing1.setContactInfo("abc@gmail.com");
        assertEquals("abc@gmail.com", onCampusListing1.getContactInfo());
    }

    @Test
    void testSetRentEmpty() {
        onCampusListing1.setRent("");
        assertEquals("1000", onCampusListing1.getRent());
    }

    @Test
    void testSetRent() {
        onCampusListing1.setRent("1500");
        assertEquals("1500", onCampusListing1.getRent());
    }

    @Test
    void testGetStatusOnCampus() {
        assertEquals("On Campus", onCampusListing1.getStatus());
    }

    @Test
    void testGetStatusOffCampus() {
        assertEquals("Off Campus", offCampusListing1.getStatus());
    }

}
