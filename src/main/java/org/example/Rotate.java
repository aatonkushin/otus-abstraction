package org.example;

public class Rotate {
    Rotatable r;

    public Rotate(Rotatable r) {
        this.r = r;
    }

    public void execute() {
        if (r.getAngularVelocity() == 0) {
            throw new RuntimeException("Невозможно повернуть объект с нулевой скоростью");
        }

        r.setDirection(
                (r.getDirection() + r.getAngularVelocity()) % r.getDirectionsNumber()
        );
    }
}
