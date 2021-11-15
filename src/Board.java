import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
public class Board {
    private Cell[][] cells;
    private Cell[][] debug;
    private Boat[] boats;
    private int turns;
    private int b_remain;
    private int pp;
    private boolean deb;


    public Board(int g) {
        /*if (g == 3) {*/
        this.cells = new Cell[g][g];
        this.debug = new Cell[g][g];
        this.turns = 1;
        this.deb = false;
        //boats = new Boat[1];
        b_remain = 0;
        pp = 1;
        for (int i = 0; i < g; i++) {
            for (int j = 0; j < g; j++) {
                if (cells[i][j] == null) {
                    // Cell jj = new Cell(i, j, '-');
                    cells[i][j] = new Cell(i, j, '-');
                    debug[i][j] = new Cell(i, j, '-');
                }
                // System.out.print(debug[i][j].get_status());
            }
        }
        this.placeBoats(g / 3);
            /*int randir = (int) (Math.random() * 2);

            Cell[] mo = new Cell[2];
            for(int i = 0;i<mo.length;i++){
                if(mo[i]==null){
                    Cell jj = new Cell(i, i, 'B');
                    mo[i]= jj;
                }

            }

            for (int i = 0; i < boats.length; i++) {
                if (randir == 0) {
                    boats[i] = new Boat(2, true, mo);
                    b_remain+=2;
                } else {
                    boats[i] = new Boat(2, false, mo);
                    b_remain+=2;
                }
            }
        }*//* else if (g == 6) {
            cells = new Cell[g][g];
            debug = new Cell[g][g];
            boats = new Boat[3];
            b_remain = 9;
            pp = 3;
            for (int i = 0; i < g; i++) {
                for (int j = 0; j < g; j++) {
                    if (cells[i][j] == null) {
                        Cell jj = new Cell(i, j, '-');
                        cells[i][j] = jj;
                        debug[i][j]= jj;
                    }

                }
            }
            Cell[] mo = new Cell[2];
            for(int i = 0;i<mo.length;i++){
                if(mo[i]==null){
                    Cell jj = new Cell(i, i, 'B');
                    mo[i]= jj;
                }

            }
            for (int i = 0; i < boats.length; i++) {
                int rand = (int) (Math.random() * 2);
                if(rand == 0) {
                    boats[i] = new Boat(2 + i, true, mo);
                    b_remain+=2;
                } else {
                    boats[i] = new Boat(2 + i, false, mo);
                    b_remain+=2;
                }
            }
            b_remain=9;

        } else if (g == 9) {
            cells = new Cell[g][g];
            debug = new Cell[g][g];
            boats = new Boat[5];
            b_remain = 5;
            pp = 5;
            for (int i = 0; i < g; i++) {
                for (int j = 0; j < g; j++) {
                    if (cells[i][j] == null) {
                        Cell jj = new Cell(i, j, '-');
                        cells[i][j] = jj;
                        debug[i][j]= jj;
                    }

                }
            }
            Cell[] mo = new Cell[2];
            for(int i = 0;i<mo.length;i++){
                if(mo[i]==null){
                    Cell jj = new Cell(i, i, 'B');
                    mo[i]= jj;
                }

            }
            for (int i = 0; i < boats.length; i++) {
                int rand = (int) (Math.random() * 2);
                if (rand == 0) {
                    if (i == 3) {
                        boats[i] = new Boat(4, true, mo);
                    } else if (i == 4) {
                        boats[i] = new Boat(5, true, mo);
                    } else if (i == 2) {
                        boats[i] = new Boat(3, true, mo);
                    } else {
                        boats[i] = new Boat(2 + i, true, mo);
                    }


                } else {
                    if (i == 3) {
                        boats[i] = new Boat(4, true, mo);
                    } else if (i == 4) {
                        boats[i] = new Boat(5, true, mo);
                    } else if (i == 2) {
                        boats[i] = new Boat(3, true, mo);
                    } else {
                        boats[i] = new Boat(2 + i, true, mo);
                    }
                }
            }
            for (int i = 0; i < boats.length; i++) {
                System.out.println(boats[i].get_size());
            }
            b_remain=17;
        } else {
            System.out.println("Invalid Board Size.");
        }
       //turns = 0;
*/
    }

    public void setDebug() {
        this.deb = true;
    }

    public void showBoard() {
        if (this.deb) {
            this.print();
        } else {
            this.display();
        }
    }

    public void changeCellStatus(Cell c, char ch) {
        this.cells[c.get_r()][c.get_c()].set_status(ch);
    }

