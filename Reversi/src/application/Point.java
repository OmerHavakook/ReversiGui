package application;

/**
 * A Point class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Point {

    private int x;
    private int y;

    /**
     * Construct a point given x and y coordinates.
     *
     * @param x
     *            the x coordinate.
     * @param y
     *            the y coordinate.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

   
    /**
     * @param other
     *            point to equal between two points.
     * @return true if equals and false otherwise.
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
     *
     * @param x1
     *            the x value.
     */
    public void setX(int x1) {
        this.x = x1;
    }

    /**
     * set y member.
     *
     * @param y1
     *            the y member.
     */
    public void setY(int y1) {
        this.y = y1;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")" + "\n";
    }
}
