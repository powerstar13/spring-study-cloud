# 스프링 클라우드

1. DNS 구성 및 활용
2. 스프링 애플리케이션 이벤트 실습
   - 로컬캐시처럼 애플리케이션 내부에서 이벤트 데이터 저장
3. Swagger를 이용하여 API 테스트
4. spring cloud gateway 활용
   - 시간에 따른 연결처리 방식
   - Header 매칭
   - API Gateway를 이용한 API 공통에러 처리
5. spring cloud amqp를 이용한 rabbitmq 연동
   - Windows의 경우 Docker를 사용하려면 사전에 WSL을 설치해야 한다.
   - Docker Desktop 설치 후 docker-compose.yml 작성
   - docker compose 실행
   - localhost:15672 접속하여 RabbitMQ admin을 사용
   - RabbitTemplate를 사용하여 메시지를 발송하기
   - @RabbitListener를 이용하여 메시지 수신하기