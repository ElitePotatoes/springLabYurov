package tech.reliab.course.toropchida.springlabyurov.serviceImpl;

import tech.reliab.course.toropchida.springlabyurov.entity.BankOffice;
import tech.reliab.course.toropchida.springlabyurov.repository.BankOfficeRepository;
import tech.reliab.course.toropchida.springlabyurov.repository.BankRepository;
import tech.reliab.course.toropchida.springlabyurov.service.BankOfficeService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;
import static tech.reliab.course.toropchida.springlabyurov.utils.Utils.getNullPropertyNames;

@Service
@AllArgsConstructor
public class BankOfficeServiceImpl implements BankOfficeService {
    public final BankOfficeRepository bankOfficeRepository;
    public final BankRepository bankRepository;

    public BankOffice createBankOffice(BankOffice office) {
        Long bankId = office.getBankId();

        office.setNumberAtms(bankRepository.getNumberAtmsByBankId(bankId));
        office.setTotalMoney(bankRepository.getTotalMoneyByBankId(bankId));

        bankRepository.incrementNumberOfficesByBankId(bankId);
        return bankOfficeRepository.save(office);
    }

    public List<BankOffice> getAllBankOffices() {
        return bankOfficeRepository.findAll();
    }

    public Optional<BankOffice> getBankOffice(Long id) {
        return bankOfficeRepository.findById(id);
    }

    public BankOffice updateBankOffice(Long id, BankOffice officeDetail) {
        Optional<BankOffice> BankOffice = bankOfficeRepository.findById(id);
        if (BankOffice.isPresent()) {
            BankOffice existingIntern = BankOffice.get();
            String[] ignore = getNullPropertyNames(existingIntern);
            copyProperties(officeDetail, existingIntern, getNullPropertyNames(officeDetail));
            System.out.println(Arrays.toString(ignore));

            existingIntern.setId(officeDetail.getId());
            return bankOfficeRepository.save(existingIntern);
        }

        return null;
    }

    public void deleteBankOffice(Long id) {
        bankOfficeRepository.deleteById(id);
    }

    public void deleteAllBanksOffices() {
        bankOfficeRepository.deleteAll();
    }
}