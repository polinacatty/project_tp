package test;

import org.example.Cell;
import org.example.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    Cell cell_1 = new Cell(5, 6);
    Cell cell_2 = new Cell(5, 7);
    Cell cell_3 = new Cell(6, 1);
    Cell cell_4 = new Cell(6, 1);
    Cell cell_5 = new Cell(7, 9);

    Cell cell_6 = new Cell("a2");
    Cell cell_7 = new Cell("b6");
    Cell cell_8 = new Cell("c8");
    Cell cell_9 = new Cell("i9");
    Cell cell_10 = new Cell("G2");

    Ship ship_1 = new Ship(cell_1, cell_2);



    @Test
    void getter_size() {
    }

    @Test
    void getter_begin() {
    }

    @Test
    void getter_end() {
    }

    @Test
    void state() {
    }

    @Test
    void return_state___() {
    }

    @Test
    void cell_in_ship() {
    }

    @Test
    void getter_cell() {
    }

    @Test
    void vertical() {
    }
}