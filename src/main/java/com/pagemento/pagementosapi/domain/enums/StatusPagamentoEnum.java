package com.pagemento.pagementosapi.domain.enums;

public enum StatusPagamentoEnum {

    PENDENTE_PROCESSAMENTO("Pendente de Processamento"),
    PROCESSADO_FALHA("Processado com Falha"),
    PROCESSADO_SUCESSO("Processado com Sucesso");

    private String value;
    private StatusPagamentoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
