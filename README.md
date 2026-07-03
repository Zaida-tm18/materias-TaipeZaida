# Materias UTEQ — Evaluación Unidad II (Spring Boot 3)

Micro CRUD web seguro con autenticacion, desarrollado como practica personal
sobre el enunciado de la Unidad II de Aplicaciones Web (UTEQ).

## 1. Datos del estudiante
- Apellidos y nombres: `<Taipe Mora Zaida Melissa>`
- Cedula: `<0604907956>`
- Paralelo: `<A>`
- Fecha del examen: `<03/ 07/ 2026>`

## 2. Pila tecnologica
- Spring Boot 3.3.4
- Java 21 (Temurin)
- PostgreSQL 16
- Thymeleaf + Spring Security 6

## 3. Requisitos previos
- Docker Desktop 4.80 (o Docker Engine + Docker Compose v2) instalado y corriendo.
- Puerto 8080 libre en el host (configurable con `APP_PORT`).

## 4. Arranque en un solo comando

```bash
git clone https://github.com/Zaida-tm18/materias-TaipeZaida.git
cd materias-<TaipeZaida>
cp .env.example .env
docker compose up -d --build
# esperar 30-60 s y abrir http://localhost:8080/login
```

Esto levanta el contenedor de PostgreSQL, construye la imagen de la app con
Maven, aplica el esquema (Hibernate `ddl-auto=update`) y crea el usuario
administrador semilla automaticamente al primer arranque.

## 5. Credenciales del usuario semilla
- Usuario: `admin`
- Contrasena: `Admin*2026`

## 6. URL local del sistema
http://localhost:8080/login

Tras autenticarse, el sistema redirige a http://localhost:8080/materias

## 7. Como probar el CRUD
Ver `docs/requests.http` para las rutas disponibles. Flujo recomendado desde
el navegador:
1. Ir a `/login` e ingresar con las credenciales semilla.
2. En `/materias`, usar **+ Nueva materia** para crear (`GET/POST /materias`).
3. Usar **Editar** sobre una fila (`GET/POST /materias/{id}` y `/{id}/editar`).
4. Usar **Eliminar** para dar de baja logica (`POST /materias/{id}/eliminar`).
5. Usar **Cerrar sesion** para invalidar la sesion (`POST /logout`).

## 8. Como verificar las defensas de seguridad activadas

```bash
curl -I http://localhost:8080/login
```

Debe mostrar, entre otras, las cabeceras:
- `X-Frame-Options: DENY`
- `X-Content-Type-Options: nosniff`
- `Strict-Transport-Security: max-age=31536000; includeSubDomains`
- `Content-Security-Policy: default-src 'self'; ...`

Adicionalmente:
- Intentar `GET /materias` sin sesion activa debe responder `302` hacia `/login`.
- Cada formulario POST (login, crear, editar, eliminar) incluye un campo
  oculto `_csrf` generado por Spring Security + Thymeleaf.

## 9. Hash del ultimo commit (≤ 10H45)
`<completar con el hash abreviado de 7 caracteres del commit correspondiente>`

---

