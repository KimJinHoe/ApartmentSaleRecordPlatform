# 전국 부동산 매물 기록 및 현황 확인 플랫폼
<a href="http://youtube.com.gKxUcRZ2uz0">
  <img src="http://img.youtube.com/vi/gKxUcRZ2uz0/0.jpg" width="50%" height="50%"/>
</a> 👈시연영상<br>

## 1. 개요
- 기간: 2022.05
- 소개: 공공 API를 이용한 전국 부동산 매물 기록 및 현황 확인 플랫폼
    - 로그인 및 회원 기능
    - 게시판 및 댓글
    - 실시간 관련 뉴스 확인
    - 공공 API 파싱, 지도 위치 검색 및 확인


## 2. 역할분담
- 참여인원: 2명
- 담당파트: 팀장, 프론트(Vue2) 및 백엔드(Spring, Mybatis) [75%]
    - 메인화면 구현
    - 로그인 및 회원 기능
    - 게시판 및 댓글
    - 최신 뉴스 크롤링
    - 페이지 권한 부여

## 3. 기술스택
<img src="https://user-images.githubusercontent.com/63358647/202610075-efdb3f96-72f4-4f11-9b88-0bad2f01b7d2.png" width="50%" height="50%"/><br>
- 백엔드
    - Java, Spring, REST API, Mybatis, JWT, Mysql, Jsoup, Open api
- 프론트
    - Vue2, Bootstrap Vue, Vuex, Vue-Router
    - HTML, CSS, JS
    - Kakao map api

## 4. 시연영상
- <a href="https://youtu.be/gKxUcRZ2uz0">바로가기</a>

## 5. 폴더구조
<img src="https://user-images.githubusercontent.com/63358647/202610080-6f3caa5f-d3e3-465c-89fa-5a3bd15e82d1.JPG" width="50%" height="50%"/><br>

## 6. 회고
```
백엔드(Spring)과 프론트(Vue)를 학습하고 처음 만든 프로젝트이다. 학습 후, 첫 프로젝트라 그런지 미숙한 부분이 많아 아쉬움이 남는다.
첫 번째, jira의 프로젝트 관리와 git 브런치 전략을 사용하지 않아 코드 충돌 문제가 많았고 협업이 아쉬웠다.
두 번째, 코드의 효율성을 고려하지 않았고 페이지네이션을 사용하지 않아 데이터를 주고 받는 시간이 꽤 길었다.
세 번째, 보안을 고려하지 않아 쉽게 유저 정보에 접근하고 조작할 수 있다.

위 문제를 해결하기 위해 jira와 같은 툴을 이용하고 git 브런치 전략을 사용해서 프로젝트 관리를 하고,
필요한 데이터만을 추출하고 전송하는 효율적인 코드를 작성,
security를 사용하며 userid의 autoincrement 사용 지양, ERD 테이블 분리 등을 통해서 보안성을 높여야겠다.
```
