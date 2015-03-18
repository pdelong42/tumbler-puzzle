(ns tumbler-puzzle.core-test
   (:require
      [clojure.pprint      :refer [pprint]]
      [clojure.test        :refer :all]
      [tumbler-puzzle.core :refer :all]  )  )

(def valid-arrangement-1
  '(  (:mango :red :blue :green :yellow)
      (:mint :white :orange :cyan)
      (0 3 3 0 1 3 2)  )  )

(deftest test-valid-arrangement-1
   (testing "valid arrangement #1"
      (is (not (nil? (test-arrangement valid-arrangement-1))))  )  )

(def invalid-arrangement-1
  '(  (:mango :red :blue :green :yellow)
      (:mint :white :orange :cyan)
      (0 3 2 0 1 3 2)  )  )

(deftest test-invalid-arrangement-1
   (testing "invalid arrangement #1"
      (is (nil? (test-arrangement invalid-arrangement-1)))  )  )

(def twelve-arrangements
  '(  ((:mango :red :blue :green :yellow) (:mint :white :orange :cyan) (0 3 3 0 1 3 2))
      ((:mango :red :blue :green :yellow) (:mint :cyan :orange :white) (0 3 2 2 3 3 2))
      ((:mango :red :blue :yellow :green) (:mint :white :orange :cyan) (0 3 3 0 0 3 3))
      ((:mango :red :blue :yellow :green) (:mint :white :orange :cyan) (3 0 2 3 3 1 3))
      ((:mango :red :blue :yellow :green) (:cyan :mint :orange :white) (2 2 1 1 0 3 1))
      ((:mango :red :green :blue :yellow) (:orange :cyan :mint :white) (1 3 0 3 2 2 2))
      ((:mango :red :green :blue :yellow) (:mint :white :cyan :orange) (3 1 1 3 2 0 0))
      ((:mango :red :yellow :blue :green) (:mint :white :orange :cyan) (1 1 3 2 3 2 0))
      ((:mango :red :yellow :blue :green) (:mint :orange :cyan :white) (0 1 3 3 1 3 2))
      ((:mango :red :yellow :green :blue) (:mint :white :cyan :orange) (1 1 3 2 0 2 3))
      ((:mango :blue :red :green :yellow) (:cyan :white :orange :mint) (0 1 2 2 1 2 2))
      ((:mango :blue :red :green :yellow) (:cyan :white :orange :mint) (2 1 1 1 2 2 2))  )  )

(deftest twelve-valid-arrangments
   (testing "12 valid arrangements"
      (is (= 12 (count (try-arrangements twelve-arrangements))))  )  )

;(pprint (try-arrangements twelve-arrangements))
