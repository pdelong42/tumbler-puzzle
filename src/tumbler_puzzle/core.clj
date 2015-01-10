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

(defn try-all-valid-arrangements []
   (map
      (fn
         [[x y z]]
         (interleave (cons z y) x)  )
      (cartesian-product
         (permutations (keys    digits))
         (permutations (keys operators))
         (apply cartesian-product (take 7 (repeat [0 1 2 3])))  )  )  )

(defn -main
   [& args]
   ;; work around dangerous default behaviour in Clojure
   (alter-var-root #'*read-eval* (constantly false))
   (dorun (map println (try-all-valid-arrangements)))  )
;   (println (count (try-all-valid-arrangements)))  )
