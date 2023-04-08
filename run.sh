#!/bin/bash

# function to stop the Spring Boot processes
function stop_spring_boot() {
    echo "Stopping Spring Boot applications..."
    kill $(jobs -p)
}

# Add a trap command to call the stop_spring_boot function when the script is stopped
trap stop_spring_boot SIGINT SIGTERM

cd game-service/
mvn spring-boot:run &

cd -

cd player-service/
mvn spring-boot:run &

# Wait for the Spring Boot processes to finish
wait