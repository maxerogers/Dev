%%Family Tree
%%Author: Max Rogers
%%Date: May 2013

%%Facts
man(bill).
man(will).
man(max).
man(aidan).

woman(joy).
woman(ayesha).
woman(regina).
woman(beth).

parent(bill,will).
parent(joy,will).
parent(will,max).
parent(regina,max).
parent(will,aidan).
parent(ayesha,aidan).
parent(joy,beth).
parent(bill,beth).

%%Rule
human(H) :- man(H).
human(H) :- woman(H).

descendent(D,A) :- parent(A,D).
descendent(D,A) :- parent(P,D), descendent(P,A).
ancestor(A,D):-descendent(D,A).

grandparent(G,C) :- parent(G,P), parent(P,C).

father(F,C) :- man(F), parent(F,C).
mother(M,C) :- woman(M), parent(M,C).
son(S,P) :- man(S), parent(P,S).
daughter(D,P) :- woman(D), parent(P,D).

isFather(F) :- father(F,_).
isMother(M) :- mother(M,_).

siblings(A,B) :- parent(P,A), parent(P,B), A \= B.
fullSiblings(A,B) :- mother(M,A), mother(M,B), father(F,A), father(F,B), A \= B.

uncle(U,N) :- man(U), parent(P,N), siblings(U,P).
aunt(A,N) :- woman(A), parent(P,N), siblings(A,P).
