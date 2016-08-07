package Root.GameObjects;
/**
 @author Tazim
 Class Description:
 Inherits Circle class and implements Runnable as All movable objects will have a Thread attatched.
 Funcions:
 MoveDirections(); common in most movable objects
 intersect():Bool; to test if this object intersects with something



 */
import Root.GameObjects.PickUps.Pickup;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public abstract class MovableObject extends Circle implements Runnable {
    private double speed;
    Thread thisThread;

    double leftBound;
    double rightBound;
    double lowerBound;
    double upperBound;

    MovableObject(double centerX, double centerY, double radius, String color) {
        super(centerX, centerY, radius, Paint.valueOf(color));
        setSpeed(1);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    boolean intersect(MovableObject object) {
        return (object.intersects(this.getBoundsInParent()));
    }
    boolean intersect(Pickup object) {
        return (object.intersects(this.getBoundsInParent()));
    }

    void setBounds(){
        lowerBound = this.getCenterY() + this.getRadius();
        upperBound = this.getCenterX() - this.getRadius();
        leftBound = this.getCenterX() - this.getRadius();
        rightBound = this.getCenterX() + this.getRadius();
    }

    void moveRight() {
        this.setCenterX(this.getCenterX() + speed);
    }

    void moveLeft() {
        this.setCenterX(this.getCenterX() - speed);
    }

    void moveDown() {
        this.setCenterY(this.getCenterY() + speed);
    }

    void moveUp() {
        this.setCenterY(this.getCenterY() - speed);
    }
}
