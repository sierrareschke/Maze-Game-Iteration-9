package polymorphia.strategy;

import csci.ooad.polymorphia.intf.CommandFactory;
import csci.ooad.polymorphia.intf.Strategy;
import csci.ooad.polymorphia.intf.StrategyProvider;

// TODO: Rename this class
public class TeamNameStrategyProvider implements StrategyProvider {

    @Override
    public String getName() {
        // TODO: This should return a unique name that identifies your team
        return "";
    }

    @Override
    public Strategy create(CommandFactory commandFactory) {
        // TODO: Fill this in
        return null;
    }
}
