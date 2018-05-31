package de.philipilihp.pub.rest;

import de.philipilihp.pub.model.Beer;
import de.philipilihp.pub.model.BeerTrademark;
import de.philipilihp.pub.service.BeerRepository;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("beers")
@RequestScoped
public class BeerController {

    @Inject
    private BeerRepository beerRepository = new BeerRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.ok()
                .entity(beerRepository.findAll())
                .build();
    }

    @Path("{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBeerByName(@PathParam("name") BeerTrademark trademark) {
        Optional<Beer> beer = beerRepository.findByName(trademark);

        if(beer.isPresent()) {
            return Response.ok().entity(beer.get()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
