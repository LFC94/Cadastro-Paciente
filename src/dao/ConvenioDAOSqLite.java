package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexao.SqLite;
import interfaces.InterfaceConvenio;
import objetos.Convenio;

public class ConvenioDAOSqLite implements InterfaceConvenio {

    @Override
    public void criarBanco() throws Exception {
        String ret = "CREATE TABLE IF NOT EXISTS convenio (";
        ret += "id INTEGER PRIMARY KEY, nome TEXT";
        ret += ")";
        try {
            Connection conect = SqLite.connectar();
            PreparedStatement statement = conect.prepareStatement(ret);
            statement.execute();
            statement.close();
            conect.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }

    }

    @Override
    public void cadastra(Convenio convenio) throws Exception {
        try {
            Connection conect = SqLite.connectar();
            PreparedStatement statement;
            if (convenio.getId() <= 0) {
                String sql = "INSERT INTO convenio (nome)"
                        + "values(?);";
                statement = conect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, convenio.getNome());
            } else {
                String sql = "UPDATE convenio SET nome = ? WHERE id = ?;";
                statement = conect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, convenio.getNome());
                statement.setInt(2, convenio.getId());
            }
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (convenio.getId() <= 0 && rs.next()) {
                convenio.setId(rs.getInt(1));
            }
            statement.close();
            conect.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void remove(Convenio convenio) throws Exception {
        try {
            Connection conect = SqLite.connectar();
            String sql = "DELETE FROM convenio WHERE nome = " + convenio.getId();
            PreparedStatement statement = conect.prepareStatement(sql);
            statement.execute();
            statement.close();
            conect.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public ArrayList<Convenio> busca(String nome) throws Exception {

        try {
            Connection conect = SqLite.connectar();
            ArrayList<Convenio> convenios = new ArrayList<Convenio>();
            String sql = "SELECT * FROM convenio WHERE nome LIKE '" + nome + "%'";
            PreparedStatement statement = conect.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Convenio co = new Convenio();
                co.setId(resultSet.getInt("id"));
                co.setNome(resultSet.getString("nome"));
                convenios.add(co);
            }
            statement.close();
            conect.close();
            return convenios;
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public Convenio busca(int id) throws Exception {
        try {
            Connection conect = SqLite.connectar();
            Convenio convenios = new Convenio();
            String sql = "SELECT * FROM convenio WHERE id = " + String.valueOf(id);
            PreparedStatement statement = conect.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                convenios.setId(resultSet.getInt("id"));
                convenios.setNome(resultSet.getString("nome"));
            }
            statement.close();
            conect.close();
            return convenios;
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

}
