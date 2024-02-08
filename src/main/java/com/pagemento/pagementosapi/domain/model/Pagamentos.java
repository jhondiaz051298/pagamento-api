package com.pagemento.pagementosapi.domain.model;

import com.pagemento.pagementosapi.domain.enums.MetodoPagamentoEnum;
import com.pagemento.pagementosapi.domain.enums.StatusPagamentoEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PAGAMENTOS")
public class Pagamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAGAMENTO")
    private Long idPagamento;

    @Column(name = "CODIGO_DEBITO")
    private Integer codigoDebito;

    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @Column(name = "METODO_PAGAMENTO")
    @Enumerated(EnumType.STRING)
    private MetodoPagamentoEnum metodoPagamento;

    @Column(name = "NUMERO_CARTAO")
    private String numeroCartao;

    @Column(name = "VALOR_PAGAMENTO")
    private BigDecimal valorPagamento;

    @Column(name = "STATUS_PAGAMENTO")
    @Enumerated(EnumType.STRING)
    private StatusPagamentoEnum statusPagamento;

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
