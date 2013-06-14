% FAMILY

parent(alex,julia).
parent(alex,rosa).
parent(lina,julia).
parent(lina,rosa).
parent(romeo,peter).
parent(julia,peter).
parent(rosa,silvia).
parent(oscar,ida).
parent(eva,ida).
parent(eva,bruno).
parent(peter,bruno).
parent(peter,georg).
parent(peter,irma).
parent(ruth,georg).
parent(ruth,irma).
parent(silvia,otto).
parent(silvia,pascal).
parent(irma,olga).
parent(irma,jean).
parent(otto,olga).
parent(otto,jean).
parent(jean,tina).
parent(marie,tina).

male(alex).
male(romeo).
male(oscar).
male(peter).
male(bruno).
male(georg).
male(otto).
male(pascal).
male(jean).

husband(alex,lina).
husband(romeo,julia).
husband(oscar,eva).
husband(peter,ruth).
husband(otto,irma).
husband(jean,marie).

% father(X,Y) :- X is the father of Y.
father(X,Y) :- 
	parent(X,Y), 
	male(X).

% grandfather(X,Y) :- X is the grandfather of Y.
grandfather(X,Y) :- 
	father(X,P), 
	parent(P,Y).

% brother(X,Y) :- X is the brother of Y.
brother(X,Y) :- 
	parent(P,X), 
	parent(P,Y), 
	male(X), 
	X \= Y.

% uncle(X,Y) :- X is the uncle of Y.
uncle(X,Y) :- 
	brother(X,P), 
	parent(P,Y).

% female(X) :- X is a female person.
female(X) :- 
	\+ male(X).

% sister(X,Y) :- X is the sister of Y.
sister(X,Y) :- 
	parent(P,X), 
	parent(P,Y), 
	female(X), 
	X \= Y.

% has_son(X) :- the person X has a son.
has_son(X) :- 
	parent(X,Y), 
	male(Y).

% married(X,Y) :- X and Y are married to each other.
married(X,Y) :- 
	husband(X,Y); husband(Y,X).

% brother_in_law(X,Y) :- X is the brother-in-law of Y.
brother_in_law(X,Y) :- 
	brother(X,P), 
	married(P,Y).
brother_in_law(X,Y) :- 
	husband(X,W), 
	sister(W,Y).

% ancestor(X,Y) :- X is an ancestor of Y.
ancestor(X,Y) :- 
	parent(X,Y).
ancestor(X,Y) :- 
	parent(X,P), 
	ancestor(P,Y).

% relatives(X,Y) :- X and Y are relatives (related by blood to each other). 
relatives(X,X).
relatives(X,Y) :- 
	ancestor(X,Y).
relatives(X,Y) :- 
	ancestor(Y,X).
relatives(X,Y) :- 
	ancestor(A,X), ancestor(A,Y).

% ancestors(As,X) :- As is the set of all (known) ancestors of X.
ancestors(As,X) :- 
	setof(A,ancestor(A,X),As).

% descendants(Ds,X) :- Ds is the set of all (known) descendants of X.
descendants(Ds,X) :-
	setof(D,ancestor(X,D),Ds).

% ancestor(X,Y,L) :- X is an ancestor of Y, and L is the list of names
% leading from X to Y.
ancestor(X,Y,[X,Y]) :- 
	parent(X,Y).
ancestor(X,Y,[X|L]) :- 
	parent(X,P), 
	ancestor(P,Y,L). 

p(f(X)):-p(X).
p(1).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% ARITHMETICS
% 1 Determine whether a given integer number is prime.
% Example:
% ?- is_prime(7).
% Yes

% is_prime(P) :- P is a prime number
%	 (integer) (+)
is_prime(2).
is_prime(3).
is_prime(P) :- 
	integer(P), 
	P > 3, 
	P mod 2 =\= 0, 
	\+ has_factor(P,3).	

% has_factor(N,L) :- N has an odd factor F >= L.
%	 (integer, integer) (+,+)
has_factor(N,L) :- 
	N mod L =:= 0.
has_factor(N,L) :- 
	L * L < N, 
	L2 is L + 2, 
	has_factor(N,L2). 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 2 Determine the prime factors of a given positive integer.
% Construct a flat list containing the prime factors in ascending order.
% Example:
% ?- prime_factors(315, L).
% L = [3,3,5,7]

% prime_factors(N, L) :- N is the list of prime factors of N.
%	 (integer,list) (+,?)
prime_factors(N,L) :- 
	N > 0,	
	prime_factors(N,L,2).

% prime_factors(N,L,K) :- L is the list of prime factors of N. It is 
% known that N does not have any prime factors less than K.
prime_factors(1,[],_) :- !.
prime_factors(N,[F|L],F) :-	% N is multiple of F
	R is N // F, N =:= R * F, !, prime_factors(R,L,F).
