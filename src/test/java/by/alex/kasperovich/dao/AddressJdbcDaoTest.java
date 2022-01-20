package by.alex.kasperovich.dao;


import by.alex.kasperovich.entity.Address;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressJdbcDaoTest {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/my_datebase";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "password";

    @Test
    public void createAddressTest() {
        try {
            long generatedId = createAddress();
            if (generatedId <= 0) {
                Assert.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    private long createAddress() {
        Address address = new Address("RU", "Mockau", "Koljbok", 122, 300);
        return createAddress(address);
    }

    private long createAddress(Address address) {
        AddressJdbcDao addressJdbcDao = new AddressJdbcDao();
        long generatedId = addressJdbcDao.createAddress(address);
        return generatedId;
    }

    @Test
    public void deleteAddressTest() {
        long generatedId = createAddress();
        if (generatedId > 0) {
            AddressJdbcDao addressJdbcDao = new AddressJdbcDao();
            addressJdbcDao.deleteAddressById(generatedId);
            Address address = addressJdbcDao.readAddressById(generatedId);
            if (address != null && address.getId() == generatedId) {
                Assert.fail();
            }
        }
    }

    @Test
    public void readAllAddressesTest() {
        createAddress();
        createAddress();
        createAddress();
        AddressJdbcDao addressJdbcDao = new AddressJdbcDao();
        List<Address> addresses = addressJdbcDao.readAllAddresses();
        if (addresses.size() <= 2) {
            Assert.fail();
        }
    }

    @Test
    public void readAddressesByIdTest() {
        Address address = new Address("XXXX", "ZZZ", "YYY", 11111, 2222);
        long generatedId = createAddress(address);
        AddressJdbcDao addressJdbcDao = new AddressJdbcDao();
        Address readedAddress = addressJdbcDao.readAddressById(generatedId);
        if (readedAddress != null) {
            if (!(readedAddress.getCountry().equals(address.getCountry()) &&
                    readedAddress.getCity().equals(address.getCity()) &&
                    readedAddress.getStreet().equals(address.getStreet()) &&
                    readedAddress.getHouse() == address.getHouse() &&
                    readedAddress.getApartment() == address.getApartment())) {

                Assert.fail();
            }
        } else {
            Assert.fail();
        }
    }

    @Test
    public void updateAddressTest() {
        long generatedId = createAddress();
        if (generatedId > 0) {
            Address address = new Address(generatedId, "NEW_COUNTRY", "NEW_CITY", "NEW_STREET", 999, 99);

            AddressJdbcDao addressJdbcDao = new AddressJdbcDao();
            addressJdbcDao.updateAddress(address);

            Address updatedAddress = addressJdbcDao.readAddressById(generatedId);
            if (updatedAddress != null) {
                if (!(updatedAddress.getCountry().equals(address.getCountry()) &&
                        updatedAddress.getCity().equals(address.getCity()) &&
                        updatedAddress.getStreet().equals(address.getStreet()) &&
                        updatedAddress.getHouse() == address.getHouse() &&
                        updatedAddress.getApartment() == address.getApartment())) {

                    Assert.fail();
                }
            } else {
                Assert.fail();
            }
        }
    }


}