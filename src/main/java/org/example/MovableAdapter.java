package org.example;

public class MovableAdapter implements Movable {
    UObject obj;

    public MovableAdapter(UObject o) {
        this.obj = o;
    }

    @Override
    public Vector getPosition() {
        return (Vector) getProperty("position", obj);
    }

    @Override
    public Vector getVelocity() {
//        int velocity = (int) obj.getProperty("velocity");
//        int maxDirections = (int) obj.getProperty("maxDirections");
//        int direction = (int) obj.getProperty("direction");
//
//        return new Vector(
//                (int) (velocity * Math.cos(2 * Math.PI / maxDirections * direction)),
//                (int) (velocity * Math.sin(2 * Math.PI / maxDirections * direction))
//        );

        return (Vector) getProperty("velocity", obj);
    }

    @Override
    public void setPosition(Vector newValue) {
        obj.setProperty("position", newValue);
    }

    private Object getProperty(String propertyName, UObject obj) {
        Object retVal = obj.getProperty(propertyName);

        if (retVal == null)
            throw new RuntimeException("Невозможно прочитать значение свойства " + propertyName);

        return retVal;
    }
}
