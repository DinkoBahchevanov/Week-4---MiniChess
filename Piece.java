package com.company;

public abstract class Piece {
    private String name;
    private String color;
    private Position position;
    private boolean isDead = false;

    public Piece(Position position, String color, String name) {
        this.name = name;
        this.position = position;
        this.color = color;

    }

    public abstract String move(int x, int y);

    public boolean getIsDead() { return isDead; }

    public void setIsDead(boolean dead) { isDead = dead; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
