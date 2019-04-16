# Transfer app

### Setup the database
Before start, it is necessary to install the MySQL database. The JPA is configured in the file src/resources/application.properties.
The username on my system was "root" and the password "Rapid2015", the schema name: "trans_app_db". In the email I attached a dump file wich are will create the tables and insert the initial datas.
the dump file will insert the predefined accounts with their balance, which is important because right now its not possible to send money to an account because of the balance check. There is no balance property for the account class, so its not possible to adding balance at creation. The balance will be calculated from the transaction history.
### Run the app
In my solution I used spring boot and the app is developed by intelliJ for application server I used Apache TomCat 9.0.1.7, so a TomCat server is also need to be installed and configured with intelliJ.
To call the proper end points I made postman collection with examples and its also sent with my email.

### End to end test
My test location src/test/java/com.monese.interviewtest.transferapp.TransferE2ETests
My solution use HTTP calls to call the proper function so it will work only when the server is running.
My test transfer money from the source account to the target account and after the test it will be sent back to the source to avoid falling test in case of the next run.



