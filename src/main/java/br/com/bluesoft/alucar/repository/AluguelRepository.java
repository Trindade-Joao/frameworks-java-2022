package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.Aluguel;
import br.com.bluesoft.alucar.model.Comissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {


    @Query("SELECT new br.com.bluesoft.alucar.model.Comissao(a.vendedor, SUM(a.valorTotal)) FROM Aluguel a INNER JOIN a.vendedor v WHERE MONTH(a.dataAluguel) = :mes GROUP BY v.vendedorKey")
    List<Comissao> findValorTotalPorVendedorMes(int mes);

    @Query("SELECT new br.com.bluesoft.alucar.model.Comissao(a.vendedor, SUM(a.valorTotal)) FROM Aluguel a INNER JOIN a.vendedor v WHERE MONTH(a.dataAluguel) = :mes AND v.vendedorKey = :vendedorKey  GROUP BY v.vendedorKey")
    Optional<Comissao> valorTotalMesPorVendedorKey(int vendedorKey, int mes);

}
