import java.util.ArrayList;
import java.util.Collections;

public class Graph {

    private ArrayList<Vertex>vertices = new ArrayList<>();
    private ArrayList<ArrayList<Edge>>adjancyMatrix =  new ArrayList<>();
    private boolean isDirected;
    public int numberOfEdges;
    public int numberOfVertices;
    ArrayList<ArrayList<Integer>>costsMatrix =  new ArrayList<>();
    ArrayList<Integer> totalPrizes = new ArrayList<>();
    ArrayList<Integer>costOfMetals;


    public Graph(boolean directed,int n, ArrayList<Integer>costOfMetals)
    {
        isDirected=directed;
        numberOfVertices=n;
        numberOfEdges=0;
        this.costOfMetals = costOfMetals;
        for(int i=0;i<numberOfVertices;i++)
        {
            vertices.add(new Vertex(i,costOfMetals.get(i)));
        }
        for(int i=0;i<numberOfVertices;i++)
        {
            ArrayList<Edge> row = new ArrayList<>();
            for(int j=0;j<numberOfVertices;j++)
            {
               row.add(null);
            }
            adjancyMatrix.add(row);
        }

    }
    public int getNumberOfEdges()
    {return numberOfEdges;}
    public int getnumberOFVertices()
    {return numberOfVertices;}
    public boolean isDirected()
    {return isDirected;}
    public Vertex SelectVertex(int n)
    {
        if(n<numberOfVertices)
        {
            return vertices.get(n);
        }
        return null;
    }
    public Edge SelectEdge(int u, int v)
    {
        if (u<numberOfVertices && v<numberOfVertices)
        {
            return adjancyMatrix.get(u).get(v);
        }
        return null;
    }
    public void AddEdge (int u, int v,int weight)
    {
        if(isDirected)
        {
            adjancyMatrix.get(u-1).set(v-1,new Edge(vertices.get(u-1),vertices.get(v-1),weight));
            numberOfEdges++;
        }
        else {
            adjancyMatrix.get(u).set(v,new Edge(vertices.get(u),vertices.get(v),weight));
            adjancyMatrix.get(v).set(u, adjancyMatrix.get(u).get(v));
            numberOfEdges++;
        }
    }
    public boolean IsEdge(int u, int v)
    {
        if (u<numberOfVertices && v<numberOfVertices)
        {
            return adjancyMatrix.get(u).get(v) != null;
        }
        return false;
    }
    public void MakeNull()
    {
        for(int i=0;i<numberOfVertices;i++)
        {
            for(int j=0;j<numberOfVertices;j++)
            {
                adjancyMatrix.get(i).set(j,null);
            }

        }
    }
    public void showAdjancyMatrix()
    {
        for(int i=0;i<numberOfVertices;i++)
        {
            for(int j=0;j<numberOfVertices;j++)
            {
                if(adjancyMatrix.get(i).get(j)!=null)
                {
                    System.out.print(adjancyMatrix.get(i).get(j).weight+" ");
                }
                else
                {
                    System.out.print(adjancyMatrix.get(i).get(j)+" ");
                }

            }
            System.out.println();
        }
    }
    public void showCostsMatrix()
    {
        for(int i=0;i<numberOfVertices;i++)
        {
            for(int j=0;j<numberOfVertices;j++)
            {
                System.out.print(costsMatrix.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }
    Integer floyd ()
    {
        // make a matrix ready

        for(int i=0;i<numberOfVertices;i++)
        {
            ArrayList<Integer> row = new ArrayList<>();
            for(int j=0;j<numberOfVertices;j++)
            {
                if(adjancyMatrix.get(i).get(j)!=null)
                {
                    row.add(j,adjancyMatrix.get(i).get(j).weight);
                } else if (i==j) {
                    row.add(j,0);
                }
                else {
                    row.add(j,Integer.MAX_VALUE/2);
                }
            }
            costsMatrix.add(row);
        }
        //showCostsMatrix();
        // main algorithm
        for(int k=0;k<numberOfVertices;k++)
        {
            for(int i=0;i<numberOfVertices;i++)
            {
                for(int j=0;j<numberOfVertices;j++)
                {
                  if(costsMatrix.get(i).get(j) > costsMatrix.get(i).get(k) + costsMatrix.get(k).get(j))
                      costsMatrix.get(i).set(j, costsMatrix.get(i).get(k) + costsMatrix.get(k).get(j));
                }
            }
        }
        showCostsMatrix();
        // finging a best path + path prize
        for(int i =1;i<numberOfVertices;i++)
        {
            totalPrizes.add(costsMatrix.get(0).get(i)+costsMatrix.get(i).get(0)+costOfMetals.get(i)/2);
            //System.out.println(totalPrizes.get(i-1));
        }
        // sorting to find the best value
        Collections.sort(totalPrizes);
        //System.out.println(totalPrizes.get(0));
        System.out.print("The answer is: ");
        return totalPrizes.get(0);
    }

    //Iterators added because of necessity

    static class AllEdgesIter
    {
        private int col;
        private int row;
        private Graph graph;


        AllEdgesIter(Graph graph)
    {
        row=0;
        col=0;
    }
        void ShowColRowNumber()
        {
            System.out.println(row);
            System.out.println(col);
        };
        void next()
        {
            if(row != 0 || col != 0)
            {
                col=(col+1)%graph.numberOfVertices;
            }
            if(graph.isDirected())
            {
                for(int i=row;i<graph.numberOfVertices;i++)
                {
                    for(int j=col;j<graph.numberOfVertices;j++)
                    {
                        if(graph.SelectEdge(i,j)!= null)
                        {
                            row=i;
                            col=j;
                            return;
                        }
                    }
                    col=0;
                }
                row=graph.numberOfVertices;
                col=graph.numberOfVertices;
            } else
            {

                for(int i=row;i<graph.numberOfVertices;i++)
                {
                    for(int j=col;j<graph.numberOfVertices;j++)
                    {
                        if(graph.SelectEdge(i,j)!= null)
                        {
                            row=i;
                            col=j;
                            return;
                        }
                    }
                    col=row+1;
                }
                row=graph.numberOfVertices;
                col=graph.numberOfVertices;
            }
        }
        boolean IsDone()
        {
            if(col == graph.numberOfVertices-1 && row == graph.numberOfVertices-1)
            {
                return true;
            }
            return false;
        }
        Edge operatorStar()
        {
            return graph.SelectEdge(row,col);
        }
        void operatorPlus(){next();}
    }

    static class AllVerticesIter {
        Graph graph;
        public int current;

        AllVerticesIter(Graph graph) {
            current = -1;
            this.operatorPlus();
        }

        boolean IsDone() {
            if (current == graph.numberOfVertices) {
                return true;
            }
            return false;
        }

        Vertex operatorStar() {
            return graph.SelectVertex(current);
        }

        void operatorPlus() {
            current += 1;
        }

    }
    class EmanEdgesIter {
        Graph graph;
        int row;
        int col;
        
        EmanEdgesIter(Graph graph,int verticesrow)
        {
            this.graph=graph;
            row = verticesrow;
            col = -1;
            next();
        }
        void next()
        {
            for(int i=col+1;i<graph.numberOfVertices;i++)
            {
                if(graph.SelectEdge(row,i)!= null)
                {
                    col=i;
                    return;
                }

            }
            col=graph.numberOfVertices;
        }
        boolean IsDone()
        {
            if(col == graph.numberOfVertices)
            {
                return true;
            }
            return false;
        }
        Edge operatorPlus()
        {
            return graph.SelectEdge(row,col);
        }
        void operatorStar(){next();}

    }

    class InciEdgesIter {
        Graph graph;
        int row;
        int col;

        InciEdgesIter(Graph graph,int verticescolumn)
        {
            this.graph= graph;
            col = verticescolumn;
            row=-1;
            next();
        }
        void next()
        {
            for(int i=row+1;i<graph.numberOfVertices;i++)
            {
                if(graph.SelectEdge(i,col)!= null)
                {
                    row=i;
                    return;
                }
            }
            row=graph.numberOfVertices;
        }
        boolean IsDone()
        {
            if(row == graph.numberOfVertices)
            {
                return true;
            }
            return false;
        }
        Edge  operatorStar()
        {
            return graph.SelectEdge(row,col);
        }
        void operatorPlus(){next();}

    }
    

}
