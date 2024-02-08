package com.pagemento.pagementosapi.domain.mapper;

import com.pagemento.pagementosapi.domain.DTO.RequestPagamento;
import com.pagemento.pagementosapi.domain.DTO.ResponsePagamento;
import com.pagemento.pagementosapi.domain.model.Pagamentos;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PagamentoMapper {
    ResponsePagamento responseToModel(Pagamentos pagamentos);
    Pagamentos requestToModel(RequestPagamento request);
}
