# Deployment Guide - VPS Deployment

Deze guide beschrijft hoe je het Gamechane project kunt deployen naar een VPS.

## Vereisten

- VPS met Ubuntu/Debian (of vergelijkbare Linux distributie)
- Java 11 of hoger
- Node.js 18+ en npm
- PostgreSQL database
- Maven 3.6+
- Nginx (optioneel, voor reverse proxy)

## Stappen voor Deployment

### 1. Server Setup

```bash
# Update system packages
sudo apt update && sudo apt upgrade -y

# Install Java 11
sudo apt install openjdk-11-jdk -y

# Install Node.js en npm
curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash -
sudo apt install -y nodejs

# Install Maven
sudo apt install maven -y

# Install PostgreSQL
sudo apt install postgresql postgresql-contrib -y
```

### 2. Database Setup

```bash
# Start PostgreSQL service
sudo systemctl start postgresql
sudo systemctl enable postgresql

# Maak database aan
sudo -u postgres psql
```

In PostgreSQL:
```sql
CREATE DATABASE gamechane;
CREATE USER gamechane_user WITH PASSWORD 'jouw_sterk_wachtwoord';
GRANT ALL PRIVILEGES ON DATABASE gamechane TO gamechane_user;
\q
```

### 3. Project Clonen en Builden

```bash
# Clone repository
git clone <jouw-repository-url>
cd gamechane

# Build frontend
cd frontend
npm install
npm run build
cd ..

# Build backend (dit integreert automatisch de frontend)
cd backend
mvn clean package -DskipTests
cd ..
```

### 4. Environment Variables Instellen

Maak een `.env` bestand of exporteer environment variables:

```bash
export PORT=8080
export DATABASE_URL=jdbc:postgresql://localhost:5432/gamechane
export DATABASE_USERNAME=gamechane_user
export DATABASE_PASSWORD=jouw_sterk_wachtwoord
export JWT_SECRET=jouw_sterke_jwt_secret_minimaal_32_karakters
export CORS_ALLOWED_ORIGINS=https://jouw-domein.nl,https://www.jouw-domein.nl
export SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

Voor productie, gebruik een sterk JWT secret (minimaal 32 karakters).

### 5. Applicatie Starten

```bash
# Start de applicatie
java -jar backend/target/skilltreez-1.0.jar
```

### 6. Systemd Service (Aanbevolen)

Maak een systemd service voor automatisch opstarten:

```bash
sudo nano /etc/systemd/system/gamechane.service
```

Voeg toe:
```ini
[Unit]
Description=Gamechane Application
After=postgresql.service

[Service]
Type=simple
User=ubuntu
WorkingDirectory=/path/to/gamechane
Environment="PORT=8080"
Environment="DATABASE_URL=jdbc:postgresql://localhost:5432/gamechane"
Environment="DATABASE_USERNAME=gamechane_user"
Environment="DATABASE_PASSWORD=jouw_sterk_wachtwoord"
Environment="JWT_SECRET=jouw_sterke_jwt_secret"
Environment="CORS_ALLOWED_ORIGINS=https://jouw-domein.nl"
Environment="SPRING_JPA_HIBERNATE_DDL_AUTO=update"
ExecStart=/usr/bin/java -jar /path/to/gamechane/backend/target/skilltreez-1.0.jar
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

Start de service:
```bash
sudo systemctl daemon-reload
sudo systemctl enable gamechane
sudo systemctl start gamechane
sudo systemctl status gamechane
```

### 7. Nginx Reverse Proxy (Optioneel)

Installeer en configureer Nginx:

```bash
sudo apt install nginx -y
sudo nano /etc/nginx/sites-available/gamechane
```

Voeg toe:
```nginx
server {
    listen 80;
    server_name jouw-domein.nl www.jouw-domein.nl;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

Activeer:
```bash
sudo ln -s /etc/nginx/sites-available/gamechane /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl restart nginx
```

### 8. SSL Certificaat (Let's Encrypt)

```bash
sudo apt install certbot python3-certbot-nginx -y
sudo certbot --nginx -d jouw-domein.nl -d www.jouw-domein.nl
```

## Security Checklist

- [ ] Wijzig default database wachtwoorden
- [ ] Gebruik een sterk JWT secret (minimaal 32 karakters)
- [ ] Configureer CORS met specifieke origins (niet `*`)
- [ ] Stel firewall in (UFW)
- [ ] Update `SPRING_JPA_HIBERNATE_DDL_AUTO` naar `validate` in productie
- [ ] Configureer SSL/HTTPS
- [ ] Stel regelmatige backups in voor de database

## Troubleshooting

### Logs bekijken
```bash
# Systemd logs
sudo journalctl -u gamechane -f

# Application logs
tail -f backend/errors.log
```

### Database connectie testen
```bash
psql -h localhost -U gamechane_user -d gamechane
```

### Port checken
```bash
sudo netstat -tlnp | grep 8080
```

## Updates Deployen

```bash
# Pull latest changes
git pull

# Rebuild frontend
cd frontend
npm install
npm run build
cd ..

# Rebuild backend
cd backend
mvn clean package -DskipTests
cd ..

# Restart service
sudo systemctl restart gamechane
```


