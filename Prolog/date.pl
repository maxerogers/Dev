%Author: Max Rogers
%Date example

%constructor
make_date(Y,M,D,date(Y,M,D)).

get_year(date(Y,_,_),Y).
get_month(date(_,M,_),M).
get_day(date(_,_,D),D).

set_year(Y,date(_,M,D),date(Y,M,D)).
set_month(M,date(Y,_,D),date(Y,M,D)).
set_day(D,date(Y,M,_),date(Y,M,D)).

next_year(Today, NextYear) :-
	get_year(Today,Y), NY is Y+1, set_year(NY,Today, NextYear).
prev_year(Today, PrevYear) :-
	get_year(Today,Y), PY is Y-1, set_year(PY,Today, PrevYear).