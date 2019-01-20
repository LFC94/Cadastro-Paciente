package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import conexao.SqLite;
import interfaces.InterfacePaciente;
import objetos.Paciente;

public class PacienteDAOSqLite implements InterfacePaciente {

    @Override
    public void criarBanco() throws Exception {
        String ret = "CREATE TABLE IF NOT EXISTS paciente (";
        ret += "id INTEGER PRIMARY KEY, ";
        ret += "nome TEXT, mae TEXT, pai TEXT, profissao TEXT, cep TEXT, endereco TEXT, bairro TEXT, cidade TEXT, ";
        ret += "uf TEXT, telefone TEXT, dataNasc TEXT,";
        ret += "convenio INTEGER, numero INTEGER";
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
    public void cadastra(Paciente paciente) throws Exception {
        try {
            Connection conect = SqLite.connectar();
            PreparedStatement statement;
            if (paciente.getId() <= 0) {
                String sql = "INSERT INTO paciente(nome, mae, pai, profissao, cep, endereco, bairro, cidade, uf, telefone,dataNasc, convenio, numero)"
                        + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                statement = conect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, paciente.getNome());
                statement.setString(2, paciente.getMae());
                statement.setString(3, paciente.getPai());
                statement.setString(4, paciente.getProfissao());
                statement.setString(5, paciente.getCep());
                statement.setString(6, paciente.getEndereco());
                statement.setString(7, paciente.getBairro());
                statement.setString(8, paciente.getCidade());
                statement.setString(9, paciente.getUf());
                statement.setString(10, paciente.getTelefone());
                statement.setString(11, paciente.getDataNascimento());
                statement.setInt(12, paciente.getConvenio());
                statement.setString(13, paciente.getNumero());
            } else {
                String sql = "UPDATE paciente SET nome = ?, mae = ?, pai = ?, profissao = ?, cep = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, telefone = ?,"
                        + " dataNasc = ?, convenio = ?, numero = ?"
                        + " WHERE id = ?;";
                statement = conect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, paciente.getNome());
                statement.setString(2, paciente.getMae());
                statement.setString(3, paciente.getPai());
                statement.setString(4, paciente.getProfissao());
                statement.setString(5, paciente.getCep());
                statement.setString(6, paciente.getEndereco());
                statement.setString(7, paciente.getBairro());
                statement.setString(8, paciente.getCidade());
                statement.setString(9, paciente.getUf());
                statement.setString(10, paciente.getTelefone());
                statement.setString(11, paciente.getDataNascimento());
                statement.setInt(12, paciente.getConvenio());
                statement.setString(13, paciente.getNumero());
                statement.setInt(14, paciente.getId());
            }
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (paciente.getId() <= 0 && rs.next()) {
                paciente.setId(rs.getInt(1));
            }
            statement.close();
            conect.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public void remove(Paciente paciente) throws Exception {
        try {
            Connection conect = SqLite.connectar();
            String sql = "DELETE FROM paciente WHERE nome = " + paciente.getId();
            PreparedStatement statement = conect.prepareStatement(sql);
            statement.execute();
            statement.close();
            conect.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public ArrayList<Paciente> busca(String nome) throws Exception {

        try {
            Connection conect = SqLite.connectar();
            ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
            String sql = "SELECT * FROM paciente WHERE nome LIKE '" + nome + "%'";
            PreparedStatement statement = conect.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Paciente pa = new Paciente();
                pa.setId(resultSet.getInt("id"));
                pa.setNome(resultSet.getString("nome"));
                pa.setMae(resultSet.getString("mae"));
                pa.setPai(resultSet.getString("pai"));
                pa.setProfissao(resultSet.getString("profissao"));
                pa.setCep(resultSet.getString("cep"));
                pa.setEndereco(resultSet.getString("endereco"));
                pa.setBairro(resultSet.getString("bairro"));
                pa.setCidade(resultSet.getString("cidade"));
                pa.setUf(resultSet.getString("uf"));
                pa.setTelefone(resultSet.getString("telefone"));
                pa.setDataNasc(resultSet.getString("dataNasc"));
                pa.setConvenio(resultSet.getInt("convenio"));
                pa.setNumero(resultSet.getString("numero"));
                pacientes.add(pa);
            }
            statement.close();
            conect.close();
            return pacientes;
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    @Override
    public Paciente busca(int id) throws Exception {
        try {
            Connection conect = SqLite.connectar();
            Paciente paciente = new Paciente();
            String sql = "SELECT * FROM paciente WHERE id = " + String.valueOf(id);
            PreparedStatement statement = conect.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                paciente.setId(resultSet.getInt("id"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setMae(resultSet.getString("mae"));
                paciente.setPai(resultSet.getString("pai"));
                paciente.setProfissao(resultSet.getString("profissao"));
                paciente.setCep(resultSet.getString("cep"));
                paciente.setEndereco(resultSet.getString("endereco"));
                paciente.setBairro(resultSet.getString("bairro"));
                paciente.setCidade(resultSet.getString("cidade"));
                paciente.setUf(resultSet.getString("uf"));
                paciente.setTelefone(resultSet.getString("telefone"));
                paciente.setDataNasc(resultSet.getString("dataNasc"));
                paciente.setConvenio(resultSet.getInt("convenio"));
                paciente.setNumero(resultSet.getString("numero"));
            }
            statement.close();
            conect.close();
            return paciente;
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

}
