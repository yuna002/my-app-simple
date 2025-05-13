# 使用輕量版的 OpenJDK 基底映像
FROM openjdk:17-jdk-slim

# 建立工作目錄
WORKDIR /app

# 複製本機 JAR 檔到容器中
COPY my-app-simple-0.0.1-SNAPSHOT.jar app.jar

# 開放應用埠
EXPOSE 8090

# 啟動指令
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
