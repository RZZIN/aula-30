package br.com.starlog.main;

import br.com.starlog.dao.ProdutoDAO;
import br.com.starlog.dao.ProdutoDAOSqlite;
import br.com.starlog.model.Produto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** Simulação de negócio: ciclo CRUD completo do Almoxarifado StarLog. */
public class Main {

    public static void main(String[] args) throws IOException {
        Files.deleteIfExists(Path.of("almoxarifado.db"));
        ProdutoDAO dao = new ProdutoDAOSqlite();

        System.out.println("=== [Create] ===");
        Produto teclado = new Produto("Teclado Mecânico RGB", 349.90, 8);
        Produto mousepad = new Produto("Mousepad Speed XL", 59.90, 25);
        dao.salvar(teclado);
        dao.salvar(mousepad);
        System.out.println("IDs gerados: teclado=" + teclado.getId() + ", mousepad=" + mousepad.getId());

        System.out.println("\n=== [Read] ===");
        imprimir(dao.listarTodos());

        System.out.println("\n=== [Update] ===");
        teclado.setEstoque(15);
        teclado.setPreco(289.90);
        boolean atualizou = dao.atualizar(teclado);
        System.out.println("atualizar() retornou: " + atualizou);
        dao.buscarPorId(teclado.getId()).ifPresent(p -> System.out.println("Registro atualizado: " + p));

        System.out.println("\n=== [Delete] ===");
        System.out.println("excluir(mousepad) -> " + dao.excluir(mousepad.getId()));
        boolean existia = dao.excluir(888L);
        System.out.println("excluir(888) -> " + existia
                + (existia ? "" : "  (ID inexistente: tratado sem travar o sistema)"));

        System.out.println("\n=== [Final State] ===");
        List<Produto> finais = dao.listarTodos();
        imprimir(finais);
        System.out.println("Restou(aram) " + finais.size() + " produto(s) no banco.");
    }

    private static void imprimir(List<Produto> lista) {
        System.out.printf("%-4s | %-24s | %10s | %7s%n", "ID", "NOME", "PREÇO", "ESTOQUE");
        System.out.println("-".repeat(54));
        for (Produto p : lista) {
            System.out.printf("%-4d | %-24s | R$ %7.2f | %7d%n", p.getId(), p.getNome(), p.getPreco(), p.getEstoque());
        }
    }
}
