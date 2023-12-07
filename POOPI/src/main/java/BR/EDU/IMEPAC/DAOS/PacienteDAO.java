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
        String sql = "INSERT INTO pacientes(nome, cpf, rg, data_nascimento, registro_paciente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getCpf());
            preparedStatement.setString(3, paciente.getRg());
            preparedStatement.setString(4, paciente.getData_nascimento());
            preparedStatement.setInt(5, paciente.getRegistro_paciente());
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
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("rg"),
                        resultSet.getString("data_nascimento"),
                        resultSet.getInt("registro_paciente")
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
        String sql = "UPDATE pacientes SET nome = ?, cpf = ?, rg = ?, data_nascimento = ?, registro_paciente = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getCpf());
            preparedStatement.setString(3, paciente.getRg());
            preparedStatement.setString(4, paciente.getData_nascimento());
            preparedStatement.setInt(5, paciente.getRegistro_paciente());
            preparedStatement.setLong(6, paciente.getId());
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
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("rg"),
                        resultSet.getString("data_nascimento"),
                        resultSet.getInt("registro_paciente")
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