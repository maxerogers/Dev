%Test example: 
%?- count_leaves(t(2,t(7,t(2,nil,nil), t(6, t(5,nil,nil), t(11,nil,nil))), t(5,nil, t(9, t(4,nil,nil),nil))),N).

%count_leaves(T,N) :- the binary tree T has N leaves
count_leaves(nil,0).
count_leaves(t(_,nil,nil),1).
count_leaves(t(_,L,nil),N) :-
	L = t(_,_,_),
	count_leaves(L,N).
count_leaves(t(_,nil,R),N) :- 
	R = t(_,_,_),
	count_leaves(R,N).
count_leaves(t(_,L,R),N) :-
	L = t(_,_,_),
	R = t(_,_,_),
	count_leaves(L, NL),
	count_leaves(R, NR),
	N is NL + NR.