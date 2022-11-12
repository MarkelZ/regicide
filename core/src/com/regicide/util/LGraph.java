package com.regicide.util;

import java.util.ArrayList;
import java.util.List;

// List Graph
// Uses lists of lists to represent adjacency (instead of adj matrices).
public class LGraph<T> {
    private ArrayList<T> vertices;
    private ArrayList<ArrayList<T>> adjacency;

    // Construct an empty graph
    public LGraph() {
        vertices = new ArrayList<>();
        adjacency = new ArrayList<>();
    }

    public LGraph(List<T> vertices) {
        this.vertices = new ArrayList<T>(vertices);
        int N = vertices.size();
        adjacency = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            adjacency.add(new ArrayList<T>());
        }
    }

    // If the graph does not already contain the vertex, add the vertex to the graph
    // and return true.
    // Otherwise, just return false.
    public boolean addVertex(T vertex) {
        int ind = vertices.indexOf(vertex);
        boolean isVertexNew;

        if (ind == -1) {
            vertices.add(vertex);
            adjacency.add(new ArrayList<T>());
            isVertexNew = true;
        } else {
            isVertexNew = false;
        }

        return isVertexNew;
    }

    // True if vertex1 and vertex2 are adjacent.
    // If either vertex is not in the graph, return false.
    public boolean isAdjacent(T vertex1, T vertex2) {
        int ind = vertices.indexOf(vertex1);
        if (ind == -1) {
            return false;
        } else {
            return adjacency.get(ind).contains(vertex2);
        }
    }

    // Return an array with all the vertices.
    // This array should be used as read-only, and not for modifying the graph.
    // To add a new vertex use method addVertex.
    public ArrayList<T> getVertexList() {
        return vertices;
    }

    // Return an array of vertices adjacent to the given vertex.
    // This list is to be used as read-only, and not for modifying the adjacency of
    // the vertex. For adding adjacencies use method addAdjacency.
    public ArrayList<T> getAdjacentVertices(T vertex) {
        int ind = vertices.indexOf(vertex);
        if (ind == -1) {
            return null;
        } else {
            return adjacency.get(ind);
        }
    }

    // Adds an adjacency between vertex1 and vertex2.
    // If either vertex is not in the graph, or they are already adjacent,
    // the graph remains unchanged and a warning is printed.
    public void addAdjacency(T vertex1, T vertex2) {
        int ind1 = vertices.indexOf(vertex1);
        int ind2 = vertices.indexOf(vertex2);
        if (ind1 != -1 && ind2 != -1) {
            ArrayList<T> adj1 = adjacency.get(ind1);
            ArrayList<T> adj2 = adjacency.get(ind2);
            if (adj1.contains(vertex2)) {
                System.out.println("WARNING: Vertices are already adjacent.");
            } else {
                adj1.add(vertex2);
                adj2.add(vertex1);
            }
        } else {
            System.out.println("WARNING: Tried to add adjacency of vertices not included in graph!!");
        }
    }

    // Adds an adjacency between vertex1 and vertex2.
    // Does not perform any checks.
    public void addAdjacencyNoChecks(T vertex1, T vertex2) {
        int ind1 = vertices.indexOf(vertex1);
        int ind2 = vertices.indexOf(vertex2);
        adjacency.get(ind1).add(vertex2);
        adjacency.get(ind2).add(vertex1);
    }
}
