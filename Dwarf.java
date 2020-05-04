package com.company;

public class Dwarf extends Piece {
    private boolean isEndReached;

    public Dwarf(Position position, String color, String name) {
        super(position, color, name);
        this.isEndReached = false;
    }

    @Override
    public String move(int x, int y) {
        if (x > -1 && x < 6 && y > -1 && y < 6) {
            if (getColor().equals("White")) {
                if(isEndReached && getPosition().getX() == 0) {
                    isEndReached = false;
                }
                if(!isEndReached && getPosition().getX() == 5) {
                    isEndReached = true;
                }
            } else {
                if(isEndReached && getPosition().getX() == 5) {
                    isEndReached = false;
                }
                if(!isEndReached && getPosition().getX() == 0) {
                    isEndReached = true;
                }
            }

            if (x == getPosition().getX() + 1 && getColor().equals("White") && !isEndReached) {
                getPosition().setX(x);
            } else if (x == getPosition().getX() - 1 && getColor().equals("White") && isEndReached) {
                getPosition().setX(x);
            } else if (x == getPosition().getX() - 1 && getColor().equals("Black") && !isEndReached) {
                getPosition().setX(x);
            } else if (x == getPosition().getX() + 1 && getColor().equals("Black") && isEndReached) {
                getPosition().setX(x);
            } else return "Invalid move";

            return "Successful move";
        }
        return "Invalid move";
    }
}
