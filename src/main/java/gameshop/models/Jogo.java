package gameshop.models;

import gameshop.models.enums.EnumPlataforma;
import gameshop.models.enums.EnumControle;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Jogo {
    @JsonProperty("id")
    private int id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("plataformas")
    private EnumPlataforma[] plataformas;

    @JsonProperty("controles")
    private EnumControle[] controles;

    @JsonProperty("avaliacoes")
    private ArrayList<String> idAvaliacoes;

    @JsonProperty("descricao_biblioteca")
    private DescricaoJogoBiblioteca[] descricaoBiblioteca;

    @JsonProperty("descricao_catalogo")
    private DescricaoJogoCatalogo[] descricaoCatalogo;

    @JsonProperty("conquistas")
    private Conquista[] conquistas;

    public Jogo() {
    }

    public Jogo(int id, String nome, EnumPlataforma[] plataformas, EnumControle[] controles, ArrayList<String> idAvaliacoes) {
        this.id = id;
        this.nome = nome;
        this.plataformas = plataformas;
        this.controles = controles;
        this.idAvaliacoes = idAvaliacoes;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String [] getPlataformas() {
        String[] plataformas = new String[this.plataformas.length];
        for (int i = 0; i < this.plataformas.length; i++) {
            plataformas[i] = this.plataformas[i].getNome();
        }
        
        return plataformas;
    }

    public String[] getControles() {
        String[] controles = new String[this.controles.length];
        for (int i = 0; i < this.controles.length; i++) {
            controles[i] = this.controles[i].getNome();
        }
        
        return controles;
    }

    public ArrayList<String> getIdAvaliacoes() {
        return idAvaliacoes;
    }
}