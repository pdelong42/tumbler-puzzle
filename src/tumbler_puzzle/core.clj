(ns tumbler-puzzle.core
  (  :require
     [clojure.pprint :refer [pprint]]
     [clojure.math.combinatorics :refer
        [  permutations
           cartesian-product  ]  ]  )
  (:gen-class))

; Here's a first pass at the a data structure.  This is inadquate in
; many ways, but I just wanted to get the basic structure transcribed
; for starters, and make it more robust as this evolves.  Update: I
; made a second pass - it's still woefully inadequate, but it's
; slightly more useful.

(def tumblers
   {  :red    '(1 3 4 2)
      :orange '(+ * - /)
      :green  '(1 3 2 4)
      :cyan   '(+ / * -)
      :mango  '(1 4 3 2)
      :mint   '(+ - / *)
      :blue   '(1 2 4 3)
      :white  '(= = = =)
      :yellow '(4 2 3 1)  }  )

(def digits [:red :green :mango :blue :yellow])

(def operators [:orange :cyan :mint :white])

(defn interleave-off-by-one
   [[x y z]]
   (cons
      (first x)
      (interleave y (rest x))  )  )

; This is an intermediate state - for one thing, I don't use 'z'
; above, yet.  But I checked, and the count of results (47185920) is
; exactly what I expected it to be.

(defn -main
   [& args]
   ;; work around dangerous default behaviour in Clojure
   (alter-var-root #'*read-eval* (constantly false))
   (pprint
      (map
         interleave-off-by-one
         (cartesian-product
            (permutations digits)
            (permutations operators)
            (apply cartesian-product (take 7 (repeat [0 1 2 3])))  )  )  )  )

; So far so good.  Now I need to interleave these 2880 results, and
; use them as an vector index into the 16384 tumbler orderings.  Or
; rather, use each of the 16384 tumbler orderings as a vector index
; into the tumbler permutations.  I haven't decided which way to think
; of it. See also the following snippet of code:
;
; (count (apply cartesian-product (take 7 (repeat [0 1 2 3]))))
