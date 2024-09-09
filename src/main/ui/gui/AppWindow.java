package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.*;

import model.Consumer;
import model.Event;
import model.EventLog;
import persistance.JsonWriter;
import model.RentalListings;

/**
 * This abstract class serves as the base for window classes in the HOUSE-EASE
 * APP, providing common functionalities like window setup, button creation, and
 * session saving.
 */
public abstract class AppWindow extends WindowAdapter implements ActionListener {

    protected final String jsonDestination = "./data/listings.json";
    private JsonWriter writer;
    protected static Consumer consumer = new Consumer();
    protected JFrame frame;
    protected static boolean newSession = true;
    protected final String homeButtonIcon = "assets/HouseEaseHomeButtonIcon.png";
    protected JButton homeButton;
    protected static RentalListings rentalListing = new RentalListings();

    // EFFECTS: Constructs an AppWindow; initializes frame, JSON writer, and home
    // button; sets up frame properties
    public AppWindow() {
        frame = new JFrame("HOUSE-EASE APP");

        this.writer = new JsonWriter(jsonDestination);
        homeButton = createButton(homeButtonIcon);

        setUpFrame();
    }

    // EFFECTS: Sets up the frame with default close operation, size, background
    // color, and adds a window listener
    private void setUpFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(new Color(0xF1EFE7));
        frame.addWindowListener(this);
    }

    // EFFECTS: Returns a JLabel with an ImageIcon loaded from the specified
    // destination
    protected JLabel createImageIconLabel(String iconDestination) {
        JLabel label = new JLabel(new ImageIcon(iconDestination));
        return label;
    }

    // EFFECTS: Creates and returns a JButton with an icon, with no border painted
    // and no focus painted, and adds this as an action listener
    protected JButton createButton(String iconDestination) {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(iconDestination));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }

    // EFFECTS: Invoked when the window is closing; prompts user to save the session
    // and handles the response
    @Override
    public void windowClosing(WindowEvent e) {
        int choice = saveSessionChoice();
        handleSaveSessionChoice(choice);
        printLog(EventLog.getInstance());

    }

    // EFFECTS: Displays a dialog asking the user if they want to save their
    // favourites list and returns the user's choice
    private int saveSessionChoice() {
        int choice = JOptionPane.showOptionDialog(null,
                "Would you like to save your favourites list?",
                "Save Session", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("assets/HouseEaseOptionsPaneIcon.png"), null, 0);
        return choice;
    }

    // REQUIRES: choice is a valid JOptionPane option
    // MODIFIES: this
    // EFFECTS: If the user chooses YES, saves the consumer's favourites list and
    // all listings to a JSON file; handles file not found exception if file cannot
    // be accessed
    private void handleSaveSessionChoice(int choice) {
        if (choice == JOptionPane.YES_OPTION) {
            try {
                writer.createAndOpenFile();
                writer.convertListingsToJson(consumer, rentalListing);
                writer.close();
            } catch (FileNotFoundException e) {
                fileNotFoundMessage();
            }
        }
    }

    // EFFECTS: Displays an informational message indicating that the file was not
    // found
    protected void fileNotFoundMessage() {
        JOptionPane.showMessageDialog(null, "No data found.", "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: Creates and returns an empty JPanel with the background color set to
    // match the frame's content pane
    protected JPanel createEmptyPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xF1EFE7));
        return panel;
    }

    // EFFECTS: Creates and adds the home button to the bottom of the frame
    protected void createHomeButton() {
        JPanel panel = createEmptyPanel();
        homeButton = createButton(homeButtonIcon);
        panel.add(homeButton);
        frame.add(panel, BorderLayout.SOUTH);
    }

    // EFFECTS: Iterates over each Event in the EventLog, printing its date and
    // description to the console.
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println("\n" + next.getDate());
            System.out.println(next.getDescription());
        }
    }

    // EFFECTS: Adds UI components to the frame; implementation specifics depend on
    // the subclass
    protected abstract void addComponentsToFrame();

}
