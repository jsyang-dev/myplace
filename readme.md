# MY.플레이스

## 개요

- 장소를 체계적으로 관리하여 상황에 맞는 장소를 제공할 수 있는 서비스

## 개발 환경

- Java 11
- Spring Boot 2.4.5
- MySQL / H2

## 개발 방법론

- 인수 테스트 -> 문서화 -> 기능 구현
![atdd](https://user-images.githubusercontent.com/35869083/115026421-6e565c00-9efd-11eb-8fa2-5ed6c81ff3b5.png)

## 도메인 설명

### 장소(place)

- 장소에 대한 기본 정보
- 속성
  - 이름(name)
  - 좌표(point)
  - 이미지 URL(imageUrl)
  - 추천수(recommendCount)
  - 조회수(readCount)
  - 설명(description)    

### 태그(tag)

- 장소의 태그 정보
- 속성
    - 이름(name)

### 영업시간(openingHour)

- 장소의 기간별 영업시간 정보
- 속성
  - 이름(name)
  - 시작일(startDate)
  - 종료일(endDate)
  - 일자 구분(dayType): 요일 or 공휴일
  - 시작 시각(startTime)
  - 종료 시각(endTime)

### 입장료(fee)

- 장소의 연령대별 입장료 정보
- 속성
    - 이름(name)
    - 시작 개월(startMonth)
    - 종료 개월(endMonth)
    - 금액(amount)

### 휴무일(closedDay)

### 공휴일(holiday)

### 카테고리(category)

## 단계별 요구사항

### 장소 관리

- 장소 관리 기능 구현
  - 장소 생성
  - 장소 리스트 조회
  - 장소 조회
  - 장소 수정
  - 장소 삭제
    