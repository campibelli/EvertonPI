/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BR.EDU.IMEPAC.DAOS;

import BR.EDU.IMEPAC.UTILS.DatabaseConfig;
import BR.EDU.IMEPAC.ENTIDADES.Atendente;
import br.edu.imepac.message.manager.interfaces.daodao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AtendenteDAO implements daodao<Atendente> {
    private Connection connection;

    private void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password);
    }

    @Override
    public int save(Atendente person) throws SQLException {
        this.createConnection();
        String sql = "insert into contacts(name, email) values(?,?)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setString(1, person.getNome());
        preparedStatement.setString(2, person.getEmail());
        int result = preparedStatement.executeUpdate();
        this.destroyConnection();
        return result;
    }

    @Override
    public Contact read(Long id) throws SQLException {
        this.createConnection();
        String sql = "select * from contacts where id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Contact person = null;
        if (resultSet.next()) {
            person = new Contact(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email"));
        }
        this.destroyConnection();
        return person;
    }

    @Override
    public int delete(Long id) throws SQLException {
        this.createConnection();
        String sql = "delete from contacts where id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        int result = preparedStatement.executeUpdate();
        this.destroyConnection();
        return result;
    }

    @Override
    public int update(Contact person) throws SQLException {
        this.createConnection();
        String sql = "update contacts set name = ?, email = ? where id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getEmail());
        preparedStatement.setLong(3, person.getId());
        int result = preparedStatement.executeUpdate();
        this.destroyConnection();
        return result;
    }

    @Override
    public ArrayList<Atendente> findAll() throws SQLException {
        this.createConnection();
        String sql = "select * from contacts";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Atendente> persons = new ArrayList<>();
        while (resultSet.next()) {
            persons.add(new Contact(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email")));
        }
        this.destroyConnection();
        return persons;
    }

    private void destroyConnection() throws SQLException {
        this.connection.close();
    }
}
