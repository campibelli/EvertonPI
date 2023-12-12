package BR.EDU.IMEPAC.DAOS;

import BR.EDU.IMEPAC.UTILS.configdatabase;
import BR.EDU.IMEPAC.INTERFACE.idatabasecrudk;
import BR.EDU.IMEPAC.ENTIDADES.Pacientes;

import java.sql.*;
import java.util.ArrayList;

public class PacienteDAO implements idatabasecrudk<Pacientes> {
    private Connection connection;

    private void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(configdatabase.url, configdatabase.user, configdatabase.password);
    }

    @Override
    public int save(Pacientes paciente) throws SQLException {
        this.createConnection();
        String sql = "INSERT INTO pacientes(cpf, nome, endereco, historico_medico) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, paciente.getCpf());
            preparedStatement.setString(2, paciente.getNome());
            preparedStatement.setString(3, paciente.getEndereco());
            preparedStatement.setString(4, paciente.getHistoricoMedico());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating patient failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    paciente.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating patient failed, no ID obtained.");
                }
            }

            return affectedRows;
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public Pacientes read(Long id) throws SQLException {
        this.createConnection();
        String sql = "SELECT * FROM pacientes WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? new Pacientes(
                        resultSet.getLong("id"),
                        resultSet.getString("cpf"),
                        resultSet.getString("nome"),
                        resultSet.getString("endereco"),
                        resultSet.getString("historico_medico")
                ) : null;
            }
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public int delete(Long id) throws SQLException {
        this.createConnection();
        String sql = "DELETE FROM pacientes WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate();
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public int update(Pacientes paciente) throws SQLException {
        this.createConnection();
        String sql = "UPDATE pacientes SET cpf = ?, nome = ?, endereco = ?, historico_medico = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, paciente.getCpf());
            preparedStatement.setString(2, paciente.getNome());
            preparedStatement.setString(3, paciente.getEndereco());
            preparedStatement.setString(4, paciente.getHistoricoMedico());
            preparedStatement.setLong(5, paciente.getId());
            return preparedStatement.executeUpdate();
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public ArrayList<Pacientes> findAll() throws SQLException {
        this.createConnection();
        String sql = "SELECT * FROM pacientes";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            ArrayList<Pacientes> pacientesList = new ArrayList<>();
            while (resultSet.next()) {
                pacientesList.add(new Pacientes(
                        resultSet.getLong("id"),
                        resultSet.getString("cpf"),
                        resultSet.getString("nome"),
                        resultSet.getString("endereco"),
                        resultSet.getString("historico_medico")
                ));
            }
            return pacientesList;
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