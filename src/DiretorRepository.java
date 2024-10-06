
package Atividade_Fujioka.repository;

import Atividade_Fujioka.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Long> {

    List<Diretor> findByAnoDeLancamento (Integer AnoDeLancamento);

    @Query("select d.titulo from Diretor d where d.titulo=: titulo")
    List<Diretor> listarDiretorTitulo(@Param("titulo") String titulo);
}
