
# H O U S E - E A S E 

## An application for UBC students to find housing and roommates with ease!

 ### 📌 PROJECT OVERVIEW:

 **HOUSE-EASE** is an application designed to streamline the housing search process for UBC students. It centralizes the currently fragmented student sublet and rental market by providing a single platform where students can both find and list sublets. Additionally, nearby residents with available rental properties can also list them on the platform. This unified approach makes it easier for students to find housing that meets their needs, all in one place, simplifying what is often a complex and scattered process. 

### 🙎🏻‍♀️ TARGET AUDIENCE:

- Students and homeowners near UBC who wish to list their available housing.
- UBC students transitioning from their first year in residence to subsequent years and looking for suitable housing.

### 💡 MOTIVATION FOR THE PROJECT:

This project is inspired by my own challenges and those of my friends in finding suitable housing at UBC. It aims to simplify the housing search process for students, making it less daunting and more efficient.

---
---

## USER STORIES:

   - As a user, I want to be able to add off-campus or on-campus listings to the UBC listings portal.
   - As a user, I want to be able to view my listings.
   - As a user, I want to be able to edit my listings (change the rent, contact information, etc).
   - As a user, I want to be able to view all of the on-campus and off-campus listings in the UBC listings portal.
   - As a user, I want to be able to add listings to my favourites list.
   - As a user, I want to be able to view my favourites list.
   - As a user, I want to get the option to save or not save my favourites list to a file before quitting the application.
   - As a user, I want to get the option to load or not load my saved favourites list when I reopen the applcation.

   ---
   ---

## INSTRUCTIONS FOR GRADER:

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on the button labelled "ADD A LISTING", then add the details for the listing and click the button labelled "SUBMIT". This will add a listing to the list of all listings and you should be able to view them by clicking on the button labelled "FIND A LISTING". 
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking on the button labelled "FIND A LISTING", and adding a listing to your favourites list by clicking on the heart button. You can then view your Favourites by clicking on the button labelled "VIEW MY FAVOURITES".
- You can locate my visual component on any window - it is an image of the house logo.  
- You can save the state of my application by clicking the X on any window and then clicking on the yes option when prompted to save the session.
- You can reload the state of my application by clicking on the yes option when promted to load previous session when the app is started.

---
---

## PHASE 4 - TASK 2:

Wed Aug 07 12:45:26 PDT 2024
Favourites restored

Wed Aug 07 12:45:26 PDT 2024
Listings restored

Wed Aug 07 12:45:32 PDT 2024
On-campus listing added

Wed Aug 07 12:45:38 PDT 2024
Off-campus listing added

Wed Aug 07 12:45:40 PDT 2024
Listing added to favourites

Wed Aug 07 12:45:41 PDT 2024
Listing added to favourites


---
---

## PHASE 4 - TASK 3:

Upon reviewing the UML diagram, I recognized that the `RentalListingApp` class does not require a direct connection to the `Consumer` class. Currently, the sole purpose of this association is to facilitate access and modifications to the favorites list managed by the `Consumer`. Given that our application does not yet support user accounts or the saving of user-specific data, the `Consumer` class becomes somewhat redundant. Its primary function is merely to manage the favorites list. While separating it into its own class enhances cohesion and adheres to the single responsibility principle, it also complicates our architecture unnecessarily at this stage.

A potential refactoring could involve eliminating the `Consumer` class and integrating the favorites list directly within the `RentalListings` class. This would simplify the application's structure by removing unnecessary associations and make the system more straightforward and less coupled. However, this approach does have a trade-off: if we plan to extend the application's functionality to include user-specific features, reintroducing the `Consumer` class would become essential to manage individual user data effectively.