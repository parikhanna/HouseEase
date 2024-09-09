package ui.cli;

import model.*;
import persistance.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface for a rental listing application.
 * This application helps manage rental listings including creating, viewing,
 * and editing listings, as well as managing favourite listings.
 */
public class RentalListingApp {

    private final String welcomeMessage = "WELCOME TO THE UBC HOUSE-EASE APP";
    private final String goodbyeMessage = "THANKS FOR USING THE HOUSE-EASE APP!";
    private final String jsonDestination = "./data/listings.json";

    private Scanner scanner;
    private boolean isAppRunning;
    private RentalListings rentalListings;
    private Consumer consumer;
    private JsonWriter writer;
    private JsonReader reader;

    // EFFECTS: Initializes the application and starts the main application loop
    public RentalListingApp() {

        printLine();
        System.out.println(welcomeMessage);
        printLine();

        scanner = new Scanner(System.in);
        this.isAppRunning = true;
        rentalListings = new RentalListings();
        consumer = new Consumer();
        this.writer = new JsonWriter(jsonDestination);
        this.reader = new JsonReader(jsonDestination);

        runApp();
    }

    // EFFECTS: Runs the main loop of the application, continuously displaying the
    // main menu
    public void runApp() {

        while (isAppRunning) {
            loadFromFile();
        }
    }

    // EFFECTS: Displays the main menu and processes user input
    public void mainMenu() {
        displayMainMenu();
        String input = scanner.nextLine();
        processMainMenu(input);
    }

    // MODIFIES: this
    // EFFECTS: Processes the main menu input to navigate to different parts of the
    // application
    public void processMainMenu(String input) {
        switch (input) {
            case "1":
                createAListing();
                break;
            case "2":
                viewListings();
                break;
            case "3":
                List<RentalListing> favouriteListings = consumer.getFavourites();
                printListingsFavourites(favouriteListings);
                break;
            case "4":
                myListings();
                break;
            case "5":
                optionToSave();
                printLog(EventLog.getInstance());
                this.isAppRunning = false;
                break;
            default:
                invalidInput();
                mainMenu();
        }
    }

    // EFFECTS: Displays the main menu options to the user
    public void displayMainMenu() {
        printLine();
        System.out.println("Choose an option [1,2,3,4,OR,5]:");
        System.out.println(" \n1. Create a Listing \n2. View Listings");
        System.out.println("3. My Favourites \n4. My Listings \n5. Quit");
        printLine();
    }

    // EFFECTS: Guides the user through creating a new listing
    public void createAListing() {
        createAListingDisplay();
        String input = scanner.nextLine();
        processCreateAListing(input);
    }

    // EFFECTS: Displays options for creating a listing
    public void createAListingDisplay() {
        printLine();
        System.out.println("Choose an option [1,OR,2]: \n1. On Campus \n2. Off Campus \n3. Back");
        printLine();
    }

    // EFFECTS: Processes the user's input for creating a new listing
    public void processCreateAListing(String input) {
        switch (input) {
            case "1":
                listing(true);
                break;
            case "2":
                listing(false);
                break;
            case "3":
                mainMenu();
            default:
                invalidInput();
                createAListing();
        }
    }

    // REQUIRES: rent > 0
    // MODIFIES: this
    // EFFECTS: Creates a new listing based on user input and adds it to the system
    public void listing(boolean onCampus) {
        printLine();
        System.out.println("Address:");
        String address = scanner.nextLine();
        System.out.println("Rent (Must be greater than 0):");
        String rent = scanner.nextLine();
        System.out.println("Contact Info:");
        String contactInfo = scanner.nextLine();
        printLine();

        RentalListing listing = new RentalListing(address, rent, contactInfo, onCampus);
        rentalListings.addRentalListing(listing);
        consumer.addToMyListings(listing);
        System.out.println("Listing successfully added!");
        mainMenu();
    }

    // EFFECTS: Displays and handles the viewing of listings
    public void viewListings() {
        viewListingsDisplay();
        String input = scanner.nextLine();
        processViewListings(input);
    }

    // EFFECTS: Displays options for viewing listings
    public void viewListingsDisplay() {
        printLine();
        System.out.println("Choose an option [1,2, OR, 3]:");
        System.out.println("1. On Campus Sublet \n2. Off Campus Housing \n3. View All Listings \n4. Back");
        printLine();
    }

    // EFFECTS: Processes the user's input for viewing listings.
    public void processViewListings(String input) {
        switch (input) {
            case "1":
                displayParticularListings(true);
                break;
            case "2":
                displayParticularListings(false);
                break;
            case "3":
                List<RentalListing> listings = rentalListings.getAllListings();
                printListings(listings);
                break;
            case "4":
                mainMenu();
                break;
            default:
                invalidInput();
                viewListings();
        }
    }

