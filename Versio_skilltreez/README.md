# Gamechane
Een platform voor docenten om educatieve spellen te maken die door leerlingen gespeeld kunnen worden.

# Documentatie

Alle documentatie staat in de directory `/documentatie`, daarnaast zijn de wireframes beschikbaar via [Figma](https://www.figma.com/file/sgcCBa2xb31MIq3hpmAw4h/UI-SkillTreez?type=design&node-id=0-1).

# Installatie + Troubleshooting
Hier wordt het process laten zien van het clonen van de remote repository tot het draaien van de front-end, back-end en de database

1. Clone de repository in je IDE.
2. Open de terminal in de repository root.
3. Navigeer in de terminal naar de te runnen front-end directory met het commando `cd {front_end_directory_name}`,
waar front_end_directory_name met de naam van de gewenste front-end directory vervangen dient te worden.
4. Run `npm i` of `npm install` in de terminal om de packages te installeren.
5. Run `npm run serve` om de server op te starten.

### Optioneel voor een lokale database
Met de tot nu toe gevolgde installatiestappen is er een connectie gemaakt met de staging database.
Volg de volgende stappen om een lokale database op te zetten indien nodig.

6. Open de folder `backend`
7. Ga naar `application.properties` in `src>main>resources>application.properties`
8. Open pgAdmin (postgres) en maak een nieuwe database aan.
9. Geef de database een naam en laat de owner staan op `postgres`
10. In `application.properties`, verander de `url`, `username` en `password` naar die van je postgres\
Voorbeeld:\
spring.datasource.url=jdbc:postgresql://localhost:5432/gamechane\
spring.datasource.username=postgres\
spring.datasource.password=JeWachtWoord
11. Draai de Front-End (zie stap 4 en 5), Draai de back-end (via de 'play' knop in IntelliJ)
12. Na het draaien van de backend zal er in pgAdmin in het netwerk een connectie zijn met je backend, en de front-end zal draaien op localhost:8080

## Troubleshooting
Bij (meerdere) errors tijdens het bouwen van de applicatie (4 en 5 van de installatie) kunnen dit mogelijke oplossingen zijn.

### Algemene problemen met npm & gyp
1. Verwijder het gegenereerde `package-lock.json` file, deze wordt weer opnieuw gegenereerd met `npm i`/`npm install`.
2. Doe hetzelfde met de `node_modules` directory.
3. Volg vervolgens de installatiestappen 2 tot en met 5.

### Problemen met python
* Download [Python 2.7.18](https://www.python.org/downloads/release/python-2718/) , na het installeren van deze python, ga naar systeem variablen omgeving en zet `C:\Python27` en `C:\Python27\Scripts` in de PATH.
* Download [Python 3.10.4](https://www.python.org/downloads/release/python-3104/) , tijdens het installeren, vink de checkbox 'toevoegen aan PATH' aan.
* Als je systeem beschikt over een Python versie > 3.12, Run `pip install setuptools`.

### Problemen met .NET of Visual Studio
* Download [Visual Studio Community](https://visualstudio.microsoft.com/downloads/) , en in de installer, vink de juiste installatie tools aan, zie: [stackoverflow VS hulp](https://stackoverflow.com/a/59882818) voor meer informatie.
Vink in ieder geval de volgende optie aan: `Desktop development with C++` en vervolgens de juiste Windows SDK in de installatie details daarvan.

