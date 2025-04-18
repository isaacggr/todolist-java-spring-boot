#!/bin/bash

# Script para preparar e implantar a aplicação TodoList (backend)
# Autor: [Seu Nome]
# Versão: 1.1

# Cores para output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Função para imprimir mensagem de sucesso
success() {
  echo -e "${GREEN}✓ $1${NC}"
}

# Função para imprimir erro
error() {
  echo -e "${RED}✗ $1${NC}"
  exit 1
}

# Função para imprimir aviso
warning() {
  echo -e "${YELLOW}⚠ $1${NC}"
}

# Função para imprimir título
title() {
  echo -e "\n${YELLOW}===== $1 =====${NC}\n"
}

# Verificar se estamos no diretório raiz do projeto
if [ ! -f "pom.xml" ]; then
  error "Este script deve ser executado no diretório raiz do projeto backend"
fi

# Verificar se o Maven está instalado
if ! command -v mvn &> /dev/null; then
  warning "Maven não está instalado. Tentando usar mvnw"
  MVN_CMD="./mvnw"
  if [ ! -f "$MVN_CMD" ]; then
    error "Nem mvn nem mvnw foram encontrados. Por favor, instale o Maven"
  fi
else
  MVN_CMD="mvn"
fi

# Menu de opções
title "TODOLIST - SCRIPT DE DEPLOY"
echo "Opções disponíveis:"
echo "1. Preparar backend para produção"
echo "2. Executar backend localmente"
echo "3. Executar testes do backend"
echo "4. Sair"

read -p "Escolha uma opção: " option

case $option in
  1)
    title "PREPARANDO BACKEND PARA PRODUÇÃO"
    
    # Compilando o projeto
    echo "Compilando o projeto..."
    $MVN_CMD clean package -DskipTests || error "Falha ao compilar o projeto"
    success "Projeto compilado com sucesso"
    
    # Verificando o arquivo JAR gerado
    JAR_FILE="target/todolist-0.0.1-SNAPSHOT.jar"
    if [ -f "$JAR_FILE" ]; then
      success "Arquivo JAR gerado: $JAR_FILE"
      echo "Tamanho do arquivo: $(du -h $JAR_FILE | cut -f1)"
    else
      error "Arquivo JAR não foi gerado"
    fi
    
    # Instruções para deploy
    echo ""
    echo "Para deploy do backend, você pode usar:"
    echo "1. Heroku: heroku deploy:jar $JAR_FILE --app seu-app-nome"
    echo "2. Render: Faça upload do repositório no Render e configure:"
    echo "   - Build Command: ./mvnw clean package -DskipTests"
    echo "   - Start Command: java -jar $JAR_FILE --spring.profiles.active=prod"
    echo ""
    echo "Use as seguintes variáveis de ambiente:"
    echo "- MONGODB_URI=sua-url-mongodb"
    echo "- ALLOWED_ORIGINS=url-do-frontend"
    echo "- SPRING_PROFILES_ACTIVE=prod"
    ;;
    
  2)
    title "EXECUTANDO BACKEND LOCALMENTE"
    
    # Verificar se o MongoDB está rodando
    echo "Verificando MongoDB..."
    # Esta verificação é simplificada e pode não funcionar em todos os ambientes
    if command -v mongosh &> /dev/null; then
      if ! mongosh --eval "db.version()" --quiet &> /dev/null; then
        warning "MongoDB parece não estar em execução. Inicie o MongoDB antes de continuar."
      else
        success "MongoDB está em execução"
      fi
    else
      warning "Não foi possível verificar MongoDB. Certifique-se que ele está em execução."
    fi
    
    echo "Iniciando aplicação Spring Boot..."
    $MVN_CMD spring-boot:run || error "Falha ao iniciar a aplicação"
    ;;
    
  3)
    title "EXECUTANDO TESTES DO BACKEND"
    
    echo "Executando testes..."
    $MVN_CMD test || error "Falha nos testes"
    success "Testes executados com sucesso"
    ;;
    
  4)
    echo "Encerrando script."
    exit 0
    ;;
    
  *)
    error "Opção inválida"
    ;;
esac

success "Operação concluída com sucesso!" 