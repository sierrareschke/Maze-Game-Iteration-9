package polymorphia.strategy;

import csci.ooad.polymorphia.intf.*;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

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

        Boolean isCreature = currentRoom.getCharacters()
                .stream()
                .anyMatch(character -> character.isCreature());

        Boolean healthy = iCharacter.getHealth() > 3.0;

        ICharacter weakestCreature = currentRoom.getCharacters()
                .stream()
                .filter(ICharacter::isCreature) // Select only creatures
                .min(Comparator.comparingDouble(ICharacter::getHealth)) // Find the one with the least health
                .orElse(null); // Return null if no creatures are present


        if(isCreature && healthy){
            return commandFactory.createFightCommand(iCharacter,weakestCreature);
        }

        Random random = new Random();

        List<String> neighbors = currentRoom.getNeighborRoomNames();

        String targetRoom = neighbors.isEmpty()
                ? null // Or handle the empty case appropriately
                : neighbors.get(random.nextInt(neighbors.size()));

        if(isCreature && targetRoom != null){
            return commandFactory.createMoveCommand(iCharacter,targetRoom);
        }


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



        // Default: Do nothing
        return commandFactory.createDoNothingCommand();
    }
}
