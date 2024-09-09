package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.RentalListing;

/**
 * This class provides a user interface to display the list of favourite rental
 * listings.
 */
public class FavouritesWindow extends AppWindow {

    private final String title = "assets/HouseEaseFavouritesWindowIcon.png";

    // EFFECTS: Constructs a FavouritesWindow; initializes UI components and makes
    // the window visible
    public FavouritesWindow() {
        super();
        addComponentsToFrame();
        frame.setVisible(true);
    }

    // EFFECTS: Adds all UI components to the frame
    public void addComponentsToFrame() {
        frame.setLayout(new BorderLayout(20, 0));
        frame.add(createEmptyPanel(), BorderLayout.WEST);
        frame.add(createEmptyPanel(), BorderLayout.EAST);
        addTitle();
        addHomeButton();
        addListings();
    }

    // EFFECTS: Adds the title to the top of the frame
    private void addTitle() {
        JPanel panel = createEmptyPanel();
        panel.add(createImageIconLabel(title));
        frame.add(panel, BorderLayout.NORTH);
    }

    // EFFECTS: Adds the home button to the bottom of the frame
    private void addHomeButton() {
        JPanel panel = createEmptyPanel();
        panel.add(homeButton);
        frame.add(panel, BorderLayout.SOUTH);
    }

    // EFFECTS: Retrieves favourite listings from the consumer and displays each in
    // a text area in the center panel
    private void addListings() {

        List<RentalListing> listings = consumer.getFavourites();

        JPanel panel = createEmptyPanel();
        panel.setLayout(new GridLayout(0, 2));

        for (RentalListing listing : listings) {
            JTextArea textArea = new JTextArea(
                    "Address: " + listing.getAddress() + "\nRent: " + listing.getRent() + "\nContact Info: "
                            + listing.getContactInfo() + "\nStatus: " + listing.getStatus());
            textArea.setBackground(new Color(0xF1EFE7));

            panel.add(textArea);
        }

        frame.add(panel, BorderLayout.CENTER);
    }

    // EFFECTS: Handles action performed events, specifically navigating back home
    // when the home button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            newSession = false;
            new GraphicalRentalListingApp();
            frame.dispose();
        }
    }

}
