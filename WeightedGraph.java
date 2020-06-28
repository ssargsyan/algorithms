public class WeightedGraph {
  private int[][] adjMatrix;
  private int[] distance;

  public WeightedGraph(int[][] adjMatrix) {
    this.adjMatrix = adjMatrix;
    this.distance = new int[adjMatrix.length];
  }

  private int findMinVertex(int[] dist, boolean[] visited) {
    int min = Integer.MAX_VALUE, minVertex = -1;

    for (int v = 0; v < dist.length; v++) if (
      visited[v] == false && dist[v] <= min
    ) {
      min = dist[v];
      minVertex = v;
    }

    return minVertex;
  }

  public void dijkstra() {
    int V = adjMatrix.length;

    boolean[] visited = new boolean[V];

    for (int i = 0; i < V; i++) {
      distance[i] = Integer.MAX_VALUE;
      visited[i] = false;
    }

    distance[0] = 0;

    for (int count = 0; count < V - 1; count++) {
      int u = findMinVertex(distance, visited);

      visited[u] = true;

      for (int v = 0; v < V; v++) {
        if (
          !visited[v] &&
          adjMatrix[u][v] != 0 &&
          distance[u] != Integer.MAX_VALUE &&
          distance[u] + adjMatrix[u][v] < distance[v]
        ) distance[v] = distance[u] + adjMatrix[u][v];
      }
    }
  }

  public void printDistances() {
    for (int i = 0; i < distance.length; i++) {
      System.out.println(i + " " + distance[i]);
    }
  }

  public static void main(String[] args) {
    int[][] adjMatrix = {
      { 0, 4, 8, 0, 0 },
      { 0, 0, 2, 5, 0 },
      { 0, 0, 0, 5, 9 },
      { 0, 0, 0, 0, 4 },
      { 0, 0, 9, 4, 8 },
    };
    WeightedGraph graph = new WeightedGraph(adjMatrix);
    graph.dijkstra();
    graph.printDistances();
  }
}
