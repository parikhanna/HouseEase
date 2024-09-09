package persistence;

import persistance.JsonReader;
import persistance.JsonWriter;
import model.Consumer;
import model.RentalListing;
import model.RentalListings;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJsonWriter {

    RentalListings rentalListings;
    Consumer consumer;


    @BeforeEach
    void runBefore() {
        rentalListings = new RentalListings();
        consumer = new Consumer();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.createAndOpenFile();
            fail("IOException was expected.");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmpty() {
        try {
            Consumer consumer = new Consumer();
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.createAndOpenFile();
            writer.convertListingsToJson(consumer, rentalListings);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            List<RentalListing> myFavourites = reader.readFavourites();
            assertEquals(0, myFavourites.size());
            List<RentalListing> myListings = reader.readListings();
            assertEquals(0, myListings.size());

        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testWriterGeneral() {
        RentalListing r1 = new RentalListing("Walter Gage", "1000", "pkhanna@gmail.com", false);
        RentalListing r2 = new RentalListing("Downtown", "5000", "p@gmail.com", false);
        try {
            consumer.addToFavourites(r1);
            consumer.addToFavourites(r2);
            rentalListings.addRentalListing(r1);
            rentalListings.addRentalListing(r2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneral.json");
            writer.createAndOpenFile();
            writer.convertListingsToJson(consumer, rentalListings);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneral.json");
            List<RentalListing> myFavourites = reader.readFavourites();
            assertEquals(2, myFavourites.size());
            assertEquals("Walter Gage", myFavourites.get(0).getAddress());
            assertEquals("1000", myFavourites.get(0).getRent());
            assertEquals("pkhanna@gmail.com", myFavourites.get(0).getContactInfo());
            assertEquals("Downtown", myFavourites.get(1).getAddress());
            assertEquals("5000", myFavourites.get(1).getRent());
            assertEquals("p@gmail.com", myFavourites.get(1).getContactInfo());
            
            List<RentalListing> myListings = reader.readListings();
            assertEquals(2, myListings.size());
            assertEquals("Walter Gage", myListings.get(0).getAddress());
            assertEquals("1000", myListings.get(0).getRent());
            assertEquals("pkhanna@gmail.com", myListings.get(0).getContactInfo());
            assertEquals("Downtown", myListings.get(1).getAddress());
            assertEquals("5000", myListings.get(1).getRent());
            assertEquals("p@gmail.com", myListings.get(1).getContactInfo());

        } catch (IOException e) {
            fail("Exception not expected");
        }
    }
}
