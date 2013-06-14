import memberchk/2 from basics

node(a).
node(b).
node(c).
node(d).
node(e).

edge(a,b).
edge(b,c).
edge(b,d).
edge(b,e).
edge(c,e).

path(X,Z) :- edge(X,Y), path(Y,Z).
path(X,Y) :- edge(X,Y).

cycle(G,A,P) :-
	adjacent(B,A,G),
	path(G,A,B,P1),
	length(P1,L),
	L>2,
	append(P1,[A],P).

path(G,A,B,P) :- path1(G,A,[B],P).

path1(_,A,[ A|P1 ], [A|P1]).
path1(G,A,[Y|P1],P) :- 
	adjacent(X,Y,G), \+ memberchk(X,[Y|P1]),
	path1(G,A,[X,Y|P1],P1).
	
adjacent(X,Y,graph(_,Es)) :- member(e(X,Y),Es).
adjacent(X,Y,graph(_,Es)) :- member(e(Y,X),Es).

member(X, [X|_]).
member(X, [_|Tail]) :- member(X, Tail).