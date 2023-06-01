import java.util.Arrays;
import java.util.Collections;
import doodlepad.*;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        Window window = new Window(1200, 700, 100);
    }
}

class Bar extends Rectangle {

    private double value;
    private int numBars;
    private int place;
    private int screenWidth;
    private int screenHeight;

    public Bar(int place, int numBars, int value, int screenWidth, int screenHeight) {
       super(0, 0, 0, 0);

       this.value = value;
       this.numBars = numBars;
       this.place = place;
       this.screenWidth = screenWidth;
       this.screenHeight = screenHeight;
       
       setupBar();
    }

    public void setupBar() {
        //set color
        //find height
        //find position and width
        int whiteSpace = 2;
        double barSpace = screenWidth/numBars; //good
        double barWidth = barSpace - whiteSpace; //good
        super.setWidth(barWidth);
        super.setFillColor(252, 127, 3);
        //current_rect = ((current_bar * bar_space) + (white_space/2), bar_top, bar_width, bar_height)
        super.setHeight(screenHeight * value / numBars);
        super.setY(screenHeight - super.getHeight());
        super.setX(place * barSpace + (whiteSpace/2));
    }

    public void setPlace(int place) { //move the bar based on new place
        // do the math
        double barSpace = screenWidth/numBars; //good
        this.place = place;
        super.setX(place * barSpace);// do math
    }

    public double getValue() {
        return value;
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

        //background
        Rectangle r = new Rectangle(0, 0, w, h);
        r.setFillColor(46, 51, 61);

        //set value of bars from 1 to numBars
        for(int i = 0; i < numBars; i++) {
            Bar temp = new Bar(i, numBars, i + 1, w, h);
            bars[i] = temp;
        }

        for (Bar bar : bars){
            System.out.println(bar);
        }
        
        //ArrayList<Bar> list = 
        Collections.shuffle(Arrays.asList(bars));
        quickSortIterative(bars, 0, numBars - 1);
        updatePlace();
    }
    
    public void updatePlace() {
        try{
            TimeUnit.MILLISECONDS.sleep(250);
        } catch(Exception e){
            System.out.println(e + "uh oh");
        }
        for (int i = 0; i < numBars; i++) {
            bars[i].setPlace(i);
        }
    }

	/* This function takes last element as pivot,
	places the pivot element at its correct
	position in sorted array, and places all
	smaller (smaller than pivot) to left of
	pivot and all greater elements to right
	of pivot */
	static int partition(Bar arr[], int low, int high)
	{
		int pivot = (int)arr[high].getValue();

		// index of smaller element
		int i = (low - 1);
		for (int j = low; j <= high - 1; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (arr[j].getValue() <= pivot) {
				i++;

				// swap arr[i] and arr[j]
				Bar temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		Bar temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}

	/* A[] --> Array to be sorted,
l --> Starting index,
h --> Ending index */
	public void quickSortIterative(Bar arr[], int l, int h)
	{   
		// Create an auxiliary stack
		int[] stack = new int[h - l + 1];

		// initialize top of stack
		int top = -1;

		// push initial values of l and h to stack
		stack[++top] = l;
		stack[++top] = h;

		// Keep popping from stack while is not empty
		while (top >= 0) {
            updatePlace();
			// Pop h and l
			h = stack[top--];
			l = stack[top--];

			// Set pivot element at its correct position
			// in sorted array
			int p = partition(arr, l, h);

			// If there are elements on left side of pivot,
			// then push left side to stack
			if (p - 1 > l) {
				stack[++top] = l;
				stack[++top] = p - 1;
			}

			// If there are elements on right side of pivot,
			// then push right side to stack
			if (p + 1 < h) {
				stack[++top] = p + 1;
				stack[++top] = h;
			}
		}
	}
}