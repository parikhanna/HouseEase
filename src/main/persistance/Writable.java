package persistance;

import org.json.JSONObject;

/**
 * This interface is used to convert objects into JSON format.
 * Any class that implements Writable should be able to turn its information
 * into a JSON object.
 * This is useful for saving information in a way that is easy to read and
 * process.
 * This interface is based on the JsonSerializationDemo provided in the course
 */
public interface Writable {
    // EFFECTS: returns this as JSON object
    public JSONObject toJson();
}
