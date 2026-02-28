# Proj 2b/c design document
**Name** | **class id** | **Email**
|-----|-----|-----|
| _ | _ | _ |

---

## Data Strucutre
* I will build a graph class to store the wordnet
* a set for vertices, since words in a node cannot repeat and has no hierarchy
* synnets will be stored in hashmaps, java built-in should work fine

--- 

## Algorithms
entered word is counted as words not nodes, so the same word in two different nodes 
are considered same words, meaning that children under two different nodes with the 
same words are also hyponyms of that word. 

### hyponyms (single word)
After taking a word, traverse through the graph until reach end (no more available 
neighbor). Then, manualy sort the list using comparater, since the order of retreaving 
words from the word graph does not garantee alphabetical order. 

### Hyponyms (multiple word)
Requirement: has to be both words' hyponym. 
If C is hyponym of both B and A, B must be either hyponym or hypernym of A. Therefore, 
start with one until found the other, then look down. 

