package model;

import java.util.List;
import java.util.ArrayList;

/**
 * The Consumer class represents a user who can manage their favorite rental
 * listings and their own listings.
 * It provides functionality to add listings to favorites and personal listings,
 * as well as retrieve them.
 */
public class Consumer {

    private List<RentalListing> myFavouritesList;
    private List<RentalListing> myListings;

    // EFFECTS: Constructs a new Consumer with an empty favourites
    // and listings
    public Consumer() {
        this.myFavouritesList = new ArrayList<RentalListing>();
        this.myListings = new ArrayList<RentalListing>();
    }

    // MODIFIES: this
    // EFFECTS: adds the listing to myFavouritesList if it is not already there
    public void addToFavourites(RentalListing listing) {
        if (!this.myFavouritesList.contains(listing)) {
            this.myFavouritesList.add(listing);
            EventLog.getInstance().logEvent(new Event("Listing added to favourites"));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the favourites list to the given list
    public void addToFavourites(List<RentalListing> listings) {
        this.myFavouritesList = listings;
        EventLog.getInstance().logEvent(new Event("Favourites restored"));
    }

    // MODIFIES: myListings
    // EFFECTS: adds the listing to myListings
    public void addToMyListings(RentalListing listing) {
        this.myListings.add(listing);
    }

    // GETTERS:

    public List<RentalListing> getFavourites() {
        return this.myFavouritesList;
    }

    public List<RentalListing> getMyListings() {
        return this.myListings;
    }
}
