(ns clojure-fu.core-test
  (:require [clojure.test :refer :all]
            [clojure-fu.core :refer :all]))

(def grid '[\t \h \e \d \o \i \c \k \b \r
            \o \w \n \g \o \x \j \u \m \p])

(deftest exists-in-grid-test
  (testing "Given a word and a grid of letters, determines if the word can be found in the grid."
    (is (= true (exists-in-grid "good" grid 10)))
    (is (= false (exists-in-grid "quick" grid 10)))
))

(deftest test-north
  (testing "Returns the position one block north of the current position"
    (let [grid_width 10
          grid_height 2]
      (is (= 3 (north 13 grid_width grid_height)))
      (is (= nil (north 3 grid_width grid_height))))))

(deftest test-south
  (testing "Returns the position one block south of the current position"
    (let [grid_width 10
          grid_height 2]
      (is (= 13 (south 3 grid_width grid_height)))
      (is (= nil (south 13 grid_width grid_height))))))

(deftest test-east
  (testing "Returns the position one block east of the current position"
    (let [grid_width 10
          grid_height 2]
      (is (= 14 (east 13 grid_width grid_height)))
      (is (= nil (east 9 grid_width grid_height))))))

(deftest test-west
  (testing "Returns the position one block west of the current position"
    (let [grid_width 10
          grid_height 2]
      (is (= 12 (west 13 grid_width grid_height)))
      (is (= nil (west 10 grid_width grid_height))))))

