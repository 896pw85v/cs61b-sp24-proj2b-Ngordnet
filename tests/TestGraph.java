import graph.Synset;
import org.junit.jupiter.api.Test;

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
}
