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
        String sql = "INSERT INTO pacientes(cpf, nome, data_nascimento, endereco, historico_medico, tipo_sanguineo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, paciente.getCpf());
            preparedStatement.setString(2, paciente.getNome());
            preparedStatement.setString(3, paciente.getDataNascimento());
            preparedStatement.setString(4, paciente.getEndereco());
            preparedStatement.setString(5, paciente.getHistoricoMedico());
            preparedStatement.setString(6, paciente.getTipoSanguineo());
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
                        resultSet.getString("data_nascimento"),
                        resultSet.getString("endereco"),
                        resultSet.getString("historico_medico"),
                        resultSet.getString("tipo_sanguineo")
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
        String sql = "UPDATE pacientes SET cpf = ?, nome = ?, data_nascimento = ?, endereco = ?, historico_medico = ?, tipo_sanguineo = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, paciente.getCpf());
            preparedStatement.setString(2, paciente.getNome());
            preparedStatement.setString(3, paciente.getDataNascimento());
            preparedStatement.setString(4, paciente.getEndereco());
            preparedStatement.setString(5, paciente.getHistoricoMedico());
            preparedStatement.setString(6, paciente.getTipoSanguineo());
            preparedStatement.setLong(7, paciente.getId());
            return preparedStatement.executeUpdate();
        } finally {
            this.destroyConnection();
        }
    }
    
    public Pacientes findByName(String name) throws SQLException {
    this.createConnection();

    String sql = "SELECT * FROM pacientes WHERE nome = ?";
    
    try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
        preparedStatement.setString(1, name);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next() ? new Pacientes(
                    resultSet.getLong("id"),
                    resultSet.getString("cpf"),
                    resultSet.getString("nome"),
                    resultSet.getString("data_nascimento"),
                    resultSet.getString("endereco"),
                    resultSet.getString("historico_medico"),
                    resultSet.getString("tipo_sanguineo")
            ) : null;
        }
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
                        resultSet.getString("data_nascimento"),
                        resultSet.getString("endereco"),
                        resultSet.getString("historico_medico"),
                        resultSet.getString("tipo_sanguineo")
                ));
            }
            return pacientesList;
        } finally {
            this.destroyConnection();
        }
    }

    public int deleteByNameAndCpf(String nome, String cpf) throws SQLException {
        this.createConnection();
        String sql = "DELETE FROM pacientes WHERE nome = ? AND cpf = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cpf);
            return preparedStatement.executeUpdate();
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