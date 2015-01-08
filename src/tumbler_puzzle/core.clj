(ns tumbler-puzzle.core
  (  :require
     [clojure.pprint :refer [pprint]]
     [clojure.math.combinatorics :refer [permutations cartesian-product]]  )
  (:gen-class))

; Here's a first pass at the a data structure.  This is inadquate in
; many ways, but I just wanted to get the basic structure transcribed
; for starters, and make it more robust as this evolves.  Update: I
; made a second pass - it's still woefully inadequate, but it's
; slightly more useful.

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

(defn dothething []
   (map
      (fn
         [[x y z]]
         (println (interleave (cons z y) x))  )
      (cartesian-product
         (permutations (keys    digits))
         (permutations (keys operators))
         (apply cartesian-product (take 7 (repeat [0 1 2 3])))  )  )  )

(defn -main
   [& args]
   ;; work around dangerous default behaviour in Clojure
   (alter-var-root #'*read-eval* (constantly false))
   (dorun (dothething))  )
