
package Atividade_Fujioka.service;

import Atividade_Fujioka.model.DTO.DiretorDTO;
import Atividade_Fujioka.model.Diretor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiretorService {

    private final DiretorRepository filmeRepository;

    public List<DiretorDTO> listarTodos() {
        return diretorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DiretorDTO> buscarPorId(Long id) {
        return diretorRepository.findById(id)
                .map(this::convertToDTO);
    }

    public DiretorDTO criarFilme(DiretorDTO diretorDTO) {
        Diretor diretor = convertToEntity(diretorDTO);
        return convertToDTO(diretorRepository.save(diretor));
    }

    public Optional<DiretorDTO> atualizarDiretor(Long id, DiretorDTO diretorDTO) {
        return diretorRepository.findById(id).map(diretor -> {
            diretor.setTitulo(diretorDTO.getTitulo());
            diretor.setAnoDeLancamento(diretorDTO.getAnoDeLancamento());
            diretor.setDiretor(convertToEntity(diretorDTO.getDiretor()));
            diretor.setAtores(diretorDTO.getAtores().stream().map(this::convertToEntity).collect(Collectors.toList()));
            diretor.setGeneros(diretorDTO.getGeneros().stream().map(this::convertToEntity).collect(Collectors.toList()));
            return convertToDTO(diretorRepository.save(filme));
        });
    }

    public boolean deletarDiretor(Long id) {
        if (diretorRepository.existsById(id)) {
            diretorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private DiretorDTO convertToDTO(Diretor diretor) {
        return new DiretorDTO(
                diretor.getId(),
                diretor.getTitulo(),
                diretor.getAnoDeLancamento(),
                diretor.getDiretor() != null ? new DiretorDTO(filme.getDiretor().getId(), filme.getDiretor().getNome()) : null,
                diretor.getAtores()
                        .stream()
                        .map(ator -> new AtorDTO(ator.getId(), ator.getNome()))
                        .collect(Collectors.toList()),
                diretor.getGeneros()
                        .stream()
                        .map(genero -> new GeneroDTO(genero.getId(), genero.getNome()))
                        .collect(Collectors.toList())
        );
    }


    private Filme convertToEntity(FilmeDTO filmeDTO) {
        Filme filme = new Filme();
        filme.setTitulo(filmeDTO.getTitulo());
        filme.setAnoDeLancamento(filmeDTO.getAnoDeLancamento());
        filme.setDiretor(convertToEntity(filmeDTO.getDiretor()));
        filme.setAtores(filmeDTO.getAtores()
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
        filme.setGeneros(filmeDTO.getGeneros()
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
        return filme;
    }

    private Diretor convertToEntity(DiretorDTO diretorDTO) {
        if (diretorDTO == null) {
            return null; // Retorne null caso não haja Diretor
        }
        Diretor diretor = new Diretor();
        diretor.setId(diretorDTO.getId());
        diretor.setNome(diretorDTO.getNome());
        return diretor;
    }

    private Ator convertToEntity(AtorDTO atorDTO) {
        Ator ator = new Ator();
        ator.setId(atorDTO.getId());
        ator.setNome(atorDTO.getNome());
        return ator;
    }

    private Genero convertToEntity(GeneroDTO generoDTO) {
        Genero genero = new Genero();
        genero.setId(generoDTO.getId());
        genero.setNome(generoDTO.getNome());
        return genero;
    }
}
