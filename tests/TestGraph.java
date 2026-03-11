import graph.Synset;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.TreeSet;

import static com.google.common.truth.Truth.assertThat;

public class TestGraph {
    Synset synset = new Synset();

    @Test
    public void testNullWords() {
        synset.addSynset(1, "you", "me");

        assertThat(synset).isNotNull();
        synset.addSynset(2, null);
        assertThat(synset).isNotNull();
        assertThat(synset.get(2)).isEmpty();
    }

    @Test
    public void testGet() {
        synset.addSynset(1, "you", "me");

        assertThat(synset).isNotNull();
        assertThat(synset.get(1)).isNotNull();
        assertThat(synset.get(1)).containsExactly("you", "me");

        assertThat(synset.get(-1)).isEmpty();
        assertThat(synset.get(0)).isEmpty();
    }

    @Test
    public void testGetIndices() {
        synset.addSynset(1, "you", "me");
        synset.addSynset(2, "you");
        synset.addSynset(3, "me");
        synset.addSynset(4, "four");

        assertThat(synset.getIndices("four")).containsExactly(4);
        assertThat(synset.getIndices("you")).containsExactly(1, 2);

        assertThat(synset.getIndices("unrelated")).isEmpty();
    }

    @Test
    public void testIO() {
        Synset ioset = new Synset("data/data/wordnet/synsets11.txt");
        assertThat(ioset).isNotNull();
        assertThat(ioset.get(0)).containsExactly("action");
        assertThat(ioset.get(4)).containsExactly("jump", "parachuting");
        assertThat(ioset.get(10)).containsExactly("actifed");
        assertThat(ioset.get(-1)).isEmpty();

        try {
            Synset wrongset = new Synset("wrong path");
            assertThat(wrongset).isNotNull();
            assertThat(wrongset.get(0)).isEmpty();
            assertThat(wrongset.table).isEmpty();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testLargeFile() {
        Synset largeset = new Synset("data/data/wordnet/synsets.txt");
        System.out.println(largeset.table);
        System.out.println(largeset.table.size());
    }

    @Test
    public void testIndicesLarge() {
        Synset largeset = new Synset("data/data/wordnet/synsets.txt");
        assertThat(largeset.getIndices("1530s")).contains(1);
        assertThat(largeset.getIndices("1830s")).contains(10);
        assertThat(largeset.getIndices("derivative")).contains(34695);
        assertThat(largeset.getIndices("propulsion")).contains(63362);
        assertThat(largeset.getIndices("zymosis")).contains(82191);
        // i don't really know what these words belong to so only testing one index
        // finished in around 720ms for 82191 entries, pretty fast
    }
}
