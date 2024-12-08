package tech.reliab.course.toropchida.springlabyurov.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.toropchida.springlabyurov.entity.*;
import tech.reliab.course.toropchida.springlabyurov.repository.*;
import tech.reliab.course.toropchida.springlabyurov.service.BankService;
import tech.reliab.course.toropchida.springlabyurov.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;
import static tech.reliab.course.toropchida.springlabyurov.utils.Utils.getNullPropertyNames;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {
    public final BankRepository bankRepository;
    public final BankAtmRepository bankAtmRepository;
    public final BankOfficeRepository bankOfficeRepository;
    public final EmployeeRepository employeeRepository;
    public final BankUserRepository bankUserRepository;

    @Override
    public Bank createBank(Bank bank) {
        bank.setNumberOffices(0);
        bank.setNumberAtms(0);
        bank.setNumberEmployees(0);
        bank.setNumberUsers(0);
        bank.setTotalMoney(Utils.getRandomFromAToB(1, 1000000));

        int bankRating = Utils.getRandomFromAToB(0, 100);
        bank.setBankRating(Utils.getRandomFromAToB(0, 100));

        float interestRate =  Utils.getRandomFromAToB(0, 20);
        if (bankRating > 80) {
            interestRate *= 0.5F;
        } else if (bankRating > 60) {
            interestRate *= 0.7F;
        } else if (bankRating > 40) {
            interestRate *= 0.9F;
        }
        bank.setInterestRate(interestRate);

        return bankRepository.save(bank);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Optional<Bank> getBank(Long id) {
        return bankRepository.findById(id);
    }

    @Override
    public Bank updateBank(Long id, Bank bankDetails) {
        Optional<Bank> bank = bankRepository.findById(id);

        if (bank.isPresent()) {
            Bank existingIntern = bank.get();
            String[] ignore = getNullPropertyNames(existingIntern);
            copyProperties(bankDetails, existingIntern, getNullPropertyNames(bankDetails));
            System.out.println(Arrays.toString(ignore));

            existingIntern.setId(bankDetails.getId());
            return bankRepository.save(existingIntern);
        }

        return null;
    }

    @Override
    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }

    @Override
    public void deleteAllBanks() {
        bankRepository.deleteAll();
    }

    @Override
    public void outputAllBankInfo(Long id) {
        Optional<Bank> bank = getBank(id);
        if (bank.isPresent()) {
            List<BankUser> bankUsers = bankUserRepository.getBankUserByBankName(bank.get().getName());
            List<BankOffice> bankOffices = bankOfficeRepository.getBankOfficeByBankId(id);
            List<BankAtm> bankAtms = bankAtmRepository.getBankAtmByBankId(id);
            List<Employee> employees = employeeRepository.getEmployeeByBankId(id);

            System.out.println("Bank: " + bank.get());

            System.out.println("Users:");
            for (BankUser user : bankUsers) {
                System.out.println("\t" + user);
            }

            System.out.println("Offices:");
            for (BankOffice office : bankOffices) {
                System.out.println("\t" + office);
            }

            System.out.println("BankAtms:");
            for (BankAtm bankAtm : bankAtms) {
                System.out.println("\t" + bankAtm);
            }

            System.out.println("Employees:");
            for (Employee employee : employees) {
                System.out.println("\t" + employee);
            }
        } else {
            System.out.println("NOT-FOUND");
        }
    }
}