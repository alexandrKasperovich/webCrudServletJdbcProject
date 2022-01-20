package by.alex.kasperovich.dao;

import by.alex.kasperovich.entity.Address;

import java.util.List;

public interface AddressDao {
    long createAddress(Address address);

    Address readAddressById(long id);

    List<Address> readAllAddresses();

    void updateAddress(Address addressUpdated);

    void deleteAddressById(long id);
}
