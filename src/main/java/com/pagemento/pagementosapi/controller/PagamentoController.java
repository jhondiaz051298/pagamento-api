package com.pagemento.pagementosapi.controller;

import com.pagemento.pagementosapi.domain.DTO.RequestPagamento;
import com.pagemento.pagementosapi.domain.DTO.ResponsePagamento;
import com.pagemento.pagementosapi.domain.PagamentoCode;
import com.pagemento.pagementosapi.service.PagamentoService;
import com.pagemento.pagementosapi.utils.ResponseRest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/api/pagamentos")
public class PagamentoController {

    PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }


    @GetMapping()
    public ResponseEntity<ResponseRest<List<ResponsePagamento>>> listAll(
            @RequestBody
            RequestPagamento request) {
        return ResponseEntity.ok(
                new ResponseRest<>(service.list(request),
                        PagamentoCode.PAGAMENTO_BUSCA_SUCCESS.getProperties(), null));
    }

    @PostMapping
    public ResponseEntity<ResponseRest<ResponsePagamento>> create(
            @Valid
            @RequestBody
            RequestPagamento request
    ) {
        try {
            return ResponseEntity.ok(
                    new ResponseRest<>(service.create(request), PagamentoCode.PAGAMENTO_SUCESSO_I.getProperties(), null));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    new ResponseRest<>(null, PagamentoCode.PAGAMENTO_ERROR_I.getProperties(), e.getMessage()));

        }
    }

    @PutMapping()
    public ResponseEntity<ResponseRest<ResponsePagamento>> update(
            @RequestBody
            RequestPagamento request
    ) {
        try {
            return ResponseEntity.ok(
                    new ResponseRest<>(service.update(request),
                            PagamentoCode.PAGAMENTO_SUCESSO_A.getProperties(), null));

        } catch (IllegalStateException | EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(
                    new ResponseRest<>(null, PagamentoCode.PAGAMENTO_ERROR_A.getProperties(), e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseRest<Void>> excluir(
            @RequestBody
            @PathVariable Long id
    ) {
        try {
            service.delete(id);
            return ResponseEntity.ok(
                new ResponseRest<>(null,
                        PagamentoCode.PAGAMENTO_SUCESSO_E.getProperties(), null));

        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(
                    new ResponseRest<>(null, PagamentoCode.PAGAMENTO_ERROR_E.getProperties(), e.getMessage()));
        }
    }
}
