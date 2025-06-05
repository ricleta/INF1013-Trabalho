package gameshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import gameshop.models.enums.EnumPlataforma;
import gameshop.models.enums.EnumControle;

import java.util.ArrayList;
import java.util.List;

public class Jogo {
    private int id;
    private String nome;

    private EnumPlataforma[] plataformas;
    private EnumControle[] controles;

    @JsonProperty("avaliacoes") // Maps JSON "avaliacoes" field to this Java field
    private ArrayList<String> idAvaliacoes;

    @JsonProperty("descricao_biblioteca") // Maps JSON "descricao_biblioteca" field to this Java field
    private List<DescricaoJogoBiblioteca> descricoesBiblioteca;

    @JsonProperty("descricao_catalogo") // Maps JSON "descricao_catalogo" field to this Java field
    private List<DescricaoJogoCatalogo> descricoesCatalogo;

    @JsonProperty("conquistas") // Maps JSON "conquistas" field to this Java field
    private List<ConquistaJogo> conquistas;

    // Default constructor for Jackson deserialization
    public Jogo() {
        this.idAvaliacoes = new ArrayList<>(); // Initialize new field
        this.descricoesBiblioteca = new ArrayList<>();
        this.descricoesCatalogo = new ArrayList<>();
        this.conquistas = new ArrayList<>();
    }

    
    public Jogo(int id, String nome, EnumPlataforma[] plataformas, EnumControle[] controles, ArrayList<String> idAvaliacoes) {
        this.id = id;
        this.nome = nome;
        this.plataformas = plataformas;
        this.controles = controles;

        this.idAvaliacoes = new ArrayList<>();
        if (idAvaliacoes != null) {
            this.idAvaliacoes.addAll(idAvaliacoes);
        }

        this.descricoesBiblioteca = new ArrayList<>();
        this.descricoesCatalogo = new ArrayList<>();
        this.conquistas = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnumPlataforma[] getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(EnumPlataforma[] plataformas) {
        this.plataformas = plataformas;
    }

    public EnumControle[] getControles() {
        return controles;
    }

    public void setControles(EnumControle[] controles) {
        this.controles = controles;
    }

    public ArrayList<String> getIdAvaliacoes() {
        return idAvaliacoes;
    }

    public void setIdAvaliacoes(ArrayList<String> idAvaliacoes) {
        this.idAvaliacoes = idAvaliacoes;
    }

    public List<DescricaoJogoBiblioteca> getDescricoesBiblioteca() {
        return descricoesBiblioteca;
    }

    public void setDescricoesBiblioteca(List<DescricaoJogoBiblioteca> descricoesBiblioteca) {
        this.descricoesBiblioteca = descricoesBiblioteca;
    }

    public List<DescricaoJogoCatalogo> getDescricoesCatalogo() {
        return descricoesCatalogo;
    }

    public void setDescricoesCatalogo(List<DescricaoJogoCatalogo> descricoesCatalogo) {
        this.descricoesCatalogo = descricoesCatalogo;
    }

    public List<ConquistaJogo> getConquistas() {
        return conquistas;
    }

    public void setConquistas(List<ConquistaJogo> conquistas) {
        this.conquistas = conquistas;
    }

    /**
     * Lista todas as conquistas associadas a este jogo.
     * @return uma lista de objetos ConquistaJogo.
     */
    public List<ConquistaJogo> listarConquistas() {
        return this.conquistas;
    }

    /**
     * Obtém o texto da primeira descrição de biblioteca do jogo, se existir.
     * @return O texto da descrição da biblioteca ou null se não houver.
     */
    public String obterDescricaoBibliotecaTexto() {
        if (this.descricoesBiblioteca != null && !this.descricoesBiblioteca.isEmpty()) {
            DescricaoJogoBiblioteca desc = this.descricoesBiblioteca.get(0);
            return desc != null ? desc.getTexto() : null;
        }
        return null;
    }

    /**
     * Obtém o texto da primeira descrição de catálogo do jogo, se existir.
     * @return O texto da descrição do catálogo ou null se não houver.
     */
    public String obterDescricaoCatalogoTexto() {
        if (this.descricoesCatalogo != null && !this.descricoesCatalogo.isEmpty()) {
            DescricaoJogoCatalogo desc = this.descricoesCatalogo.get(0);
            return desc != null ? desc.getTexto() : null;
        }
        return null;
    }

    public void criarAvaliacao(int idUsuario, int nota, String comentario) {
        // TODO
    }

    /**
     * Deleta uma "avaliação" do jogo por sua representação string.
     * @param idAvaliacao A string que representa o ID da avaliação a ser removida.
     * @return true se a avaliação foi removida, false caso contrário.
     */
    public boolean deletarAvaliacao(String idAvaliacao) {
        if (this.idAvaliacoes != null && idAvaliacao != null) {
            return this.idAvaliacoes.remove(idAvaliacao);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jogo{\n");
        sb.append("  id=").append(id).append(",\n");
        sb.append("  nome='").append(nome).append("',\n");
        sb.append("  plataformas=[");
        if (plataformas != null) {
            for (int i = 0; i < plataformas.length; i++) {
                sb.append(plataformas[i].getNome());
                if (i < plataformas.length - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("],\n");
        sb.append("  controles=[");
        if (controles != null) {
            for (int i = 0; i < controles.length; i++) {
                sb.append(controles[i].getNome());
                if (i < controles.length - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("],\n");
        sb.append("  idAvaliacoes=").append(idAvaliacoes).append(",\n"); // Changed to idAvaliacoes
        sb.append("  descricoesBiblioteca=").append(descricoesBiblioteca).append(",\n");
        sb.append("  descricoesCatalogo=").append(descricoesCatalogo).append(",\n");
        sb.append("  conquistas=").append(conquistas).append("\n");
        sb.append("}");
        return sb.toString();
    }
}