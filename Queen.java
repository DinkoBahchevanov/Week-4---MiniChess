package com.company;

import com.company.Piece;
import com.company.Position;

public class Queen extends Piece {

    public Queen(Position position, String color, String name) {
        super(position, color, name);
    }

    @Override
    public String move(int x, int y) {
        if(x > -1 && x < 6 && y > -1 && y < 6) {
            if ((x == getPosition().getX() + 1 && y == getPosition().getY() + 1)
                    || (x == getPosition().getX() - 1 && y == getPosition().getY() - 1)
                    || (x == getPosition().getX() + 1 && y == getPosition().getY() - 1)
                    || (x == getPosition().getX() - 1 && y == getPosition().getY() + 1)){

                getPosition().setX(x);
                getPosition().setY(y);

                return "Successful move";
            }

            return "Invalid move";
        }
        return "Invalid move";
    }
}
