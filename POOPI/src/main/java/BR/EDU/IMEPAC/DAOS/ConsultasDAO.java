package BR.EDU.IMEPAC.DAOS;

import BR.EDU.IMEPAC.UTILS.configdatabase;
import BR.EDU.IMEPAC.INTERFACE.idatabasecrudk;
import BR.EDU.IMEPAC.ENTIDADES.Consultas;

import java.sql.*;
import java.util.ArrayList;

public class ConsultasDAO implements idatabasecrudk<Consultas> {
    private Connection connection;

    private void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(configdatabase.url, configdatabase.user, configdatabase.password);
    }

    @Override
    public int save(Consultas consulta) throws SQLException {
        this.createConnection();
        String sql = "INSERT INTO consultas(nome_paciente, tipo_consulta, horario) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, consulta.getPatientName());
            preparedStatement.setString(2, consulta.getDoctorSpecialty());
            preparedStatement.setString(3, consulta.getDate());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating consultation failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    consulta.setId(generatedKeys.getLong(1));
                } else {
                    System.out.println("ID not obtained for the consultation.");
                }
            }

            return affectedRows;
        } finally {
            this.destroyConnection();
        }
    }

    @Override
    public Consultas read(Long id) throws SQLException {
        this.createConnection();
        String sql = "SELECT * FROM consultas WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? new Consultas(
                        resultSet.getLong("id"),
                        resultSet.getString("nomePaciente"),
                        resultSet.getString("tipo_consulta"),
                        resultSet.getString("horario")
                ) : null;
            }
        } finally {
            this.destroyConnection();
        }
    }
    
     @Override
    public ArrayList<Consultas> findAll() throws SQLException {
        this.createConnection();
        String sql = "SELECT * FROM consultas";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            ArrayList<Consultas> consultasList = new ArrayList<>();
            while (resultSet.next()) {
                consultasList.add(new Consultas(
                        resultSet.getLong("id"),
                        resultSet.getString("patient_name"),
                        resultSet.getString("doctor_specialty"),
                        resultSet.getString("date")
                ));
            }
            return consultasList;
        } finally {
            this.destroyConnection();
        }
    }
    
    @Override
public int delete(Long id) throws SQLException {
    this.createConnection();
    String sql = "DELETE FROM consultas WHERE id = ?";
    try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate();
    } finally {
        this.destroyConnection();
    }
}
    
    @Override
    public int update(Consultas consulta) throws SQLException {
        this.createConnection();
        String sql = "UPDATE consultas SET nome_paciente = ?, tipo_consulta = ?, horario = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, consulta.getPatientName());
            preparedStatement.setString(2, consulta.getDoctorSpecialty());
            preparedStatement.setString(3, consulta.getDate());
            preparedStatement.setLong(4, consulta.getId());
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