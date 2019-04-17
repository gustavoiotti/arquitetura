package br.edu.utfpr.dao;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.dto.PaisDTO;
import lombok.extern.java.Log;

@Log
public class PaisDAO {

    // Responsável por criar a tabela País no banco
    public PaisDAO() {
        //try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {
        try (Connection conn = new ConnectionF().getConnection()) {

            log.info("Criando tabela pais ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE pais (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_pais_pk PRIMARY KEY," +
						"nome varchar(255)," +
						"sigla varchar(3)," + 
						"codigoTelefone int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean add(PaisDTO pais) {

        try (Connection conn = new ConnectionF().getConnection()) {

            String sql = "INSERT INTO pais (nome, sigla, codigoTelefone) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, pais.getNome());
            preparedStatement.setString(2, pais.getSigla());
            preparedStatement.setInt(3, pais.getCodigoTelefone());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                log.info("País ok");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return true;
    }

    public List<PaisDTO> listar() {
        List<PaisDTO> paises = new ArrayList<>();

        try (Connection conn = new ConnectionF().getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM pais");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PaisDTO pais = PaisDTO.builder()
                        .id(resultSet.getInt("id"))
                        .nome(resultSet.getString("nome"))
                        .sigla(resultSet.getString("telefone"))
                        .codigoTelefone(resultSet.getInt("idade"))
                        .cliente(ClienteDTO.builder().id(resultSet.getInt("id_cliente")).build()).build();
                paises.add(pais);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return paises;
    }

    public boolean delete(int idPais) {

        try (Connection conn = new ConnectionF().getConnection()) {

            String sql = "DELETE FROM pais WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idPais);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                log.info("País excluído!!!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return true;
    }
}