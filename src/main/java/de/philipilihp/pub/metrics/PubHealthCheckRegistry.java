package de.philipilihp.pub.metrics;

import com.codahale.metrics.health.HealthCheckRegistry;

public class PubHealthCheckRegistry {

    private static final HealthCheckRegistry registry = new HealthCheckRegistry();

    public static HealthCheckRegistry registry() {
        return registry;
    }

}
