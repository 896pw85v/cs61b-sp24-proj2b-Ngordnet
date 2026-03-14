package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Represents a directed graph of hyponym relationships.
 * @author me
 *
 */
public class WordNet {
    // int -> set of int
    // look up int -> words in synsets table
    // for outside world this is not a graph class. So ultimate api is:
    // String word -> String words[] of all hyponyms
    // String words[] -> words[] all common hyponyms

    public TreeMap<Integer, TreeSet<Integer>> graph;
    public Synset table;

    /**
     * Creates a WordNet obj with empty graph.
     * Don't think this is needed, but still want to provide an empty constructor.
     */
    public WordNet() {
        graph = new TreeMap<>();
        table = new Synset();
    }

    /**
     * Create a WordNet obj by reading the txt files at the provided path
     * @param path that points to the hyponym data set
     * @param tablePath that points to the set of words
     * @author me
     */
    public WordNet(String path, String tablePath) {
        table = new Synset(tablePath);
        graph = new TreeMap<>();
        File file = new File(path);
        try {
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] tokens = line.split(",");
                String[] words = Arrays.copyOfRange(tokens,1, tokens.length);
                Integer[] wordz = new Integer[words.length];
                for (int i = 0; i < words.length; i++) {
                    wordz[i] = Integer.parseInt(words[i]);
                }
                TreeSet<Integer> hypo = new TreeSet<>(List.of(wordz));
                int i = Integer.parseInt(tokens[0]);
                if (graph.get(i) == null) graph.put(i, hypo);
                else {
                    TreeSet<Integer> set = graph.get(i);
                    set.addAll(hypo);
                    graph.put(i, set);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get the set of words contained in the current node
     * @param i index of the current node
     * @return a TreeSet of words that are in the current node
     */
    public TreeSet<String> get(int i) {
        return table.get(i);
    }

    /**
     * simply returns the child indices of the current index
     * @param i the current (parent) index
     * @return a set of integers that are indices to the child nodes
     */
    public TreeSet<Integer> getChildKeys(int i) {
        if (!graph.containsKey(i)) return new TreeSet<>();
        return graph.get(i);
    }

    /**
     * a recursive approach that gets all the hyponyms in words not indices. Does not include
     * the current index, ordered in the order of the words being added to the list. Strictly
     * node (synset) based.
     * @param i the current (parent) node index
     * @return a list (ArrayList) of strings that are hyponyms of the current (parent) node
     */
    public List<String> getHyponyms(int i) {

        List<String> list = new ArrayList<>();
        for (int child : this.getChildKeys(i)) {
            list.addAll(table.get(child)); // adding all words in current node
            list.addAll(this.getHyponyms(child)); // adding all the words in the child nodes, recursively
        }
        return list;
    }

    /**
     * Provided a word, searches for the hyponyms of this word, including every synset this word belongs to
     * @param word word to search for
     * @return a list (ArrayList) containing all hyponyms of the word
     */
    public List<String> hyponyms(String word) {
        List<String> list = new ArrayList<>();
        for (int i : table.getIndices(word)) { // every node containing word
            list.addAll(this.getHyponyms(i));
        }
        return list;
    }

    /**
     * Returns whether node i points to node j in a single direction
     * @param i the starting node
     * @param j the target node
     * @return boolean value true if j is a child of i, vice versa
     */
    public boolean isConnected(int i, int j) {
        if (this.getChildKeys(i).contains(j)) return true;
        boolean connected = false;
        for (int child : this.getChildKeys(i)) connected = connected || isConnected(child, j);
        return connected;
    }

}
