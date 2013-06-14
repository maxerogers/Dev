% Selection Sort
selection_sort([],[]) :- 
	!.
selection_sort(L,[H|T]) :- 
	min(L,H),
	select(H,L,LR),
	selection_sort(LR,T).
	
%min(InputList, Minimum)
min([H|T],M) :- 
	min(T,H,M).
%min(InputList, CurrentMinium, Minimum)
min([H|T],Ri,R):-
	H < Ri,
	!,
	min(T,H,R).
min([H|T],Ri,R) :-
	H>= Ri,
	!,
	min(T,Ri,R).
min([],R,R).