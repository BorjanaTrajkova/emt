package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Country;

public interface CountryService {
    Country findById(Long id);

    Country createCountry(Country country);
}
