[![](https://img.plantuml.biz/plantuml/svg/XLLDRzim3Bq7o7yGUje74cpOBIYAkeuxU6WR8sbp6OQ1bPb0W6GPabmCxVRVH_ahifEWbpYadiZ7Hr6wjWwCgvISJgOJBi5Q5ak1XKFsDfqmzfc9VC6Udj9bOvdzWcsS4SM-iNcv6peAhN3eAOtU2Mj13zrOv6YGapTf_xL-LHhSGwuDsWMx4zPT9hthYltyCfz_-pMT_3iGxlPtZANU6m9NPfIng1IEVLohKgAYWaSBpo3C4LZaa8-gGn8m7-EmF3X2edqTPyI5Lz0RpGN8OK13OecQv3qMrgykaGi5iWqlg5zc1nnPPdHf159J1UaPMkpoxtpUDVV0ATgVR_PnBvqegTP7-ctsrVcHX0ryx8rvC6F9VRPEDb5htKGFyOhL6uvfTT5wLfNJIh-WF4rC0N7YbOGXEoY876dHWGmV0PJEKOwQLKe8OK6xCin5hYDPxQjpH79m41j14IZg4dm9tardmbNM1_NV9awR3QdcNAU5u6AmfLsu5QhpTig6GNfjutKQBLUTph-ZX-WktIID7MNhrRPVNIS_alInDsDgGt979XlK6hs0hCku5GKK78M1ynLdGEE6Pa2_SxLhZLS_MpGlm3NQWS9rNIsoISDOjjgaZ-csfVcGV8yQAo0Pwy8PSE-t9BAMod2LWP2Zg4NV2ZfhD6BXoaweiVj4sjXG3vm-dPRqqoQOotfQu0z4HJMOr-P6X5UQPEjLgOzhdMnut2w-Nh3PxAh-zaDx7j8Fp168xiiGrupeWjdgMQ4Z8BK1tjjqV3Ycat4pHdi60Tz7XpV2mZyPlRdKM_IphWz1kaQCWenodC2V4e9hgiXppUXQ_d13zUcFlXtTC-Z1UZQhluUNgb_rpr4BeB-VBe9BtaUvffkxVbt_0m00)](https://editor.plantuml.com/uml/XLLDRzim3Bq7o7yGUje74cpOBIYAkeuxU6WR8sbp6OQ1bPb0W6GPabmCxVRVH_ahifEWbpYadiZ7Hr6wjWwCgvISJgOJBi5Q5ak1XKFsDfqmzfc9VC6Udj9bOvdzWcsS4SM-iNcv6peAhN3eAOtU2Mj13zrOv6YGapTf_xL-LHhSGwuDsWMx4zPT9hthYltyCfz_-pMT_3iGxlPtZANU6m9NPfIng1IEVLohKgAYWaSBpo3C4LZaa8-gGn8m7-EmF3X2edqTPyI5Lz0RpGN8OK13OecQv3qMrgykaGi5iWqlg5zc1nnPPdHf159J1UaPMkpoxtpUDVV0ATgVR_PnBvqegTP7-ctsrVcHX0ryx8rvC6F9VRPEDb5htKGFyOhL6uvfTT5wLfNJIh-WF4rC0N7YbOGXEoY876dHWGmV0PJEKOwQLKe8OK6xCin5hYDPxQjpH79m41j14IZg4dm9tardmbNM1_NV9awR3QdcNAU5u6AmfLsu5QhpTig6GNfjutKQBLUTph-ZX-WktIID7MNhrRPVNIS_alInDsDgGt979XlK6hs0hCku5GKK78M1ynLdGEE6Pa2_SxLhZLS_MpGlm3NQWS9rNIsoISDOjjgaZ-csfVcGV8yQAo0Pwy8PSE-t9BAMod2LWP2Zg4NV2ZfhD6BXoaweiVj4sjXG3vm-dPRqqoQOotfQu0z4HJMOr-P6X5UQPEjLgOzhdMnut2w-Nh3PxAh-zaDx7j8Fp168xiiGrupeWjdgMQ4Z8BK1tjjqV3Ycat4pHdi60Tz7XpV2mZyPlRdKM_IphWz1kaQCWenodC2V4e9hgiXppUXQ_d13zUcFlXtTC-Z1UZQhluUNgb_rpr4BeB-VBe9BtaUvffkxVbt_0m00)

``` plantuml
@startuml

class Cliente {
  + id: UUID
  + rg: String
  + cpf: String
  + nome: String
  + profissao: String
  + endereco: Endereco
  + empregadores: List<Empregador> [0..3]
}

class Endereco {
  + logradouro: String
  + numero: String
  + complemento: String
  + bairro: String
  + cidade: String
  + estado: String
  + cep: String
}

class Empregador {
  + id: UUID
  + razaoSocial: String
  + rendimentoMensal: Decimal
}

interface Proprietario
Proprietario <|.. Cliente

class Agente {
  + id: UUID
  + nome: String
  + tipo: TipoAgente
}
enum TipoAgente { 
    EMPRESA 
    BANCO 
}
Proprietario <|.. Agente

class Automovel {
  + id: UUID
  + matricula: String
  + ano: int
  + marca: String
  + modelo: String
  + placa: String
}

class PedidoAluguel {
  + id: UUID
  + dataCriacao: Date
  + status: StatusPedido
  + periodoInicio: Date
  + periodoFim: Date
}
enum StatusPedido { 
    CRIADO
    EM_ANALISE
    APROVADO
    REJEITADO
    CANCELADO 
}

class AvaliacaoFinanceira {
  + id: UUID
  + dataParecer: Date
  + parecer: Parecer
  + observacoes: String
}
enum Parecer {
    POSITIVO
    NEGATIVO
}

class ContratoAluguel {
  + id: UUID
  + dataAssinatura: Date
  + inicioVigencia: Date
  + fimVigencia: Date
}

class ContratoCredito {
  + id: UUID
  + valor: Decimal
  + taxaAnual: Decimal
  + prazoMeses: int
}

PedidoAluguel "1" --> "1" Cliente
PedidoAluguel "1" --> "1" Automovel
PedidoAluguel "0..1" --> "1" Agente : submetidoPara
PedidoAluguel "0..1" --> "1" AvaliacaoFinanceira
ContratoAluguel "0..1" --> "1" PedidoAluguel
ContratoAluguel "0..1" --> "1" Proprietario : proprietarioDoVeiculo
ContratoCredito "0..1" --> "1" ContratoAluguel
ContratoCredito "1" --> "1" Agente : concedidoPor (Banco)

Cliente "1" o-- "1" Endereco
Cliente "0..3" o-- "0..*" Empregador

@enduml
```