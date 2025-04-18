FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /workspace/app

# Copiar o pom.xml e arquivos do Maven wrapper
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Tornar o script mvnw executável
RUN chmod +x ./mvnw

# Baixar dependências para cache (isso será feito apenas se o pom.xml mudar)
RUN ./mvnw dependency:go-offline -B

# Copiar o código fonte
COPY src src

# Compilar e empacotar a aplicação
RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Imagem final
FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency

# Copiar camadas da aplicação
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Configurar ambiente
ENV SPRING_PROFILES_ACTIVE=prod

# Porta da aplicação
EXPOSE 8080

# Iniciar a aplicação
ENTRYPOINT ["java","-cp","app:app/lib/*","com.isaacggr.todolist.TodolistApplication"] 