    public void placeBoats(int difficulty) {
        //Creates boats of given sizes and then randomizes orientation
        int numBoats = (2 * difficulty) - 1;
        Boat[] boatArray = new Boat[numBoats];
        for (int i = 0; i < numBoats; i++) {
            if (i != 4) {
                boatArray[i] = new Boat(i + 2, new Random().nextBoolean(), new Cell[i + 2]);
            } else {
                boatArray[i] = new Boat(3, new Random().nextBoolean(), new Cell[3]);
            }
            //System.out.println(boatArray[i]);
        }

        //For each boat it randomly places boats on board
        for (Boat b : boatArray) {
            int boatSize = b.get_size();
            int boardSize = this.cells.length;
            if (b.get_dir()) {
                //board = 6 length
                //2 long boat = column 0...4   == board size - boat Size
                //3 long boat = column 0...3
                //4 long boat = column 0...2
                boolean validLocation = false;
                while (validLocation == false) {
                    int col = new Random().nextInt(boardSize + 1 - boatSize);
                    int row = new Random().nextInt(boardSize);
                    Cell[] c = new Cell[boatSize];
                    for (int i = 0; i < boatSize; i++) {
                        c[i] = new Cell(row, col + i, 'B');
                    }
                    b.setLocation(c);
                    validLocation = isVaildBoatLocation(b);
                    //System.out.println(validLocation);
                }
            } else {
                //board = 6 length
                //2 long boat = row 0...4   == board size - boat Size
                //3 long boat = row 0...3
                //4 long boat = row 0...2
                boolean validLocation = false;
                while (validLocation == false) {
                    int row = new Random().nextInt(boardSize + 1 - boatSize);
                    int col = new Random().nextInt(boardSize);
                    Cell[] c = new Cell[boatSize];
                    for (int i = 0; i < boatSize; i++) {
                        c[i] = new Cell(row + i, col, 'B');
                    }
                    b.setLocation(c);
                    validLocation = isVaildBoatLocation(b);
                    //System.out.println(validLocation);
                }

            }
        }
        this.boats = boatArray;
        this.b_remain = this.boats.length;
        this.pp = this.boats.length;
    } // places boats randomly across board

    public int checkrow(int x, int y, boolean b, int j) {
        int count = 0;
        for (int i = 0; i < cells.length; i++) {
            //if(x==cells.length-1){x=0;}
            //if(i==cells.length-f1){i=0;}
            if (cells[x][i].get_status() == '-') {
                count += 1;
                System.out.println(count);
            } else {
                count = 0;
            }
            if (count == boats[j].get_size()) {
                return y - count + 1;
            }
        }
        return y;
    }

    public boolean isVaildBoatLocation(Boat b) {
        Cell[] boatCoords = b.get_cc();
        for (int i = 0; i < b.get_size(); i++) {
            int row = boatCoords[i].get_r();
            int col = boatCoords[i].get_c();
            //System.out.println(this.boardArray[row][col].get_status());
            if (this.cells[row][col].get_status() == 'B') {
                return false;
            }
        }
        for (int i = 0; i < b.get_size(); i++) {
            this.changeCellStatus(b.get_cc()[i], 'B');
        }
        return true;
    }

    /*public void placeBoats(int d) {
        for (int i = 0; i < boats.length; i++) {
            if (boats[i].get_dir() == true) {
                int randr = (int) (Math.random() * cells.length);
                int randc = (int) (Math.random() * (cells.length - boats[i].get_size() + 1));
                if (cells[randr][randc].get_status() == 'B') {
                    randr = randr + 1;
                }
                for (int x = randc; x < randc + boats[i].get_size(); x++) {
                    if (cells[randr][x].get_status() == '-') {
                        cells[randr][x].set_status('B');
                    }
                    else {
                        x = x;
                    }

                }
            } else {
                int c = (int) (Math.random() * cells.length);
                int r = (int) (Math.random() * (cells.length - boats[i].get_size() + 1));
                //System.out.println(r);
                for (int x = r; x < r + boats[i].get_size(); x++) {
                    if (cells[x][c].get_status() == '-') {
                        cells[x][c].set_status('B');

                    } else {
                        x = x;
                    }

                }
            }
        }

    }*/

    public int getB_remain() {
        return b_remain;
    }

    public int getTurns() {
        return turns;
    }

    public int getPp() {
        return pp;
    }

    public void print() {
        for (int i = 0; i < debug.length; i++) {
            for (int j = 0; j < debug.length; j++) {
                System.out.print(debug[i][j].get_status());
                if (j == debug.length - 1) {
                    System.out.println();
                }
            }
        }
    }

