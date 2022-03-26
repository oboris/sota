package com.company;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameScreen extends BaseScreen {

    public final int num = 15;
    private final Hexagon[][] hexagons = new Hexagon[num][num];
    public GameScreen() {
        super();

        int ofs;
        int len = 40;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (j % 2 == 0){
                    ofs = 10;
                } else {
                    ofs = 10 + len / 2;
                }
                hexagons[i][j] = new Hexagon(len, i * len, j * len , ofs, i, j);
            }
        }
        addMouseListener(new MAdapter());

        start();
    }

    @Override
    public void update(long tick) {
        requestFocus();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                hexagons[i][j].draw(g);
            }
        }
    }

    private class MAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    hexagons[i][j].setSelected(hexagons[i][j].inRegion(e.getX(), e.getY()));
                    hexagons[i][j].setLocalNeighbor(false);
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (hexagons[i][j].inRegion(e.getX(), e.getY()) && hexagons[i][j].isSelected()) {
                        findNeighbor(i, j);
                    } else {
                        hexagons[i][j].setSelected(false);
                    }
                }
            }
        }
    }

    private final int oddNeighborChain[][] = {{1, 0}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}};
    private final int evenNeighborChain[][] = {{1, 0}, {1, 1}, {0, 1}, {-1, 0}, {0, -1}, {1, -1}};
    private void findNeighbor(int i, int j) {
        if (i > 0){
            hexagons[i - 1][j].setLocalNeighbor(true); //(-1, 0) - 3
            hexagons[i - 1][j].setNeighborHexagon(hexagons[i][j]);
            hexagons[i - 1][j].setNeighborNumber(3);
        }
        if (i < num - 1){
            hexagons[i + 1][j].setLocalNeighbor(true); //(1, 0) - 0
            hexagons[i + 1][j].setNeighborHexagon(hexagons[i][j]);
            hexagons[i + 1][j].setNeighborNumber(0);
        }
        if (j > 0) {
            hexagons[i][j - 1].setLocalNeighbor(true); //(0, -1) - 5 (4)
            hexagons[i][j - 1].setNeighborHexagon(hexagons[i][j]);
            if (j % 2 == 0){
                hexagons[i][j - 1].setNeighborNumber(5);
            } else {
                hexagons[i][j - 1].setNeighborNumber(4);
            }
        }
        if (j < num - 1) {
            hexagons[i][j + 1].setLocalNeighbor(true); //(0, 1) - 1 (2)
            hexagons[i][j + 1].setNeighborHexagon(hexagons[i][j]);
            if (j % 2 == 0){
                hexagons[i][j + 1].setNeighborNumber(1);
            } else {
                hexagons[i][j + 1].setNeighborNumber(2);
            }
        }

        if (j % 2 ==0){
            if (j > 0 && i > 0) {
                hexagons[i - 1][j - 1].setLocalNeighbor(true); //(-1, -1) - 4
                hexagons[i - 1][j - 1].setNeighborHexagon(hexagons[i][j]);
                hexagons[i - 1][j - 1].setNeighborNumber(4);
            }
            if (j < num - 1 && i > 0) {
                hexagons[i - 1][j + 1].setLocalNeighbor(true); //(-1, 1) - 2
                hexagons[i - 1][j + 1].setNeighborHexagon(hexagons[i][j]);
                hexagons[i - 1][j + 1].setNeighborNumber(2);
            }
        } else {
            if (i < num - 1) {
                hexagons[i + 1][j - 1].setLocalNeighbor(true); //(1, -1) - (5)
                hexagons[i + 1][j - 1].setNeighborHexagon(hexagons[i][j]);
                hexagons[i + 1][j - 1].setNeighborNumber(5);
            }
            if (j < num - 1 && i < num - 1) {
                hexagons[i + 1][j + 1].setLocalNeighbor(true); //(1, 1) - (1)
                hexagons[i + 1][j + 1].setNeighborHexagon(hexagons[i][j]);
                hexagons[i + 1][j + 1].setNeighborNumber(1);
            }
        }
    }
}
