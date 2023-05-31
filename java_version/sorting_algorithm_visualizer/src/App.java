import doodlepad.*;

public class App {
    public static void main(String[] args) throws Exception {
        Window window = new Window(1280, 720, 100);
    }
}

class Bar extends Rectangle {

    private double value;
    private int numBars;
    private int place;

    public Bar(int place, int numBars, int value) {
       super(0, 0, 0, 0);

       this.value = value;
       this.numBars = numBars;
       this.place = place;
       
       setupBar();
    }

    private void setupBar() {
        //set color
        //find height
        //find position and width
    }

    public void setPlace(int place) { //move the bar based on new place
        // do the math
        super.setX(place);// do math
    }

    public String toString() {
        return "There is a bar at place: " + place + " and width value: " + value;
    }
}

class Window extends Pad { //base class for the particle system

    private int numBars;
    private Bar[] bars;

    public Window(int w, int h, int n) {
        super("Display", w, h);

        numBars = n;
        bars = new Bar[numBars];

        //set value of bars from 1 to numBars
        for(int i = 0; i < numBars; i++) {
            Bar temp = new Bar(i, numBars, i + 1);
            bars[i] = temp;
        }

        for (Bar bar: bars) {
            System.out.println(bar);
        }
        
        //background
        Rectangle r = new Rectangle(0, 0, w, h);
        r.setFillColor(46, 51, 61);
    }
}