# Smart Climate Monitoring System


This is a project for the Distributed Systems module of my Software Development course. It’s a JavaFX desktop application that connects to three gRPC microservices: Environmental Monitoring, Climate Response, and Climate Analytics.

The GUI acts as a central dashboard to display live sensor data, control climate devices, and run analytics.

This project was developed using IntelliJ.


## Features

Automatic Service Management: All three gRPC servers start automatically when GUI launches
Real-time Environmental Monitoring: Live sensor data (temperature, humidity, CO₂, air quality)
Server-side Streaming: Continuous sensor data updates every 2 seconds
Device Control: Activate climate devices with adjustable intensity levels
Batch Device Operations: Send multiple device commands via client-side streaming
Real-time Climate Analytics: Bidirectional streaming for live data analysis and recommendations
Service Discovery: Automatic service registration and discovery using jmDNS
Comprehensive Dashboard: Unified control interface with real-time logging

## Services

Environmental Monitoring Service (Port 50051) - Sensor data collection and streaming
Climate Response Service (Port 50052) - Device control and batch command processing
Climate Analytics Service (Port 50053) - Data analysis and trend recommendations

## Technologies

Java 21 - Implementation language
gRPC 1.53.0 - Inter-service communication
Protocol Buffers - Message serialization
JavaFX 17.0.2 - GUI framework
jmDNS 3.5.8 - Service discovery

## Prerequisites

Java 11+ (tested with Java 21)
Maven 3.6+ (if using Maven option)
[JavaFX SDK](https://openjfx.io/)
Protocol Buffers (`protoc`) – *Only if you want to recompile `.proto` files*

## Running the Project

Note: The GUI automatically starts al 3 servers so you shouldn't need to run anything seperartely

Option 1: run with Maven

```bash
mvn compile
mvn exec:java -Dexec.mainClass="ds.client.ControllerGUI"

Option 2: run manually (if no Maven, this is what I did)

# Optional: Recompile proto files if needed
protoc --java_out=src/main/java src/main/resources/*.proto

# Compile Java
javac -cp "lib/*" src/main/java/ds/**/*.java

# Run the GUI
java -cp ".:lib/*" ds.client.ControllerGUI

## How it all works

1. GUI starts, launches 3 gRPC services in background threads
2. Wait, 2 seconds to allow services to start
3. JmDNS discovers services locally
4. Can now interact with all features in GUI

## gRPC Comm Types

Simple RPC: getCurrentReadigns(), activateDevice(), analyzeClimateData()
Server Streaming: streamSensorData(), 2 second updates
Client Streaming: processDeviceCommands(), send multiple commands
Bidrectional Streaming: realTimeAnalysis, simulate live feedback

## GUI usage

Environmental Monitoring:
Enter location (e.g., "room1")
"Get Current Data": Fetch single sensor reading
"Start/Stop Stream": Toggle continuous sensor updates

Device Control:
Enter device ID (e.g., "airpurifier1")
Set intensity level (0-100%)
"Activate Device": Control single device
"Send Batch Commands": Send multiple commands via streaming

Climate Analytics:
"Analyze Climate Data": Get analysis summary with recommendations
"Start Real-Time Analysis": Begin bidirectional streaming analysis

## UN SDG Support, Climate Action

Real-time Environmental Monitoring: Continuous tracking of air quality and climate conditions
Automated Response Systems: Energy-efficient device control based on environmental data
Data-Driven Insights: Analytics provide actionable recommendations for climate optimization
Predictive Analysis: Trend detection for proactive climate management

## Notes
So the .proto were compiled manually using after downloading protoc. The JavaFX and gRPC depenedencies were added manually (wasn't Maven auto-import). Folder structure might be a bit different from normal Maven standards as a result. For full functionality try using the same Java version (21.0.7) and javaFX SDK (17.0.2 or compatible). 
Also, if the GUI is not launching make sure you've added JavaFX VM options if running manually:
--module-path "C:\path\to\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml
I did not see the VM options immediately, you may need to "modify options" and choose to add it.
Lastly, there may be a SLF4J warning, it is safe to ignore, only a logger fallback.