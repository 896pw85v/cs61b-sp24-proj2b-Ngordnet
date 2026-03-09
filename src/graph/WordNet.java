package graph;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordNet {
    // int -> set of int
    // look up int -> words in synsets table

    TreeMap<Integer, TreeSet<Integer>> graph;
    Synset table;

    public WordNet() {
        graph = new TreeMap<>();
        table = new Synset();
    }



}
