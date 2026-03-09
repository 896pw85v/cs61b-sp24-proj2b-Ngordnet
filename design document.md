# Proj 2b/c design document
| **Name** | **class id** | **Email** |
|----------|--------------|-----------|
| _        | _            | _         |

<!-- --- -->

## Data Strucutre
* I will build a graph class to store the wordnet. The graph is the wordnet. 
* a set for vertices, since words in a node cannot repeat and has no hierarchy. A vertex is a synnet, containing words. 
* synnets will be stored in hashmaps, java built-in should work fine
* I can do caching, but it's not my concern right now. 

### Graph
* __Adjacency list__: a hash map like structure that stores an array or list of vertices and a linked list of it's adjacent vertices to represent connection. Linked list can also be trees, sets, maps, etc. But the datasets of this assignment will not require so many edges since most words do not contain a very complicated hierarchy. 
* __parent-child__: naturally denoted by having a directed connection. If A -> B, A is parent of B. And B -> A should not be true, otherwise they are synnonyms. 
* __marked__: hmmm maybe just use a hashmap, or hashset. 
* __nodes__: Could be just a set / linked list of integers, which is synnet indeces
<!-- ---  -->

## Algorithms
entered word is counted as words not nodes, so the same word in two different nodes are considered same words, meaning that children under two different nodes with the same words are also hyponyms of that word. 

__word -> index__: just go through the whole list. Worst case if it's the dictionary only takes 30s. 

__parent-child__: `isConnected(parent, child) && ! isConnected(child, parent)`
### hyponyms (single word)
After taking a word, traverse through the graph until reach end (no more available 
neighbor). Then, manualy sort the list using comparater, since the order of retreaving 
words from the word graph does not garantee alphabetical order. 

### Hyponyms (multiple word)
Requirement: has to be both words' hyponym. 

If C is hyponym of both B and A, B must be either hyponym or hypernym of A. Therefore, start with one until found the other, then look down. Or, check if they are connected, so that we don't traverse through the whole graph when they are not connected and waste time. 

### common ancester
ok I should watch some lectures first. 


