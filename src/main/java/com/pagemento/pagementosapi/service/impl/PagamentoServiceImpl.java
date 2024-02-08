package com.pagemento.pagementosapi.service.impl;

import com.pagemento.pagementosapi.domain.DTO.RequestPagamento;
import com.pagemento.pagementosapi.domain.DTO.ResponsePagamento;
import com.pagemento.pagementosapi.domain.enums.MetodoPagamentoEnum;
import com.pagemento.pagementosapi.domain.enums.StatusPagamentoEnum;
import com.pagemento.pagementosapi.domain.mapper.PagamentoMapper;
import com.pagemento.pagementosapi.domain.model.Pagamentos;
import com.pagemento.pagementosapi.repository.PagamentoRepository;
import com.pagemento.pagementosapi.service.PagamentoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagamentoServiceImpl implements PagamentoService {
    private PagamentoRepository repository;
    private PagamentoMapper mapper;

    public PagamentoServiceImpl(PagamentoRepository repository, PagamentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ResponsePagamento> list(RequestPagamento request) {
        String statusPagamento = Optional.ofNullable(request.getStatusPagamento())
                .map(Enum::toString)
                .orElse(null);

        List<Pagamentos> pagamentos = repository.findOrListAll(
                request.getCodigoDebito(),
                request.getCpfCnpj(),
                statusPagamento
        );

        return pagamentos.stream()
                .map(mapper::responseToModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponsePagamento create(RequestPagamento request) {
        this.validarNumeroCartao(request);
        Pagamentos pagamentos = this.prepararPagamento(request);
        return mapper.responseToModel(repository.save(pagamentos));
    }

    @Override
    public ResponsePagamento update(RequestPagamento request) {
        Pagamentos pagamentos = repository.findById(request.getIdPagamento())
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado"));

        validarStatusPagamento(pagamentos, request.getStatusPagamento());

        pagamentos.setStatusPagamento(request.getStatusPagamento());
        repository.save(pagamentos);

        return mapper.responseToModel(pagamentos);
    }

    @Override
    public void delete(Long idPagamento) {
        Pagamentos pagamento = repository.findById(idPagamento)
                .orElseThrow(() -> new IllegalStateException("Pagamento com ID " + idPagamento + " não encontrado."));

        if (!StatusPagamentoEnum.PENDENTE_PROCESSAMENTO.equals(pagamento.getStatusPagamento())) {
            throw new IllegalStateException("Pagamento não pode ser deletado, pois não está em status pendente.");
        }

        repository.deleteById(idPagamento);
    }

    private void validarStatusPagamento(Pagamentos pagamentos, StatusPagamentoEnum novoStatus) {
        StatusPagamentoEnum statusAtual = pagamentos.getStatusPagamento();

        if (statusAtual == StatusPagamentoEnum.PROCESSADO_SUCESSO) {
            throw new IllegalStateException("Pagamento já processado com sucesso e não pode ser alterado.");
        }

        if (statusAtual == StatusPagamentoEnum.PENDENTE_PROCESSAMENTO) {
            if (novoStatus != StatusPagamentoEnum.PROCESSADO_SUCESSO && novoStatus != StatusPagamentoEnum.PROCESSADO_FALHA) {
                throw new IllegalStateException("Pagamento pendente de processamento só pode ser alterado para Processado com Sucesso ou Processado com Falha.");
            }
        }

        if (statusAtual == StatusPagamentoEnum.PROCESSADO_FALHA && novoStatus != StatusPagamentoEnum.PENDENTE_PROCESSAMENTO) {
            throw new IllegalStateException("Pagamento processado com falha só pode ser alterado para Pendente de Processamento.");
        }
    }

    private void validarNumeroCartao(RequestPagamento request) {
        boolean isNumeroCartaoNecessario = request.isNumeroCartaoNecessario();
        String numeroCartao = request.getNumeroCartao();

        if (isNumeroCartaoNecessario && (numeroCartao == null || numeroCartao.isEmpty())) {
            throw new IllegalArgumentException("Número do cartão é obrigatório para o método de pagamento selecionado.");
        }
    }

    private Pagamentos prepararPagamento(RequestPagamento request) {
        Pagamentos pagamentos = mapper.requestToModel(request);
        pagamentos.setStatusPagamento(StatusPagamentoEnum.PENDENTE_PROCESSAMENTO);

        boolean isMetodoPagamentoCartao = MetodoPagamentoEnum.CARTAO_CREDITO.equals(request.getMetodoPagamento()) || MetodoPagamentoEnum.CARTAO_DEBITO.equals(request.getMetodoPagamento());

        if (!isMetodoPagamentoCartao) {
            pagamentos.setNumeroCartao(null);
        }

        return pagamentos;
    }
}
