package com.teste.primeiroexemplo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BibliotecaVirtual {

  // #region Atributos
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String nome;
  private String autor;
  private Integer paginas;
  private Integer quantidade;
  private Double valor;
  private String observacao;
  // #endregion

  // #region Getters e Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public Integer getPaginas() {
    return paginas;
  }

  public void setPaginas(Integer paginas) {
    this.paginas = paginas;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }
  // #endregion Getters e Setters
}