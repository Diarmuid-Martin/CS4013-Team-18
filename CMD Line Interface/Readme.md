## How to launch
Clone the repo
Install maven
Run the following commands
mvn compile -DskipTests
mvn exec:java -Dexec.mainClass="com.team18.taxprogram.App" -Dexec.args="properties.csv owners.csv transactions.csv" -q
Alternatively you can build the source manually passing "properties.csv owners.csv transactions.csv" on the command line
