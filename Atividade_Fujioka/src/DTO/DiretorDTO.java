
package Atividade_Fujioka.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Diretor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private Integer anoDeLancamento;

    @ManyToOne
    @JoinColumn(name = "diretor_id")
    private Diretor diretor;

    @ManyToMany
    @JoinTable(
            name = "filme_ator",
            joinColumns = @JoinColumn(name = "filme_id"),
            inverseJoinColumns = @JoinColumn(name = "ator_id")
    )
    private List<Ator> atores;

    @ManyToMany
    @JoinTable(
            name = "diretor_genero",
            joinColumns = @JoinColumn(name = "diretor_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private List<Genero> generos;
}
