# Overdrachtsupdate - 22 februari 2026

Dit document is een aanvulling op het bestaande overdrachtsdocument en beschrijft de actuele situatie van de codebase op basis van branch-analyse.

## Samenvatting

- De code staat verdeeld over meerdere branch-lijnen.
- De huidige `origin/main` in `..\gamechane_2025\gamechane` bevat de nieuwere `frontend` + `backend` structuur.
- De AI-module en klassieke `gamemaster_frontend` zitten niet in die `main`, maar wel in `origin/staging` en `origin/staging-ai-module`.
- Docker is niet volledig uitgewerkt voor de hele applicatie; in docker-branches staat alleen database-compose.

## Waar staat wat

### 1) Nieuwe lijn (zonder AI-module in main)

- Repository: `..\gamechane_2025\gamechane`
- Branch: `origin/main` (commit `0699a5f`, 2025-12-19)
- Structuur: `frontend/` + `backend/`
- Doel: moderne basislijn

### 2) Lijn met AI + gamemaster-frontend

- Branches: `origin/staging-ai-module` (2025-06-30), `origin/staging` (2025-07-06) en enkele fix-branches daarna.
- Voorbeelden van AI-bestanden:
  - `backend/src/main/java/com/gamechane/backend/ai/presentation/SkilltreeAIController.java`
  - `backend/src/main/java/com/gamechane/backend/ai/service/OpenAIService.java`
  - `backend/src/main/java/com/gamechane/backend/ai/service/SkilltreeAIService.java`
  - `gamemaster_frontend/src/components/ai/AIGenerationModal.vue`
  - `gamemaster_frontend/src/services/ai.service.js`
  - `gamemaster_frontend/src/views/skilltree/SkilltreesView.vue` (knop "Genereer met AI")

### 3) Rubriceerspel (aanmaak)

- Klassieke gamemaster-flow:
  - `gamemaster_frontend/src/views/spel/Spel.vue`
  - `gamemaster_frontend/src/services/spel.service.js`
- In deze lijn bestaat rubriceerspel-aanmaak en save via backend endpoint `/api/spel/`.

## Kritieke aandachtspunten

### 1) Groot bestand in historie (Git/GitHub risico)

- Bestand: `gamemaster_frontend/node/node.exe` (~61 MB)
- Gevolg: problemen met push/import/migratie en repo-hygiëne.

### 2) Gevoelige gegevens in versiebeheer (security incident)

- In AI/staging-branches staan secrets direct in:
  - `backend/src/main/resources/application.properties`
- Daarin staan o.a. DB-credentials en OpenAI-key in plain text.
- Verplicht: direct roteren/vervangen van alle gelekte credentials.

### 3) Architectuurverschil tussen lijnen

- Nieuwe lijn gebruikt andere frontend-opzet dan oude gamemaster-lijn.
- API-benamingen en domeinmodel verschillen (`/api/game` vs `/api/spel` in verschillende code-lijnen).
- Blind kopieren tussen lijnen geeft regressies.

### 4) Docker-onvolledig

- Docker-branches bevatten een `docker-compose.yml` voor PostgreSQL.
- Geen volledige app-containerisering (frontend/backend services ontbreken).

## Besluitvoorstel voor nieuw team

Kies expliciet 1 bronlijn voor doorontwikkeling:

1. **Optie A (aanbevolen):** doorgaan op moderne `origin/main` en AI gericht terugporten.
2. **Optie B:** tijdelijk op `origin/staging` voor demo-continuiteit, maar eerst security + repo-opschoning uitvoeren.

## Startchecklist (dag 1)

1. Leg vast welke branch "source of truth" is.
2. Roteer alle gelekte sleutels/wachtwoorden en verwijder hardcoded secrets uit repo.
3. Beslis deploymentpad: VPS/systemd of echte Docker-stack.
4. Maak 1 setup-document dat exact klopt met de gekozen branch.
5. Documenteer welke features "werkend", "deels", en "niet aanwezig" zijn.

## Status van deze update

- Geen functionele codewijzigingen uitgevoerd.
- Alleen documentatie-aanvulling op basis van branch- en bestandsanalyse.
