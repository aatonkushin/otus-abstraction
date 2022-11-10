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
        if (m.getVelocity().getX() == 0 && m.getVelocity().getY() == 0) {
            throw new RuntimeException("Невозможно переместить объект с нулевой скоростью");
        }

        m.setPosition(Vector.plus(m.getPosition(), m.getVelocity()));
    }
}
