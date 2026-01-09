# 📋 TODO Lijst voor Volgende Sessie

We hebben de deployment pipeline werkend gekregen. De volgende sessie focust op het volledig operationeel maken van de applicatie (functionaliteiten).

## 1. Admin Functionaliteit Onderzoeken
*   **Probleem**: Inloggen met `admin@example.com` werkt, maar stuurt door naar `/dashboard`. Er lijkt geen specifieke admin-omgeving zichtbaar.
*   **Hypothese**:
    *   Mogelijk is 'Admin' geen aparte pagina, maar een rol die extra knoppen op het dashboard toont.
    *   OF: De admin-functionaliteit zit in een fysiek gescheiden applicatie (andere repo op GitHub?).
    *   OF: De database seed (`DataInitializer`) heeft de admin-rol niet goed toegekend.
*   **Actie**:
    *   Check GitHub organisatie `gamechane` op andere repo's (bijv. `admin` of `gamemaster`) en kijk of die relevant zijn.
    *   Lees oude overdrachtsdocumenten door op zoek naar "Admin instructies".
    *   Maak eventueel zelf een eenvoudige "User Management" pagina om nieuwe docenten aan te kunnen maken.

## 2. Applicatie Volledig Testen
*   **Database**: Lokaal de database goed opzetten zodat alles werkt (nu crasht de backend soms nog omdat DB ontbreekt).
*   **Use Cases**:
    *   Inloggen als Docent.
    *   Nieuwe 'Game' aanmaken.
    *   Student laten joinen.
    *   Scores bekijken.
*   **Doel**: Zeker weten dat de applicatie niet alleen "start", maar ook echt "werkt".

## 3. Code Analyse
*   Doorloop de Backend code (`UserService`, `SecurityConfig`) om te zien hoe rollen (ROLE_ADMIN, ROLE_TEACHER) werken.
*   Doorloop de Frontend code om te zien waar deze rollen worden gecheckt (bijv. `v-if="isAdmin"`).

---
**Startpunt volgende keer:**
Start met de lokale omgeving (`.\start_local.ps1`) en zorg dat je pgAdmin bij de hand hebt om in de database te kijken.
