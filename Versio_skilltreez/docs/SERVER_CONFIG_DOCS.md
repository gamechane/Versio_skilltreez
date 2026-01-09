# 🖥️ Server Configuratie Documentatie

**Server IP**: 185.224.89.206
**OS**: CentOS 7
**Control Panel**: DirectAdmin

## 1. Architectuur
De applicatie draait in Docker containers, geïsoleerd van de rest van de server (DirectAdmin/Apache/PHP).
Er draait een Apache Reverse Proxy (via DirectAdmin Custom HTTPD config) die verkeer van `skilltreez.netwerkspel.nl` doorstuurt naar `localhost:8090` (Frontend Container).

## 2. Gebruikers & Toegang
Er zijn twee belangrijke gebruikers op het systeem:

### Root (`root`)
*   **Rol**: Systeembeheerder.
*   **Toegang**: SSH.

### Deploy User (`deploy_user`)
*   **Rol**: Automatische deployments via GitHub Actions.
*   **Toegang**: SSH (via Key), lid van `docker` groep.
*   **Locatie App**: `/opt/skilltreez/source/Versio_skilltreez`
*   **Beperkingen**: Mag alleen inloggen met specifieke SSH key (vastgelegd in GitHub Secrets).
*   **SSH Config**: Toegevoegd aan `AllowUsers` in `/etc/ssh/sshd_config`.

## 3. Deployment Workflow
1.  Code wordt gepusht naar GitHub `main`.
2.  Action logt in als `deploy_user`.
3.  Gaat naar `/opt/skilltreez/source/Versio_skilltreez`.
4.  Voert `git pull` uit.
5.  Voert `docker-compose up -d --build` uit.

## 4. Docker Configuratie
Het bestand `docker-compose.yml` definieert 3 services:
1.  **db**: PostgreSQL 16 (in intern netwerk).
2.  **backend**: Java 17 Spring Boot API (poort 8080 intern).
3.  **frontend**: Vue.js + Nginx (poort 8090 extern tbv proxy).

Geheimen (zoals DB wachtwoord) staan in het `.env` bestand op de server.

## 5. Locaties
*   **Project**: `/opt/skilltreez/source/Versio_skilltreez`
*   **Logs**: `/opt/skilltreez/source/Versio_skilltreez` (via `docker-compose logs`)
*   **Nginx Proxy Config**: DirectAdmin -> Custom HTTPD Configurations -> skilltreez.netwerkspel.nl
