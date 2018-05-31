package de.philipilihp.pub.rest;

import de.philipilihp.pub.metrics.PubMetricsRegistry;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


/**
 * Metrics Filter. Counts the requested beers and the time needed for delivery.
 */
@Provider
public class MetricsFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final String METRICS_BEER_PREFIX = "pub.beers.";
    private static final String REQUEST_PROPERTY_TIME = "request.time";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Start request time
        requestContext.setProperty(REQUEST_PROPERTY_TIME, System.nanoTime());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        UriInfo uriInfo = requestContext.getUriInfo();

        if (!requestContext.getPropertyNames().contains(REQUEST_PROPERTY_TIME)
                || !uriInfo.getPath().startsWith("beers")
                || !uriInfo.getPathParameters().containsKey("name")) {
            return;
        }

        long startTime = (long) requestContext.getProperty(REQUEST_PROPERTY_TIME);
        long durationInMs = (System.nanoTime() - startTime) / 1000000;

        String beer = uriInfo.getPathParameters()
                .getFirst("name")
                .toLowerCase();

        PubMetricsRegistry.registry()
                .meter(METRICS_BEER_PREFIX + beer)
                .mark();

        PubMetricsRegistry.registry()
                .meter(METRICS_BEER_PREFIX + beer + ".time")
                .mark(durationInMs);
    }
}
