package tech.reliab.course.toropchida.springlabyurov.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.toropchida.springlabyurov.entity.CreditAccount;
import tech.reliab.course.toropchida.springlabyurov.repository.BankRepository;
import tech.reliab.course.toropchida.springlabyurov.repository.BankUserRepository;
import tech.reliab.course.toropchida.springlabyurov.repository.CreditAccountRepository;
import tech.reliab.course.toropchida.springlabyurov.service.CreditAccountService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;
import static tech.reliab.course.toropchida.springlabyurov.utils.Utils.getNullPropertyNames;

@Service
@AllArgsConstructor
public class CreditAccountServiceImpl implements CreditAccountService {
    public final CreditAccountRepository creditAccountRepository;
    public final BankRepository bankRepository;
    public final BankUserRepository bankUserRepository;

    public CreditAccount createCreditAccount(CreditAccount creditAccount) {
        creditAccount.setInterestRate(bankRepository.getInterestRateByBankName(creditAccount.getBankName()));

        CreditAccount ca = creditAccountRepository.save(creditAccount);

        bankUserRepository.setCreditAccountIdByUserId(ca.getId(), ca.getUserId());

        return ca;
    }

    public List<CreditAccount> getAllCreditAccounts() { return creditAccountRepository.findAll(); }

    public Optional<CreditAccount> getCreditAccount(Long id) { return creditAccountRepository.findById(id); }

    public CreditAccount updateCreditAccount(Long id, CreditAccount creditAccDetail) {
        Optional<CreditAccount> CreditAccount = creditAccountRepository.findById(id);

        if (CreditAccount.isPresent()) {
            CreditAccount existingIntern = CreditAccount.get();
            String[] ignore = getNullPropertyNames(existingIntern);
            copyProperties(creditAccDetail, existingIntern, getNullPropertyNames(creditAccDetail));
            System.out.println(Arrays.toString(ignore));

            existingIntern.setId(creditAccDetail.getId());
            return creditAccountRepository.save(existingIntern);
        }

        return null;
    }

    public void deleteCreditAccount(Long id) { creditAccountRepository.deleteById(id); }

    public void deleteAllCreditAccounts() { creditAccountRepository.deleteAll(); }
}