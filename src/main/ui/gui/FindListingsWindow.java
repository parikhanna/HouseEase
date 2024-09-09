package ui.gui;

import javax.swing.*;

import model.RentalListing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides a user interface to find and interact with rental
 * listings, including adding them to favourites and viewing favourite listings.
 */
public class FindListingsWindow extends AppWindow {

    private final String title = "assets/HouseEaseFindListingsWindowTitle.png";
    private final String heartButtonIcon = "assets/HouseEaseAddToFavouritesButtonIcon.png";
    private final String favouritesButtonIcon = "assets/HouseEaseViewMyFavouritesButtonIcon.png";
    private Map<JButton, RentalListing> favouritesButtonsMap;
    private JButton favouritesButton;

    // EFFECTS: Constructs a FindListingsWindow; initializes the map for favourites
    // buttons and listings, sets up UI components, and makes the window visible
    public FindListingsWindow() {
        super();
        this.favouritesButtonsMap = new HashMap<>();
        addComponentsToFrame();
        frame.setVisible(true);
    }

    // EFFECTS: Adds all UI components to the frame
    @Override
    public void addComponentsToFrame() {
        frame.setLayout(new BorderLayout(50, 0));
        frame.add(createEmptyPanel(), BorderLayout.WEST);
        frame.add(createEmptyPanel(), BorderLayout.EAST);
        addTitle();
        addFavouritesAndHomeButton();
        addListings();
    }

    // EFFECTS: Adds the title to the top of the frame
    private void addTitle() {
        JPanel panel = createEmptyPanel();
        panel.add(createImageIconLabel(title));
        frame.add(panel, BorderLayout.NORTH);
    }

    // EFFECTS: Adds home and favourites buttons to the bottom of the frame
    private void addFavouritesAndHomeButton() {
        JPanel panel = createEmptyPanel();
        panel.add(homeButton);
        favouritesButton = createButton(favouritesButtonIcon);
        panel.add(favouritesButton);
        frame.add(panel, BorderLayout.SOUTH);
    }

    // EFFECTS: Retrieves all listings and displays them in the center panel; each
    // listing includes a button to add to favourites
    private void addListings() {
        List<RentalListing> listings = rentalListing.getAllListings();

        JPanel panel = createEmptyPanel();
        panel.setLayout(new GridLayout(0, 4));

        for (RentalListing listing : listings) {
            JTextArea textArea = new JTextArea(
                    "Address: " + listing.getAddress() + "\nRent: " + listing.getRent() + "\nContact Info: "
                            + listing.getContactInfo() + "\nStatus: " + listing.getStatus());
            textArea.setBackground(new Color(0xF1EFE7));

            JButton button = createButton(heartButtonIcon);

            panel.add(textArea);
            panel.add(button);

            favouritesButtonsMap.put(button, listing);
        }

        frame.add(panel, BorderLayout.CENTER);
    }

    // EFFECTS: Handles action events from all buttons; navigates home or to
    // favourites window, or adds a listing to favourites
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            newSession = false;
            new GraphicalRentalListingApp();
            frame.dispose();
        } else if (e.getSource() == favouritesButton) {
            new FavouritesWindow();
            frame.dispose();
        } else {
            consumer.addToFavourites(favouritesButtonsMap.get(e.getSource()));
        }
    }

}
