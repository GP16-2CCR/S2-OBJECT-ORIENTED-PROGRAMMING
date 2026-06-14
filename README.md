# Sprint 2 — Object-Oriented Programming

Sistema de monitoramento e priorização de roçada de vegetação nas rodovias (Motiva Rodovias).

## Equipe

| Integrante | RM |
|---|---|
| Caio Cordeiro Salgado | RM565400 |
| Hector van Tol Taver | RM562881 |
| Juan Gigliotti da Cunha | RM563253 |
| Rafael Alves da Silva | RM561878 |
| Raissa Fabrício Lima | RM563772 |

## Como executar

```bat
javac -encoding UTF-8 -d bin Main.java intervencao\*.java iot\*.java modelo\*.java crescimento\*.java servico\*.java
java -cp bin Main
```

## Estrutura

```
Main.java
intervencao/     → IntervencaoOperacional, RocadaMecanizada, Pulverizacao
iot/             → MonitoravelViaIoT
modelo/          → TrechoRodovia, DadosSensor, TipoTerreno
crescimento/     → CrescimentoUmido, CrescimentoSeco
servico/         → MotorPrioridade, RelatorioPrioridade, GerenciadorTrechos
```
