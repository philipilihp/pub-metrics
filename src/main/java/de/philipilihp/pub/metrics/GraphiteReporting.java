package de.philipilihp.pub.metrics;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.concurrent.TimeUnit;

@Startup
@Singleton
public class GraphiteReporting {

    @PostConstruct
    public void startReporting() {
        String url = System.getProperty("graphite.url");
        String port = System.getProperty("graphite.port");

        if(StringUtils.isEmpty(url) || StringUtils.isEmpty(port)) {
            return;
        }

        final Graphite graphite = new Graphite(url, Integer.parseInt(port));

        final GraphiteReporter reporter = GraphiteReporter.forRegistry(PubMetricsRegistry.registry())
                .prefixedWith("be.pub")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);

        reporter.start(10, TimeUnit.SECONDS);
    }
}
