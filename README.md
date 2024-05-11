# modern-warfare-marketplace
Designing a sample marketplace for players to transact their weapons


Problem Statement
Objective: 

To develop a marketplace for In-game items of COD: Modern Warfare 2. These items are guns that are used by players in the game. We can support trading of 10 guns for this assignment. These guns will be purchased by gamers in the US on their android phone. Starting price of the item will be 10 USD and the maximum price is 1000 USD. Each user is allowed to purchase only one item in a month. Users can not list an item for a price less than 10 USD and more than 1000 USD. 

We want to launch V1 of Game’s marketplace. This marketplace 

Should be able to fetch the list of available items for sale
The details that should be visible to a potential buyer are - Name of the item, Quantity, Price of the item (USD)
Buyer should be able to purchase the item based on conditions mentioned above.
Buyer will purchase in USD - we can assume that they will pay via credit card
Buyer will pay to SUPAKI. SUPAKI  will confirm the receipt of funds. SUPAKI will transfer the item to Buyer account. Seller will get the funds. This entire trade will happen in a single request
SUPAKI will earn 10% from this TRX. Seller will earn 90
Buyer should not be able to list in the marketplace for another 24 hours
Buyer should be able to view this item in their account

Deliverables 

Design a simple backend application in Spring Boot with either MySQL or PostgreSQL as the DB. Identify the Objects/Entities required for doing so and design the table schema as such.


Some of the core APIs of this application are as follows:

Fetch-Listings API 
List-Item API
Purchase API
Account-Details API

Feel free to add more if required. List down all the assumptions you have made. 

Bonus:

Dockerise the application. 
Share the Github link of the full source code.
Prepare a Swagger of the created APIs.
Create unit cases test.


Solution link for the same :-
