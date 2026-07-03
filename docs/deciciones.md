# Decisiones tecnicas

1. **Spring Data JPA en lugar de JdbcTemplate/JPA manual**: todas las consultas
   pasan por Spring Data JPA, que internamente usa `PreparedStatement`. Esto
   garantiza que ningun dato del usuario se concatene al SQL, cumpliendo la
   defensa anti-SQL Injection exigida en el Paso 6.

2. **Spring Security con `formLogin` propio en `/login`**: en vez de usar
   HTTP Basic o una solucion ad-hoc, se configura el filtro estandar de
   Spring Security, que ya implementa el manejo de sesion, la migracion de
   sesion tras login (anti session-fixation) y el bloqueo de rutas sin
   autenticar con redireccion 302, tal como exige el enunciado.

3. **CSRF activo por defecto + Thymeleaf**: se aprovecha la integracion
   `thymeleaf-extras-springsecurity6`, que inyecta automaticamente el token
   CSRF en cualquier formulario declarado con `th:action`, evitando errores
   manuales de omision del token.

4. **Eliminacion logica mediante el campo `activa`**: el `MateriaService`
   nunca ejecuta `delete`; solo actualiza el booleano `activa=false`, y el
   listado filtra por `findByActivaTrueOrderByCodigoAsc()`.

5. **Postgres en Docker en lugar de H2 en memoria**: se eligio Postgres para
   que el `docker-compose.yml` refleje un entorno mas cercano a produccion y
   para que las restricciones `CHECK` de creditos/semestre esten tambien a
   nivel de base de datos, no solo en la capa de validacion de Java.
