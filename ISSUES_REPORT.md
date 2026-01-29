# Project Issues Report

This document outlines the issues identified during the analysis of the codebase. It categorizes them into Critical (Security) and Improvement (Design/Convention) areas.

## 🚨 Critical Security Issues (Addressed locally)

### 1. Committed Private Keys

**Issue:** The `private.pem` file used for JWT signing was committed to the Git repository in `src/main/resources/keys/`.
**Risk:** If this repository is public, anyone can sign valid JWTs and impersonate users/admins.
**Status:**

- Added `src/main/resources/keys/` to `.gitignore`.
- **Action Required:** If you plan to make this repo public, you **MUST** generate new keys and not use the old ones. The old keys are considered compromised because they are in the git history.

### 2. Hardcoded Database Credentials

**Issue:** The `application.yml` contained the plain text password `123`.
**Risk:** Exposing database credentials.
**Status:**

- Updated `application.yml` to use environment variables with defaults: `${DB_PASSWORD:123}`.
- **Action Required:** In a production environment, provide these values via environment variables (e.g., `export DB_PASSWORD=my_secure_pass`).

## 🛠 Architectural & Code Improvements

### 1. Package Naming Typo

**Issue:** There is a package named `comman` at `com.travel.safe.buses.comman`.
**Recommendation:** Rename it to `common` to follow standard English naming conventions.

### 2. REST API Design Violations

**Issue:** The `DepartmentController` uses `@RequestBody` for a `GET` request:

```java
@GetMapping
public ResponseEntity<Page<Department>> getDepartments(@RequestBody DepartmentSearchDTO departmentSearchDTO)
```

**Problem:** While technically possible in some clients, HTTP GET requests should not have a body according to standard specs. Caching proxies and some libraries will strip the body.
**Recommendation:**

- Change the method to `@PostMapping("/search")` if the search parameters are complex.
- OR map individual fields to `@RequestParam` (e.g., `?name=...&manager=...`) if the object is simple.

### 3. Testing Coverage

**Issue:** The project appears to have minimal test coverage (mostly context loading).
**Recommendation:** Add unit tests for your Services and Domain logic.

### 4. Global Exception Handling

**Observation:** You have `GlobalExceptionHandler`, which is excellent. Ensure it covers all custom exceptions like `DepartmentNotFoundException`.
