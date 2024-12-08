package tech.reliab.course.toropchida.springlabyurov.serviceImpl;

import tech.reliab.course.toropchida.springlabyurov.entity.PaymentAccount;
import tech.reliab.course.toropchida.springlabyurov.repository.BankUserRepository;
import tech.reliab.course.toropchida.springlabyurov.repository.PaymentAccountRepository;
import tech.reliab.course.toropchida.springlabyurov.service.PaymentAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;
import static tech.reliab.course.toropchida.springlabyurov.utils.Utils.getNullPropertyNames;

@Service
public class PaymentAccountServiceImpl implements PaymentAccountService {
    public final PaymentAccountRepository paymentAccountRepository;
    public final BankUserRepository bankUserRepository;

    @Autowired
    public PaymentAccountServiceImpl(PaymentAccountRepository paymentAccountRepository, BankUserRepository bankUserRepository) {
        this.paymentAccountRepository = paymentAccountRepository;
        this.bankUserRepository = bankUserRepository;
    }

    public PaymentAccount createPaymentAccount(PaymentAccount paymentAccount) {
        paymentAccount.setCurrentAmount(0);

        PaymentAccount pa = paymentAccountRepository.save(paymentAccount);
        bankUserRepository.setPaymentAccountIdByUserId(pa.getId(), pa.getUserId());

        return pa;
    }

    public List<PaymentAccount> getAllPaymentAccounts() {
        return paymentAccountRepository.findAll();
    }

    public Optional<PaymentAccount> getPaymentAccount(Long id) {
        return paymentAccountRepository.findById(id);
    }

    public PaymentAccount updatePaymentAccount(Long id, PaymentAccount PaymentAccDetail) {
        Optional<PaymentAccount> PaymentAccount = paymentAccountRepository.findById(id);

        if (PaymentAccount.isPresent()) {
            PaymentAccount existingIntern = PaymentAccount.get();
            String[] ignore = getNullPropertyNames(existingIntern);
            copyProperties(PaymentAccDetail, existingIntern, getNullPropertyNames(PaymentAccDetail));
            System.out.println(Arrays.toString(ignore));

            existingIntern.setId(PaymentAccDetail.getId());
            return paymentAccountRepository.save(existingIntern);
        }

        return null;
    }

    public void deletePaymentAccount(Long id) {
        paymentAccountRepository.deleteById(id);
    }

    public void deleteAllPaymentAccounts() {
        paymentAccountRepository.deleteAll();
    }
}