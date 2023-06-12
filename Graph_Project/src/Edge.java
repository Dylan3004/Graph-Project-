public class Edge {
    Vertex v0;
    Vertex v1;
    int weight;
    String label;
    public Edge(Vertex v0, Vertex v1)
    {
        this.v0=v0;
        this.v1=v1;
    }
    public Edge(Vertex v0, Vertex v1, int w)
    {
        this.v0=v0;
        this.v1=v1;
        weight=w;
    }

    public Vertex getV0() {
        return v0;
    }

    public Vertex getV1() {
        return v1;
    }
    public Vertex Mate(Vertex v)
    {
        if(v.getNumber()==v0.getNumber())
        {
            return v0;
        } else if (v.getNumber()==v1.getNumber()) {
            return v1;
        }
        else {
            return null;
        }
    }
}
