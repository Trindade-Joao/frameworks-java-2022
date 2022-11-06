package br.com.bluesoft.alucar.repository;

import br.com.bluesoft.alucar.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM vendedor v INNER JOIN " +
                    "   (SELECT vendedor_key, SUM(valor_total) AS soma FROM aluguel " +
                    "       WHERE MONTH(data_aluguel) = :mesAtual " +
                    "       GROUP BY vendedor_key ORDER BY soma DESC LIMIT 1) cont" +
                    "   ON v.vendedor_key = cont.vendedor_key" +
                    "   WHERE v.vendedor_key = cont.vendedor_key")
    Optional<Vendedor> findVendedorDoMes(int mesAtual);
}
