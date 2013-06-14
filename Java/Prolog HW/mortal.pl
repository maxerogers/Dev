mortal(X) :- person(X).
person(socrates).
person(plato).
person(aristotle).
mortal_resport :- 
	write(;Known mortals are:'), nl,
	mortal(X),
	write(X), nl,
	fail.