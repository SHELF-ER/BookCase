---
description: 도서관 사용자 정보 api입니다.
---

# 🧋 User

## Read user list

{% swagger baseUrl="http://localhost:8080" method="get" path="/api/users" summary="read user list" %}
{% swagger-description %}
모든 회원 목록
{% endswagger-description %}

{% swagger-response status="200" description="Read success" %}
```javascript
[
    {
        "id": 1,
        "uid": "abcd",
        "pw": "1234",
        "name": "루빔",
        "donate": null,
        "borrow1": "클린코드",
        "borrow2": null,
        "borrow3": null
    },
    {
        "id": 2,
        "uid": "a9oe12v",
        "pw": "1234",
        "name": "류환",
        "donate": null,
        "borrow1": "하이큐",
        "borrow2": "귀멸의 칼날"
        "borrow3": null
    },
]
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="Error" %}

{% endswagger-response %}
{% endswagger %}

## Read user

{% swagger baseUrl="http://localhost:8080" method="get" path="/api/users/{id}" summary="read user" %}
{% swagger-description %}
회원 한명정보를 가져오는 api
{% endswagger-description %}

{% swagger-parameter in="path" required="true" name="id" %}
회원 id
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

## Edit user

{% swagger baseUrl="http://localhost:8080" method="put" path="/api/users/{id}" summary="edit user" %}
{% swagger-description %}
회원 한명 정보 수정하는api
{% endswagger-description %}

{% swagger-parameter in="body" name="name" %}
회원 이름
{% endswagger-parameter %}

{% swagger-parameter in="body" name="pw" %}
비밀번호
{% endswagger-parameter %}

{% swagger-parameter in="body" name="donate" %}
회원이 기부한 책
{% endswagger-parameter %}

{% swagger-parameter in="body" name="uid" %}
회원id - 도서대출증
{% endswagger-parameter %}

{% swagger-parameter in="body" name="borrow1" %}
빌린 책
{% endswagger-parameter %}

{% swagger-parameter in="body" name="borrow2" %}
빌린 책2
{% endswagger-parameter %}

{% swagger-parameter in="body" name="borrow3" %}
빌린 책3
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

## Create user

{% swagger baseUrl="http://localhost:8080" method="post" path="/api/users" summary="create user" %}
{% swagger-description %}
회원 한명을 생성하는api
{% endswagger-description %}

{% swagger-parameter in="body" name="name" %}
회원 이름
{% endswagger-parameter %}

{% swagger-parameter in="body" name="pw" %}
비밀번호
{% endswagger-parameter %}

{% swagger-parameter in="body" name="donate" %}
회원이 기부한 책
{% endswagger-parameter %}

{% swagger-parameter in="body" name="uid" %}
회원id - 도서대출증
{% endswagger-parameter %}

{% swagger-parameter in="body" name="borrow1" %}
빌린 책
{% endswagger-parameter %}

{% swagger-parameter in="body" name="borrow2" %}
빌린 책2
{% endswagger-parameter %}

{% swagger-parameter in="body" name="borrow3" %}
빌린 책3
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

## Delete user

{% swagger baseUrl="http://localhost:8080" method="delete" path="/api/users/{id}" summary="delete user" %}
{% swagger-description %}
회원 한명을 삭제하는 api
{% endswagger-description %}

{% swagger-parameter in="path" required="true" name="id" %}
 회원 id
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
