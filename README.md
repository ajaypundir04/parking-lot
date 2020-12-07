# Parking Lot
Parking lot can hold up to 'n' cars at any given point in time. 
Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps of one. It's an automated ticketing system that allows to manage parking lot without human intervention.

### Assumptions
1. Number Plate is unique for every vehicle.
2. We will create parking lot only once throughout the application, i.e `create_parking_lot`.
3. `create_parking_lot` will be the first command in the input file, otherwise application will through the exception.
3. One slot is provided to the single car.
4. Default color for the vehicle is `noColor`, if not supplied.
5. `-1` indicates the invalid slot number.

### Application Flow
1. `create_parking_lot` is used to create the parking lot, this command will be used once though-out the application. 
2. `park` is used to park the vehicle at the available slot.
3. `leave` will un-park the vehicle and return the charges of the parking, provided vehicle is already present in the parking lot.
4. `status` will display the parking lot.
5.  We have used the `ReentrantLock` for the better communication between threads performing parking operations.

### Test
1. We can run `./gradlew test`  for running test cases

### Setup Dependencies
1. Java : JDK1.8
2. Build Tool : Gradle 4.10.3
3. Junit : 4.12
4. Mockito : 2.7.22


### Start Application
1. ApplicationLaucher is the main entry for application.
2. we can execute the command `bin/parking_lot/<input_file_path>` to run the application. Eg: `bin/parking_lot src/main/resources/file_inputs.txt`
3. `bin/setup` for build and test the application.
