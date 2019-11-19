package com.example.projetofarmacia2.model;

import java.io.Serializable;
import java.util.List;

public class Medicamento implements Serializable {
    private Long id;
    private String nome;
    private String principioAtivo;
    private String concentracao;
    private String formaFarmaceutica;
    private int registroAnvisa;
    private String detentorRegistro;
    private Farmacia farmacia;
    private double custo, preco;
    private List<Imagem> imagens;
    private List<Categoria> categoria;
    int qnt;

    private List<String> lista_id_imagens;

    public List<String> getLista_id_imagens() {
        return lista_id_imagens;
    }

    public void setLista_id_imagens(List<String> lista_id_imagens) {
        this.lista_id_imagens = lista_id_imagens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public String getConcentracao() {
        return concentracao;
    }

    public void setConcentracao(String concentracao) {
        this.concentracao = concentracao;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public int getRegistroAnvisa() {
        return registroAnvisa;
    }

    public void setRegistroAnvisa(int registroAnvisa) {
        this.registroAnvisa = registroAnvisa;
    }

    public String getDetentorRegistro() {
        return detentorRegistro;
    }

    public void setDetentorRegistro(String detentorRegistro) {
        this.detentorRegistro = detentorRegistro;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }


}
