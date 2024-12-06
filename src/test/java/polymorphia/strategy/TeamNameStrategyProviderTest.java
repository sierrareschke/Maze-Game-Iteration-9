package polymorphia.strategy;

import csci.ooad.polymorphia.intf.StrategyProvider;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.*;

class TeamNameStrategyProviderTest {

    @Test
    // TODO: This test should run successfully when you have configured your
    // source/mainresources/META-INF/services/csci.ooad.polymorphia.intf.StrategyProvider file correctly
    void testProviderLoading() {
        ServiceLoader<StrategyProvider> loader = ServiceLoader.load(StrategyProvider.class);
        List<StrategyProvider> services = new ArrayList<>();
        for (StrategyProvider service : loader) {
            services.add(service);
            System.out.println(service.getClass().getName());
            service.create(null);
        }
        assertFalse(services.isEmpty());
    }
}