import java.util.*;

public class Dijkstra {
//    private static double[][] coords = {
//        {2.25, 0.50},
//        {4.75, 0.25},
//        {2.75, 0.375},
//        {0.875, 0.75},
//        {4.75, 3.00},
//        {2.75, 2.875},
//        {3.25, 3.25},
//        {4.75, 1.75},
//        {6.25, 0.875},
//        {6.625, 2.00}
//    }, dist;
    private static double[][] coords = {
            {15.0, 10.0},
            {23.0, 11.0},
            {16.0, 11.0},
            {9.50, 9.00},
            {24.0, 2.00},
            {16.5, 2.00},
            {18.5, 1.00},
            {22.5, 6.00},
            {28.0, 9.00},
            {29.5, 4.00}
        }, dist;
//    private static boolean[][] walls = {
//      {false,  true,  true,  true,  true,  true,  true,  true,  true,  true},
//      { true, false,  true,  true, false,  true, false, false, false,  true},
//      { true,  true, false,  true,  true,  true,  true,  true,  true,  true},
//      { true,  true,  true, false,  true,  true,  true,  true,  true,  true},
//      { true, false,  true,  true, false, false, false, false, false, false},
//      { true,  true,  true,  true, false, false, false, false,  true,  true},
//      { true, false,  true,  true, false, false, false, false,  true, false},
//      { true, false,  true,  true, false, false, false, false, false,  true},
//      { true, false,  true,  true, false,  true,  true, false, false, false},
//      { true,  true,  true,  true, false,  true, false,  true, false, false}
//    };
    private static boolean[][] walls = {
    	{false,  true,  true,  true,  true,  true,  true,  true,  true,  true},
    	{ true, false, false, false, false,  true, false, false, false,  true},
    	{ true, false, false, false,  true, false, false, false, false,  true},
    	{ true, false, false, false,  true, false, false,  true,  true,  true},
    	{ true, false,  true,  true, false, false, false, false, false, false},
    	{ true,  true, false, false, false, false, false, false,  true,  true},
    	{ true, false, false, false, false, false, false, false,  true, false},
    	{ true, false, false,  true, false, false, false, false, false,  true},
    	{ true, false, false,  true, false,  true,  true, false, false, false},
    	{ true,  true,  true,  true, false,  true, false,  true, false, false}
      };
    private static double[] weights = {0.00, 0.00, 1.00, 1.05, 0.77, 0.90, 0.875, 0.85, 0.65, 0.70};
    private static String[] allDeps = {"Entrance", "Checkout", "Floral", "Produce", "Meat", "Bakery", "Seafood", "Grocery", "Frozen", "Dairy"};
    private static ArrayList<String> targets;
    private static ArrayList<Integer> targetsInts;
    private static HashMap<String, Integer> allDepsMap = new HashMap<>();
    private static Node[] deps;
    private static int len;
    
    //pass in necessary departments for customer item pick-up
    //fills in all needed arrays(works sort of like a constructor in terms of this program)
    public static void neededDepartments(ArrayList<String> departments) {
        for (int i = 0; i < allDeps.length; i++) {allDepsMap.put(allDeps[i], i);}

        len = allDeps.length;
        deps = new Node[len];
        targets = new ArrayList<>(departments);
        targetsInts = new ArrayList<>();

        for (int i = 0; i < targets.size(); i++) {
            targetsInts.add(allDepsMap.get(targets.get(i)));
        }
        for (int i = 0; i < len; i++) {deps[i] = new Node(allDeps[i]);} //coords
        enterCoords(coords, deps);
    }

    private static void enterCoords(double[][] coords, Node[] deps) {
        for (int i = 0; i < len; i++) {
            deps[i].enterCoords(coords[i][0], coords[i][1]);
        }

        findDists(); //call if distances aren't entered in manually
    }

