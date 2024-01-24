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
 - 기본 기능만 연결
 - DTO, RequestBody 추후연결
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
1. 강의실 조회 API
- GET /kstd/venue
- 강의실 고유ID, 강의실 이름 응답
<p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/f7d03ae3-dde2-4fa0-9976-5704abe91c0b"></p>
  
2. 강의실 등록 API
- POST /kstd/venue
<p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/376a5e70-f1f7-4cfe-ad29-657ae153fdf2"></p>
<p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/b38d2ba5-2043-42ba-9d9d-15c3bc00a47c"></p>  

3. 강연 등록 API
- POST /kstd/lecture/registor
<p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/7528ef89-e1dc-4c71-83ba-6ac86a099438"></p>
<p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/44516740-9327-4fa1-9266-d1f55d8e75f5"></p>  

4. 강연목록(신청 간능한 시점부터 시작 1일후 노출)
- GET /kstd/lecture/list/upcomming
- 강의ID, 강연자, 강연장, 강연내용, 강의시작시간, 강의시간, 총인원, 신청인원 응답
<p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/2524765c-edcd-46b9-9bef-ecd2bb59f9ca"></p>

5. 강연목록(전체)
- GET /kstd/lecture/list/all
- 강의ID, 강연자, 강연장, 강연내용, 강의시작시간, 강의시간, 총인원, 신청인원 응답
<p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/aac55c46-f913-44c7-8ab8-cbd7958ca1cb"></p>

6. 실시간 인기 강의
- GET /kstd/lecture/list/popular
- 4의 신청가능한 목록을 3일이내 신청기록이 가장 많은 순으로 정렬
- 강의ID, 강연자, 강연장, 강연내용, 강의시작시간, 강의시간, 총인원, 신청인원 응답
 <p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/9372a9ec-0f6a-4da0-bd14-99ab6be7de1c"></p>

7. 강연 신청 목록 조회(특정강의)
- GET /kstd/lecture/applications/{lectureId}
- 강연 ID 파라미터로 Path에 입력
- 강연ID, 강연자, 강연장ID, 강연내용, 강의시작시간, 강의시간, 신청자목록 리스트 응답
 <p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/354ed09b-ca88-435a-a48b-2b8f65c96766"></p>

8. 강연 신청 목록 조회(전체)
- GET /kstd/lecture/application/all
- (강연ID, 강연자, 강연장ID, 강연내용, 강의시작시간, 강의시간, 신청자목록 리스트) 의 리스트 응답
 <p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/c6700f1e-7c2c-42ea-8bc3-a4f92f1d58c7"></p>

9. 강연 신청
- POST /kstd/lecture/apply
- 강연ID, 사원ID 입력
- 중복신청, 없는강의 신청, 신청하지 않은 강의, 가득찬 강의에 대한 예외처리
 <p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/c769f767-fd6d-4037-ac97-1d6928b1bf87"></p>

10. 강연 취소
- PATCH /kstd/lecture/cancle
- 강연ID, 사원ID 입력
 <p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/3e5a160b-8eb1-4771-adf6-2f749b49cdb7"></p>

11. 강연 신청내역 조회
- GET /kstd/lecture/apply/history/{employeeId}
- 강연ID, 신청일 응답
 <p align="center">
  <img src="https://github.com/Cho-woo-pil/kstd/assets/20333090/9e8ebcec-8ee4-4040-99d5-1f72c5d573f4"></p>


## 🚗 TO-BE
1. 회원가입에 대한 Validation 추가
2. Redis, Cache를 이용하여 동일 정보 조회시 속도 향상
3. 히스토리 테이블 추가
4. Cognito, JWT 등 로그인을 이용하여 토큰을 통해 사원번호 확인
5. Swagger 기능 고도화
6. 연관관계에 대한 고찰
