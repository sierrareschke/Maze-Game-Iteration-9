package polymorphia.strategy;

import csci.ooad.polymorphia.intf.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// TODO: Rename this class
@ExtendWith(MockitoExtension.class)
class TeamNameStrategyTest {

    @Mock CommandFactory commandFactory;
    @Mock ICharacter character;
    @Mock ICharacter demon;
    @Mock Command command;
    @Mock IFood steak;
    @Mock IFood candy;
    @Mock Room room;

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
        TeamNameStrategy fighterStrategy = new TeamNameStrategy(commandFactory);

        // Assert
        // No good way to verify Command, so we just verify we called the correction creation method
        Command selectedCommand = fighterStrategy.generateCommand(character);
        verify(commandFactory).createEatCommand(character, steak);
    }

    // TODO: Add enough tests to get full line coverage of your getCommand() method on your Strategy class
}