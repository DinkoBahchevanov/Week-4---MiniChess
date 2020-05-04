package com.company;

import com.company.Piece;
import com.company.Position;

public class Donkey extends Piece {

    public Donkey(Position position, String color, String name) {
        super(position, color, name);
    }

    @Override
    public String move(int x, int y) {
        if(x > -1 && x < 6 && y > -1 && y < 6){
            if ((x == getPosition().getX() + 2 && y == getPosition().getY() + 2)
                    || (x == getPosition().getX() - 2 && y == getPosition().getY() - 2)
                    || (x == getPosition().getX() + 2 && y == getPosition().getY() - 2)
                    || (x == getPosition().getX() - 2 && y == getPosition().getY() + 2)
                    || (y == getPosition().getY() + 2)
                    || (y == getPosition().getY() - 2)
                    || (x == getPosition().getX() + 2)
                    || (x == getPosition().getX() - 2)) {

                super.getPosition().setX(x);
                super.getPosition().setY(y);

                return "Successful move";
            }
            return "Invalid move";
        }
        return "Invalid move";
    }
}
