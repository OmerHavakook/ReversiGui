package application;

public class Point {
    private int x;
    private int y;

    /**
     * constructor
     * @param x - the x coordinate
     * @param y - the y coordiante
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this method checks if 2 points are equal by comparing there coordinates
     * @param other - point
     * @return true if the points are equal and false otherwise
     */
    public boolean equals(Point other) {
        if (this.x == other.x && this.y == other.y) {
            return true;
        }
        return false;
    }

    /**
     * @return the x coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y coordinate.
     */
    public int getY() {
        return this.y;
    }

    /**
     * set x member.
     * @param xCoordinate the x value.
     */
    public void setX(int xCoordinate) {
        this.x = xCoordinate;
    }

    /**
     * set y member.
     * @param yCoordinate the y member.
     */
    public void setY(int yCoordinate) {
        this.y = yCoordinate;
    }

}
