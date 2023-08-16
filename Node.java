//basic Node class that stores the name and coordinates of each department
//used to simplify the dijkstras and pathFinder methods (reduces the number of arrays that need to be worked with by two)
public class Node {
    String name;
    double x, y;

    public Node (String str, double xCoor, double yCoor) {
        name = str;
        x = xCoor;
        y = yCoor;
    }

    public Node (String str) {
        name = str;
        x = 0;
        y = 0;
    }

    public void enterCoords (double xCoords, double yCoords) {
        x = xCoords;
        y = yCoords;
    }
}
