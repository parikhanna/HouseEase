package persistance;

import model.RentalListing;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a reader that reads Favourites from JSON data stored in file
 * This class is based on the JsonSerializationDemo provided in the course
 */
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Favourites from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<RentalListing> readFavourites() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseData(jsonObject, "Favourites");
    }

    // EFFECTS: reads Listings from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<RentalListing> readListings() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseData(jsonObject, "Listings");
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private List<RentalListing> parseData(JSONObject jsonObject, String key) {

        JSONArray jsonArray = jsonObject.getJSONArray(key);
        List<RentalListing> listings = new ArrayList<RentalListing>();

        for (Object object : jsonArray) {
            JSONObject jobject = (JSONObject) object;
            String address = jobject.getString("Address");
            String rent = jobject.getString("Rent");
            String contactInfo = jobject.getString("ContactInfo");
            Boolean onCampus = jobject.getBoolean("OnCampus");
            RentalListing listing = new RentalListing(address, rent, contactInfo, onCampus);
            listings.add(listing);
        }

        return listings;
    }

}
