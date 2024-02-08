package com.pagemento.pagementosapi.repository;

import com.pagemento.pagementosapi.domain.model.Pagamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamentos, Long> {

    @Query(value = "SELECT * FROM PAGAMENTOS" +
            " WHERE (:codigoDebito IS NULL OR CODIGO_DEBITO = :codigoDebito)" +
            " AND (:cpfCnpj IS NULL OR CPF_CNPJ = :cpfCnpj)" +
            " AND (:statusPagamento IS NULL OR STATUS_PAGAMENTO = :statusPagamento)", nativeQuery = true)
    List<Pagamentos> findOrListAll(
            @Param("codigoDebito") Integer codigoDebito,
            @Param("cpfCnpj") String cpfCnpj,
            @Param("statusPagamento") String statusPagamento);
}
