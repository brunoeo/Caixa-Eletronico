package com.br.caixaEletronico.caixaEletronico.domain;

import javax.persistence.Enumerated;

public enum TipoTransacao {

    SAQUE,
    TRANSFERENCIA,
    PAGAMENTO,
    DEPOSITO;
}
