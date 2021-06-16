# Introduction
## Functionality
The app displays records of data usage from year 2008 to 2018.

### Project Structure
 #### activities 
 adapter - data listing for recyclerview
 data - response data captured from the server
 di - module level class for DI
 viewHolder - binding views for adapter
 viewModel - viewmodel attached to ListActivity
 #### backend 
 api implementation for fetching data
 #### di 
 retrofit for interacting with server
 #### util 
 constants and data state holder

### HomePage
Allows you to see the data consumption yearly. When there is a drop in usage in the quarter of the year, a image will be displayed to highlight the same.
Each result from api is kept in the database in records_data table where the list of year data quarterly stored. Each time an api is called, the same RecordsData record in the Database is updated with the new list of repository ids.

### Testing
The project uses both instrumentation tests that run on the device and local unit tests that run on your computer. To run both of them and generate a coverage report, you can run:

./gradlew fullCoverageReport (requires a connected device or an emulator)

### Local Unit Tests
ViewModel Tests
ViewModel is mock tested using local unit tests with mock implementations.

### Test Coverage
![Alt text](coverage.PNG?raw=true "Coverage")

### Libraries
Android Support Library
Android Architecture Components
MVVM
Android Data Binding
Dagger 2 for dependency injection
Retrofit for REST api communication
espresso for UI tests
mockito for mocking in tests
