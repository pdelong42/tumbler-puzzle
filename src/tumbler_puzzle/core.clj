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
         new-index-tuple (concat [0] left-side [0] right-side)  ]
      (build-equations new-tumbler-tuple new-index-tuple)  )  )

; Okay, it looks like I can generate one equation for each tumbler
; arrangement.  Now I need to implement the logic to generate the
; other three.

(defn try-all-valid-arrangements []
   (map
      test-arrangement
      (cartesian-product
         (permutations (keys    digits))
         (permutations (keys operators))
         (apply cartesian-product (take 7 (repeat index-range)))  )  )  )

; ToDo: parameterize the "magic" (i.e., hard-coded) numbers on the
; previous line

(defn -main
   [& args]
   ;; work around dangerous default behaviour in Clojure
   (alter-var-root #'*read-eval* (constantly false))
   (dorun (map println (try-all-valid-arrangements)))  )
;   (println (count (try-all-valid-arrangements)))  )
