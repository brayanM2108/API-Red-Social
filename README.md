# 🎵 Vibyn - Red Social Musical

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?style=for-the-badge&logo=spring)
![Spotify API](https://img.shields.io/badge/Spotify-API-1DB954?style=for-the-badge&logo=spotify)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-336791?style=for-the-badge&logo=postgresql)

</div>

---

## ¿Qué es Vibyn?

**Vibyn** es una plataforma de red social innovadora que combina la pasión por la música con la conexión social. Integrada directamente con la **API de Spotify**, Vibyn permite a los usuarios crear publicaciones enriquecidas con sus canciones y álbumes favoritos, compartir sus gustos musicales, interactuar con otros usuarios mediante comentarios y likes, todo en un ecosistema diseñado para los verdaderos amantes de la música.

### Características Principales

- 🎧 **Integración Total con Spotify**: Conecta tu cuenta de Spotify y enriquece tus publicaciones con tracks y álbumes
- 📝 **Publicaciones Musicales**: Crea posts compartiendo tu música favorita con contexto y descripciones personalizadas
- 💬 **Sistema de Comentarios**: Interactúa y discute sobre música con otros usuarios
- ❤️ **Sistema de Likes**: Expresa tu aprecio por las publicaciones de otros melómanos
- 👤 **Perfiles de Usuario**: Personaliza tu perfil con biografía y preferencias musicales
- 🔐 **Autenticación Segura con OAuth 2.0**: Integración segura con Spotify mediante OAuth
- 🔒 **Encriptación de Tokens**: Almacenamiento seguro de tokens de acceso con encriptación AES

---

## 🏗️ Arquitectura del Proyecto

Vibyn está construido siguiendo los principios de **Arquitectura Hexagonal (Ports & Adapters)** combinada con **CQRS** y el patrón **Mediator**, garantizando:

- ✅ **Separación de responsabilidades**
# 🎵 Vibyn — Plataforma Social Musical (enfoque técnico)

![Java 21](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=spring) ![Spotify API](https://img.shields.io/badge/Spotify-API-1DB954?style=for-the-badge&logo=spotify) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-336791?style=for-the-badge&logo=postgresql)

Este documento describe en detalle la arquitectura técnica de `Vibyn`, cómo se integra con la API de Spotify, la estrategia de persistencia (incluyendo la persistencia local de entidades de Spotify), la estructura del código y pasos para ejecutar y desarrollar localmente.

## Objetivo técnico

Vibyn es una aplicación backend construida con Java y Spring Boot cuyo propósito es ofrecer una experiencia social centrada en música. Técnicamente se busca:

- Separar la lógica de dominio de las infraestructuras externas.
- Aplicar Arquitectura Hexagonal (Ports & Adapters) con CQRS para casos de uso claros (comandos vs consultas).
- Utilizar un Mediator para enrutar requests a handlers específicos, manteniendo controladores delgados.
- Integrar de forma segura con la Web API de Spotify (OAuth 2.0), persistir tokens encriptados y cachear/almacenar localmente metadatos de Spotify (tracks, albums, artists) para resiliencia y rendimiento.

---

## Arquitectura

Principales características de la arquitectura:

- Arquitectura Hexagonal (Ports & Adapters):
  - Las interfaces de los casos de uso (puertos) residen en los paquetes de dominio/aplicación.
  - Las implementaciones concretas (adaptadores) —por ejemplo repositorios JPA, clientes HTTP a Spotify o servicios de encriptación— quedan en infraestructura.
- CQRS (Command Query Responsibility Segregation):
  - Comandos (create/update/delete) y Queries (lecturas) están separados en handlers distintos.
  - Los comandos modifican estado; las queries sólo leen.
- Patrón Mediator:
  - Un despachador central recibe Requests (Commands/Queries) y resuelve el handler correspondiente.
  - Permite añadir pipelines (middlewares) para validación, logs, transacciones, etc.

Estructura de paquetes (resumen):

`src/main/java/com/melo/vibyn/`
- `comment/`, `likes/`, `post/`, `spotify/`, `user/` — cada módulo sigue la separación: application (casos de uso), domain (entidades/valores), infrastructure (adaptadores)
- `common/` — excepciones, mediator y utilidades compartidas

Ventaja práctica: el dominio no depende de Spring ni de JPA; los detalles de infraestructura son intercambiables.

---

## CQRS + Mediator (cómo está planteado)

- Los objetos Request se dividen en Command y Query. Cada Request tiene un RequestHandler asociado.
- Un ejemplo simplificado del flujo:

  1. El controlador HTTP convierte la petición en un Command/Query.
  2. El controlador llama al `Mediator.dispatch(request)`.
  3. El Mediator busca el `RequestHandler` registrado y ejecuta la lógica de aplicación.
  4. El handler usa puertos (interfaces) para acceder a repositorios o servicios externos.

- Beneficios:
  - Testeabilidad (mocks fáciles de los puertos)
  - Pipelines reutilizables (autenticación, validación, logging)

---

## Integración con Spotify

Se implementa una integración robusta con la Web API de Spotify centrada en:

- Autenticación OAuth 2.0 (Authorization Code Flow) para obtener access & refresh tokens.
- Persistencia cifrada de tokens: los tokens se encriptan antes de almacenarse en la base de datos (por ejemplo AES-256) y se desencriptan en memoria cuando es necesario.
- Gestión automática de refresh: un servicio `SpotifyTokenService` se encarga de refrescar tokens expirados y actualizar la persistencia.
- Cacheo y persistencia local de entidades de Spotify:
  - Para reducir latencia y dependencia externa, Vibyn persiste localmente información esencial de Spotify (tracks, albums, artists) en su propia base de datos PostgreSQL.
  - Estas entidades locales sirven para enriquecer posts y permitir búsquedas offline o cuando la API externa está limitada.

Arquitecturas y componentes clave:

- `SpotifyClient` / `SpotifyHttpAdapter`: cliente HTTP responsable de llamadas a la API de Spotify (refresh token, obtener track/album/artist, búsquedas).
- `SpotifyTokenService`: encripta/desencripta tokens, refresca tokens automáticamente.
- `SpotifyRepository` (JPA): persiste metadatos de Spotify en tablas como `spotify_track`, `spotify_album`, `spotify_artist`.
- Estrategia de sincronización:
  - On-demand: cuando un usuario añade una pista a un post, el sistema consulta la API y guarda la entidad local si no existe.
  - Batch/Sync opcional: procesos que actualizan metadatos periódicamente (cover, artistas relacionados, popularidad).

Seguridad:

- Nunca exponer tokens sin desencriptar.
- Guardar la clave de encriptación en una variable de entorno o un vault (no en source control).

---

## Persistencia y modelos

- Base de datos: PostgreSQL (configurable vía `application-*.properties`).
- Mapeo entre DTOs y entidades: MapStruct se utiliza para conversión eficiente entre capas.
- Repositorios: Spring Data JPA para implementaciones de `Repository`.
- Entidades importantes (resumen):
  - `UserEntity` — perfil del usuario
  - `PostEntity` — post con referencias a `SpotifyTrackEntity` y `SpotifyAlbumEntity`
  - `SpotifyTrackEntity`, `SpotifyAlbumEntity`, `SpotifyArtistEntity` — representación local de las entidades de Spotify

Índices y consultas: diseñar índices sobre `spotify_id` y campos de búsqueda para optimizar consultas.

---

## Endpoints (ejemplos técnicos)

- Autenticación con Spotify
  - `GET /api/v1/spotify/auth/connect?userId={userId}` — genera la URL de autorización (redirección a Spotify)
  - `GET /api/v1/spotify/auth/callback?code={code}&state={userId}` — callback que intercambia el code por tokens

- Spotify data
  - `GET /api/v1/spotify/track/{id}` — devuelve la entidad local si existe o consulta y persiste desde Spotify
  - `GET /api/v1/spotify/track/search?query={q}&userId={userId}` — busca en Spotify y/o en cache local

- Posts
  - `POST /api/v1/posts` — Command: crea un post (puede recibir `trackIds` y/o `albumIds`)
  - `GET /api/v1/posts/user/{userId}` — Query paginada: obtiene posts de un usuario

Notas: los controladores son delgados: validan entrada y despachan Command/Query al Mediator.

---



