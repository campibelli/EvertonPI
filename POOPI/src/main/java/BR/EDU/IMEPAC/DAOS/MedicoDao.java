package BR.EDU.IMEPAC.DAOS;

import BR.EDU.IMEPAC.UTILS.configdatabase;
import BR.EDU.IMEPAC.INTERFACE.idatabasecrudk;
import BR.EDU.IMEPAC.ENTIDADES.Medicos;

import java.sql.*;
import java.util.ArrayList;

public class MedicoDAO implements idatabasecrudk<Medicos> {
    private Connection connection;

    private void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(configdatabase.url, configdatabase.user, configdatabase.password);
    }

    @Override
    public int save(Medicos medico) throws SQLException {
        this.createConnection();
        String sql = "INSERT INTO medicos(nome, cpf, CRM, data_nascimento, convenio) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setString(2, medico.getCpf());
            preparedStatement.setString(3, medico.getCRM());
            preparedStatement.setString(4, medico.getData_nascimento());
            preparedStatement.setBoolean(5, medico.getConvenio());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating doctor failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    medico.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating doctor failed, no ID obtained.");
                }
            }

            return affectedRows;
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public Medicos read(Long id) throws SQLException {
        this.createConnection();
        String sql = "SELECT * FROM medicos WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? new Medicos(
                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("CRM"),
                        resultSet.getString("data_nascimento"),
                        resultSet.getBoolean("convenio")
                ) : null;
            }
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public int delete(Long id) throws SQLException {
        this.createConnection();
        String sql = "DELETE FROM medicos WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate();
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public int update(Medicos medico) throws SQLException {
        this.createConnection();
        String sql = "UPDATE medicos SET nome = ?, cpf = ?, CRM = ?, data_nascimento = ?, convenio = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setString(2, medico.getCpf());
            preparedStatement.setString(3, medico.getCRM());
            preparedStatement.setString(4, medico.getData_nascimento());
            preparedStatement.setBoolean(5, medico.getConvenio());
            preparedStatement.setLong(6, medico.getId());
            return preparedStatement.executeUpdate();
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public ArrayList<Medicos> findAll() throws SQLException {
        this.createConnection();
        String sql = "SELECT * FROM medicos";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            ArrayList<Medicos> medicosList = new ArrayList<>();
            while (resultSet.next()) {
                medicosList.add(new Medicos(
                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("CRM"),
                        resultSet.getString("data_nascimento"),
                        resultSet.getBoolean("convenio")
                ));
            }
            return medicosList;
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