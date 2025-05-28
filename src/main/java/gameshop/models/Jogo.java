package gameshop.models;

import gameshop.models.enums.EnumPlataforma;
import gameshop.models.enums.EnumControle;

import gameshop.models.Avaliacao;

import java.util.ArrayList;

public class Jogo {
    private int id;
    private String nome;
    private EnumPlataforma[] plataformas;
    private EnumControle[] controles;
    private ArrayList<Avaliacao> avaliacao;

    public Jogo(int id, String nome, EnumPlataforma[] plataformas, EnumControle[] controles, ArrayList<Avaliacao> avaliacao) {
        this.id = id;
        this.nome = nome;
        this.plataformas = plataformas;
        this.controles = controles;
        
        this.avaliacao = new ArrayList<Avaliacao>();
        if (avaliacao != null) {
            for (Avaliacao a : avaliacao) {
                this.avaliacao.add(a);
            }
        }
    }
}