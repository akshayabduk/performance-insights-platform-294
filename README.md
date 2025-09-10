# Project Repository

This repository contains a multi-container AI-powered Employee Performance Insight Platform.

Backend (Spring Boot) highlights:
- MongoDB integration (via MONGODB_URL and MONGODB_DB env vars)
- JWT authentication with USER and ADMIN roles
- CRUD APIs: Employees, Metrics, Reviews
- Analytics endpoints (AI/heuristics): overall and per-employee insights
- Trend/chart endpoints for time-series data
- Reporting endpoints: PDF and CSV exports
- Notifications: create, list, mark read/unread, delete
- OpenAPI/Swagger UI at `/swagger-ui.html`

Environment variables (backend_api/.env.example):
- MONGODB_URL
- MONGODB_DB
- JWT_SECRET (Base64)
- JWT_EXPIRATION_MS (optional)