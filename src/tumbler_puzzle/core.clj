(ns tumbler-puzzle.core
   (  :require
      [clojure.math.combinatorics :refer [permutations cartesian-product]]
      [clojure.pprint :refer [pprint]]
      [clojure.tools.trace :refer [dotrace]]
      [joy.unfix.infix :refer [infix infix-func]]  )
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

(def tumblers (merge digits operators))

(def idx-max 4)

(def index-range (range idx-max))

(defn mod-index-tuple [tuple offset]
   (map #(mod (+ % offset) idx-max) tuple)  )

(defn build-equation
   [tumbler-name index-number]
   (nth (tumbler-name tumblers) index-number)  )

(defn build-equations
   [tumbler-tuple index-tuple]
   (map
     #(map build-equation tumbler-tuple %)
      (map (partial mod-index-tuple index-tuple) index-range)  )  )

(defn test-arrangement
   [  [digit-tumblers mobile-operators index-tuple]  ]
   (let
      [  fixed-tumbler (first digit-tumblers)
         mobile-digits (rest  digit-tumblers)
         mobile-tumblers (interleave mobile-operators mobile-digits)
         equals-index (.indexOf mobile-tumblers :white)
         left-side  (take equals-index index-tuple)
         right-side (drop equals-index index-tuple)
         new-tumbler-tuple (cons fixed-tumbler mobile-tumblers)
         new-index-tuple (concat [0] left-side [0] right-side)
         quadruplet (build-equations new-tumbler-tuple new-index-tuple)  ]
      (if (every? infix-func quadruplet) quadruplet)  )  )
;      (every? infix-func quadruplet)  )  )

; ToDo: write a test routine which takes pre-defined arrangements as
; input (rather than all of them), so that we can test known true and
; known false scenarios;

(defn try-arrangements
   [arrangements]
   (remove nil? (map test-arrangement arrangements))  )

(defn produce-all-valid-arrangements []
   (cartesian-product
      (permutations (keys    digits))
      (permutations (keys operators))
      (apply cartesian-product (take 7 (repeat index-range)))  )  )

; We only need to take seven copies of the index-range, since we
; require a seven-tuple to construct our index vectors.

(defn -main
   [& args]
   ;; work around dangerous default behaviour in Clojure
   (alter-var-root #'*read-eval* (constantly false))
   (dorun (map println (try-arrangements (produce-all-valid-arrangements))))  )
;   (println (count (try-arrangements (produce-all-valid-arrangements))))  )
