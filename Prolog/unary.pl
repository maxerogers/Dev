%	unary representation of numbers
%	author: Max Rogers

unary_num(0).
unary_num(s(X)) :- unary_num(X).

plus(0,X,X).
plus(s(X),Y,Z) :- plus(X,s(Y),Z).