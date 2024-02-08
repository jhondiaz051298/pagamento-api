package com.pagemento.pagementosapi.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MetodoPagamentoEnum {

    PIX("PIX"),
    BOLETO("BOLETO"),
    CARTAO_CREDITO("CARTAO_CREDITO"),
    CARTAO_DEBITO("CARTAO_DEBITO");

    private String value;

    private MetodoPagamentoEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MetodoPagamentoEnum forValue(String value) {
        for (MetodoPagamentoEnum metodo : MetodoPagamentoEnum.values()) {
            if (metodo.getValue().equalsIgnoreCase(value) || metodo.name().equalsIgnoreCase(value)) {
                return metodo;
            }
        }
        throw new IllegalArgumentException("Valor desconhecido ou não suportado: " + value);
    }
}
