package br.com.starlog.model;

/** Entidade Produto do Almoxarifado StarLog, com controle de invariantes. */
public class Produto {
    private Long id;
    private String nome;
    private double preco;
    private int estoque;

    /** Construtor de persistência (com id). */
    public Produto(Long id, String nome, double preco, int estoque) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome deve ter ao menos 3 caracteres.");
        }
        validarPreco(preco);
        validarEstoque(estoque);
        this.id = id;
        this.nome = nome.trim();
        this.preco = preco;
        this.estoque = estoque;
    }

    /** Construtor de negócio (sem id). */
    public Produto(String nome, double preco, int estoque) {
        this(null, nome, preco, estoque);
    }

    private static void validarPreco(double preco) {
        if (!(preco > 0.0)) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
    }

    private static void validarEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("O estoque não pode ser negativo.");
        }
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        validarPreco(preco);
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        validarEstoque(estoque);
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return String.format("Produto{id=%d, nome='%s', preco=R$ %.2f, estoque=%d}", id, nome, preco, estoque);
    }
}
