package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    private UObject getMock(){
        UObject uObjectMock = Mockito.mock(UObject.class);

        Mockito.doAnswer(invocation -> {
            String name = (String)invocation.getArguments()[0];
            Object value = invocation.getArguments()[1];
            Mockito.when(uObjectMock.getProperty(name)).thenReturn(value);
            return null;
        }).when(uObjectMock).setProperty(Mockito.anyString(), Mockito.any());

        return uObjectMock;
    }
    /**
     * 7.3.1. Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3)
     * движение меняет положение объекта на (5, 8)
     */
    @Test
    void testPositive(){
        UObject uObjectMock = getMock();

        uObjectMock.setProperty("position", new Vector(12, 5));
        uObjectMock.setProperty("velocity", new Vector(-7, 3));

        MovableAdapter adapter = new MovableAdapter(uObjectMock);
        Move move = new Move(adapter);
        move.execute();

        Vector position = (Vector) uObjectMock.getProperty("position");
        Assertions.assertEquals(5, position.getX());
        Assertions.assertEquals(8, position.getY());
    }

    /**
     * 7.3.2. Попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке
     */
    @Test
    void testNoPosition(){
        UObject uObjectMock = getMock();
        uObjectMock.setProperty("velocity", new Vector(-7, 3));

        MovableAdapter adapter = new MovableAdapter(uObjectMock);
        Move move = new Move(adapter);

        RuntimeException thrown = Assertions.assertThrows(
                RuntimeException.class,
                move::execute
        );
        Assertions.assertTrue(thrown.getMessage().contains("position"));
    }

    /**
     * 7.3.3. Попытка сдвинуть объект, у которого невозможно прочитать значение мгновенной скорости, приводит к ошибке
     */
    @Test
    void testNoVelocity(){
        UObject uObjectMock = getMock();
        uObjectMock.setProperty("position", new Vector(12, 5));

        MovableAdapter adapter = new MovableAdapter(uObjectMock);
        Move move = new Move(adapter);

        RuntimeException thrown = Assertions.assertThrows(
                RuntimeException.class,
                move::execute
        );
        Assertions.assertTrue(thrown.getMessage().contains("velocity"));
    }
}
