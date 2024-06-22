set ProjectPath=%~dp0
java -jar "%ProjectPath%\libraries\selenium-server-4.21.0.jar" sessions --publish-events tcp://192.168.1.10:4442 --subscribe-events tcp://192.168.1.10:4443 --port 5556