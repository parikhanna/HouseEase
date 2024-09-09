package persistance;

import model.*;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.List;
import java.io.*;

/**
 * Represents a writer that writes JSON representation of favourites to file
 * This class is based on the JsonSerializationDemo provided in the course
 */
public class JsonWriter {

    private static final int TAB = 4;

    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file
    // cannot
    // be opened for writing
    public void createAndOpenFile() throws FileNotFoundException {
        File file = new File(destination);
        this.writer = new PrintWriter(file);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of favourites and listings to file
    public void convertListingsToJson(Consumer c, RentalListings rentalListings) {

        List<RentalListing> favourites = c.getFavourites();
        List<RentalListing> listings = rentalListings.getAllListings();

        JSONObject json = new JSONObject();
        JSONArray jsonFavouritesList = new JSONArray();
        JSONArray jsonListings = new JSONArray();

        json.put("Favourites", jsonFavouritesList);
        json.put("Listings", jsonListings);

        for (RentalListing listing : favourites) {
            jsonFavouritesList.put(listing.toJson());
        }

        for (RentalListing listing : listings) {
            jsonListings.put(listing.toJson());

        }

        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
