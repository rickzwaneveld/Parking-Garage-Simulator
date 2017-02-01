
package parkeersimulator;

import java.awt.Color;

import parkeersimulator.car.AbstractCar;

public class ParkingSpot {

    public static final int TYPE_AD_HOC = 0;
	public static final int TYPE_PASS = 1;
	public static final int TYPE_HANDI = 2;
	public static final int TYPE_RES = 3;
	public static final int TYPE_COUNT = 4;

	private int type;
	private Color color;
	private AbstractCar car;

	public ParkingSpot() {
	    car = null;
	}

	public static Color getColorForType(int type) {
    	switch (type) {
    	case TYPE_AD_HOC:
    		return Color.red;
    	case TYPE_PASS:
    		return Color.blue;
    	case TYPE_HANDI:
    		return Color.green;
    	case TYPE_RES:
    		return Color.yellow;
    	}
    	return Color.black;
	}

    public void setType(int type) {
    	color = getColorForType(type);
    	this.type = type;
    }
    public int getType() {
    	return type;
    }

	public void setCar(AbstractCar new_car) {
		car = new_car;
	}
    public AbstractCar getCar() {
    	return car;
    }

    public Color getColor() {
    	float c[] = color.getColorComponents(null);
    	float alpha;
    	if (car != null) {
    		alpha = 1.0f;
    	} else {
    		alpha = 0.1f;
    	}
    	return new Color(c[0], c[1], c[2], alpha);
    }
}
