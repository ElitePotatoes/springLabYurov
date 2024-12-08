package tech.reliab.course.toropchida.springlabyurov.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.toropchida.springlabyurov.entity.BankAtm;
import tech.reliab.course.toropchida.springlabyurov.repository.BankAtmRepository;
import tech.reliab.course.toropchida.springlabyurov.repository.BankOfficeRepository;
import tech.reliab.course.toropchida.springlabyurov.repository.BankRepository;
import tech.reliab.course.toropchida.springlabyurov.service.BankAtmService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;
import static tech.reliab.course.toropchida.springlabyurov.utils.Utils.getNullPropertyNames;

@Service
@AllArgsConstructor
public class BankAtmServiceImpl implements BankAtmService {
    public final BankAtmRepository bankAtmRepository;
    public final BankRepository bankRepository;
    public final BankOfficeRepository bankOfficeRepository;

    public BankAtm createBankAtm(BankAtm atm) {
        Long bankId = atm.getBankId();
        Long bankOfficeId = atm.getBankOfficeId();

        atm.setTotalMoney(bankRepository.getTotalMoneyByBankId(bankId));
        atm.setAddress(bankOfficeRepository.getAddressByOfficeId(bankOfficeId));

        bankRepository.incrementNumberAtmsByBankId(bankId);
        bankOfficeRepository.incrementNumberAtmsByOfficeId(bankOfficeId);

        return bankAtmRepository.save(atm);
    }

    public List<BankAtm> getAllBankAtms() {
        return bankAtmRepository.findAll();
    }

    public Optional<BankAtm> getBankAtm(Long id) {
        return bankAtmRepository.findById(id);
    }

    public BankAtm updateBankAtm(Long id, BankAtm atmDetail) {
        Optional<BankAtm> bankAtm = bankAtmRepository.findById(id);

        if (bankAtm.isPresent()) {
            BankAtm existingIntern = bankAtm.get();
            String[] ignore = getNullPropertyNames(existingIntern);
            copyProperties(atmDetail, existingIntern, getNullPropertyNames(atmDetail));
            System.out.println(Arrays.toString(ignore));

            existingIntern.setId(atmDetail.getId());
            return bankAtmRepository.save(existingIntern);
        }

        return null;
    }

    public void deleteBankAtm(Long id) {
        bankAtmRepository.deleteById(id);
    }

    public void deleteAllBanksAtms() {
        bankAtmRepository.deleteAll();
    }
}