//monkey banana problem 


/* Description:

Imagine a room containing a monkey, chair and some bananas. That have been hanged from the center of ceiling. If the monkey is clever enough he can reach the bananas by placing the chair directly below the bananas and climb on the chair . 

The problem is to prove the monkey can reach the bananas.

The monkey can perform the following actions:
1) Walk on the floor
2) Climb the box
3) Push the box around(if it is beside the box).
4) Grasp the banana if it is standing on the box directly under the banana.

*/

% Production rules:

can_reach 🡪 clever,close.
get_on: 🡪 can_climb.

under 🡪 in room,in_room, in_room,can_climb.
Close 🡪 get_on,under | tall

% Clauses:

in_room(bananas).
in_room(chair).
in_room(monkey).
clever(monkey).
can_climb(monkey, chair).
tall(chair).
can_move(monkey, chair, bananas).
can_reach(X, Y):-clever(X),close(X, Y).
get_on(X,Y):- 
	can_climb(X,Y).
under(Y,Z):-
	in_room(X),in_room(Y),
	in_room(Z),can_climb(X,Y,Z).
close(X,Z):-
	get_on(X,Y), under(Y,Z);
	tall(Y).

Output:
% Queries:

 ?- can_reach(A, B).
 A = monkey.
 B = banana.

 ?- can_reach(monkey, banana).
 Yes.
