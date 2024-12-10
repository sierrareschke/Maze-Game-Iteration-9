package polymorphia.strategy;

import csci.ooad.polymorphia.intf.*;

import java.util.Comparator;
import java.util.List;

public class GraceNolanSierraStrategy implements Strategy {
    private final CommandFactory commandFactory;


    // TODO: Implement this method
    public GraceNolanSierraStrategy(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public Command generateCommand(ICharacter iCharacter) {
        // TODO: implement your strategy here
        Room currentRoom = iCharacter.getCurrentRoom();

        // TODO - flee if demon in room ? (can we check if there's a demon?)

        // TODO - put on armor if present
        List<IArmor> armorItems = currentRoom.getArmoredSuits();
        if (!armorItems.isEmpty()) {
            return commandFactory.createWearCommand(iCharacter, armorItems.get(0));
        }


        // if food present eat the most nutritious food
        List<IFood> foodItems = currentRoom.getFoodItems();
        if (!foodItems.isEmpty()) {
            IFood bestFood = foodItems.stream()
                    .max(Comparator.comparingDouble(IFood::healthValue))
                    .orElse(null);
            return commandFactory.createEatCommand(iCharacter, bestFood);
        }

        // TODO - fight if have more health than creature ?


        // Default: Do nothing
        return commandFactory.createDoNothingCommand();
    }
}
