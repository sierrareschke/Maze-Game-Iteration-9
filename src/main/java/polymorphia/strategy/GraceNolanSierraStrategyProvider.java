package polymorphia.strategy;

import csci.ooad.polymorphia.intf.CommandFactory;
import csci.ooad.polymorphia.intf.Strategy;
import csci.ooad.polymorphia.intf.StrategyProvider;

public class GraceNolanSierraStrategyProvider implements StrategyProvider {

    @Override
    public String getName() {
        return "GraceNolanSierra";
    }

    @Override
    public Strategy create(CommandFactory commandFactory) {
        return new GraceNolanSierraStrategy(commandFactory);
    }
}
