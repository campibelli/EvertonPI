package BR.EDU.IMEPAC.DAOS;

import BR.EDU.IMEPAC.UTILS.configdatabase;
import BR.EDU.IMEPAC.INTERFACE.idatabasecrudk;
import BR.EDU.IMEPAC.ENTIDADES.Atendente;

import java.sql.*;
import java.util.ArrayList;

public class AtendenteDAO implements idatabasecrudk<Atendente> {
    private Connection connection;

    private void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(configdatabase.url, configdatabase.user, configdatabase.password);
    }

    @Override
    public int save(Atendente atendente) throws SQLException {
        this.createConnection();
        String sql = "INSERT INTO contacts(nome, cpf, data_nascimento, registro_funcionario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, atendente.getNome());
            preparedStatement.setString(2, atendente.getCpf());
            preparedStatement.setString(3, atendente.getData_nascimento());
            preparedStatement.setString(4, atendente.getRegistro_funcionario());
            return preparedStatement.executeUpdate();
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public Atendente read(Long id) throws SQLException {
        this.createConnection();
        String sql = "SELECT * FROM contacts WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? new Atendente(
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("data_nascimento"),
                        resultSet.getString("registro_funcionario")
                ) : null;
            }
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public int delete(Long id) throws SQLException {
        this.createConnection();
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate();
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public int update(Atendente atendente) throws SQLException {
        this.createConnection();
        String sql = "UPDATE contacts SET nome = ?, cpf = ?, data_nascimento = ?, registro_funcionario = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, atendente.getNome());
            preparedStatement.setString(2, atendente.getCpf());
            preparedStatement.setString(3, atendente.getData_nascimento());
            preparedStatement.setString(4, atendente.getRegistro_funcionario());
            preparedStatement.setLong(5, atendente.getId());
            return preparedStatement.executeUpdate();
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public ArrayList<Atendente> findAll() throws SQLException {
        this.createConnection();
        String sql = "SELECT * FROM contacts";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            ArrayList<Atendente> atendentes = new ArrayList<>();
            while (resultSet.next()) {
                atendentes.add(new Atendente(
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("data_nascimento"),
                        resultSet.getString("registro_funcionario")
                ));
            }
            return atendentes;
        } finally {
            this.destroyConnection();
        }
    }

    private void destroyConnection() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }
}