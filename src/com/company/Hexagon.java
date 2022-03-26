package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Hexagon {
    private static BufferedImage hexagonImage = null;

    private boolean localNeighbor = false;
    private boolean selected = false;
    private boolean marked = false;

    private final int len, ofs;
    private final int x, y, i, j;

    public final Color selectColor = Color.GREEN;
    public final Color neighborColor = Color.CYAN;
    public final Color markColor = Color.MAGENTA;

    private Hexagon neighborHexagon = null;
    private int neighborNumber;

    private ArrayList<Hexagon> neighborHexagons = new ArrayList<>(6);

    public Hexagon(int len, int x, int y, int ofs, int i, int j) {
        this.len = len;
        this.ofs = ofs;
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
    }

    public static BufferedImage getHexagonImage(){
        if (hexagonImage == null){
            try {
                hexagonImage = ImageIO.read(new File("res/gexa.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return hexagonImage;
    }

    public boolean isLocalNeighbor() {
        return localNeighbor;
    }

    public void setLocalNeighbor(boolean localNeighbor) {
        this.localNeighbor = localNeighbor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void draw(Graphics g){
        if (isSelected()) {
            g.drawImage(getHexagonImage(), x + ofs, y, len, len, selectColor, null);
        } else if (isMarked()){
            g.drawImage(getHexagonImage(), x + ofs, y, len, len, neighborColor, null);
        } else if (isLocalNeighbor()){
            g.drawImage(getHexagonImage(), x + ofs, y, len, len, neighborColor, null);
        } else {
            g.drawImage(getHexagonImage(), x + ofs, y, len, len, null);
        }
    }

    public boolean inRegion(int x, int y) {
        return x >= this.x + ofs && x <= this.x + ofs + len && y >= this.y && y <= this.y + len;
    }

    public Hexagon getNeighborHexagon() {
        return neighborHexagon;
    }

    public void setNeighborHexagon(Hexagon neighborHexagon) {
        this.neighborHexagon = neighborHexagon;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getNeighborNumber() {
        return neighborNumber;
    }

    public void setNeighborNumber(int neighborNumber) {
        this.neighborNumber = neighborNumber;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public ArrayList<Hexagon> getNeighborHexagons() {
        return neighborHexagons;
    }

    public void setNeighborHexagons(ArrayList<Hexagon> neighborHexagons) {
        this.neighborHexagons = neighborHexagons;
    }
}
