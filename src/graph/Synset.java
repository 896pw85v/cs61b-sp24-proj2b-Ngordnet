package graph;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class Synset {

    TreeMap<Integer, TreeSet<String>> table;
    int size = 0; // since dataset is consecutive, assume every add is +1 to the size
    // but size does not correspond to ids, should abolish

    /**
     * @construtor
     * Fucking algo let's do this
     * reads a fucking txt and loads the entries into the table
     * constructor
     */
    public Synset(String path) {

    }







    public Synset() {
        table = new TreeMap<>();
    }

    public void addSynset(Integer i, String... syns) {
        if (syns == null) return;
//        if (table.containsKey(i)) table.get(i).addAll(List.of(syns)); // under same index
        // actually this whole line is unnecessary, cuz synset dataset is single line
        // but good idea as a api
        table.put(i, new TreeSet<>(List.of(syns)));
        size++;
    }

    public TreeSet<String> get(Integer i) {
        return table.get(i) == null ? new TreeSet<>() : table.get(i);
    }

    public TreeSet<Integer> getIndices(String word) {
        if (word == null) return new TreeSet<>();
        TreeSet<Integer> indices = new TreeSet<>();
        for (Integer i : table.keySet()) {
            if (table.get(i).contains(word)) indices.add(i);
        }
        return indices;
    }
}
