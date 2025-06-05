cd ..
wsl mvn clean package -DskipTests

cd target

copy GameShop-1.0-SNAPSHOT-jar-with-dependencies.jar ..