    // EFFECTS: Displays a specific subset of listings based on whether they are on
    // or off campus
    public void displayParticularListings(boolean onCampus) {
        List<RentalListing> listings = rentalListings.getParticularListings(onCampus);
        printListings(listings);
    }

    // EFFECTS: Displays all available listings. If there are no listings, prompts
    // return to the main menu
    public void printListings(List<RentalListing> listings) {
        int count = 1;
        if (listings.size() == 0) {
            System.out.println("No Available Listings");
            goBackToMainMenu();
        } else {
            for (RentalListing listing : listings) {
                printLine();
                System.out.println(count + "." + " Address: " + listing.getAddress() + "\nRent: " + listing.getRent()
                        + "\nContact Info: " + listing.getContactInfo());
                printLine();
                count++;
            }
            addFavourites(listings);
        }
    }

    // EFFECTS: Displays listings and adds an option for adding to favourites
    public void printListingsFavourites(List<RentalListing> listings) {
        int count = 1;
        if (listings.size() == 0) {
            System.out.println("No Available Listings");
            goBackToMainMenu();
        } else {
            for (RentalListing listing : listings) {
                printLine();
                System.out.println(count + "." + " Address: " + listing.getAddress() + "\nRent: " + listing.getRent()
                        + "\nContact Info: " + listing.getContactInfo());
                printLine();
                count++;
            }
            goBackToMainMenu();
        }
    }

    // EFFECTS: Displays and handles adding a listing to the user's favourites
    public void addFavourites(List<RentalListing> listings) {

        printLine();
        System.out.println("Enter a listing number to add it to favourites or enter b to go back to the options menu.");
        String input = scanner.nextLine().toLowerCase();
        printLine();
        if (input.equals("b")) {
            viewListings();
        } else {
            handleAddFavourite(input, listings);
            addFavourites(listings);
        }
    }

    // MODIFIES: Consumer
    // EFFECTS: Adds a specific listing to the user's favourites based on their
    // selection.
    public void handleAddFavourite(String input, List<RentalListing> listings) {
        int index = (Integer.valueOf(input) - 1);
        if (index >= listings.size() || index < 0) {
            System.out.println("Invalid number");
            printLine();
            addFavourites(listings);
        } else {
            RentalListing listing = listings.get(index);
            consumer.addToFavourites(listing);
            System.out.println("Successfully Added to Favourites!");
        }

    }

    // EFFECTS: Offers the user a prompt to go back to the main menu.
    public void goBackToMainMenu() {
        System.out.println("Enter any key to go back to the main menu");
        scanner.nextLine();
        mainMenu();
    }

    // EFFECTS: Displays options and manages the user's own listings
    public void myListings() {
        myListingsDisplay();
        String myListingsInput = scanner.nextLine();
        processMyListings(myListingsInput);
    }

    // EFFECTS: Displays options for viewing or editing the user's listings
    public void myListingsDisplay() {
        printLine();
        System.out.println("Choose an Option [1, 2, OR, 3]: \n1. View My Listings \n2. Edit MyListings \n3. Back");
        printLine();
    }

    // EFFECTS: Processes user input for managing their listings
    public void processMyListings(String input) {
        List<RentalListing> myListings = consumer.getMyListings();
        switch (input) {
            case "1":
                viewMyListings(myListings);
                goBackToMyListingsMenu();
                break;
            case "2":
                viewMyListings(myListings);
                editMyListings(myListings);
                break;
            case "3":
                mainMenu();
                break;
            default:
                System.out.println("Invalid Input");
                myListings();
        }
    }

    // EFFECTS: Displays the user's own listings
    public void viewMyListings(List<RentalListing> myListings) {
        printListingsMyListings(myListings);
    }

    // EFFECTS: Displays the listings owned by the user. If there are no listings,
    // prompts return to My Listings menu
    public void printListingsMyListings(List<RentalListing> listings) {
        int count = 1;
        if (listings.size() == 0) {
            System.out.println("No Available Listings");
            goBackToMyListingsMenu();
        } else {
            for (RentalListing listing : listings) {
                printLine();
                System.out.println(count + "." + " Address: " + listing.getAddress() + "\nRent: " + listing.getRent()
                        + "\nContact Info: " + listing.getContactInfo());
                printLine();
                count++;
            }
        }
    }

