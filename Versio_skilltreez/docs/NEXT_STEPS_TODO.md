# 📋 TODO Lijst voor Volgende Sessie

We hebben de deployment pipeline werkend gekregen. De volgende sessie focust op het volledig operationeel maken van de applicatie (functionaliteiten).

## 1. Admin Functionaliteit Onderzoeken
*   **Status**: ✓ **Opgelost**. Het is mogelijk in te loggen via Admin (rol "Docent" te kiezen in het dropdown menu) met `admin@example.com` en `*2#!4a5N7ki@K%Ky&4Vt`. (Dit inlogaccount wordt automatisch gecreëerd door de backend).
*   **Actie**: 
    *   Test via het Dashboard en andere Views in de UI of specifieke CRUD functionaliteit rond docenten (User Management) klopt voor dit account.
    *   Lees overdrachtsdocumenten nog wel even door voor meer context qua game master settings indien aanwezig.

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
