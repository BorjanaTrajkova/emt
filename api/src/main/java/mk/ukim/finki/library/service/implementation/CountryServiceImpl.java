package mk.ukim.finki.library.service.implementation;

import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.library.repository.CountryRepository;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public Country createCountry(Country country) {
        return this.countryRepository.save(country);
    }
}
