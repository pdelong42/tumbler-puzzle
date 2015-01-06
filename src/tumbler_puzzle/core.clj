(ns tumbler-puzzle.core
  (  :require [clojure.math.combinatorics :refer
     [  permutations
        cartesian-product  ]  ]  )
  (:gen-class))

; Here's a first pass at the a data structure.  This is inadquate in many ways,
; but I just wanted to get the basic structure transcribed for starters, and ;
; make it more robust as this evolves.  Update: I made a second pass - it's
; still woefully inadequate, but it's slightly more useful.

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

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (println "Hello, World!")  )
