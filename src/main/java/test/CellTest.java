package test;

import org.example.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    Cell cell_1 = new Cell(5, 6);
    Cell cell_2 = new Cell(0, 7);
    Cell cell_3 = new Cell(6, 1);
    Cell cell_4 = new Cell(6, 1);
    Cell cell_5 = new Cell(7, 9);

    Cell cell_6 = new Cell("a2");
    Cell cell_7 = new Cell("b6");
    Cell cell_8 = new Cell("c8");
    Cell cell_9 = new Cell("i9");
    Cell cell_10 = new Cell("G2");


    @Test
    void getter_coordinate_X() {
        int actual_1 = cell_1.Getter_coordinate_X();
        int actual_2 = cell_2.Getter_coordinate_X();
        int actual_3 = cell_3.Getter_coordinate_X();
        int actual_4 = cell_4.Getter_coordinate_X();
        int actual_5 = cell_5.Getter_coordinate_X();
        int actual_6 = cell_6.Getter_coordinate_X();
        int actual_7 = cell_7.Getter_coordinate_X();
        int actual_8 = cell_8.Getter_coordinate_X();
        int actual_9 = cell_9.Getter_coordinate_X();
        int actual_10 = cell_10.Getter_coordinate_X();

        assertEquals(5, actual_1);
        assertEquals(0, actual_2);
        assertEquals(0, actual_6);
        assertEquals(1, actual_7);
    }

    @Test
    void getter_coordinate_Y() {
        int actual_1 = cell_1.Getter_coordinate_Y();
        int actual_2 = cell_2.Getter_coordinate_Y();
        int actual_3 = cell_3.Getter_coordinate_Y();
        int actual_4 = cell_4.Getter_coordinate_Y();
        int actual_5 = cell_5.Getter_coordinate_Y();
        int actual_6 = cell_6.Getter_coordinate_Y();
        int actual_7 = cell_7.Getter_coordinate_Y();
        int actual_8 = cell_8.Getter_coordinate_Y();
        int actual_9 = cell_9.Getter_coordinate_Y();
        int actual_10 = cell_10.Getter_coordinate_Y();

        assertEquals(6, actual_1);
        assertEquals(7, actual_2);
        assertEquals(1, actual_6);
        assertEquals(5, actual_7);
    }

    @Test
    void setter_coordinate_X() {
    }

    @Test
    void setter_coordinate_Y() {
    }

    @Test
    void testEquals() {
        boolean actual_1 = cell_3.equals(cell_4);
        boolean actual_2 = cell_3.equals(cell_5);
        boolean actual_3 = cell_3.equals(cell_6);
        boolean actual_4 = cell_3.equals(cell_10);

        assertEquals(true, actual_1);
        assertEquals(false, actual_2);
        assertEquals(false, actual_3);
        assertEquals(true, actual_4);
    }

    @Test
    void cells_on_one_line() {
        boolean actual_1 = cell_3.Cells_on_one_line(cell_4);
        boolean actual_2 = cell_3.Cells_on_one_line(cell_5);
        boolean actual_3 = cell_2.Cells_on_one_line(cell_6);
        boolean actual_4 = cell_5.Cells_on_one_line(cell_7);

        assertEquals(true, actual_1);
        assertEquals(false, actual_2);
        assertEquals(true, actual_3);
        assertEquals(false, actual_4);

    }

    static class ShipTest {

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
}