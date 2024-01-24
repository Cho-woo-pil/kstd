## 🙌 조우필의 KSTD 과제

## ❓ 구현 내용   
 1. 강연 신청 플랫폼 구현
 - 강연장 등록, 조회 API 구현
 - 강연 등록, 전체 강연 조회, 다가오는 강연 조회, 실시간 인기 강연 조회, 전체 강연 신청자 조회, 특정 강연 신청자 조회 API 구현
 - 강연 신청, 강연 취소, 강연 신청 내역 조회 API 구현

 2. 현업 경험으로 인한 연관관계 사용지양
 - 작은 프로젝트이고 DB 성능저하, n+1 문제등을 피하기위해 연관관계 사용을 지양
 - 필요한 경우라면 단방향 맵핑등 사용 여부 검토
 - 고유 ID 값을 이용하여 데이터 일관성 유지
  
 3. @Version 을 이용한 동시성 문제 해결
 - Transaction시 항상 Version을 확인하고, 동시문제일시 Transaction 오류 발생
 
 4. 논리삭제 사용
 - History테이블 등은 안만들었지만 데이터의 추후를 위한 논리적 삭제 적용

 5. JSP를 사용하되 복잡한 쿼리에는 Nagative 쿼리 사용
 - 특정 Join이나, 복잡한 쿼리는 직접적인 SQL문이 더 적확하고 빠르다 판단

 6. Setter 사용 지양
 - 엔티티의 불변성을 위하여 Setter 사용을 지양하였습니다.

 7. Dto, Presenter 사용
 - 데이터 맵핑시 Dto 사용
 - 데이터 표출시 Presenter를 사용
 - 현재는 프론트연동이아닌 결과를 보여주기 위해 한글 표시명 사용(컨벤션에 맞춰 사용 인지)

 8. 예외처리 핸들러를 따로 작성안해주었기에 임시로 출력에 ResponseEntity<?> 제네릭 타입 사용
 - 데이터의 형태 및 예외처리 핸들러 존재하면 데이터 타입 명시적 인지

 9. 단위 테스트 코드 작성 및 테스트 커버리지 확인
<p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/ef828a75-2caa-44d0-afcd-cbac98019ad9"></p>

10. Swagger 작성
 - http://localhost:8080/swagger-ui/index.html
   <p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/29ca1535-b5df-411b-bb31-ca74dd1f7beb"></p>

## 🛠 사용 기술
- Java17
- Spring Boot 3.2.1
- JPA
- RDBS
- RDS(Mysql)
- Gradle
- Swagger
- Junit5

## 🙋‍♀️ Swagger 확인
1. 

2. 회원가입 API
- /szs/signup
- 파라미터에 아이디, 패스워드, 이름, 주민등록 번호를 입력하여 회원가입
- Member Entity Response
<p align="center">
  <img src="https://github.com/Cho-woo-pil/szs/assets/20333090/1e3fbe01-a4f1-40fd-a5a6-e464de096d45"></p>
<p align="center">
  <img src="https://github.com/Cho-woo-pil/szs/assets/20333090/d8a02eae-e987-430d-9491-863408c42aa3"></p>  

3. 로그인 API
- /szs/login
- 파라미터에 아이디, 패스워드를 입력하여 로그인
- Jwt Token Response
<p align="center">
  <img src="https://github.com/Cho-woo-pil/szs/assets/20333090/e2f2b086-4a3e-40bc-8dca-0f717aab4cd8"></p>
<p align="center">
  <img src="https://github.com/Cho-woo-pil/szs/assets/20333090/b368f3bd-3946-4822-93c9-8b9296114dc3"></p>

4. 내 정보 보기
- /szs/me
- 스웨거 오른쪽위 상단의 Authorize를 클릭하여 Bearer 이후의 Jwt 토큰을 입력
- Hearder에 Authorization: Bearer JwtToken
- 회원가입 정보 Response
<p align="center">
  <img src="https://github.com/Cho-woo-pil/szs/assets/20333090/1325f02d-c8c1-4be0-8245-a57221f2e2e2"></p>

5.유저 스크랩
- /szs/scrap
- Hearder에 Authorization: Bearer JwtToken
- 스크랩 후 필요정보 맵핑하여 저장
- 저장정보 Response
<p align="center">
  <img src="https://github.com/Cho-woo-pil/szs/assets/20333090/c1fc0d23-953f-43bd-a951-9002c22658a4"></p>

6.환급금 조회
- /szs/refund
- Hearder에 Authorization: Bearer JwtToken
- 스크랩 정보를 조회하여 환급금 계산후 Response 출력
<p align="center">
  <img src="https://github.com/Cho-woo-pil/szs/assets/20333090/809a2f3a-43cc-426e-a9b7-25d94aa0c99c"></p>

## 🚗 TO-BE
1. 회원가입에 대한 Validation 추가
2. Redis, Cache를 이용하여 동일 정보 조회시 속도 향상
3. 기능이 별로 없어 Controller, Service를 한폴더에 묶었으나 Domain별 분리
4. 데이터 조회, 스크랩일 등 히스토리 테이블 추가
5. Refresh토큰을 통한 토큰만료시 토큰 재발급
6. feature별 branch 세분화
