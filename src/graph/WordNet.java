package graph;

import org.antlr.v4.runtime.tree.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Represents a directed graph of hyponym relationships.
 * @author me
 *
 */
public class WordNet {
    // int -> set of int
    // look up int -> words in synsets table

    TreeMap<Integer, TreeSet<Integer>> graph;
    Synset table;

    /**
     * Creates a WordNet obj with empty graph
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
                graph.put(Integer.parseInt(tokens[0]), hypo);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the direct hyponyms of node i represented in a TreeSet
     * @param i dataset index of the current (parent) node
     * @return a TreeSet of hyponym Synset(s)
     */
    public TreeSet<Synset> get(int i) {
        return null;
    }

    /**
     * Returns whether node i points to node j in a single direction
     * @param i the starting node
     * @param j the target node
     * @return boolean value true if i points to j, vice versa
     */
    public boolean isConnected(int i, int j) {
        return false;
    }

}
