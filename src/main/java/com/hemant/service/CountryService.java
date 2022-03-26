package com.hemant.service;

import com.hemant.repository.CountryRepository;
import com.hemant.exception.BussinesException;
import com.hemant.exception.NotFoundException;
import com.hemant.model.dto.CountryDto;
import com.hemant.entity.Country;
import com.hemant.model.request.CreateUpdateCountryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hemant.model.mapper.CountryMapper.COUNTRY_MAPPER;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository repository;

    public CountryDto createCountry(CreateUpdateCountryRequest request){
        Country country=COUNTRY_MAPPER.createCountry(request);
        return COUNTRY_MAPPER.toCountryDto(repository.save(country));
    }
    public CountryDto updateCountry(int id,CreateUpdateCountryRequest request){
        Country country=repository.findById(id).orElseThrow(()->new BussinesException("Country is not found."));
        COUNTRY_MAPPER.updateCountry(country,request);
        Country updatedcountry=repository.save(country);
        return COUNTRY_MAPPER.toCountryDto(updatedcountry);
    }
    public CountryDto getCountryById(int id){
        return COUNTRY_MAPPER.toCountryDto(repository.findById(id).orElseThrow(()-> new NotFoundException("Country   not found.")));
    }
    public List<CountryDto> getCountryList(){
        return  COUNTRY_MAPPER.toCountryDtoList(repository.findAll());
    }
    public void deleteCountry(int id){
        repository.deleteById(id);
    }
}
