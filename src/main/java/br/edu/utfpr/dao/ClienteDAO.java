package br.edu.utfpr.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.dto.ConnectionF;

import lombok.Builder;
import lombok.extern.java.Log;

@Log
public class ClienteDAO {

    // Responsável por criar a tabela Cliente no banco.
    public ClienteDAO() {

        //try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {
        try (Connection conn = new ConnectionF().getConnection()) {

            log.info("Criando tabela cliente");
            conn.createStatement().executeUpdate(
                    "CREATE TABLE cliente (" +
                            "id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_cliente_pk PRIMARY KEY," +
                            "nome varchar(255)," +
                            "telefone varchar(30)," +
                            "idade int," +
                            "limiteCredito double," +
                            "id_pais int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean add(ClienteDTO cliente) {

        try (Connection conn = new ConnectionF().getConnection()) {

            String sql = "INSERT INTO cliente (nome, telefone, idade, limiteCredito, id_pais) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getTelefone());
            preparedStatement.setInt(3, cliente.getIdade());
            preparedStatement.setDouble(4, cliente.getLimiteCredito());
            preparedStatement.setInt(5, (cliente.getPais());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                log.info("Cliente incluído com sucesso!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return true;
    }

    public ClienteDTO pesquisaNome(String nome) {
        ClienteDTO cliente = null;

        try (Connection conn = new ConnectionF().getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM cliente WHERE nome like '%" + nome + "%'");
            ResultSet resultSet = preparedStatement.executeQuery();

            cliente = ClienteDTO.builder()
                    .id(resultSet.getInt("id"))
                    .nome(resultSet.getString("nome"))
                    .telefone(resultSet.getString("telefone"))
                    .idade(resultSet.getInt("idade"))
                    .limiteCredito(resultSet.getDouble("limiteCredito"))
                    .pais(PaisDTO.builder().id(resultSet.getInt("id_pais")).build()).build();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cliente;
    }

    public List<ClienteDTO> listar() {
        List<ClienteDTO> clientes = new ArrayList<>();

        try (Connection conn = new ConnectionF().getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM cliente");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ClienteDTO cliente = ClienteDTO.builder()
                        .id(resultSet.getInt("id"))
                        .nome(resultSet.getString("nome"))
                        .telefone(resultSet.getString("telefone"))
                        .idade(resultSet.getInt("idade"))
                        .limiteCredito(resultSet.getDouble("limiteCredito"))
                        .pais(PaisDTO.builder().id(resultSet.getInt("id_pais")).build()).build();
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return clientes;
    }

    public boolean update(ClienteDTO cliente) {
        boolean success = false;

        try (Connection conn = new ConnectionF().getConnection()) {

            String sql = "UPDATE cliente SET nome = ?, telefone = ?, idade = ?, limiteCredito = ?, id_pais = ? WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getTelefone());
            statement.setInt(3, cliente.getIdade());
            statement.setDouble(4, cliente.getLimiteCredito());
            statement.setInt(5, (cliente.getPais());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                log.info("Cliente alterado!!!");
                success = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return success;
    }

    public boolean delete(int idCliente) {
        boolean success = false;

        try (Connection conn = new ConnectionF().getConnection()) {

            String sql = "DELETE FROM cliente WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idCliente);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                log.info("Usuário excluído!!!");
                success = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return success;
    }

}