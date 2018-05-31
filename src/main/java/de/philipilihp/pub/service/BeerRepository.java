package de.philipilihp.pub.service;

import de.philipilihp.pub.model.Beer;
import de.philipilihp.pub.model.BeerTrademark;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class BeerRepository {

    private Set<Beer> repository = new HashSet<>();

    public BeerRepository() {
        repository.add(new Beer(BeerTrademark.LEFFE, 6.6, BigDecimal.valueOf(3.40)));
        repository.add(new Beer(BeerTrademark.GRIMBERGEN, 6.5, BigDecimal.valueOf(3.60)));
        repository.add(new Beer(BeerTrademark.KASTEEL, 11.0,BigDecimal.valueOf(4.5)));
    }

    public Set<Beer> findAll() {
        return repository;
    }

    public Optional<Beer> findByName(BeerTrademark trademark) {
        return repository.stream()
                .filter(beer -> beer.getTrademark() == trademark)
                .findFirst();
    }

}
