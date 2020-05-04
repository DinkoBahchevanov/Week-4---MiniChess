package com.company;

public class Molar extends Piece {
    public Molar(Position position, String color, String name) {
        super(position, color, name);
    }

    @Override
    public String move(int x, int y) {
        if (x > -1 && x < 6 && y > -1 && y < 6) {
            if (x == getPosition().getX() + 1 || x == getPosition().getX() - 1) {
                getPosition().setX(x);
            } else if (y == getPosition().getY() + 1 || y == getPosition().getY() - 1) {
                getPosition().setY(y);
            } else return "Invalid move";

            return "Successful move";
        }
        return "Invalid move";
    }
}
