package by.alex.kasperovich.dao;

import by.alex.kasperovich.entity.Phone;

import java.util.List;

public interface PhoneDao {
    int createPhone(Phone phone);

    Phone readPhoneById(int id);

    List<Phone> readAllPhones();

    void updatePhone(Phone phoneUpdated);

    void deletePhoneById(int id);
}
