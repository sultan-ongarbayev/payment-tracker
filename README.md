# Table of contents
* [Introduction](#introduction)
* [Installing](#installing)
    - [Clone](#clone)
    - [Build](#build)
* [Run](#run)
    - [Options](#options)
* [Usage](#usage)
* [For developers](#developers)
    - [External dependencies](#dependencies)
    - [Program components](#components)

### Payment Tracker <a name="introduction"></a>

Payment tracker is an application that keeps records of payments.
Each payment includes a currency and an amount.
Data is stored in memory, no database engines used.

#### Installing application <a name="installing"></a>
##### Clone project <a name="clone"></a>
To build `payment-tracker` application, you'll need to clone this repository to your local machine.
You can do that now with the following command:
```
$ git clone https://github.com/sultan-ongarbayev/payment-tracker.git   
```
This will download all necessary project files to your computer.
##### Build with [Maven](https://maven.apache.org) <a name="build"></a>
First you need to change the current directory:
```
$ cd payment-tracker
```
Then you can build the project by `mvn` command:
```
$ mvn install
```
It will compile all java classes, run unit tests and create runnable `.jar` package.

#### Run application <a name="run"></a>
You should now be able to run `payment-tracker` with the following command:
```
$ java -jar payment-tracker-1.0-jar-with-dependencies.jar
```

##### Options <a name="options"></a>
* --file, -f: set the file path from which payments data will loaded

#### Usage  <a name="usage"></a>
When you start the application you will see a command prompt, where you can input
new payment entry. Every 1 minute terminal screen will be updated to print net amounts
of each currency.
![Alt text](screenshots/tracker-system-screenshot.png?raw=true "Screenshot")

#### For developers <a name="developers"></a>

##### External dependencies <a name="dependencies"></a>
* [JLine 3](https://github.com/jline/jline3) - used to handle console input/output
* [Lombok](https://projectlombok.org/) - used to reduce the amount of redundant code
* [Slf4j](https://www.slf4j.org/) - logger facade
* [Log4j](https://logging.apache.org/log4j/2.x/) - actual implementation of logger
* [Commons CLI](https://commons.apache.org/proper/commons-cli/) - used to parse command line arguments
* [JUnit](https://junit.org/junit5/) - used for testing

##### Program components <a name="components"></a>
 * InputService - component which is actually a wrapper around the Runnable interface. It's responsible for handling user input.
 * OutputService - component which is mostly the same as InputService but it's responsible for terminal output.
 * PaymentRepository - component which is responsible for storing payments data. It's possible to extend data storage system by implementing this interface. Simple realization of this is used. It stores data in memory only. No database engines.
 * PaymentParser - this component is responsible for parsing payment data.
 * NetAmountCalculator - class, which is possible to calculate net amount for every currency. Gets data from repository.
 * Payment - model class, represents any payment in the system.
 * Console - this class takes care of communication with virtual terminal.
 * PaymentTracker - main class, which starts the program.