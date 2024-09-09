package persistence;

import persistance.JsonReader;
import model.RentalListing;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader {

    @Test
    void testReaderFileDoesNotExist() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        try {
            reader.readFavourites();
            fail("Exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderListingsFileDoesNotExist() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        try {
            reader.readListings();
            fail("Exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFile() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            List<RentalListing> myFavourites = reader.readFavourites();
            assertEquals(0, myFavourites.size());
            List<RentalListing> myListings = reader.readListings();
            assertEquals(0, myListings.size());
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testReaderGeneralFile() {
        JsonReader reader = new JsonReader("./data/testReaderGeneral.json");
        try {
            List<RentalListing> myFavourites = reader.readFavourites();
            assertEquals(2, myFavourites.size());
            assertEquals("Walter Gage", myFavourites.get(0).getAddress());
            assertEquals("1000", myFavourites.get(0).getRent());
            assertEquals("pkhanna@gmail.com", myFavourites.get(0).getContactInfo());
            assertEquals("Downtown", myFavourites.get(1).getAddress());
            assertEquals("5000", myFavourites.get(1).getRent());
            assertEquals("p@gmail.com", myFavourites.get(1).getContactInfo());

            List<RentalListing> myListings = reader.readListings();
            assertEquals(1, myListings.size());
            assertEquals("walter gage", myListings.get(0).getAddress());
            assertEquals("1000", myListings.get(0).getRent());
            assertEquals("p@gmail.com", myListings.get(0).getContactInfo());
            assertEquals("On Campus", myListings.get(0).getStatus());

        } catch (IOException e) {
            fail("Exception not expected");
        }

    }

}
