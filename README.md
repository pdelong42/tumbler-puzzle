This is going to be my brute-force attempt at finding the solutions to my a puzzle I acquired a number of years ago (I think I may have inherited it from a roommate).  Look in the docs directory for the photos of the puzzle at a number of angles, which captures all sides of it.  My first task will be to convert this to a convenient schematic, data structure for manipulating.

In short (as you can probably see), the goal is to arrange the tumblers so that all four simple arithmetic equations are true simultaneously.  The tumblers can be pulled apart and re-assembled, and rotated, into many permutations.  There are some restrictions:

 - it makes no sense to have two operators adjacent
 - by elimination, this means all digits must be singletons (no multi-digit numbers)
 - the equal sign probably shouldn't count, except as a separator (since it's the same on all four sides)

There's probably some group-theoretic arugments that can be made to really simplify the solution space, but I'm only a group theory novice.

A Lisp, or some other language with lazy evaluation (I plan to use Clojure), is probably the best tool for working through all the permutations and trying each one in-turn.  It might help reduce memory footprint (we'll see).
