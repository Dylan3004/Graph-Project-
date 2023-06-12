import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Wpisz ilość metali");

        Scanner scanner = new Scanner(System.in);

        int numberOfMetals= Integer.parseInt(scanner.nextLine());

        //System.out.println(numberOfMetals);

        System.out.println("Wpisz poszczegolne ceny metali");
        //scanner.nextLine();
        ArrayList<Integer> costsOfMetals =  new ArrayList<>();
       for(int i=0;i<numberOfMetals;i++)
        {
                int cost = Integer.parseInt(scanner.nextLine());
                costsOfMetals.add(cost);
            System.out.println("Added cost of metal: "+(i+1)+" as "+cost);
        }
        Graph graph = new Graph(true,numberOfMetals,costsOfMetals);
        System.out.println("Write a number of recepies");
        int numberOfTransformations = Integer.parseInt(scanner.nextLine());
        System.out.println("Write a recepies");
        for(int i=0;i<numberOfTransformations;i++)
        {
            String procesRecipie = String.valueOf(scanner.nextLine());
            String[] numbers = procesRecipie.split(" ");
            graph.AddEdge(Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1]),Integer.parseInt(numbers[2]));
            System.out.println("Added edge between "+numbers[0]+" and "+numbers[1]+" with cost of "+numbers[2]);
            int left = numberOfTransformations-1;
            System.out.println("There are: " +(numberOfTransformations-1-i)+ " edges left to add");
        }

        // test 1
        // +1
//        graph.AddEdge(0,1,10);
//        graph.AddEdge(0,2,5);
//        graph.AddEdge(1,0,25);
//        graph.AddEdge(2,1,10);
//        graph.AddEdge(2,3,5);
//        graph.AddEdge(3,0,50);
        // +1
        // test 2
//        graph.AddEdge(0,1,10);
//        graph.AddEdge(0,2,5);
//        graph.AddEdge(1,0,25);
//        graph.AddEdge(2,1,10);
//        graph.AddEdge(2,3,5);
//        graph.AddEdge(1,0,16);
//        graph.AddEdge(3,0,50);
//        graph.AddEdge(0,4,30);
//        graph.AddEdge(4,0,360);
//        graph.AddEdge(4,2,8);
        // test 3
//        graph.AddEdge(1,2,10);
//        graph.AddEdge(1,3,5);
//        graph.AddEdge(4,2,10);
//        graph.AddEdge(3,2,10);
//        graph.AddEdge(3,4,5);
//        graph.AddEdge(2,1,16);
//        graph.AddEdge(4,1,50);
//        graph.AddEdge(1,5,12);
//        graph.AddEdge(5,1,360);
//        graph.AddEdge(5,3,8);



        graph.showAdjancyMatrix();
        System.out.println(graph.floyd());
        //graph.showCostsMatrix();


    }
}