prime_factors(N,L,F) :- % N is not multiple of F
	next_factor(N,F,NF), 
	prime_factors(N,L,NF).

% next_factor(N,F,NF) :- when calculating the prime factors of N
%	 and if F does not divide N then NF is the next larger candidate to
%	 be a factor of N.
next_factor(_,2,3) :- !.
next_factor(N,F,NF) :- 
	F * F < N, 
	!, 
	NF is F + 2.
next_factor(N,_,N). % F > sqrt(N) 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 3 Determine the prime factors of a given positive integer (2).
% Construct a list containing the prime factors and their multiplicity.
% Example:
% ?- prime_factors_mult(315, L).
% L = [[3,2],[5,1],[7,1]]

% prime_factors_mult(N, L) :- L is the list of prime factors of N. It is
%	 composed of terms [F,M] where F is a prime factor and M its multiplicity.
%	 (integer,list) (+,?)
prime_factors_mult(N,L) :- 
	N > 0, 
	prime_factors_mult(N,L,2).

% prime_factors_mult(N,L,K) :- L is the list of prime factors of N. It is 
% known that N does not have any prime factors less than K.
prime_factors_mult(1,[],_) :- 
	!.
prime_factors_mult(N,[[F,M]|L],F) :- 
	divide(N,F,M,R), % F divides N
	!, 
	next_factor(R,F,NF), 
	prime_factors_mult(R,L,NF).
prime_factors_mult(N,L,F) :- % F does not divide N
	!,									
	next_factor(N,F,NF), 
	prime_factors_mult(N,L,NF).

% divide(N,F,M,R) :- N = R * F**M, M >= 1, and F is not a factor of R. 
%	 (integer,integer,integer,integer) (+,+,-,-)
divide(N,F,M,R) :- 
	divi(N,F,M,R,0), 
	M > 0.

divi(N,F,M,R,K) :- 
	S is N // F, 
	N =:= S * F, % F divides N 
	!,			 
	K1 is K + 1, 
	divi(S,F,M,R,K1).
divi(N,_,M,N,M). 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 4 A list of prime numbers.
% Given a range of integers by its lower and upper limit, construct a list of all prime numbers in that range.

% prime_list(A,B,L) :- L is the list of prime number P with A <= P <= B
prime_list(A,B,L) :- 
	A =< 2, 
	!, 
	p_list(2,B,L).
