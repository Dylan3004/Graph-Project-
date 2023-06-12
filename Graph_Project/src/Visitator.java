public class Visitator {

    private int numberOfVisited;

    public Visitator()
    {numberOfVisited=0;}
    public void visit()
    {
        numberOfVisited+=1;
        System.out.println("Number of visited vertices : "+numberOfVisited);
    }
    public void reset()
    {numberOfVisited=0;}
    int GetNumberOfVisited()
    {return numberOfVisited;}
}
