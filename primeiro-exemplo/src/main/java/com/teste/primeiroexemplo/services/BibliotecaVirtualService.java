package com.teste.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teste.primeiroexemplo.model.BibliotecaVirtual;
import com.teste.primeiroexemplo.model.exception.ResourceNotFoundException;
import com.teste.primeiroexemplo.repository.BibliotecaVirtualRepository;
import com.teste.primeiroexemplo.shared.BibliotecaVirtualDTO;

@Service
public class BibliotecaVirtualService {

  @Autowired
  private BibliotecaVirtualRepository bibliotecaRepository;

  public List<BibliotecaVirtualDTO> consultarTodos() {

    List<BibliotecaVirtual> bibliotecas = bibliotecaRepository.findAll();

    return bibliotecas.stream()
        .map(bibliotecaVirtual -> new ModelMapper().map(bibliotecaVirtual, BibliotecaVirtualDTO.class))
        .collect(Collectors.toList());
  }

  /**
   * Método que retorna o livro que foi encontrado pelo seu id.
   * 
   * @param id do livro que será encontrado na consulta.
   * @return Retorna um livro, caso seja encontrado.
   */
  public Optional<BibliotecaVirtualDTO> consultarPorId(Integer id) {
    // Obtendo optional de livro pelo id
    Optional<BibliotecaVirtual> bibliotecaVirtual = bibliotecaRepository.findById(id);

    // Se não localizar, lança exception
    if (bibliotecaVirtual.isEmpty()) {
      throw new ResourceNotFoundException("Livro com id: " + id + " não localizado.");
    }

    // Converte o optional de bibliotecaVirtual em uma BibliotecaVirtualDTO
    BibliotecaVirtualDTO dto = new ModelMapper().map(bibliotecaVirtual.get(), BibliotecaVirtualDTO.class);

    // Cria e retorna o optional em um optional de bibliotecaVirtualDto
    return Optional.of(dto);
  }

  /**
   * Método para cadastrar um livro novo na biblioteca virtual.
   * 
   * @param bibliotecaVirtual onde o livro será cadastrado.
   * @return Retorna o livro que foi cadastrado na biblioteca virtual.
   */
  public BibliotecaVirtualDTO cadastrarLivro(BibliotecaVirtualDTO bibliotecaVirtualDto) {

    bibliotecaVirtualDto.setId(null);

    // Criar um objeto para mapear
    ModelMapper mapper = new ModelMapper();

    // Converter a minha BiliotecaVirtualDTO em uma BibliotecaVirtual
    BibliotecaVirtual bibliotecaVirtual = mapper.map(bibliotecaVirtualDto, BibliotecaVirtual.class);

    // Salvar a BibliotecaVirtual ao banco de dados
    bibliotecaVirtual = bibliotecaRepository.save(bibliotecaVirtual);

    bibliotecaVirtualDto.setId(bibliotecaVirtual.getId());

    // Retorna a BibliotecaVirtualDTO atualizada
    return bibliotecaVirtualDto;
  }

  /**
   * Método para excluir livro pelo seu id.
   * 
   * @param id do livro para ser excluído da biblioteca virtual.
   */
  public void excluir(Integer id) {
    // Verifico se a bibliotecaVirtual existe
    Optional<BibliotecaVirtual> bibliotecaVirtual = bibliotecaRepository.findById(id);

    // Se não existir. lanço uma exception
    if (bibliotecaVirtual.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível excluir o livro com o id: " + id + " = Livro não existe.");
    }

    // Exclui o livro pelo seu id
    bibliotecaRepository.deleteById(id);
  }

  /**
   * Método para atualizar um livro na biblioteca virtual.
   * 
   * @param bibliotecaVirtual onde o livro será atualizado.
   * @param id                do livro.
   * @return Retorna o livro quando é atualizado na biblioteca virtual.
   */
  public BibliotecaVirtualDTO atualizar(Integer id, BibliotecaVirtualDTO bibliotecaVirtualDto) {

    // Passar o id para a bibliotecaVirtualDto
    bibliotecaVirtualDto.setId(id);

    // Criar um objeto de mapeamento
    ModelMapper mapper = new ModelMapper();

    // Converter a BibliotecaVirtualDTO em uma bibliotecaVirtual
    BibliotecaVirtual bibliotecaVirtual = mapper.map(bibliotecaVirtualDto, BibliotecaVirtual.class);

    // Atualizar o livro no banco de dados
    bibliotecaRepository.save(bibliotecaVirtual);

    // Retornar a bibliotecaVirtualDto atualizado
    return bibliotecaVirtualDto;
  }
}