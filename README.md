# A Real Estate Portfolio Tracker
___
Vivaan's Real Estate Portfolio Tracker is an **intuitive and user-friendly application** that allows real estate agents to 
efficiently manage and keep track of their real estate properties and listings. This application will
serve as a convenient and effortless solution to complicated record-keeping, as it allows users to add new listings, keep track
of existing listings, sell listings and more.

Users can categorise the listings by demand, which helps determine trending listings and thus, provides valuable insights
to improve sales.

Growing up, I have always been interested in real estate and real estate management. As such, when I learnt that real estate agents
often manage a lot of listings at one time, I wanted to create an application that helps track the status of these listings
in the form of a real estate portfolio tracker.

---
# User Stories
- As a user, I want to be able to add a new real estate listing (with specific details about the listing).
- As a user, I want to be able to maintain a portfolio of all my current listings.
- As a user, I want to be able to be able to view all my unsold listings at any time.
- As a user, I want to be able to mark a listing as sold and remove it from the portfolio.
- As a user, I want to be able to view my listings, based on certain criteria (e.g., those that are in demand).
- As a user, I want to be able to see the value of my portfolio at any time.
- As a user, I want to have the option to save my entire real estate portfolio (containing all listings) to file.
- As a user, I want to have the option to load my entire real estate portfolio from file, which contains all my saved listings.
---
# Instructions for Grader
- When the application is run, the "Enter Portfolio" button allows you to enter the main menu.
- At every stage of the application, there is a button at the bottom of the panel to go back to the main menu.
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the "Add 
new listing to portfolio" button. This will then take you to a screen where you enter the listing details and then click 
"Create listing" to add it to the portfolio.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking on the 
"View listings in demand" button in the main menu. This will allow you to filter all the listings in the portfolio and 
only display those that are "in demand".
- You can "sell" a listing by clicking the "Sell current listing" button in the main menu. This will take you to a screen 
where you must enter the listing ID of the listing you want to sell and then click "Sell listing" button. This will remove 
the listing from the portfolio.
- You can view all current listings in the portfolio by clicking the "View all listings" button in the main menu.
- You can view the sum total value of the listings in the portfolio by clicking the "View portfolio value" button in the 
main menu.
- You can locate my visual component by looking at the background image of the GUI.
- You can save the state of my application by clicking the "Load Portfolio button" in the main menu.
- You can reload the state of my application by clicking the "Save Portfolio" button in the main menu.
---
# Phase 4: Task 2
Events logged since application started:<br>
Tue Apr 02 11:40:37 PDT 2024 <br>
Portfolio created<br>
Tue Apr 02 11:40:49 PDT 2024<br>
Portfolio does not contain listing ID 123<br>
Tue Apr 02 11:40:49 PDT 2024<br>
New listing with ID 123 created.<br>
Tue Apr 02 11:41:09 PDT 2024<br>
Portfolio does not contain listing ID 456<br>
Tue Apr 02 11:41:09 PDT 2024<br>
New listing with ID 456 created.<br>
Tue Apr 02 11:41:13 PDT 2024<br>
Current portfolio value is 536.0<br>
Tue Apr 02 11:41:19 PDT 2024<br>
Portfolio contains listing ID 456<br>
Tue Apr 02 11:41:19 PDT 2024<br>
Listing 456 sold.<br>
Event log cleared.
---
# Phase 4: Task 3
With more time to improve on the design on my application, I would improve the organization and cohesion of the `Listing` class with the help of refactoring.
Currently, this seems to handle multiple responsibilities related to a property, such as its ID, name, type, price, size, and demand status. 
A refactoring approach could involve breaking down the `Listing` class into smaller, more focused classes, each responsible for a single aspect of a property. 
For example, separate classes could be created for `ListingID`, `ListingName`, `ListingPrice`, `ListingSize`, and `ListingDemand`. 
This refactoring would adhere more closely to the Single Responsibility Principle (SRP) and make it easier to understand, modify, and extend in the future.
Additionally, introducing an interface or abstract class for listings could provide a standardized structure for different types of properties. This could accommodate various property types (e.g., residential, commercial, industrial) with specific attributes and behaviors.