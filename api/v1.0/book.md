---
description: DB에 저장된 책 정보 api입니다.
---

# 📔 Book

Path = Url뒤에 붙는 특정 값

Body = FormData로 통신하여 주고 받는 데이터

## Read book list

{% swagger baseUrl="http://localhost:8080" method="get" path="/api/books" summary="read book list" %}
{% swagger-description %}
책 목록을 반환하는 api
{% endswagger-description %}

{% swagger-response status="200" description="Read success" %}
```javascript
[
    {
        "id": 33,
        "uid": "2a",
        "smartUid": null,
        "name": "클린코드",
        "bookNum": 100,
        "borrower": "X",
        "bookCmp": 0,
        "bookFloor": 2,
        "donor": "X",
        "category": null,
        "writer": null,
        "count": null,
        "img": null
    },
    {
        "id": 34,
        "uid": "2b",
        "smartUid": null,
        "name": "유니티교과서",
        "bookNum": 200,
        "borrower": "서빈",
        "bookCmp": 0,
        "bookFloor": 2,
        "donor": "X",
        "category": null,
        "writer": null,
        "count": null,
        "img": null
    }
]
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="Error" %}

{% endswagger-response %}
{% endswagger %}

## Read book

{% swagger baseUrl="http://localhost:8080" method="get" path="/api/books/{id}" summary="read book" %}
{% swagger-description %}
책 한권의 정보를 가져오는 api
{% endswagger-description %}

{% swagger-parameter in="path" required="true" name="id" %}
책 id
{% endswagger-parameter %}

{% swagger-response status="200" description="{result : success}" %}
```javascript
[
    {
        "id": 33,
        "uid": "2a",
        "smartUid": null,
        "name": "클린코드",
        "bookNum": 100,
        "borrower": "X",
        "bookCmp": 0,
        "bookFloor": 2,
        "donor": "X",
        "category": null,
        "writer": null,
        "count": null,
        "img": null
    },
    {
        "id": 34,
        "uid": "2b",
        "smartUid": null,
        "name": "유니티교과서",
        "bookNum": 200,
        "borrower": "서빈",
        "bookCmp": 0,
        "bookFloor": 2,
        "donor": "X",
        "category": null,
        "writer": null,
        "count": null,
        "img": null
    }
]
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="Error" %}

{% endswagger-response %}
{% endswagger %}

## Edit book

{% swagger baseUrl="http://localhost:8080" method="put" path="/api/books/{id}" summary="edit book" %}
{% swagger-description %}
책 한권의 정보를 변경하는 api
{% endswagger-description %}

{% swagger-parameter in="path" required="true" name="id" %}
책 id
{% endswagger-parameter %}

{% swagger-parameter in="body" name="name" %}
책 이름
{% endswagger-parameter %}

{% swagger-parameter in="body" name="bookNum" %}
책 번호(100,200...)
{% endswagger-parameter %}

{% swagger-parameter in="body" name="borrower" %}
책을 대여한 회원명
{% endswagger-parameter %}

{% swagger-parameter in="body" name="uid" %}
책 uid - 스부케용
{% endswagger-parameter %}

{% swagger-parameter in="body" name="rUid" %}
책 uid2 - 셀퍼 로봇용
{% endswagger-parameter %}

{% swagger-parameter in="body" name="donor" %}
책 기부하신 분 성함
{% endswagger-parameter %}

{% swagger-parameter in="body" name="bookFloor" %}
책이 있어야할 층
{% endswagger-parameter %}

{% swagger-parameter in="body" name="bookCmp" %}
책 정위치 확인용
{% endswagger-parameter %}

{% swagger-parameter in="body" name="category" %}
분류
{% endswagger-parameter %}

{% swagger-parameter in="body" name="img" %}
사진
{% endswagger-parameter %}

{% swagger-parameter in="body" name="writer" %}
저자
{% endswagger-parameter %}

{% swagger-parameter in="body" name="count" %}
대여된 횟수
{% endswagger-parameter %}

