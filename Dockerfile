FROM maven:3.8.5-openjdk-11 AS build

# 앱 소스 코드를 컨테이너의 작업 디렉토리에 복사
COPY . /usr/src/app

# 작업 디렉토리로 이동
WORKDIR /usr/src/app

# Maven을 사용해 WAR 파일 빌드
RUN mvn clean package

# Tomcat 이미지를 베이스로 사용
FROM tomcat:9.0-jdk8-openjdk

# 빌드된 WAR 파일을 Tomcat 웹앱 디렉토리로 복사 및 ROOT.war로 이름 변경
COPY --from=build /usr/src/app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# 컨테이너 시작 시 톰캣 실행
CMD ["catalina.sh", "run"]

# 톰캣의 기본 포트를 외부에 노출
EXPOSE 8080
