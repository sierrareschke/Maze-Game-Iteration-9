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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GraceNolanSierraStrategyTest {

    @Mock CommandFactory commandFactory;
    @Mock ICharacter character;
    @Mock ICharacter demon;
    @Mock Command command;
    @Mock IFood steak;
    @Mock IFood candy;
    @Mock Room room;
    @Mock IArmor armor;


    @Test
    // TODO: This is an example test using mocks. You can delete this test altogether, or you can get it to pass.
    // To get this test to pass you have to implement the Strategy. Nothing necessarily needs to change in this test.
    void testEatingMostNutritiousFood() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(commandFactory.createEatCommand(eq(character), any(IFood.class))).thenReturn(command);
        when(steak.healthValue()).thenReturn(2.0);
        when(candy.healthValue()).thenReturn(1.0);
        when(room.getFoodItems()).thenReturn(List.of(candy, steak));

        // Act
        GraceNolanSierraStrategy fighterStrategy = new GraceNolanSierraStrategy(commandFactory);

        // Assert
        // No good way to verify Command, so we just verify we called the correction creation method
        Command selectedCommand = fighterStrategy.generateCommand(character);
        verify(commandFactory).createEatCommand(character, steak);
    }

    // TODO: Add enough tests to get full line coverage of your getCommand() method on your Strategy class


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
    void testNoArmorAvailable() {
        // Arrange
        when(character.getCurrentRoom()).thenReturn(room);
        when(room.getArmoredSuits()).thenReturn(Collections.emptyList());

        // Act
        GraceNolanSierraStrategy strategy = new GraceNolanSierraStrategy(commandFactory);
        Command selectedCommand = strategy.generateCommand(character);

        // Assert
        verify(room).getArmoredSuits();
        assertNull(selectedCommand, "Expected no command to be generated when no armor is available.");
    }


    // TODO fix
//    @Test
//    void testIdleWhenNoActionsAvailable() {
//        // Arrange
//        when(character.getCurrentRoom()).thenReturn(room);
//        when(commandFactory.createDoNothingCommand().thenReturn(doNothingCommand);
//        when(room.getFoodItems()).thenReturn(List.of());
//        when(room.getCharacters()).thenReturn(List.of());
//        when(room.getNeighborRoomNames()).thenReturn(List.of());
//
//        // Act
//        GraceNolanSierraStrategy strategy = new GraceNolanSierraStrategy(commandFactory);
//        Command command = strategy.generateCommand(character);
//
//        // Assert
//        verify(commandFactory).createDoNothingCommand();
//        assertEquals(doNothingCommand, command);
//    }

}