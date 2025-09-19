[![](https://img.plantuml.biz/plantuml/svg/VPIzRjim58Lt0Ne7mbsSmObaotz5O4HndKm5WWQ5booiUAqGeKY3f98MHHycxL1axYFenKfAz0zLgvkEpaVUSoyfNIcDfIuB5WOCDXffWIJD7pKYL4AcgU1Xe9uetsA92uGpBUIz_iO0uHTGee1ZjrIGOGNNGXAGIy64HE-IQ1BDebCKpfvoAKfE7BcQtqGtwJ6f7Z4HBvJdQ8EPCgN2e0w0Uaj6WMle8QoGUzvxQMubwg-AhGI5BwunpyHPZJRMWIG5vLHfQSh81h1lR1tRE-QvwRDtRmWeC2A0Ra7fwfUmZoahyx9wewg7leS1sdLbec62xPPoe9fydvUn1wIbDYbeTamCRSqZPhK1NMA9xe1GSgW7pF4ZZ_ue2DtO3HtleCJF9RWgcMv3urPudW5hCvDMklGPCxg77dW_vTKheyQweTpkG2K-Sskd_qsA-isHxEYPHz-26UF5wYjafSrjEZ1prRjKSp-JKYAZHvGTsb9MRuJkLyIHj-GJvFNXcp1IR2K5WiqQ4ygSa2YgFy_xSSTnQm8c6PK7pjw21XoUmDm5tJEZKrlb7fAqwjd3qxAiVjlRzu7owcTXflZ0JTwxwZKtKrTe3L_SylLw7GO_x1QkD1eCcclewM5BZrewQUbnIzVNmdrjzOkfBsU-dFlI7eQdwndN7w1xCVJbo9U99-kMmy0qWSxF5xOvz1vTNbAUiPB0Oc6jfDiQTrkJRclQRSswhRcpflzQSUIy-GalxlAkW9Fw1_uN)](https://editor.plantuml.com/uml/VPIzRjim58Lt0Ne7mbsSmObaotz5O4HndKm5WWQ5booiUAqGeKY3f98MHHycxL1axYFenKfAz0zLgvkEpaVUSoyfNIcDfIuB5WOCDXffWIJD7pKYL4AcgU1Xe9uetsA92uGpBUIz_iO0uHTGee1ZjrIGOGNNGXAGIy64HE-IQ1BDebCKpfvoAKfE7BcQtqGtwJ6f7Z4HBvJdQ8EPCgN2e0w0Uaj6WMle8QoGUzvxQMubwg-AhGI5BwunpyHPZJRMWIG5vLHfQSh81h1lR1tRE-QvwRDtRmWeC2A0Ra7fwfUmZoahyx9wewg7leS1sdLbec62xPPoe9fydvUn1wIbDYbeTamCRSqZPhK1NMA9xe1GSgW7pF4ZZ_ue2DtO3HtleCJF9RWgcMv3urPudW5hCvDMklGPCxg77dW_vTKheyQweTpkG2K-Sskd_qsA-isHxEYPHz-26UF5wYjafSrjEZ1prRjKSp-JKYAZHvGTsb9MRuJkLyIHj-GJvFNXcp1IR2K5WiqQ4ygSa2YgFy_xSSTnQm8c6PK7pjw21XoUmDm5tJEZKrlb7fAqwjd3qxAiVjlRzu7owcTXflZ0JTwxwZKtKrTe3L_SylLw7GO_x1QkD1eCcclewM5BZrewQUbnIzVNmdrjzOkfBsU-dFlI7eQdwndN7w1xCVJbo9U99-kMmy0qWSxF5xOvz1vTNbAUiPB0Oc6jfDiQTrkJRclQRSswhRcpflzQSUIy-GalxlAkW9Fw1_uN)

``` plantuml
@startuml
left to right direction
skinparam actorStyle awesome
skinparam usecaseBorderColor #406080
skinparam usecaseBackgroundColor #E9F0FA
skinparam shadowing false

actor "Cliente" as Cliente
actor "Agente (Empresa/Banco)" as Agente
actor "Administrador" as Admin

rectangle "Sistema de Gestão de Aluguéis" {
  usecase "Cadastrar-se" as UC1
  usecase "Autenticar-se" as UC2

  usecase "Efetuar Pedido de Aluguel" as UC3
  usecase "Modificar Pedido" as UC4
  usecase "Consultar Pedido" as UC5
  usecase "Cancelar Pedido" as UC6

  usecase "Avaliar Pedido\n(Análise Financeira)" as UC7
  usecase "Modificar Pedido (Agente)" as UC8
  usecase "Gerar/Executar Contrato" as UC9
  usecase "Associar Contrato de Crédito" as UC10
  usecase "Registrar Propriedade do Automóvel" as UC11

  usecase "Gerir Automóveis" as UC12
  usecase "Gerir Clientes" as UC13
  usecase "Gerir Agentes" as UC14

  usecase "Construção Dinâmica\nde Páginas Web" as UCWWW
}

Cliente -- UC1
Cliente -- UC2
Cliente -- UC3
Cliente -- UC4
Cliente -- UC5
Cliente -- UC6

Agente -- UC7
Agente -- UC8
Agente -- UC9
Agente -- UC10
Agente -- UC11

Admin -- UC12
Admin -- UC13
Admin -- UC14
Admin -- UCWWW

UC3 ..> UC2 : <<include>>
UC4 ..> UC2 : <<include>>
UC5 ..> UC2 : <<include>>
UC6 ..> UC2 : <<include>>
UC7 ..> UC2 : <<include>>
UC8 ..> UC2 : <<include>>
UC9 ..> UC7 : <<include>>
UC10 ..> UC9 : <<include>>
UC11 ..> UC9 : <<include>>
@enduml
```