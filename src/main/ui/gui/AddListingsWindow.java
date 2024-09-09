package ui.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;

import javax.swing.*;

import model.RentalListing;

/**
 * This class provides the UI for adding rental listings in the application.
 * It includes text fields for address, rent, and contact information, and a
 * checkbox to indicate if the rental is on campus.
 * It supports adding a new listing and navigating back to the home screen of
 * the app.
 */
public class AddListingsWindow extends AppWindow {

    private final String title = "assets/HouseEaseAddAListingWindowTitleIcon.png";
    private final String submitButtonIcon = "assets/HouseEaseSubmitButtonIcon.png";
    private JCheckBox onCampusCheckBox;
    private JButton submitButton;
    private TextField address;
    private TextField rent;
    private TextField contactInfo;

    // EFFECTS: Constructs an AddListingsWindow object, initializes UI components,
    // and displays the window
    public AddListingsWindow() {
        super();
        addComponentsToFrame();
        frame.setVisible(true);
    }

    // EFFECTS: Adds all initial components to the frame including empty panels on
    // the west and east sides, a title to the north, and the main fields to the
    // center.
    @Override
    public void addComponentsToFrame() {
        frame.setLayout(new BorderLayout(100, 50));
        frame.add(createEmptyPanel(), BorderLayout.WEST);
        frame.add(createEmptyPanel(), BorderLayout.EAST);
        addTitle();
        addFields();
    }

    // EFFECTS: Adds a title panel at the top of the frame
    private void addTitle() {
        JPanel panel = createEmptyPanel();
        panel.add(createImageIconLabel(title));
        frame.add(panel, BorderLayout.NORTH);
    }

    // EFFECTS: Sets up the input fields for the rental listing information in the
    // center panel
    private void addFields() {
        JPanel panel = createEmptyPanel();
        panel.setLayout(new GridLayout(0, 2, 0, 50));
        address = new TextField();
        rent = new TextField();
        contactInfo = new TextField();
        addTextField(panel, "Address: ", address);
        addTextField(panel, "Rent (must be > 0): ", rent);
        addTextField(panel, "Contact Info: ", contactInfo);
        addOnCampusCheckBox(panel);
        addSubmitAndHomeButton();
        frame.add(panel, BorderLayout.CENTER);
    }

    // EFFECTS: Adds a label and a text field to the specified panel
    private void addTextField(JPanel panel, String fieldName, TextField textField) {
        JLabel label = new JLabel(fieldName);
        label.setFont(new Font("Tahoma", Font.PLAIN, 22));
        panel.add(label);
        panel.add(textField);
    }

    // EFFECTS: Adds an on-campus checkbox to the specified panel
    private void addOnCampusCheckBox(JPanel panel) {
        onCampusCheckBox = new JCheckBox("On Campus");
        onCampusCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
        panel.add(onCampusCheckBox);
    }

    // EFFECTS: Adds submit and home buttons to the lower panel of the frame
    private void addSubmitAndHomeButton() {
        JPanel panel = createEmptyPanel();
        panel.add(homeButton);
        submitButton = createButton(submitButtonIcon);
        panel.add(submitButton);
        frame.add(panel, BorderLayout.SOUTH);
    }

    // EFFECTS: Handles button action events to navigate back home or submit a new
    // listing
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            newSession = false;
            new GraphicalRentalListingApp();
            frame.dispose();
        } else {
            handleAddNewListing();
        }
    }

    // REQUIRES: Non-null and valid text in address, rent, and contactInfo fields;
    // rent must be a positive number
    // MODIFIES: this, rentalListing
    // EFFECTS: Adds a new RentalListing to rentalListing and shows a confirmation
    // message
    private void handleAddNewListing() {
        RentalListing listing = new RentalListing(address.getText(), rent.getText(), contactInfo.getText(),
                onCampusCheckBox.isSelected());
        rentalListing.addRentalListing(listing);
        JOptionPane.showMessageDialog(null, "Listing Added!", "Message", JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("assets/HouseEaseOptionsPaneIcon.png"));
        newSession = false;
        new GraphicalRentalListingApp();
        frame.dispose();
    }

}
