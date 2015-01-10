(ns tumbler-puzzle.core
  (  :require
     [clojure.pprint :refer [pprint]]
     [clojure.math.combinatorics :refer [permutations cartesian-product]]  )
  (:gen-class))

(def digits
   {  :red    '(1 3 4 2)
      :green  '(1 3 2 4)
      :mango  '(1 4 3 2)
      :blue   '(1 2 4 3)
      :yellow '(4 2 3 1)  }  )

(def operators
   {  :orange '(+ * - /)
      :cyan   '(+ / * -)
      :mint   '(+ - / *)
      :white  '(= = = =)  }  )

(defn test-arrangement
   [  [digit-tumblers mobile-operators index-tuple]  ]
   (let
      [  fixed-tumbler (first digit-tumblers)
         mobile-digits (rest  digit-tumblers)
         mobile-tumblers (interleave mobile-operators mobile-digits)
         equals-index (.indexOf mobile-tumblers :white)
         left-side  (take equals-index index-tuple)
         right-side (drop equals-index index-tuple)
         new-tumblers (cons fixed-tumbler mobile-tumblers)
         new-index-tuple (concat [:fixed] left-side [:equals] right-side)  ]
      [new-index-tuple new-tumblers]  )  )

; The above looks like it works when I pipe the output to head, but I
; should test that for when I pipe it to tail as well

(defn try-all-valid-arrangements []
   (map
      test-arrangement
      (cartesian-product
         (permutations (keys    digits))
         (permutations (keys operators))
         (apply cartesian-product (take 7 (repeat [0 1 2 3])))  )  )  )

; ToDo: parameterize the "magic" (i.e., hard-coded) numbers on the
; previous line

(defn -main
   [& args]
   ;; work around dangerous default behaviour in Clojure
   (alter-var-root #'*read-eval* (constantly false))
   (dorun (map println (try-all-valid-arrangements)))  )
;   (println (count (try-all-valid-arrangements)))  )
