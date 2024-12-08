package tech.reliab.course.toropchida.springlabyurov.service;

import tech.reliab.course.toropchida.springlabyurov.entity.BankUser;

import java.util.List;
import java.util.Optional;

public interface BankUserService {
    BankUser createUser(BankUser bank);

    List<BankUser> getAllUsers();

    Optional<BankUser> getUser(Long id);

    BankUser updateUser(Long id, BankUser bankUserDetail);

    void deleteUser(Long id);

    void deleteAllUsers();

    void outputAllUserInfo(Long id);
}