{% swagger-response status="200" description="{result : success}" %}
```javascript
[
    {
        "id": 33,
        "uid": "2a",
        "smartUid": null,
        "name": "클린코드",
        "bookNum": 100,
        "borrower": "X",
        "bookCmp": 0,
        "bookFloor": 2,
        "donor": "X",
        "category": null,
        "writer": null,
        "count": null,
        "img": null
    },
    {
        "id": 34,
        "uid": "2b",
        "smartUid": null,
        "name": "유니티교과서",
        "bookNum": 200,
        "borrower": "서빈",
        "bookCmp": 0,
        "bookFloor": 2,
        "donor": "X",
        "category": null,
        "writer": null,
        "count": null,
        "img": null
    }
]
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="Error" %}

{% endswagger-response %}
{% endswagger %}

## Create book

{% swagger baseUrl="http://localhost:8080" method="post" path="/api/books" summary="create book" %}
{% swagger-description %}
책 한권을 생성하는api
{% endswagger-description %}

{% swagger-parameter in="body" name="name" %}
책 이름
{% endswagger-parameter %}

{% swagger-parameter in="body" name="bookNum" %}
책 번호(100,200...)
{% endswagger-parameter %}

{% swagger-parameter in="body" name="borrower" %}
책을 대여한 회원명
{% endswagger-parameter %}

{% swagger-parameter in="body" name="uid" %}
책 uid - 스부케용
{% endswagger-parameter %}

{% swagger-parameter in="body" name="rUid" %}
책 uid2 - 셀퍼 로봇용
{% endswagger-parameter %}

{% swagger-parameter in="body" name="donor" %}
책 기부하신 분 성함
{% endswagger-parameter %}

{% swagger-parameter in="body" name="bookFloor" %}
책이 있어야할 층
{% endswagger-parameter %}

{% swagger-parameter in="body" name="bookCmp" %}
책 정위치 확인용
{% endswagger-parameter %}

{% swagger-parameter in="body" name="category" %}
분류
{% endswagger-parameter %}

{% swagger-parameter in="body" name="img" %}
사진
{% endswagger-parameter %}

{% swagger-parameter in="body" name="writer" %}
저자
{% endswagger-parameter %}

{% swagger-parameter in="body" name="count" %}
대여된 횟수
{% endswagger-parameter %}

{% swagger-response status="200" description="{result : success}" %}
```javascript
[
    {
        "id": 33,
        "uid": "2a",
        "smartUid": null,
        "name": "클린코드",
        "bookNum": 100,
        "borrower": "X",
        "bookCmp": 0,
        "bookFloor": 2,
        "donor": "X",
        "category": null,
        "writer": null,
        "count": null,
        "img": null
    },
    {
        "id": 34,
        "uid": "2b",
        "smartUid": null,
        "name": "유니티교과서",
        "bookNum": 200,
        "borrower": "서빈",
        "bookCmp": 0,
        "bookFloor": 2,
        "donor": "X",
        "category": null,
        "writer": null,
        "count": null,
        "img": null
    }
]
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="Error" %}

{% endswagger-response %}
{% endswagger %}

## Delete book

{% swagger baseUrl="http://localhost:8080" method="delete" path="/api/books/{id}" summary="Delete book" %}
{% swagger-description %}
책 한권을 삭제하는api
{% endswagger-description %}

{% swagger-parameter in="path" required="true" name="id" %}
책 id
{% endswagger-parameter %}

{% swagger-response status="200" description="Read success" %}
```javascript
[
    {
        "id": 33,
        "uid": "2a",
        "smartUid": null,
        "name": "클린코드",
        "bookNum": 100,
        "borrower": "X",
        "bookCmp": 0,
        "bookFloor": 2,
        "donor": "X",
        "category": null,
        "writer": null,
        "count": null,
        "img": null
    },
    {
        "id": 34,
        "uid": "2b",
        "smartUid": null,
        "name": "유니티교과서",
        "bookNum": 200,
        "borrower": "서빈",
        "bookCmp": 0,
        "bookFloor": 2,
        "donor": "X",
        "category": null,
        "writer": null,
        "count": null,
        "img": null
    }
]
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="Error" %}

{% endswagger-response %}
{% endswagger %}
