## host-server-check
호스트들의 Alive 상태 체크 및 모니터링 API 서버 개발

> 등록시 도메인 주소를 입력하면 ip주소를 받아서 alive상태인지 체크. (도메인주소 대신 ip주소도 가능) <br/>
> host측에서 방화벽이나 ping의 접근을 막아뒀다면 isReachable()로 체크 불가. (naver, google, youtube 등등)

### Rest API

* GET
  * 호스트 리스트 (/)
  * 호스트 상세정보 (/info/${host})
  * 호스트 등록화면 (/create)
  * 호스트 수정화면 (/update)
  
* POST
  * 호스트 등록 (/create)

* PATCH
  * 호스트 수정 (/update)

* DELETE
  * 호스트 삭제 (/delete)
