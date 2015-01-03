(ns tumbler-puzzle.core (:gen-class))

; Here's a first pass at the a data structure.  This is inadquate in many ways,
; but I just wanted to get the basic structure transcribed for starters, and
; make it more robust as this evolves.

(def tumblers
  #{  [1 3 4 2]
      [+ * - /]
      [1 3 2 4]
      [+ / * -]
      [1 4 3 2]
      [+ - / *]
      [1 2 4 3]
      [= = = =]
      [4 2 3 1]  }  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (println "Hello, World!")  )
