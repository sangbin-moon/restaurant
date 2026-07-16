# 요식업 웹페이지 연습 과제

eGovFrame 5.0 기반으로 제작한 요식업 웹페이지 연습 과제입니다.
말씀하신 기능 구현을 우선으로 진행하였으며, CSS 등 UI 구성은 시간이 오래 걸릴 것 같아 제외하였습니다.

## 개발 환경

- Java 17
- eGovFrame 5.0
- Spring MVC
- MyBatis
- Oracle Database
- Tomcat 10.1

## 프로젝트 구조

JSP
→
Controller
→
Service
→
MyBatis Mapper
→
Oracle Database

## 구현 기능

### CRUD
- 관리자 페이지에서 메뉴 등록/조회/수정/삭제

### 검색 + 페이징
- 관리자 페이지에서 메뉴명 검색(LIKE)

### 파일 업로드 / 다운로드
- 관리자 페이지에서 파일 업로드/다운로드

### Validator + BindingResult
- 관리자 페이지에서 입력값 유효성 검증 및 오류 처리

### Interceptor
- 로그인 체크
- 관리자 권한 체크

### @Transactional
- 관리자 페이지에서 데이터 등록 예외 발생 시 Rollback 확인

### Ajax
- 카트 페이지에서 수량 변경시 Fetch API를 이용한 비동기 조회 및 수정
