This is going to be my brute-force attempt at finding the solutions to
my a puzzle I acquired a number of years ago (I think I may have
inherited it from a roommate).  Look in the docs directory for the
photos of the puzzle at a number of angles, which captures all sides
of it.  My first task will be to convert this to a convenient
schematic, data structure for manipulating.

In short (as you can probably see), the goal is to arrange the
tumblers so that all four simple arithmetic equations are true
simultaneously.  The tumblers can be pulled apart and re-assembled,
and rotated, into many permutations.  There are some restrictions and
simplifying constraints:

 - it makes no sense to have two operators adjacent

 - by elimination, this means all digits must be singletons (no
   multi-digit numbers)

 - the equal sign should not count as a real tumbler, otherwise it
   will result in duplicate solutions (x4), which are only different
   by an offset

 - the first tumbler in any permutation should be held stationary
   while the others are rotated, to avoid further duplicate solutions
   (again, x4)

To enumerate the solutions:

 - there are 24 (4!) permutations of the operator tumblers
 - there are 120 (5!) permutations of the digit tumblers
 - there are 2880 (24*120) meaningful orderings of all tumblers
 - there are 16384 (4^7) possible arrangements of each tumbler
   ordering (not 4^8, since we always hold one tumbler fixed)
 - there are 47185920 (2880*16384) total solutions to try

There are probably some group-theoretic arguments that can be made to
really simplify the solution space, but I'm only a group theory
novice.

A Lisp, or some other language with lazy evaluation (I plan to use
Clojure), is probably the best tool for working through all the
permutations and trying each one in-turn.  It might help reduce memory
footprint (we'll see).

What remains open to interpretation is whether to treat the operations
as floating-point, or to treat them strictly as operating on integers.
Another is whether to enforce precedence of operators, or apply them
naively from left to right.

As a first pass, I'm going to assume left-to-right (for each side of
the equation), and take advantage of Clojure's default representation
of numbers as rationals.  I could always change it later.
