package by.alex.kasperovich.dao;

import by.alex.kasperovich.entity.Address;
import by.alex.kasperovich.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AddressJdbcDao implements AddressDao {

    @Override
    public long createAddress(Address address) {
        String country = address.getCountry();
        String city = address.getCity();
        String street = address.getStreet();
        int house = address.getHouse();
        int apartment = address.getApartment();
        //   int personIdFk = address.getPersonIdFk();

        String sql = "INSERT INTO `my_datebase`.`address` " +
                "(`country`, `city`, `street`, `house`, `apartment`) VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = MysqlJdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, country);
            preparedStatement.setString(2, city);
            preparedStatement.setString(3, street);
            preparedStatement.setInt(4, house);
            preparedStatement.setInt(5, apartment);

//            preparedStatement.execute();
            int executeUpdate = preparedStatement.executeUpdate();
            if (executeUpdate > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long generatedId = generatedKeys.getLong(1);
                        return generatedId;
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Address readAddressById(long id) {
        String sql = "SELECT * FROM my_datebase.address WHERE (`id_address` = ?);";

        try (Connection connection = MysqlJdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                String country = resultSet.getString(2);
                String city = resultSet.getString(3);
                String street = resultSet.getString(4);
                int house = resultSet.getInt(5);
                int apartment = resultSet.getInt(6);
                Address address = new Address(id, country, city, street, house, apartment);
                return address;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Address> readAllAddresses() {
        List<Address> addressList = new ArrayList<>();
        String sql = "SELECT * FROM my_datebase.address;";

        try (Connection connection = MysqlJdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getInt(1);
                String country = resultSet.getString(2);
                String city = resultSet.getString(3);
                String street = resultSet.getString(4);
                int house = resultSet.getInt(5);
                int apartment = resultSet.getInt(6);
                Address address = new Address(id, country, city, street, house, apartment);
                addressList.add(address);
            }
            return addressList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return addressList;
    }

    @Override
    public void updateAddress(Address addressUpdated) {
        String sql = "UPDATE `my_datebase`.`address` SET `country` = ?, " +
                "`city` = ?, `street` = ?, `house` = ?, `apartment` = ?" +
                " WHERE (`id_address` = ?);\n;";


        try (Connection connection = MysqlJdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, addressUpdated.getCountry());
            preparedStatement.setString(2, addressUpdated.getCity());
            preparedStatement.setString(3, addressUpdated.getStreet());
            preparedStatement.setInt(4, addressUpdated.getHouse());
            preparedStatement.setInt(5, addressUpdated.getApartment());
            preparedStatement.setLong(6, addressUpdated.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteAddressById(long id) {
        String sql = "DELETE FROM `my_datebase`.`address` WHERE (`id_address` = ?);";

        try (Connection connection = MysqlJdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
