#!/usr/bin/env python

__author__ = "Alonso Vidales"
__email__ = "alonso.vidales@tras2.es"
__date__ = "2013-01-14"

# Obtained from: http://code.activestate.com/recipes/123641-hopcroft-karp-bipartite-matching/
# Hopcroft-Karp bipartite max-cardinality matching and max independent set
# David Eppstein, UC Irvine, 27 Apr 2002

def bipartiteMatch(graph):
	'''Find maximum cardinality matching of a bipartite graph (U,V,E).
	The input format is a dictionary mapping members of U to a list
	of their neighbors in V.  The output is a triple (M,A,B) where M is a
	dictionary mapping members of V to their matches in U, A is the part
	of the maximum independent set in U, and B is the part of the MIS in V.
	The same object may occur in both U and V, and is treated as two
	distinct vertices if this happens.'''

	# initialize greedy matching (redundant, but faster than full search)
	matching = {}
	for u in graph:
		for v in graph[u]:
			if v not in matching:
				matching[v] = u
				break

	while 1:
		# structure residual graph into layers
		# pred[u] gives the neighbor in the previous layer for u in U
		# preds[v] gives a list of neighbors in the previous layer for v in V
		# unmatched gives a list of unmatched vertices in final layer of V,
		# and is also used as a flag value for pred[u] when u is in the first layer
		preds = {}
		unmatched = []
		pred = dict([(u,unmatched) for u in graph])
		for v in matching:
			del pred[matching[v]]
		layer = list(pred)

		# repeatedly extend layering structure by another pair of layers
		while layer and not unmatched:
			newLayer = {}
			for u in layer:
				for v in graph[u]:
					if v not in preds:
						newLayer.setdefault(v,[]).append(u)
			layer = []
			for v in newLayer:
				preds[v] = newLayer[v]
				if v in matching:
					layer.append(matching[v])
					pred[matching[v]] = v
				else:
					unmatched.append(v)

		# did we finish layering without finding any alternating paths?
		if not unmatched:
			unlayered = {}
			for u in graph:
				for v in graph[u]:
					if v not in preds:
						unlayered[v] = None
			return (matching,list(pred),list(unlayered))

		# recursively search backward through layers to find alternating paths
		# recursion returns true if found path, false otherwise
		def recurse(v):
			if v in preds:
				L = preds[v]
				del preds[v]
				for u in L:
					if u in pred:
						pu = pred[u]
						del pred[u]
						if pu is unmatched or recurse(pu):
							matching[v] = u
							return 1
			return 0

		for v in unmatched: recurse(v)

class CatVsDog:
    __debug = False
    __votesList = None

    def resolve(self):
        # This graph will contain all the conflictive relations between voters
        bipartiteByCat = {}

        catLovers = []
        # Dog lovers groupped by the dog who they loves
        byDogLove = {}
        # Dog lovers groupped by the cat who they hate
        byDogHate = {}

        # This counter will be used to avoid problems with duplicate votes on the
        # sets and the key of the dict who conforms the graph
        count = 0
        for vote in self.__votesList:
            vote = "%s %d" % (vote, count)
            count += 1
            if vote[0] == 'D':
                voteParts = vote.split()

                if voteParts[0] in byDogLove:
                    byDogLove[voteParts[0]].add(vote)
                else:
                    byDogLove[voteParts[0]] = set([vote])

                if voteParts[1] in byDogHate:
                    byDogHate[voteParts[1]].add(vote)
                else:
                    byDogHate[voteParts[1]] = set([vote])
            else:
                catLovers.append(vote)

        # Create the bipartite graph using a dictionary, the key will be the vote of the cat lover, and the
        # values a set with all the votes who have problems with the vote of the cat lover at the key
        for catVote in catLovers:
            catVoteParts = catVote.split()
            bipartiteByCat[catVote] = byDogLove.get(catVoteParts[1], set()) | byDogHate.get(catVoteParts[0], set())

        # Use the Hopcroft-Karp algorith in order to determinate the max cardinality, then we will know the
        # min number of conflictive votters that we have
        maxMatching, pred, unlayered = bipartiteMatch(bipartiteByCat)

        if self.__debug:
            print "Votes: %s" % (bipartiteByCat)
            print "Max matching %s" % (maxMatching)
            print "Dog love: %s" % (byDogLove)
            print "Dog hate: %s" % (byDogHate)


        # The max number of happy voters are the number of total voters minus the number of conflictive pairs (we can remove
        # one of the members of each pair)
        return len(self.__votesList) - len(maxMatching)

    def __init__(self, inVotes):
        self.__votesList = inVotes

if __name__ == "__main__":
    # I'll use raw_input to get the lines because I can't import fileinput on the test server
    problems = int(raw_input())
    results = []
    for problem in xrange(0, problems):
        problemInfo = map(int, raw_input().split())
        votes = []
        for problemLine in xrange(0, problemInfo[2]):
            votes.append(raw_input())

        results.append(CatVsDog(votes).resolve())

    print "\n".join(map(str, results))