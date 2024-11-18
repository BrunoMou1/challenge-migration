## Passo 1 - clonando o projeto
git clone [https://github.com/usuario/projeto-x.git](https://github.com/BrunoMou1/challenge-migration.git)
cd challenge-migration

## Passo 2 - construindo imagem docker
docker build -t challenge-migration .

## Passo 3 - rodando imagem docker
docker run -p 8080:8080 challenge-migration

## Rodando localmente
mvn spring-boot:run

## Testes unitarios
mvn clean test


