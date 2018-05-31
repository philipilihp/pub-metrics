package de.philipilihp.pub.service;

import de.philipilihp.pub.model.Beer;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class BeerRepository {

    private Set<Beer> repository = new HashSet<>();

    public BeerRepository() {
        repository.add(new Beer("Leffe", 6.6, BigDecimal.valueOf(3.40)));
        repository.add(new Beer("Grimbergen", 6.5, BigDecimal.valueOf(3.60)));
        repository.add(new Beer("Kasteel", 11.0,BigDecimal.valueOf(4.5)));
    }

    public Set<Beer> findAll() {
        return new HashSet<>(repository);
    }

    public Optional<Beer> findByName(String trademark) {
        return repository.stream()
                .filter(beer -> StringUtils.equalsIgnoreCase(beer.getTrademark(), trademark))
                .findFirst();
    }

}
