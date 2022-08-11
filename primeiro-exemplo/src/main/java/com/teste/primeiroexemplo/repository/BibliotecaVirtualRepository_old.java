package com.teste.primeiroexemplo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.teste.primeiroexemplo.model.BibliotecaVirtual;
import com.teste.primeiroexemplo.model.exception.ResourceNotFoundException;

@Repository
public class BibliotecaVirtualRepository_old {

  private List<BibliotecaVirtual> bibliotecas = new ArrayList<BibliotecaVirtual>();
  private Integer ultimoId = 0;

  /**
   * Método para retornar uma lista de livros.
   * 
   * @return Lista com livros disponíveis na biblioteca.
   */
  public List<BibliotecaVirtual> consultarTodos() {
    return bibliotecas;
  }

  /**
   * Método que retorna o livro que foi encontrado pelo seu Id.
   * 
   * @param id do livro que será encontrado.
   * @return Retorna um livro caso seja encontrado.
   */
  public Optional<BibliotecaVirtual> consultarPorId(Integer id) {
    return bibliotecas
        .stream()
        .filter(BibliotecaVirtual -> BibliotecaVirtual.getId() == id)
        .findFirst();
  }

  /**
   * Método para cadastrar um livro novo na biblioteca virtual on-line.
   * 
   * @param bibliotecaVirtual onde o livro será cadastrado.
   * @return Retorna o livro que foi cadastrado na biblioteca virtual on-line.
   */
  public BibliotecaVirtual cadastrarLivro(BibliotecaVirtual bibliotecaVirtual) {

    ultimoId++;

    bibliotecaVirtual.setId(ultimoId);
    bibliotecas.add(bibliotecaVirtual);
    return bibliotecaVirtual;
  }

  /**
   * Método para excluir livro pelo seu Id.
   * 
   * @param id do livro para ser excluído da biblioteca virtual on-line.
   */
  public void excluir(Integer id) {
    bibliotecas.removeIf(BibliotecaVirtual -> BibliotecaVirtual.getId() == id);
  }

  /**
   * Método para atualizar um livro na biblioteca virtual.
   * 
   * @param bibliotecaVirtual onde o livro será atualizado.
   * @return Retorna o livro quando é atualizado na biblioteca virtual.
   */
  public BibliotecaVirtual atualizar(BibliotecaVirtual bibliotecaVirtual) {

    // Lembrando, que antes tenho que encontrar o livro na biblioteca virtual.
    Optional<BibliotecaVirtual> livroEncontrado = consultarPorId(bibliotecaVirtual.getId());
    if (livroEncontrado.isEmpty()) {
      throw new ResourceNotFoundException("Desculpe, o livro não foi encontrado.");
    }

    // Exclui o livro antigo da biblioteca virtual.
    excluir(bibliotecaVirtual.getId());

    // Cadastra o livro novo e atualiza ele na biblioteca.
    bibliotecas.add(bibliotecaVirtual);
    return bibliotecaVirtual;
  }
}