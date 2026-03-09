package graph;

import java.util.List;
import java.util.TreeSet;

public class WordNode {
    int node; // is a int indicating a synset node
    TreeSet<Integer> synsets; // is a set representing child nodes under this node

    public WordNode(int node) {
        this.node = node;
        synsets = new TreeSet<>();
    }

    // want to use int for performance but can't due to of() requires
    public void addChild(Integer... child) {
        if (child != null) synsets.addAll(List.of(child));
    }
}
