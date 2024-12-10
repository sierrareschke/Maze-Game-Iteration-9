package polymorphia.strategy;

import csci.ooad.polymorphia.intf.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GraceNolanSierraStrategyTest {

    @Mock CommandFactory commandFactory;
    @Mock ICharacter character;
    @Mock ICharacter weakestCreature;
    @Mock ICharacter strongerCreature;
    @Mock Command command;
    @Mock Command doNothingCommand;
    @Mock IFood steak;
    @Mock IFood candy;
    @Mock Room room;
    @Mock IArmor armor;

    @Test
    void testFightWeakestCreatureWhenHealthy() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(character.getHealth()).thenReturn(5.0); // Healthy
        when(weakestCreature.getHealth()).thenReturn(1.0);
        when(strongerCreature.getHealth()).thenReturn(4.0);
        when(weakestCreature.isCreature()).thenReturn(true);
        when(strongerCreature.isCreature()).thenReturn(true);
        when(room.getCharacters()).thenReturn(List.of(weakestCreature, strongerCreature));
        when(commandFactory.createFightCommand(character, weakestCreature)).thenReturn(command);

        // Act
        GraceNolanSierraStrategy strategy = new GraceNolanSierraStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createFightCommand(character, weakestCreature);
        assertEquals(command, selectedCommand);
    }

    @Test
    void testMoveToRandomNeighborIfCreaturePresentButNotHealthy() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(character.getHealth()).thenReturn(2.0); // Not healthy
        when(weakestCreature.isCreature()).thenReturn(true);
        when(room.getCharacters()).thenReturn(List.of(weakestCreature));
        when(room.getNeighborRoomNames()).thenReturn(List.of("Room1", "Room2"));
        when(commandFactory.createMoveCommand(eq(character), any(String.class))).thenReturn(command);

        // Act
        GraceNolanSierraStrategy strategy = new GraceNolanSierraStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createMoveCommand(eq(character), any(String.class));
        assertEquals(command, selectedCommand);
    }

    @Test
    void testWearingArmorWhenAvailable() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(commandFactory.createWearCommand(eq(character), eq(armor))).thenReturn(command);
        when(room.getArmoredSuits()).thenReturn(List.of(armor));

        // Act
        GraceNolanSierraStrategy strategy = new GraceNolanSierraStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createWearCommand(character, armor);
        assertEquals(command, selectedCommand);
    }

    @Test
    void testEatingMostNutritiousFood() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(commandFactory.createEatCommand(eq(character), eq(steak))).thenReturn(command);
        when(steak.healthValue()).thenReturn(5.0);
        when(candy.healthValue()).thenReturn(2.0);
        when(room.getFoodItems()).thenReturn(List.of(candy, steak));

        // Act
        GraceNolanSierraStrategy strategy = new GraceNolanSierraStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createEatCommand(character, steak);
        assertEquals(command, selectedCommand);
    }

    @Test
    void testNoArmorNoFoodNoCreatureIdle() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(commandFactory.createDoNothingCommand()).thenReturn(doNothingCommand);
        when(room.getFoodItems()).thenReturn(Collections.emptyList());
        when(room.getArmoredSuits()).thenReturn(Collections.emptyList());
        when(room.getCharacters()).thenReturn(Collections.emptyList());

        // Act
        GraceNolanSierraStrategy strategy = new GraceNolanSierraStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createDoNothingCommand();
        assertEquals(doNothingCommand, selectedCommand);
    }

    @Test
    void testNoNeighborRoomsAndCreaturePresent() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(character.getHealth()).thenReturn(2.0); // Not healthy
        when(weakestCreature.isCreature()).thenReturn(true);
        when(room.getCharacters()).thenReturn(List.of(weakestCreature));
        when(room.getNeighborRoomNames()).thenReturn(Collections.emptyList());
        when(commandFactory.createDoNothingCommand()).thenReturn(doNothingCommand);

        // Act
        GraceNolanSierraStrategy strategy = new GraceNolanSierraStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createDoNothingCommand();
        assertEquals(doNothingCommand, selectedCommand);
    }

    @Test
    void testDefaultDoNothingWhenNoActionsAvailable() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(commandFactory.createDoNothingCommand()).thenReturn(doNothingCommand);
        when(room.getFoodItems()).thenReturn(Collections.emptyList());
        when(room.getArmoredSuits()).thenReturn(Collections.emptyList());
        when(room.getCharacters()).thenReturn(Collections.emptyList());
        when(room.getNeighborRoomNames()).thenReturn(Collections.emptyList());

        // Act
        GraceNolanSierraStrategy strategy = new GraceNolanSierraStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(commandFactory).createDoNothingCommand();
        assertEquals(doNothingCommand, selectedCommand);
    }
}