    private static void findDists() {
        dist = new double[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if (walls[i][j]) {dist[i][j] = dist[j][i] = -1;}
                else {
                	dist[i][j] = weights[j]*Math.sqrt(Math.pow(Math.abs(deps[j].x - deps[i].x), 2) + Math.pow(Math.abs(deps[j].y - deps[i].y), 2));
                	dist[j][i] = weights[i]*Math.sqrt(Math.pow(Math.abs(deps[j].x - deps[i].x), 2) + Math.pow(Math.abs(deps[j].y - deps[i].y), 2));
                }
            }
        }
    }

    public static ArrayList<Node> pathFinder() {
        return pathFinder(dist, deps[1], targetsInts);
    }

    private static ArrayList<Node> pathFinder(double[][] dist, Node source, ArrayList<Integer> targets) {
        Double[][] List1 = new Double[targets.size()][2]; //stores each target and its quickest path from source(dijk)
        ArrayList<ArrayList<Integer>> List2 = new ArrayList<>(); //stores each node within quickest paths to each target labeled with that target
        ArrayList<Node> path = new ArrayList<>(); path.add(source); //stores best path and adds source
        int s = allDepsMap.get(source.name), l1Ptr = -1; //instatiates integer corresponding to source and pointer for list1

        //keeps looping until every target node (besides Checkout) has been added to the path
        while (targets.size() > 0) {

            //look through every node in targets list and do dijkstras, storing values in L1 and L2
            for (int i = 0; i < targets.size(); i++) {
                ArrayList<Integer> pathTi = dijkstras(dist, s, targets.get(i));
                double pathTLen = 0;
                for (int j = 1; j < pathTi.size(); j++) {
                    pathTLen += dist[pathTi.get(j-1)][pathTi.get(j)];
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(pathTi.get(j)); temp.add(targets.get(i));
                    List2.add(temp);
                }
                List1[++l1Ptr][0] = (double)targets.get(i);
                List1[l1Ptr][1] = pathTLen;
            }

            Arrays.sort(List1, (x,y) -> Double.compare(x[1], y[1])); //sorts L1 based on dijk path length

            //find closest node to source using List1
            int closestDest = (int)Math.round(List1[0][0]);

            //add path to closest Node
            for (int i = 0; i < List2.size(); i++) {
                if (List2.get(i).get(1) == closestDest) {
                    path.add(deps[List2.get(i).get(0)]);
                }
            }

            s = closestDest; //closest node is new source

            targets.remove(Integer.valueOf(closestDest)); //remove new source
            List1 = new Double[targets.size()][2]; //reinstatiate lists and pointer
            List2 = new ArrayList<>();
            l1Ptr = -1;
        }
        Collections.reverse(path);
        return path;
    }

    private static ArrayList<Integer> dijkstras(double[][] dist, int source, int target) {
        double[] distance = new double[len]; //distance from source
        int[] prev = new int[len]; //gives quickest path from source to target using "previous" nodes in path
        boolean[] used = new boolean[len]; //stores which nodes have been visited
        for (int i = 0; i < len; i++) {distance[i] = (i==source)?0:Double.MAX_VALUE; prev[i] = -1;} //fills arrays with correct values
        ArrayList<Integer> bothersome = new ArrayList<>(); bothersome.add(source); //unsettled nodes (priority queue replacement)

        //loop while there are unsettled nodes
        while(bothersome.size() > 0) {
            int curr = bothersome.get(0);
            for (int i = 1; i < bothersome.size(); i++) {
                if (!used[bothersome.get(i)] && distance[bothersome.get(i)] < distance[curr]) {curr = bothersome.get(i);}
            }
        
            bothersome.remove(Integer.valueOf(curr));
            used[curr] = true;

            for (int i = 0; i < len; i++) {
                if (dist[curr][i] <= 0 || used[i]) {continue;}
                double temp = distance[curr] + dist[curr][i];
                if (temp < distance[i]) {
                    distance[i] = temp;
                    prev[i] = curr;
                }

                bothersome.add(i);
            }
        }

        ArrayList<Integer> path = new ArrayList<>();
        while (target != -1) {
            path.add(target);
            target = prev[target];
        }

        Collections.reverse(path);
        return path;
    }
}
