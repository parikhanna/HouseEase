package ui.gui;

import javax.swing.*;

import model.RentalListing;
import persistance.JsonReader;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

/**
 * Main application window that allows users to access different functionalities
 * such as adding listings, finding listings, and loading previous sessions.
 */
public class GraphicalRentalListingApp extends AppWindow {

    private final String addListingsButtonIcon = "assets/HouseEaseAddAListingButton.png";
    private final String findListingsButtonIcon = "assets/HouseEaseFindAListingButton.png";
    private final String logo = "assets/HouseEaseLogo.png";

    private JsonReader reader;
    private JButton addListingsButton;
    private JButton findListingsButton;

    // EFFECTS: Constructs the main application window; initializes the JSON reader,
    // loads the previous session if applicable,
    // sets up UI components, and makes the window visible
    public GraphicalRentalListingApp() {
        super();
        reader = new JsonReader(jsonDestination);
        if (newSession) {
            loadPrevSession();
        }
        addComponentsToFrame();
        frame.setVisible(true);
    }

    // EFFECTS: Processes the user's choice about loading the previous session's
    // favourites list
    private void loadPrevSession() {
        int choice = loadPrevSessionChoice();
        loadPrevSessionProcessChoice(choice);
    }

    // EFFECTS: Displays a dialog asking the user if they want to restore their
    // favourites list from the last session and returns the user's choice
    private int loadPrevSessionChoice() {
        int choice = JOptionPane.showOptionDialog(null,
                "Would you like to restore your favourites list from last time?",
                "Restore Previous Session", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("assets/HouseEaseOptionsPaneIcon.png"), null, 0);
        return choice;
    }

    // EFFECTS: Loads the previous session's favourites and listings if
    // the user chooses YES; handles IOException if data loading fails
    private void loadPrevSessionProcessChoice(int choice) {
        if (choice == JOptionPane.YES_OPTION) {
            try {
                List<RentalListing> favourites = reader.readFavourites();
                consumer.addToFavourites(favourites);
                List<RentalListing> listings = reader.readListings();
                rentalListing.addRentalListing(listings);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "No data found.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // EFFECTS: Adds home screen components to the frame including a logo and
    // buttons to navigate to other windows
    @Override
    public void addComponentsToFrame() {
        frame.setLayout(new BorderLayout(150, 150));
        frame.add(new JPanel(), BorderLayout.NORTH);
        JPanel homeScreenPanel = createEmptyPanel();
        homeScreenPanel.add(createImageIconLabel(logo));
        addListingsButton = createButton(addListingsButtonIcon);
        findListingsButton = createButton(findListingsButtonIcon);
        homeScreenPanel.add(addListingsButton);
        homeScreenPanel.add(findListingsButton);
        frame.add(homeScreenPanel, BorderLayout.CENTER);
    }

    // EFFECTS: Handles action events from the buttons; opens appropriate windows
    // based on user interaction and disposes the current frame
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addListingsButton) {
            new AddListingsWindow();
            frame.dispose();
        }
        if (e.getSource() == findListingsButton) {
            new FindListingsWindow();
            frame.dispose();
        }
    }

}