prime_list(A,B,L) :- 
	A1 is (A // 2) * 2 + 1, 
	p_list(A1,B,L).

p_list(A,B,[]) :- 
	A > B, 
	!.
p_list(A,B,[A|L]) :- 
	is_prime(A), 
	!, 
	next(A,A1), 
	p_list(A1,B,L). 
p_list(A,B,L) :- 
	next(A,A1), 
	p_list(A1,B,L).

next(2,3) :- 
	!.
next(A,A1) :- 
	A1 is A + 2. 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 5 Determine the greatest common divisor of two positive integer numbers.
% Use Euclid's algorithm.
% Example:
% ?- gcd(36, 63, G).
% G = 9

% gcd(X,Y,G) :- G is the greatest common divisor of X and Y
%	 (integer, integer, integer) (+,+,?)
gcd(X,0,X) :- 
	X > 0.
gcd(X,Y,G) :- 
	Y > 0, 
	Z is X mod Y, 
	gcd(Y,Z,G).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 6 Determine whether two positive integer numbers are coprime.
% Two numbers are coprime if their greatest common divisor equals 1.
% Example:
% ?- coprime(35, 64).
% Yes

coprime(X,Y) :- gcd(X,Y,1). 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% LISTS
% 1 Find the last element of a list.
% Example:
% swipl
% ?- ['a.pl'].
% ?- my_last(X,[a,b,c,d]).
% X = d
my_last(X,[X]):-
	!.
my_last(X,[_|T]):-
	my_last(X,T).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 2 Find the last but one element of a list.

my_last(X,[X,_]):-
	!.
my_last(X,[_|T]):-
	my_last(X,T).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 3 Find the K'th element of a list.
% The first element in the list is number 
% Example:
% ?- element_at(X,[a,b,c,d,e],3).
% X = c

% element_at(X,L,K) :- X is the K'th element of the list L
%	 (element,list,integer) (?,?,+)
element_at(X,[X|_],1).
element_at(X,[_|L],K) :- 
	K > 1, 
	K1 is K - 1, 
	element_at(X,L,K1).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 4 Find the number of elements of a list.

% my_length(L,N) :- the list L contains N elements
%	 (list,integer) (+,?) 
my_length([],0).
my_length([_|L],N) :- 
	my_length(L,N1), 
	N is N1 + 1.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 5 Reverse a list.

% my_reverse(L1,L2) :- L2 is the list obtained from L1 by reversing 
%	 the order of the elements.
%	 (list,list) (?,?)
my_reverse(L1,L2) :- my_rev(L1,L2,[]).

my_rev([],L2,L2) :- 
	!.
my_rev([X|Xs],L2,Acc) :- 
	my_rev(Xs,L2,[X|Acc]).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 6 Find out whether a list is a palindrome.
% A palindrome can be read forward or backward; e.g. [x,a,m,a,x].

% is_palindrome(L) :- L is a palindrome list
%	 (list) (?)
is_palindrome(L) :- 
	reverse(L,L).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 7 Flatten a nested list structure.
% Transform a list, possibly holding lists as elements into a 'flat' list by replacing each list with its elements (recursively).
% Example:
% ?- my_flatten([a, [b, [c, d], e]], X).
% X = [a, b, c, d, e]
% Hint: Use the predefined predicates is_list/1 and append/3

% my_flatten(L1,L2) :- the list L2 is obtained from the list L1 by
%	 flattening; i.e. if an element of L1 is a list then it is replaced
%	 by its elements, recursively. 
%	 (list,list) (+,?)
my_flatten([],[]).
my_flatten(X,[X]) :- 
	\+ is_list(X).
my_flatten([X|Xs],Zs) :- 
	my_flatten(X,Y), 
	my_flatten(Xs,Ys), 
	append(Y,Ys,Zs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 8 Eliminate consecutive duplicates of list elements.
% If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.
% Example:
% ?- compress([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
% X = [a,b,c,a,d,e]

% compress(L1,L2) :- the list L2 is obtained from the list L1 by
%	 compressing repeated occurrences of elements into a single copy
%	 of the element.
%	 (list,list) (+,?)
compress([],[]).
compress([X],[X]).
compress([X,X|Xs],Zs) :- 
	compress([X|Xs],Zs).
compress([X,Y|Ys],[X|Zs]) :- 
	X \= Y, 
	compress([Y|Ys],Zs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 9 Pack consecutive duplicates of list elements into sublists.
% If a list contains repeated elements they should be placed in separate sublists.
% 
% Example:
% ?- pack([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
% X = [[a,a,a,a],[b],[c,c],[a,a],[d],[e,e,e,e]]

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 10 Run-length encoding of a list.
% Use the result of problem 9 to implement the so-called run-length encoding data compression method. Consecutive duplicates of elements are encoded as terms [N,E] where N is the number of duplicates of the element E.
% Example:
% ?- encode([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
% X = [[4,a],[1,b],[2,c],[2,a],[1,d][4,e]]

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 11 Modified run-length encoding.
% Modify the result of problem 10 in such a way that if an element has no duplicates it is simply copied into the result list. Only elements with duplicates are transferred as [N,E] terms. 
% Example:
% ?- encode_modified([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
% X = [[4,a],b,[2,c],[2,a],d,[4,e]]

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 12 Decode a run-length encoded list.
% Given a run-length code list generated as specified in problem 1 Construct its uncompressed version.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 13 Run-length encoding of a list (direct solution).
% Implement the so-called run-length encoding data compression method directly. I.e. don't explicitly create the sublists containing the duplicates, as in problem 9, but only count them. As in problem 11, simplify the result list by replacing the singleton terms [1,X] by X.
% Example:
% ?- encode_direct([a,a,a,a,b,c,c,a,a,d,e,e,e,e],X).
% X = [[4,a],b,[2,c],[2,a],d,[4,e]]

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 14 Duplicate the elements of a list.
% Example:
% ?- dupli([a,b,c,c,d],X).
% X = [a,a,b,b,c,c,c,c,d,d]

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 15 Duplicate the elements of a list a given number of times.
% Example:
% ?- dupli([a,b,c],3,X).
% X = [a,a,a,b,b,b,c,c,c]
% What are the results of the goal:
% ?- dupli(X,3,Y).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 16 Drop every N'th element from a list.
% Example:
% ?- drop([a,b,c,d,e,f,g,h,i,k],3,X).
% X = [a,b,d,e,g,h,k]

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 17 Split a list into two parts; the length of the first part is given.
% Do not use any predefined predicates.
% Example:
% ?- split([a,b,c,d,e,f,g,h,i,k],3,L1,L2).
% L1 = [a,b,c]
% L2 = [d,e,f,g,h,i,k]

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 18 Extract a slice from a list.
% Given two indices, I and K, the slice is the list containing the elements between the I'th and K'th element of the original list (both limits included). Start counting the elements with 
% Example:
% ?- slice([a,b,c,d,e,f,g,h,i,k],3,7,L).
% X = [c,d,e,f,g]

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 19 Rotate a list N places to the left.
% Examples:
% ?- rotate([a,b,c,d,e,f,g,h],3,X).
% X = [d,e,f,g,h,a,b,c]
% ?- rotate([a,b,c,d,e,f,g,h],-2,X).
% X = [g,h,a,b,c,d,e,f]

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% GRAPHS
% 1 Conversions
% Write predicates to convert between the different graph representations. With these predicates, all representations are equivalent; i.e. for the following problems you can always freely pick the most convenient form. The reason this problem is rated is not because it's particularly difficult, but because it's a lot of work to deal with all the special cases. 

% We use the following notation:
%
% adjacency-list (alist): [n(b,[c,g,h]), n(c,[b,d,f,h]), n(d,[c,f]), ...]
%
% graph-term (gterm)	graph([b,c,d,f,g,h,k],[e(b,c),e(b,g),e(b,h), ...]) or
%							digraph([r,s,t,u],[a(r,s),a(r,t),a(s,t), ...])
%
% edge-clause (ecl):	edge(b,g).	(in program database)
% arc-clause (acl):	arc(r,s).	(in program database)
%
% human-friendly (hf): [a-b,c,g-h,d-e]	or [a>b,h>g,c,b>a]
%
% The main conversion predicates are: alist_gterm/3 and human_gterm/2 which
% both (hopefully) work in either direction and for graphs as well as
% for digraphs, labelled or not.

% alist_gterm(Type,AL,GT) :- convert between adjacency-list and graph-term
%	 representation. Type is either 'graph' or 'digraph'.
%	 (atom,alist,gterm)	(+,+,?) or (?,?,+)

alist_gterm(Type,AL,GT):- nonvar(GT), !, gterm_to_alist(GT,Type,AL).
alist_gterm(Type,AL,GT):- atom(Type), nonvar(AL), alist_to_gterm(Type,AL,GT).

gterm_to_alist(graph(Ns,Es),graph,AL) :- memberchk(e(_,_,_),Es), ! ,
	lgt_al(Ns,Es,AL).
gterm_to_alist(graph(Ns,Es),graph,AL) :- !, 
	gt_al(Ns,Es,AL).
gterm_to_alist(digraph(Ns,As),digraph,AL) :- memberchk(a(_,_,_),As), !,
	ldt_al(Ns,As,AL).
gterm_to_alist(digraph(Ns,As),digraph,AL) :- 
	dt_al(Ns,As,AL).

% labelled graph
lgt_al([],_,[]).
lgt_al([V|Vs],Es,[n(V,L)|Ns]) :-
	findall(T,((member(e(X,V,I),Es) ; member(e(V,X,I),Es)),T = X/I),L),
	lgt_al(Vs,Es,Ns).

% unlabelled graph
gt_al([],_,[]).
gt_al([V|Vs],Es,[n(V,L)|Ns]) :-
	findall(X,(member(e(X,V),Es) ; member(e(V,X),Es)),L), gt_al(Vs,Es,Ns).

% labelled digraph
ldt_al([],_,[]).
ldt_al([V|Vs],As,[n(V,L)|Ns]) :-
	findall(T,(member(a(V,X,I),As), T=X/I),L), ldt_al(Vs,As,Ns).

% unlabelled digraph
dt_al([],_,[]).
dt_al([V|Vs],As,[n(V,L)|Ns]) :-
	findall(X,member(a(V,X),As),L), dt_al(Vs,As,Ns).


alist_to_gterm(graph,AL,graph(Ns,Es)) :- !, al_gt(AL,Ns,EsU,[]), sort(EsU,Es).
alist_to_gterm(digraph,AL,digraph(Ns,As)) :- al_dt(AL,Ns,AsU,[]), sort(AsU,As).

al_gt([],[],Es,Es).
al_gt([n(V,Xs)|Ns],[V|Vs],Es,Acc) :- 
	add_edges(V,Xs,Acc1,Acc), al_gt(Ns,Vs,Es,Acc1). 

add_edges(_,[],Es,Es).
add_edges(V,[X/_|Xs],Es,Acc) :- V @> X, !, add_edges(V,Xs,Es,Acc).
add_edges(V,[X|Xs],Es,Acc) :- V @> X, !, add_edges(V,Xs,Es,Acc).
add_edges(V,[X/I|Xs],Es,Acc) :- V @=< X, !, add_edges(V,Xs,Es,[e(V,X,I)|Acc]).
add_edges(V,[X|Xs],Es,Acc) :- V @=< X, add_edges(V,Xs,Es,[e(V,X)|Acc]).

al_dt([],[],As,As).
al_dt([n(V,Xs)|Ns],[V|Vs],As,Acc) :- 
	add_arcs(V,Xs,Acc1,Acc), al_dt(Ns,Vs,As,Acc1). 

add_arcs(_,[],As,As).
add_arcs(V,[X/I|Xs],As,Acc) :- !, add_arcs(V,Xs,As,[a(V,X,I)|Acc]).
add_arcs(V,[X|Xs],As,Acc) :- add_arcs(V,Xs,As,[a(V,X)|Acc]).

% ---------------------------------------------------------------------------

% ecl_to_gterm(GT) :- construct a graph-term from edge/2 facts in the
%	 program database.

ecl_to_gterm(GT) :-
	findall(E,(edge(X,Y),E=X-Y),Es), human_gterm(Es,GT).

% acl_to_gterm(GT) :- construct a graph-term from arc/2 facts in the
%	 program database.

acl_to_gterm(GT) :-
	findall(A,(arc(X,Y),A= >(X,Y)),As), human_gterm(As,GT).

% ---------------------------------------------------------------------------

% human_gterm(HF,GT) :- convert between human-friendly and graph-term
%	 representation.
%	 (list,gterm) (+,?) or (?,+)

human_gterm(HF,GT):- nonvar(GT), !, gterm_to_human(GT,HF).
human_gterm(HF,GT):- nonvar(HF), human_to_gterm(HF,GT).

gterm_to_human(graph(Ns,Es),HF) :-	memberchk(e(_,_,_),Es), !, 
	lgt_hf(Ns,Es,HF).
gterm_to_human(graph(Ns,Es),HF) :-	!, 
	gt_hf(Ns,Es,HF).
gterm_to_human(digraph(Ns,As),HF) :- memberchk(a(_,_,_),As), !, 
	ldt_hf(Ns,As,HF).
gterm_to_human(digraph(Ns,As),HF) :- 
	dt_hf(Ns,As,HF).

% labelled graph
lgt_hf(Ns,[],Ns).
lgt_hf(Ns,[e(X,Y,I)|Es],[X-Y/I|Hs]) :-
	delete(Ns,X,Ns1),
	delete(Ns1,Y,Ns2),
	lgt_hf(Ns2,Es,Hs).

% unlabelled graph
gt_hf(Ns,[],Ns).
gt_hf(Ns,[e(X,Y)|Es],[X-Y|Hs]) :-
	delete(Ns,X,Ns1),
	delete(Ns1,Y,Ns2),
	gt_hf(Ns2,Es,Hs).

% labelled digraph
ldt_hf(Ns,[],Ns).
ldt_hf(Ns,[a(X,Y,I)|As],[X>Y/I|Hs]) :-
	delete(Ns,X,Ns1),
	delete(Ns1,Y,Ns2),
	ldt_hf(Ns2,As,Hs).

% unlabelled digraph
dt_hf(Ns,[],Ns).
dt_hf(Ns,[a(X,Y)|As],[X>Y|Hs]) :-
	delete(Ns,X,Ns1),
	delete(Ns1,Y,Ns2),
	dt_hf(Ns2,As,Hs).

% we guess that if there is a '>' term then it's a digraph, else a graph
human_to_gterm(HF,digraph(Ns,As)) :- memberchk(_>_,HF), !, 
	hf_dt(HF,Ns1,As1), sort(Ns1,Ns), sort(As1,As).
human_to_gterm(HF,graph(Ns,Es)) :- 
	hf_gt(HF,Ns1,Es1), sort(Ns1,Ns), sort(Es1,Es).
% remember: sort/2 removes duplicates!

hf_gt([],[],[]).
hf_gt([X-Y/I|Hs],[X,Y|Ns],[e(U,V,I)|Es]) :- !, 
	sort0([X,Y],[U,V]), hf_gt(Hs,Ns,Es).
hf_gt([X-Y|Hs],[X,Y|Ns],[e(U,V)|Es]) :- !,
	sort0([X,Y],[U,V]), hf_gt(Hs,Ns,Es).
hf_gt([H|Hs],[H|Ns],Es) :- hf_gt(Hs,Ns,Es).

hf_dt([],[],[]).
hf_dt([X>Y/I|Hs],[X,Y|Ns],[a(X,Y,I)|As]) :- !, 
	hf_dt(Hs,Ns,As).
hf_dt([X>Y|Hs],[X,Y|Ns],[a(X,Y)|As]) :- !,
	hf_dt(Hs,Ns,As).
hf_dt([H|Hs],[H|Ns],As) :-	hf_dt(Hs,Ns,As).

sort0([X,Y],[X,Y]) :- X @=< Y, !.
sort0([X,Y],[Y,X]) :- X @> Y.

% tests ------------------------------------------------------------------

testdata([b-c, f-c, g-h, d, f-b, k-f, h-g]).
testdata([s > r, t, u > r, s > u, u > s, v > u]).
testdata([b-c/5, f-c/9, g-h/12, d, f-b/13, k-f/3, h-g/7]).
testdata([p>q/9, m>q/7, k, p>m/5]).
testdata([a,b(4711),c]).
testdata([a-b]).
testdata([]).

test :- 
	testdata(H1),
	write(H1), nl,
	human_gterm(H1,G1),
	alist_gterm(Type,AL,G1), 
	alist_gterm(Type,AL,G2),
	human_gterm(H2,G2),
	human_gterm(H2,G1),
	write(G1), nl, nl,
	fail.
test.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 2 Path from one node to another one
% Write a predicate path(G,A,B,P) to find an acyclic path P from node A to node B in the graph G. The predicate should return all paths via backtracking. 

% path(G,A,B,P) :- P is a (acyclic) path from node A to node B in the graph G.
%	G is given in graph-term form.
%	(+,+,+,?)
path(G,A,B,P) :- 
	path1(G,A,[B],P).

path1(_,A,[A|P1],[A|P1]).
path1(G,A,[Y|P1],P) :- 
	adjacent(X,Y,G), 
	\+ memberchk(X,[Y|P1]), 
	path1(G,A,[X,Y|P1],P).

% A useful predicate: adjacent/3
adjacent(X,Y,graph(_,Es)) :- member(e(X,Y),Es).
adjacent(X,Y,graph(_,Es)) :- member(e(Y,X),Es).
adjacent(X,Y,graph(_,Es)) :- member(e(X,Y,_),Es).
adjacent(X,Y,graph(_,Es)) :- member(e(Y,X,_),Es).
adjacent(X,Y,digraph(_,As)) :- member(a(X,Y),As).
adjacent(X,Y,digraph(_,As)) :- member(a(X,Y,_),As). 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 3 Cycle from a given node 
% Write a predicate cycle(G,A,P) to find a closed path (cycle) P starting at a given node A in the graph G. The predicate should return all cycles via backtracking. 

% cycle(G,A,P) :- P is a closed path starting at node A in the graph G.
%	 G is given in graph-term form.
%	 (+,+,?) 
cycle(G,A,P) :- 
	adjacent(B,A,G), 
	path(G,A,B,P1), 
	length(P1,L), 
	L > 2, 
	append(P1,[A],P). 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 4 Graph isomorphism
% Two graphs G1(N1,E1) and G2(N2,E2) are isomorphic if there is a bijection f: N1 -> N2 such that for any nodes X,Y of N1, X and Y are adjacent if and only if f(X) and f(Y) are adjacent.
% Write a predicate that determines whether two graphs are isomorphic. Hint: Use an open-ended list to represent the function f.

% s_tree(G,T) :- T is a spanning tree of the graph G
%	 (graph-term graph-term) (+,?) 
s_tree(graph([N|Ns],GraphEdges),graph([N|Ns],TreeEdges)) :- 
	transfer(Ns,GraphEdges,TreeEdgesUnsorted),
	sort(TreeEdgesUnsorted,TreeEdges).

% transfer(Ns,GEs,TEs) :- transfer edges from GEs (graph edges)
%	 to TEs (tree edges) until the list NS of still unconnected tree nodes
%	 becomes empty. An edge is accepted if and only if one end-point is 
%	 already connected to the tree and the other is not.

transfer([],_,[]).
transfer(Ns,GEs,[GE|TEs]) :- 
	select(GE,GEs,GEs1),			% modified 15-May-2001
	incident(GE,X,Y),
	acceptable(X,Y,Ns),
	delete(Ns,X,Ns1),
	delete(Ns1,Y,Ns2),
	transfer(Ns2,GEs1,TEs).

incident(e(X,Y),X,Y).
incident(e(X,Y,_),X,Y).

acceptable(X,Y,Ns) :- memberchk(X,Ns), \+ memberchk(Y,Ns), !.
acceptable(X,Y,Ns) :- memberchk(Y,Ns), \+ memberchk(X,Ns).

% An almost trivial use of the predicate s_tree/2 is the following
% tree tester predicate:
% is_tree(G) :- the graph G is a tree
is_tree(G) :- s_tree(G,G), !.

% Another use is the following connectivity tester:
% is_connected(G) :- the graph G is connected
is_connected(G) :- s_tree(G,_), !. 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 5 Node degree and graph coloration
% a) Write a predicate degree(Graph,Node,Deg) that determines the degree of a given node.
% b) Write a predicate that generates a list of all nodes of a graph sorted according to decreasing degree.
% c) Use Welch-Powell's algorithm to paint the nodes of a graph in such a way that adjacent nodes have different colors. 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 6 Depth-first order graph traversal
% Write a predicate that generates a depth-first order graph traversal sequence. The starting point should be specified, and the output should be a list of nodes that are reachable from this starting point (in depth-first order). 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 7 Connected components
% Write a predicate that splits a graph into its connected components. 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 8 Bipartite graphs
% Write a predicate that finds out whether a given graph is bipartite.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
% 9 Generate K-regular simple graphs with N nodes
% In a K-regular graph all nodes have a degree of K; i.e. the number of edges incident in each node is K. How many (non-isomorphic!) 3-regular graphs with 6 nodes are there?
 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% BINARY TREE
% 1 Check whether a given term represents a binary tree
% Write a predicate istree/1 which succeeds if and only if its argument is a Prolog term representing a binary tree.
% Example:
% ?- istree(t(a,t(b,nil,nil),nil)).
% Yes
% ?- istree(t(a,t(b,nil,nil))).
% No

% istree(T) :- T is a term representing a binary tree (i), (o)
istree(nil).
istree(t(_,L,R)) :- 
	istree(L), 
	istree(R).

% Test cases (can be used for other binary tree problems as well)
tree(1,t(a,t(b,t(d,nil,nil),t(e,nil,nil)),t(c,nil,t(f,t(g,nil,nil),nil)))).
tree(2,t(a,nil,nil)).
tree(3,nil).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 2 Construct completely balanced binary trees
% In a completely balanced binary tree, the following property holds for every node: The number of nodes in its left subtree and the number of nodes in its right subtree are almost equal, which means their difference is not greater than one.
% Write a predicate cbal_tree/2 to construct completely balanced binary trees for a given number of nodes. The predicate should generate all solutions via backtracking. Put the letter 'x' as information into all nodes of the tree.
% Example:
% ?- cbal_tree(4,T).
% T = t(x, t(x, nil, nil), t(x, nil, t(x, nil, nil))) ;
% T = t(x, t(x, nil, nil), t(x, t(x, nil, nil), nil)) ;
% etc......No

% cbal_tree(N,T) :- T is a completely balanced binary tree with N nodes.
% (integer, tree)	(+,?)

cbal_tree(0,nil) :- 
	!.
cbal_tree(N,t(x,L,R)) :- 
	N > 0,
	N0 is N - 1, 
	N1 is N0//2, 
	N2 is N0 - N1,
	distrib(N1,N2,NL,NR),
	cbal_tree(NL,L), 
	cbal_tree(NR,R).

distrib(N,N,N,N) :- 
	!.
distrib(N1,N2,N1,N2).
distrib(N1,N2,N2,N1). 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 3 Symmetric binary trees
% Let us call a binary tree symmetric if you can draw a vertical line through the root node and then the right subtree is the mirror image of the left subtree. Write a predicate symmetric/1 to check whether a given binary tree is symmetric. Hint: Write a predicate mirror/2 first to check whether one tree is the mirror image of another. We are only interested in the structure, not in the contents of the nodes.

% symmetric(T) :- the binary tree T is symmetric.
symmetric(nil).
symmetric(t(_,L,R)) :- 
	mirror(L,R).

mirror(nil,nil).
mirror(t(_,L1,R1),t(_,L2,R2)) :- 
	mirror(L1,R2), 
	mirror(R1,L2).
 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
% 4 Binary search trees (dictionaries)
% Use the predicate add/3, developed in chapter 4 of the course, to write a predicate to construct a binary search tree from a list of integer numbers.
% Example:
% ?- construct([3,2,5,7,1],T).
% T = t(3, t(2, t(1, nil, nil), nil), t(5, nil, t(7, nil, nil)))
% Then use this predicate to test the solution of the problem P56.
% Example:
% ?- test_symmetric([5,3,18,1,4,12,21]).
% Yes
% ?- test_symmetric([3,2,5,7,4]).
% No

% add(X,T1,T2) :- the binary dictionary T2 is obtained by 
% adding the item X to the binary dictionary T1
% (element,binary-dictionary,binary-dictionary) (i,i,o)

add(X,nil,t(X,nil,nil)).
add(X,t(Root,L,R),t(Root,L1,R)) :- 
	X @< Root, 
	add(X,L,L1).
add(X,t(Root,L,R),t(Root,L,R1)) :- 
	X @> Root, 
	add(X,R,R1).

construct(L,T) :- 
	construct(L,T,nil).

construct([],T,T).
construct([N|Ns],T,T0) :- 
	add(N,T0,T1), 
	construct(Ns,T,T1).
 	
test_symmetric(L) :- 
	construct(L,T), 
	symmetric(T). 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% MULTI TREE

% 1. Check whether a given term represents a multiway tree
% Write a predicate istree/1 which succeeds if and only if its argument is a Prolog term representing a multiway tree.
% Example:
% ?- istree(t(a,[t(f,[t(g,[])]),t(c,[]),t(b,[t(d,[]),t(e,[])])])).
% Yes

% the following is a test case:
mtree(1,t(a,[t(f,[t(g,[])]),t(c,[]),t(b,[t(d,[]),t(e,[])])])).

ismtree(t(_,F)) :- isforest(F).

isforest([]).
isforest([T|Ts]) :- ismtree(T), isforest(Ts).
 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 2. Count the nodes of a multiway tree
% Write a predicate nnodes/1 which counts the nodes of a given multiway tree.
% Example:
% ?- nnodes(t(a,[t(f,[])]),N).
% N = 2
% Write another version of the predicate that allows for a flow pattern (o,i).

nnodes(t(_,F),N) :- 
	nnodes(F,NF), 
	N is NF+1.

nnodes([],0).
nnodes([T|Ts],N) :- 
	nnodes(T,NT), 
	nnodes(Ts,NTs), 
	N is NT+NTs.

% Note that nnodes is called for trees and for forests. An early
% form of polymorphism!

% For the flow pattern (o,i) we can write:

nnodes2(t(_,F),N) :- 
	N > 0, 
	NF is N-1, 
	nnodes2F(F,NF).

nnodes2F([],0).
nnodes2F([T|Ts],N) :- 
	N > 0, 
	between(1,N,NT), 
	nnodes2(T,NT), 
	NTs is N-NT, 
	nnodes2F(Ts,NTs).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 3. Determine the internal path length of a tree.
% We define the internal path length of a multiway tree as the total sum of the path lengths from the root to all nodes of the tree.
% Write a predicate ipl(Tree,IPL) for the flow pattern (+,-). 

% ipl(Tree,L) :- L is the internal path length of the tree Tree
%	 (multiway-tree, integer) (+,?)
ipl(T,L) :- 
	ipl(T,0,L).

ipl(t(_,F),D,L) :- 
	D1 is D+1, 
	ipl(F,D1,LF), 
	L is LF+D.

ipl([],_,0).
ipl([T1|Ts],D,L) :- 
	ipl(T1,D,L1), 
	ipl(Ts,D,Ls), 
	L is L1+Ls.

% Notice the polymorphism: ipl is called with trees and with forests
% as first argument.
 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 4. Construct the bottom-up order sequence of the tree nodes
% Write a predicate bottom_up(Tree,Seq) which constructs the bottom-up sequence of the nodes of the multiway tree Tree. Seq should be a Prolog list. 
% What happens if you run your predicate backwords? 

% bottom_up(Tree,Seq) :- Seq is the bottom-up sequence of the nodes of
%	 the multiway tree Tree. (+,?)
bottom_up_f(t(X,F),Seq) :- 
	bottom_up_f(F,SeqF), 
	append(SeqF,[X],Seq).

bottom_up_f([],[]).
bottom_up_f([T|Ts],Seq):-
	bottom_up_f(T,SeqT), 
	bottom_up_f(Ts,SeqTs), 
	append(SeqT,SeqTs,Seq).

% The predicate bottom_up/2 produces a stack overflow when called
% in the (-,+) flow pattern. There are two problems with that.
% First, the polymorphism does not work properly, because during
% decomposing the string, the program cannot guess whether it should
% construct a tree or a forest next. We can fix this using two
% separate predicates bottom_up_tree/2 and bottom_up_forset/2.
% Secondly, if we maintain the order of the subgoals, then
% the interpreter falls into an endless loop after finding the
% first solution. We can fix this by changing the order of the
% goals as follows:
bottom_up_tree(t(X,F),Seq) :- % (?,+)
	append(SeqF,[X],Seq), 
	bottom_up_forest(F,SeqF).

bottom_up_forest([],[]).
bottom_up_forest([T|Ts],Seq):-
	append(SeqT,SeqTs,Seq),
	bottom_up_tree(T,SeqT), 
	bottom_up_forest(Ts,SeqTs).

% Unfortunately, this version doesn't run in both directions either.

% In order to have a predicate which runs forward and backward, we
% have to determine the flow pattern and then call one of the above
% predicates, as follows:
bottom_up(T,Seq) :- 
	nonvar(T), 
	!, 
	bottom_up_f(T,Seq).
bottom_up(T,Seq) :- 
	nonvar(Seq), 
	bottom_up_tree(T,Seq). 
 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 5. Lisp-like tree representation
% There is a particular notation for multiway trees in Lisp. Lisp is a prominent functional programming language, which is used primarily for artificial intelligence problems. As such it is one of the main competitors of Prolog. In Lisp almost everything is a list, just as in Prolog everything is a term. 
% A tree with root a and children b and c would be (a (b c)) in Lisp.

% tree_ltl(T,L) :- L is the "lispy token list" of the multiway tree T
% (i,o)
tree_ltl(t(X,[]),[X]).
tree_ltl(t(X,[T|Ts]),L) :-
	tree_ltl(T,L1),
	append(['(',X],L1,L2),
	rest_ltl(Ts,L3),
	append(L2,L3,L).

rest_ltl([],[')']).
rest_ltl([T|Ts],L) :-
	tree_ltl(T,L1),
	rest_ltl(Ts,L2),
	append(L1,L2,L).

% some auxiliary predicates
write_ltl([]) :- 
	nl.
write_ltl([X|Xs]) :- 
	write(X), 
	write(' '), 
	write_ltl(Xs).

dotest(T) :- write(T), nl, tree_ltl(T,L), write_ltl(L).

test(1) :- T = t(a,[t(b,[]),t(c,[])]), dotest(T).

test(2) :- T = t(a,[t(b,[t(c,[])])]), dotest(T).
test(3) :- T = t(a,[t(f,[t(g,[])]),t(c,[]),t(b,[t(d,[]),t(e,[])])]), dotest(T). 

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
