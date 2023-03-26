package org.example;

public class Player {
    public String name;
    public My_field my_field = new My_field();
    public Enemy_field enemy_field = new Enemy_field();
    public boolean life = true;

    private Ship[] Ship_deck1 = null;
    private Ship[] Ship_deck2 = null;
    private Ship[] Ship_deck3 = null;
    private Ship[] Ship_deck4 = null;

    public Player(String name) {
        this.name = name;
    }

    //Возвращает true, если попал, false - иначе
    public boolean Hit(Cell cell, Player enemy) {
        return true;
    }

    //операция атаки: взаимодействие со своим полем и полем противника
    public void Attack(Cell cell) {
        this.enemy_field.Shot(cell);

    }

    //расстановка кораблей
    public void Placement_ships() {

    }

}
