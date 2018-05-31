package de.philipilihp.pub.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.CachedThreadStatesGaugeSet;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ClassLoadingGaugeSet;

import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

@Singleton
public class PubMetricsRegistry {

    private static final MetricRegistry registry;

    static {
        registry = new MetricRegistry();
        registry.register("classloading", new ClassLoadingGaugeSet());
        registry.register("gc", new GarbageCollectorMetricSet());
        registry.register("threads", new CachedThreadStatesGaugeSet(10, TimeUnit.SECONDS));
        registry.register("memory", new MemoryUsageGaugeSet());
    }

    public static MetricRegistry registry() {
        return registry;
    }

}
