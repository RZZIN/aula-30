package br.com.starlog.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Fábrica de conexões JDBC com o arquivo SQLite local almoxarifado.db. */
public final class ConexaoFactory {

    private static final String URL = "jdbc:sqlite:almoxarifado.db";

    private ConexaoFactory() {
        throw new UnsupportedOperationException("ConexaoFactory é utilitária!");
    }

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
