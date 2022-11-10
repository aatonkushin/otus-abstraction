package org.example;

/**
 * Операция движения
 */
public class Move {
    Movable m;

    public Move(Movable m) {
        this.m = m;
    }

    public void execute() {
        m.setPosition(Vector.plus(m.getPosition(), m.getVelocity()));
    }
}
