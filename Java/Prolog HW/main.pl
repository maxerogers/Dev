%%Author: Max Rogers
%%ID: 107979405
%%CSE 307 - Spring 2013
%%Homework 7

%	Buy(Stores,Links,MinCustomers) 

%	Store(Name, Capacity, Bankrupt, Waiting)
%		Name is a Unique name for the store
%		Capacity is the number of customers required to buy all the sales in the store
%		Bankrupt is the number of customers bankrupt after buying all sales in the store
%		Waiting are the number of customers that remain in the store to wait for more sales.  

%	Link
%		A link connects two stores which customers are allowed to trave by
%	

%	Move(Store,Store,NumCustomer)
%		This moves customers through the link the new store

%	Bankrupt
%		This will bankrupt all customers who are currently waiting


hello_world :- write('Hello World!").