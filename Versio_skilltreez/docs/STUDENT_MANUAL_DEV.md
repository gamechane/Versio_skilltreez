# 🎓 Gamechane Ontwikkelhandleiding (Lokaal)

Welkom bij het Gamechane project! Volg deze stappen om de applicatie op je eigen laptop draaiend te krijgen.

## 1. Vereiste Software
Zorg dat je het volgende geïnstalleerd hebt:
*   **Git**: [Download](https://git-scm.com/downloads)
*   **Java 17 JDK**: [Download (Kies versie 17!)](https://adoptium.net/temurin/releases/?version=17)
*   **Node.js 18+**: [Download](https://nodejs.org/)
*   **PostgreSQL**: [Download](https://www.postgresql.org/download/) (Installeer ook pgAdmin 4).

## 2. Project Setup
1.  Clone de repository (of download de zip):
    ```bash
    git clone https://github.com/gamechane/Versio_skilltreez.git
    ```
2.  Ga naar de submap `Versio_skilltreez` (waar `start_local.ps1` staat).

## 3. Database Aanmaken
De applicatie heeft een lege database nodig om te starten.
1.  Open **pgAdmin 4** (startmenu).
2.  Log in met je PostgreSQL wachtwoord.
3.  Ga links naar **Servers** -> **PostgreSQL** -> **Databases**.
4.  Rechtsklik op Databases -> **Create** -> **Database...**
5.  Naam: `gamechane`
6.  Klik op **Save**.

## 4. Applicatie Starten
We hebben een script gemaakt dat alles voor je doet.
1.  Open **PowerShell** in de map `Versio_skilltreez`.
2.  Typ en start:
    ```powershell
    .\start_local.ps1
    ```
3.  Het script vraagt om je **Database Wachtwoord**. Dit is het wachtwoord dat je koos tijdens de installatie van PostgreSQL.
4.  Er openen nu twee extra vensters:
    *   **Backend Server**: Let op dat hier staat: `Started SkilltreezApplication`.
    *   **Frontend Server**: Let op dat hier staat: `Local: http://localhost:5173`.

## 5. Inloggen
1.  Ga in je browser naar: **[http://localhost:5173/login](http://localhost:5173/login)**
2.  Standaard Admin gegevens:
    *   Email: `admin@example.com`
    *   Wachtwoord: `*2#!4a5N7ki@K%Ky&4Vt`

## 🐛 Troubleshooting
*   **Database connection refused?** Controleer of je de database `gamechane` echt hebt aangemaakt in pgAdmin en of je wachtwoord klopt.
*   **Port already in use?** Sluit alle zwarte vensters en draai in PowerShell:
    `Stop-Process -Name "java" -Force`
    `Stop-Process -Name "node" -Force`
