package model;

import java.util.List;
import java.util.ArrayList;

/**
 * The RentalListings class manages a collection of rental listings.
 * It provides functionality to add, delete, and retrieve rental listings.
 */
public class RentalListings {

    List<RentalListing> allRentalListings;

    // Constructs a new RentalListings object with an empty list of rental listings
    public RentalListings() {
        allRentalListings = new ArrayList<RentalListing>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given rental listing to the list of all rental listings
    public void addRentalListing(RentalListing listing) {
        this.allRentalListings.add(listing);
        if (listing.isOnCampus()) {
            EventLog.getInstance().logEvent(new Event("On-campus listing added"));

        } else {
            EventLog.getInstance().logEvent(new Event("Off-campus listing added"));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the rental listings to the given list
    public void addRentalListing(List<RentalListing> listings) {
        this.allRentalListings = listings;
        EventLog.getInstance().logEvent(new Event("Listings restored"));
    }

    // MODIFIES: this
    // EFFECTS: deletes the given rental listing from the list of all rental
    // listings
    public void deletedRentalListing(RentalListing listing) {
        this.allRentalListings.remove(listing);
        EventLog.getInstance().logEvent(new Event("Listing deleted"));
    }

    // GETTERS:

    public List<RentalListing> getAllListings() {
        return allRentalListings;
    }

    // EFFECTS: if given isonCampus is true, returns all on campus listings,
    // otherwise, off campus listings
    public List<RentalListing> getParticularListings(boolean isOnCampus) {

        ArrayList<RentalListing> listings = new ArrayList<RentalListing>();

        if (isOnCampus) {

            for (RentalListing listing : allRentalListings) {
                if (listing.isOnCampus()) {
                    listings.add(listing);
                }
            }

            return listings;

        } else {

            for (RentalListing listing : allRentalListings) {
                if (!listing.isOnCampus()) {
                    listings.add(listing);
                }
            }

            return listings;
        }

    }

}
