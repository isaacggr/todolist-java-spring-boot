@echo off
setlocal enabledelayedexpansion

:: Script para preparar e implantar a aplicação TodoList (backend)
:: Autor: [Seu Nome]
:: Versão: 1.1

:: Cores para output
set "GREEN=[92m"
set "RED=[91m"
set "YELLOW=[93m"
set "NC=[0m"

:: Função para imprimir mensagem de sucesso
:success
echo %GREEN%✓ %~1%NC%
goto :eof

:: Função para imprimir erro
:error
echo %RED%✗ %~1%NC%
exit /b 1

:: Função para imprimir aviso
:warning
echo %YELLOW%⚠ %~1%NC%
goto :eof

:: Função para imprimir título
:title
echo.
echo %YELLOW%===== %~1 =====%NC%
echo.
goto :eof

:: Verificar se estamos no diretório raiz do projeto
if not exist "pom.xml" (
    call :error "Este script deve ser executado no diretório raiz do projeto backend"
    exit /b 1
)

:: Verificar se o Maven está instalado
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    call :warning "Maven não está instalado. Tentando usar mvnw"
    set "MVN_CMD=.\mvnw"
    if not exist "!MVN_CMD!" (
        call :error "Nem mvn nem mvnw foram encontrados. Por favor, instale o Maven"
        exit /b 1
    )
) else (
    set "MVN_CMD=mvn"
)

:: Menu de opções
call :title "TODOLIST - SCRIPT DE DEPLOY"
echo Opções disponíveis:
echo 1. Preparar backend para produção
echo 2. Executar backend localmente
echo 3. Executar testes do backend
echo 4. Sair

set /p option="Escolha uma opção: "

if "%option%"=="1" (
    call :option1
) else if "%option%"=="2" (
    call :option2
) else if "%option%"=="3" (
    call :option3
) else if "%option%"=="4" (
    echo Encerrando script.
    exit /b 0
) else (
    call :error "Opção inválida"
    exit /b 1
)

call :success "Operação concluída com sucesso!"
exit /b 0

:option1
    call :title "PREPARANDO BACKEND PARA PRODUÇÃO"
    
    :: Compilando o projeto
    echo Compilando o projeto...
    %MVN_CMD% clean package -DskipTests
    if %ERRORLEVEL% NEQ 0 (
        call :error "Falha ao compilar o projeto"
        exit /b 1
    )
    call :success "Projeto compilado com sucesso"
    
    :: Verificando o arquivo JAR gerado
    set "JAR_FILE=target\todolist-0.0.1-SNAPSHOT.jar"
    if exist "%JAR_FILE%" (
        call :success "Arquivo JAR gerado: %JAR_FILE%"
        for %%A in ("%JAR_FILE%") do echo Tamanho do arquivo: %%~zA bytes
    ) else (
        call :error "Arquivo JAR não foi gerado"
        exit /b 1
    )
    
    :: Instruções para deploy
    echo.
    echo Para deploy do backend, você pode usar:
    echo 1. Heroku: heroku deploy:jar %JAR_FILE% --app seu-app-nome
    echo 2. Render: Faça upload do repositório no Render e configure:
    echo    - Build Command: ./mvnw clean package -DskipTests
    echo    - Start Command: java -jar %JAR_FILE% --spring.profiles.active=prod
    echo.
    echo Use as seguintes variáveis de ambiente:
    echo - MONGODB_URI=sua-url-mongodb
    echo - ALLOWED_ORIGINS=url-do-frontend
    echo - SPRING_PROFILES_ACTIVE=prod
    
    goto :eof

:option2
    call :title "EXECUTANDO BACKEND LOCALMENTE"
    
    :: Verificar se o MongoDB está rodando
    echo Verificando MongoDB...
    
    :: Esta verificação é simplificada e pode não funcionar em todos os ambientes
    :: No Windows, tentamos verificar se o serviço do MongoDB está em execução
    sc query MongoDB >nul 2>nul
    if %ERRORLEVEL% EQU 0 (
        call :success "Serviço MongoDB encontrado"
    ) else (
        call :warning "Não foi possível confirmar se o MongoDB está em execução. Certifique-se que ele está rodando antes de continuar."
    )
    
    echo Iniciando aplicação Spring Boot...
    %MVN_CMD% spring-boot:run
    if %ERRORLEVEL% NEQ 0 (
        call :error "Falha ao iniciar a aplicação"
        exit /b 1
    )
    
    goto :eof

:option3
    call :title "EXECUTANDO TESTES DO BACKEND"
    
    echo Executando testes...
    %MVN_CMD% test
    if %ERRORLEVEL% NEQ 0 (
        call :error "Falha nos testes"
        exit /b 1
    )
    call :success "Testes executados com sucesso"
    
    goto :eof 