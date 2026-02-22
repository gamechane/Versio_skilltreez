# 🚀 Gamechane Deployment Handleiding

De applicatie wordt **automatisch** op de server gezet zodra je wijzigingen stuurt naar GitHub.

## Hoe werkt het?
1.  Jij pusht je code naar de `main` branch op GitHub:
    ```bash
    git push origin main
    ```
2.  **GitHub Actions** ziet de wijziging en start een "Workflow".
3.  Deze workflow logt automatisch in op de VPS.
4.  De nieuwste code wordt opgehaald (`git pull`).
5.  De applicatie wordt opnieuw gebouwd en gestart met Docker.

## Status Controleren
Je kunt live zien of je update gelukt is:
1.  Ga naar de GitHub Repository: **[Versio_skilltreez/actions](https://github.com/gamechane/Versio_skilltreez/actions)**
2.  Kijk naar de bovenste regel in de lijst.
    *   🟡 **Geel Bolletje**: Bezig met uitrollen (duurt ca. 2 minuten).
    *   ✅ **Groen Vinkje**: Gelukt! Je wijziging staat live op [skilltreez.netwerkspel.nl](https://skilltreez.netwerkspel.nl).
    *   ❌ **Rood Kruisje**: Mislukt. Klik erop om te zien wat er fout ging (vaak een compile error in je code).

## Server Info & Inloggen (Alleen voor Docenten)
*   **URL**: [https://skilltreez.netwerkspel.nl](https://skilltreez.netwerkspel.nl)
*   **Aanmelden als Admin**: Selecteer "Docent" bij inloggen en gebruik:
    *   **Gebruiker:** `admin@example.com`
    *   **Wachtwoord:** `*2#!4a5N7ki@K%Ky&4Vt`
*   **Map op server**: `/opt/skilltreez/source/Versio_skilltreez`
*   **Logs bekijken**:
    ```bash
    # Log in als root op de VPS
    ssh root@185.224.89.206
    
    # Ga naar de map
    cd /opt/skilltreez/source/Versio_skilltreez
    
    # Bekijk live logs
    docker-compose logs -f --tail=50
    ```
