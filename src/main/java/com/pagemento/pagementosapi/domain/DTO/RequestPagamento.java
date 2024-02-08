package com.pagemento.pagementosapi.domain.DTO;

import com.pagemento.pagementosapi.domain.enums.MetodoPagamentoEnum;
import com.pagemento.pagementosapi.domain.enums.StatusPagamentoEnum;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class RequestPagamento {

    private Long idPagamento;
    private Integer codigoDebito;
    private String cpfCnpj;
    private MetodoPagamentoEnum metodoPagamento;
    private String numeroCartao;
    @DecimalMin("0.01")
    private BigDecimal valorPagamento;
    private StatusPagamentoEnum statusPagamento;

    public boolean isNumeroCartaoNecessario() {
        return MetodoPagamentoEnum.CARTAO_CREDITO.equals(this.metodoPagamento) || MetodoPagamentoEnum.CARTAO_DEBITO.equals(this.metodoPagamento);
    }
    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Integer getCodigoDebito() {
        return codigoDebito;
    }

    public void setCodigoDebito(Integer codigoDebito) {
        this.codigoDebito = codigoDebito;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public MetodoPagamentoEnum getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamentoEnum metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public StatusPagamentoEnum getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamentoEnum statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
