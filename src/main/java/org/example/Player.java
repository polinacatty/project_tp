package org.example;

import java.util.ArrayList;

import static org.example.Game.scanner;

public class Player {
    public String name;
    public My_field my_field = new My_field();
    public Enemy_field enemy_field = new Enemy_field();

    public ArrayList<Ship> Ships = new ArrayList<>();


    public Player(String name) {
        this.name = name;
    }

    public boolean All_ships_are_placed() {
        int count_1_deck_ships = 0;
        int count_2_deck_ships = 0;
        int count_3_deck_ships = 0;
        int count_4_deck_ships = 0;
        for (int i = 0; i < this.Ships.size(); i++) {
            if (this.Ships.get(i).Getter_size() == 1) {
                count_1_deck_ships += 1;
            }
            if (this.Ships.get(i).Getter_size() == 2) {
                count_2_deck_ships += 1;
            }
            if (this.Ships.get(i).Getter_size() == 3) {
                count_3_deck_ships += 1;
            }
            if (this.Ships.get(i).Getter_size() == 4) {
                count_4_deck_ships += 1;
            }
        }

        if ((count_1_deck_ships == 4) && (count_2_deck_ships == 3) && (count_3_deck_ships == 2) && (count_4_deck_ships == 1)) {
            return true;
        }
        return false;
    }

    //Возвращает true, если попал, false - иначе
    public boolean Hit(Cell cell, Player enemy) {
        if ((enemy.my_field.Getter_cell(cell) == 0) || (enemy.my_field.Getter_cell(cell) == 1)) {
            return false;
        }
        return true;
    }

    public boolean Ship_is_dead(Cell cell) {
        for (int i = 0; i < this.Ships.size(); i++) {
            if (this.Ships.get(i).Cell_in_ship(cell)) {
                Ship ship = this.Ships.get(i);
                for (int j = 0; j < ship.Getter_size(); j++) {
                    if (this.my_field.Getter_cell(ship.Getter_cell(j)) == 5) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //операция атаки: взаимодействие со своим полем и полем противника
    public boolean Attack(Cell cell, Player enemy) {
        enemy.my_field.Taking_shot(cell);
        this.enemy_field.Shot(cell, this.Hit(cell, enemy));
        return this.Hit(cell, enemy);
    }


    public void Turn(Player enemy) {
        System.out.println(this.name + ", your turn");
        System.out.println("Your field:");
        this.my_field.Print_field();
        System.out.println("Enemy field:");
        this.enemy_field.Print_field();
        System.out.println("Please enter coordinates of the cell where you want to shot to:");
        Cell cell = new Cell(scanner.nextLine());
        if (this.Attack(cell, enemy)) {
            if (enemy.Ship_is_dead(cell)) {
                System.out.println("Super!!! You hit the target, ship is dead");
            }
            else {
                System.out.println("Super!!! You hit the target, but ship is not dead");
            }
            if (enemy.isLife()) {
                this.Turn(enemy);
            }
        }
        else {
            System.out.println("You missed :(");
        }
        this.enemy_field.Print_field();
    }



//    public void Try_add_ship() {
//        System.out.println("Enter the coordinate of the beginning of the ship:");
//        Cell begin = new Cell(scanner.next());
//        System.out.println("Enter the coordinate of the end of the ship:");
//        Cell end = new Cell(scanner.next());
//        if (this.my_field.Can_add_ship(begin, end)) {
//            Ship ship = new Ship(begin, end);
//            this.my_field.Add_ship(ship);
//            Ships.add(ship);
//            System.out.println("You have successfully added a ship!");
//        }
//        else {
//            System.out.println("You can`t add this ship, please, try again");
//            this.Try_add_ship();
//        }
//    }
//
//    public void Try_delete_ship() {
//        System.out.println("Enter the coordinate of any cell of the ship:");
//        Cell any_cell = new Cell(scanner.next());
//        if (this.my_field.Can_delete_ship(any_cell)) {
//            for (int i = 0; i < this.Ships.size(); i++) {
//                if (this.Ships.get(i).Cell_in_ship(any_cell)) {
//                    this.my_field.Delete_ship(this.Ships.get(i));
//                    break;
//                }
//            }
//            System.out.println("You have successfully deleted a ship!");
//        }
//        else {
//            System.out.println("You can`t delete this ship, please, try again");
//            this.Try_delete_ship();
//        }
//    }

//    //расстановка кораблей
//    public void Placement_ships() {
//
//        System.out.println("Hello, " + this.name + ", please, arrange your ships");
//        this.my_field.Print_field();
//        System.out.println("Enter cell coordinates in the following format: 'letter''namber', for example 'b5' or 'A2'");
//
//        while (true) {
//
//            if (this.All_ships_are_placed()) {
//                System.out.println("All ship are placed. Are you finish?");
//                System.out.println("Write yes/no");
//                if (scanner.next() == "yes") {
//                    break;
//                }
//            }
//
//            System.out.println("If you want add ship, write 'add'; if you want delete ship, write 'delete':\n");
//
//            if (scanner.next() == "add") {
//                this.Try_add_ship();
//            }
//            else {
//                if (scanner.next() == "delete") {
//                    this.Try_delete_ship();
//                }
//                else {
//                    System.out.println("wrong input format, please, try again");
//                }
//            }
//
//        }
//
//    }

    public boolean isLife() {
        return this.my_field.Survivors_ships();
    }

}
