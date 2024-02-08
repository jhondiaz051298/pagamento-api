package com.pagemento.pagementosapi.domain;

import com.pagemento.pagementosapi.utils.Properties;
import org.springframework.http.HttpStatus;

public enum PagamentoCode {

    PAGAMENTO_BUSCA_SUCCESS(0, HttpStatus.OK, "Registro encontrado com sucesso."),
    PAGAMENTO_BUSCA_NOT_FOUND(0, HttpStatus.NOT_FOUND, "Nenhum registro encontrado."),
    PAGAMENTO_BUSCA_ERROR(1, HttpStatus.BAD_REQUEST, "Error ao buscar registro."),

    PAGAMENTO_SUCESSO_I(0, HttpStatus.OK, "Pagamento incluído com sucesso"),
    PAGAMENTO_SUCESSO_E(0, HttpStatus.OK, "Pagamento excluído com sucesso"),
    PAGAMENTO_SUCESSO_A(0, HttpStatus.OK, "Pagamento atualizada com sucesso"),

    PAGAMENTO_ERROR_I(1, HttpStatus.BAD_REQUEST, "Erro ao incluir novo pagamento"),
    PAGAMENTO_ERROR_E(1, HttpStatus.BAD_REQUEST, "Erro ao excluir pagamento"),
    PAGAMENTO_ERROR_A(1, HttpStatus.BAD_REQUEST, "Erro ao atualizar pagamento");

    private final Properties properties;

    private PagamentoCode(int code, HttpStatus status, String message) {
        this.properties = new Properties(code, status, message);
    }

    public Properties getProperties() {
        return properties;
    }
}