    public void display() {
        if(b_remain!=0){
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells.length; j++) {
                    if(cells[i][j]!=null){
                        System.out.print(cells[i][j].get_status() + " ");
                    }

                }
                System.out.println();
            }
            for (Boat b : this.boats) {
                if (b != null) {
                    String res = ("Boat Data: size = " + b.get_size() + " coordinates = ");
                    Cell[] coords = b.get_cc();
                    for (Cell c : coords) {
                        res += ("(" + c.get_r() + ", " + c.get_c() + ") ");
                    }
                    System.out.println(res);
                } else {
                    System.out.println("Boat Data: Sunk");
                }
        }

        }

    }

    public void boatSunk() {
        for (int i = 0; i < this.cells.length; i++) {
            //System.out.println(this.boatArray[i]);
            int x = 0;
            if (this.cells[i] != null) {
                boolean sunk = true;
                if (i >= boats.length) {
                    x++;
                } else {
                    for (Cell c : this.boats[i].get_cc()) {
                        //System.out.println(this.boardArray[c.getRow()][c.getCol()].get_status());
                        if(this.cells[c.get_r()]==null || this.cells[c.get_c()] == null){
                            System.out.println(this.cells[c.get_r()][c.get_c()].get_status());
                        }
                        else if (this.cells[c.get_r()][c.get_c()].get_status() == 'B') {
                            //System.out.println("not sunk");
                            sunk = false;
                        }
                    }
                    if (sunk) {
                        //System.out.println("sunk");
                        this.cells[i] = null;
                        this.b_remain--;
                    }
                }
            }

        }
    } // updated the remaining boats variable

    public void fire(int x, int y) {
        int count = 0;
        for (int i = 0; i < 1; i++) {
            //for(int j = 0;j<boats[i].get_size();j++) {
            if (cells[x][y].get_status() == 'B') {
                Cell targetedCell = this.cells[x][y];
                Cell displayCell = this.debug[x][y];
                targetedCell.set_status('H');
                displayCell.set_status('H');
                int boatsRemainingBeforeShot = this.b_remain;
                this.boatSunk();
                //System.out.println("Hit");
                /*debug[x][y].set_status('H');
                cells[x][y].set_status('H');*/

                 if (boatsRemainingBeforeShot > this.b_remain) {
                    System.out.println("Turn " + this.turns + ":sunk");
                    System.out.println(b_remain);
                    //b_remain = 0;
                } else {
                    System.out.println("Turn " + this.turns + ": hit");
                }
                turns += 1;
                //System.out.println(b_remain);
            } else if (x < 0 || y < 0 || boats[i].get_c(i).get_status() == 'M' || x >= this.cells.length || y >= this.cells.length) {
                System.out.println("Penalty input not in range, lose next turn");
                turns += 2;
            } else {
                System.out.println("miss");
                debug[x][y].set_status('M');
                cells[x][y].set_status('M');
                turns += 1;
            }

        }
        /*for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if(cells[i][j]!=null){
                    if (cells[i][j].get_status() == 'H') {
                        count += 1;
                        if (count == boats[0].get_size()) {
                            System.out.println("Sunk");
                            break;
                        }
                    }
                }*/



        }


    public void missile(int x, int y) {
        if (pp > 0) {
            pp -= 1;
            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (x < 0 || x > cells.length || y < 0 || y > cells.length || i < 0 || j < 0 || j >= cells.length || i >= cells.length) {
                        x = x;
                    } else {
                        cells[i][j].set_status('H');
                        if(cells.length==3){
                            b_remain = 0;
                        }
                    }
                }

            }
        } else {
            System.out.println("Missile has been used the max amount of times");
        }
    }

    public void drone() {
        if (pp > 0) {
            pp -= 1;
            int randr = (int) (Math.random() * (cells.length));
            int count = 0;
            //for(int i = randr;i<cells.length;i++){
            for (int j = 0; j < cells.length; j++) {
                if (cells[randr][j].get_status() == 'B' || cells[randr][j].get_status() == 'H') {
                    count += 1;
                }
            }
            System.out.println("Drone has scanned " + count + " targets in row " + randr);
        } else {
            System.out.println("Drone has been used the max amount of times");
        }

    }

    public void submarine(int x, int y) {
        Cell[] moa = new Cell[boats[0].get_cc().length];
        if (pp > 0) {
            pp -= 1;
            if (cells[x][y].get_status() == 'H' || cells[x][y].get_status() == 'B') {
                moa = boats[0].get_cc();
                for (int i = 0; i < moa.length; i++) {
                    cells[moa[i].get_c()][moa[i].get_r()].set_status('H');
                    //System.out.println("Sunk");
                }
            } else {
                System.out.println("miss");
            }
        } else {
            System.out.println("Submarine has been used the max amount of times");
        }
    }
}

