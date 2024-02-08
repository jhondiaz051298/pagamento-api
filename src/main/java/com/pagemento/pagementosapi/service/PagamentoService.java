package com.pagemento.pagementosapi.service;

import com.pagemento.pagementosapi.domain.DTO.RequestPagamento;
import com.pagemento.pagementosapi.domain.DTO.ResponsePagamento;

import java.util.List;

public interface PagamentoService {

    List<ResponsePagamento> list(RequestPagamento request);

    ResponsePagamento create(RequestPagamento request);

    ResponsePagamento update(RequestPagamento request);

    void delete(Long id);
}
