
:- import length/2 from basics.
:- import member/2 from basics.
:- import memberchk/2 from basics.
:- import append/3 from basics.

isAMember(X,L) :- memberchk(X,L).

% cycle(+G,+A,-P) :- P is a closed path starting at node A in the graph G. 
% G is given in graph-term form.
% Example: cycle(graph(graph1,[e(1,2), e(2,3), e(2,4), e(2,5), e(3,5)]),X,P), write(P), nl.

cycle(G,A,P) :-
	adjacent(B,A,G), 
	path(G,A,B,P1), 
	length(P1,L),
	L > 2, 
	append(P1,[A],P).

% path(G,A,B,P) :- P is a (acyclic) path from node A to node B in the graph G. 
% G is given in graph-term form.
% (+,+,+,?)
path(G,A,B,P) :- path1(G,A,[B],P).

path1(_,A,[A|P1],[A|P1]). 
path1(G,A,[Y|P1],P) :-
	adjacent(X,Y,G),
	\+ memberchk(X,[Y|P1]), 
	path1(G,A,[X,Y|P1],P).

% adjacent/3
adjacent(X,Y,graph(_,Es)) :- member(e(X,Y),Es). 
adjacent(X,Y,graph(_,Es)) :- member(e(Y,X),Es).