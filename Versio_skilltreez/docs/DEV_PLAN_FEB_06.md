# Development Plan & Session Recap (Feb 06, 2026)

## 1. What We Accomplished Today
*   **Built Admin Page:** Implemented `AdminView.vue` with a user table, "Add User" modal, and delete functionality.
*   **Fixed Critical Backend Bugs:**
    *   **Circular Dependency:** Moved `PasswordEncoder` to a separate config to fix the initialization loop.
    *   **Startup Crash:** Fixed `Swagger` vs `RequestMappings` conflict by setting `spring.mvc.pathmatch.matching-strategy = ant_path_matcher`.
    *   **CORS:** Configured `allowedOriginPatterns` to support credentials.
*   **Deployed to Production:** configured GitHub Actions to deploy to the VPS automatically.

## 2. How to Run the Project

### Local Development (Windows)
1.  Open PowerShell in the `Versio_skilltreez` directory:
    ```powershell
    cd d:\Gamedidactiek\GamechaneProjects\Projects\Gamechane_AntiGravityclone\Versio_skilltreez
    ```
2.  Run the start script:
    ```powershell
    .\start_local.ps1
    ```
3.  Enter your local Postgres password when prompted.
4.  Two windows will open:
    *   Backend: http://localhost:8080
    *   Frontend: http://localhost:5173

### Remote Deployment (VPS)
*   **Automatic:**
    *   Run `git push origin main`.
    *   Wait ~2 minutes for GitHub Actions to build and deploy.
*   **Manual Force Update (SSH):**
    *   `ssh root@185.224.89.206`
    *   `cd /opt/skilltreez/source/Versio_skilltreez`
    *   `git pull origin main`
    *   `docker-compose up -d --build backend`

## 3. The "Missing Menu" Mystery (Next Session Plan)

### Problem
The **Admin** button is visible on the Dashboard when running locally, but **invisible** on the Production server (`https://skilltreez.netwerkspel.nl`).

### Diagnosis
1.  **Backend Change:** We updated `UserService` to strictly follow Spring Security standards by adding the `ROLE_` prefix to roles (e.g., `ROLE_ADMIN`).
2.  **Frontend Logic:** `DashboardView.vue` hides the button unless the user's role matches. It expects `ADMIN`.
3.  **Conflict:**
    *   The Token now contains `ROLE_ADMIN`.
    *   The Frontend checks `does ["ADMIN"].include("ROLE_ADMIN")?` -> **FALSE**.
4.  **Why Local Works:** Your local browser likely has a cached token from *before* the change (or the local backend state hasn't fully propagated the prefix to the token generation in `JwtTokenUtil` despite the code change—but caching is most likely).

### Action Plan
1.  **Update Frontend Role Parsing (`frontend/src/utils/jwt-utils.js`):**
    *   Modify `getRole(token)` to strip the `ROLE_` prefix before returning.
    *   *OR* Update `DashboardView.vue` to expect `ROLE_ADMIN`.
2.  **Verify:**
    *   Deploy to production.
    *   Clear browser cache/storage.
    *   Login as Admin.
    *   Button should appear.

### Debugging Tips
To confirm this yourself:
1.  Open Chrome DevTools (F12) -> **Application** tab.
2.  Look at **Local Storage**.
3.  Copy the `token` value.
4.  Paste it into [jwt.io](https://jwt.io) to see the payload. You will likely see `"roles": "ROLE_ADMIN"` on production.
