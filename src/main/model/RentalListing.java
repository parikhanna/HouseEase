package model;

import org.json.JSONObject;

import persistance.Writable;

/**
 * The RentalListing class represents a rental property listing.
 * It includes details such as address, rent amount, contact information, and
 * campus status.
 * This class implements the Writable interface to provide the
 * functionality to convert rental listings into a JSON format, facilitating
 * easy storage and transmission of data.
 */

public class RentalListing implements Writable {

    private String address;
    private String rent;
    private String contactInfo;
    private boolean onCampusStatus;

    // REQUIRES: rent > 0
    // EFFECTS: creates a rental listing with the given address, rent, contactInfo,
    // and onCampus status
    public RentalListing(String address, String rent, String contactInfo, boolean onCampus) {
        this.address = address;
        this.rent = rent;
        this.contactInfo = contactInfo;
        this.onCampusStatus = onCampus;
    }

    // EFFECTS: converts the rental listing to a JSONObject and returns that
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Address", this.getAddress());
        json.put("Rent", this.getRent());
        json.put("ContactInfo", this.getContactInfo());
        json.put("OnCampus", this.isOnCampus());
        return json;
    }

    // GETTERS:

    public boolean isOnCampus() {
        return this.onCampusStatus;
    }

    public String getStatus() {
        if (isOnCampus()) {
            return "On Campus";
        }
        return "Off Campus";
    }

    public String getAddress() {
        return this.address;
    }

    public String getRent() {
        return this.rent;
    }

    public String getContactInfo() {
        return this.contactInfo;
    }

    // SETTERS:

    // MODIFIES: this
    // EFFECTS: If the provided address is not empty, sets the address of this
    // rental listing to the given address
    public void setAddress(String address) {
        if (!address.isEmpty()) {
            this.address = address;
            EventLog.getInstance().logEvent(new Event("Listing address updated"));
        }
    }

    // MODIFIES: this
    // EFFECTS: If the provided contact information is not empty, sets the contact
    // information of this rental listing to the given contact information
    public void setContactInfo(String contactInfo) {
        if (!contactInfo.isEmpty()) {
            this.contactInfo = contactInfo;
            EventLog.getInstance().logEvent(new Event("Listing contact information updated"));
        }
    }

    // REQUIRES: rent > 0
    // MODIFIES: this
    // EFFECTS: If the provided rent string is not empty, parses it to an integer
    // and sets the rent of this rental listing to the parsed integer
    public void setRent(String rent) {
        if (!rent.isEmpty()) {
            this.rent = rent;
            EventLog.getInstance().logEvent(new Event("Listing rent updated"));
        }
    }

}