    // MODIFIES: RentalListing
    // EFFECTS: Provides a menu to edit a listing from user's own listings
    public void editMyListingProcessor(String input, List<RentalListing> listings) {
        int index = (Integer.valueOf(input) - 1);
        if (index >= listings.size() || index < 0) {
            System.out.println("Invalid number");
            printLine();
            editMyListings(listings);
        } else {
            RentalListing listing = listings.get(index);
            System.out.println("Address: \nPress enter if you don't wish to change the address");
            String address = scanner.nextLine();
            System.out.println("Rent (Must be greater than 0): \nPress enter if you don't wish to change the rent");
            String rent = scanner.nextLine();
            System.out.println("Contact Info: \nPress enter if you don't wish to change the contact info");
            String contactInfo = scanner.nextLine();
            printLine();
            listing.setAddress(address);
            listing.setRent(rent);
            listing.setContactInfo(contactInfo);
            goBackToMyListingsMenu();

        }
    }

    // EFFECTS: Prompts the user to choose a listing to edit
    public void editMyListings(List<RentalListing> myListings) {
        chooseListingDisplay();
        String editDisplayInput = scanner.nextLine();
        editMyListingProcessor(editDisplayInput, myListings);

    }

    // EFFECTS: Displays a prompt for the user to enter the number of the listing
    // they wish to edit
    public void chooseListingDisplay() {
        printLine();
        System.out.println("Enter the listing number you wish to edit");
        printLine();
    }

    // Effects: Prompts the user to save their favourites list, processes the user's
    // input, and either saves the list, exits, or re-prompts in case of invalid
    // input.
    public void optionToSave() {
        displayOptionToSave();
        String input = scanner.nextLine();
        processOptionToSave(input.toLowerCase());
    }

    // Effects: Displays the option to save the favourites list to the user.
    public void displayOptionToSave() {
        printLine();
        System.out.println("Would you like to save your favourites list? [y or n]");
        printLine();
    }

    // Effects: Processes the user's input to either save favourites, exit, or
    // re-prompt for invalid input.
    public void processOptionToSave(String input) {
        switch (input) {
            case "y":
                saveFavourites();
                break;
            case "n":
                printGoodBye();
                break;
            default:
                invalidInput();
                optionToSave();
        }
    }

    // Modifies: File at the specified jsonDestination.
    // Effects: Converts the favourites to JSON format and saves them to a file.
    // Prints a message upon successful save or error if file writing fails.
    public void saveFavourites() {
        try {
            writer.createAndOpenFile();
            writer.convertListingsToJson(consumer, rentalListings);
            writer.close();
            printLine();
            System.out.println("SAVED!");
            printLine();
            printGoodBye();
            printLine();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write the file " + jsonDestination);
        }
    }

    // Effects: Prompts the user to load the favourites list from a file, processes
    // the user's input, and either loads the list, proceeds to the main menu, or
    // re-prompts in case of invalid input.
    public void loadFromFile() {
        displayLoadOption();
        String input = scanner.nextLine();
        processLoadOption(input.toLowerCase());
    }

    // Effects: Displays the option to load the favourites list from a previous
    // session.
    public void displayLoadOption() {
        printLine();
        System.out.println("Would you like to restore your favourites list from last time? [y or n]");
        printLine();
    }

    // Effects: Processes the user's input to either load favourites from file,
    // proceed to the main menu, or re-prompt for invalid input. Catches
    // IOExceptions and informs the user of any errors.
    public void processLoadOption(String input) {
        switch (input) {
            case "y":
                try {
                    List<RentalListing> favourites = reader.readFavourites();
                    consumer.addToFavourites(favourites);
                    List<RentalListing> listings = reader.readListings();
                    rentalListings.addRentalListing(listings);
                    System.out.println("Session restored!");
                } catch (IOException e) {
                    System.out.println("No saved data found.");
                }
                mainMenu();
                break;
            case "n":
                mainMenu();
                break;
            default:
                invalidInput();
                loadFromFile();
        }
    }

    // EFFECTS: Iterates over each Event in the EventLog, printing its date and
    // description to the console.
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println("\n" + next.getDate());
            System.out.println(next.getDescription());
        }
    }

    // EFFECTS: Displays a message prompting the user to return to the options menu
    // and navigates back to it
    public void goBackToMenu() {
        System.out.println("Enter any key to go back to the options menu");
        scanner.nextLine();
        viewListings();
    }

    // EFFECTS: Displays a message prompting the user to return to the My Listings
    // menu and navigates back to it
    public void goBackToMyListingsMenu() {
        System.out.println("Enter any key to go back to My Listings menu");
        scanner.nextLine();
        myListings();
    }

    // EFFECTS: Prints a long line to serve as a divider in the ui
    public void printLine() {
        System.out.println(
                "---------------------------------------------------------------------------------------------------");
    }

    // EFFECTS: Prints an error message indicating invalid input
    public void invalidInput() {
        System.out.println("INVALID INPUT");
    }

    /// EFFECTS: Prints the goodbye message to the console.
    public void printGoodBye() {
        System.out.println(goodbyeMessage);
    }
}
