(ns clojure-fu.core)

(defn north [current_pos grid_width grid_height]
  (let [new_pos (- current_pos grid_width)]
    (if (> new_pos 0) new_pos nil)))

(defn south [current_pos grid_width grid_height]
  (let [new_pos (+ current_pos grid_width)]
    (if (< new_pos (* grid_width grid_height)) new_pos nil)))

(defn east [current_pos grid_width grid_height]
  (if (= 9 (mod current_pos grid_width)) nil (inc current_pos)))

(defn west [current_pos grid_width grid_height]
  (if (= 0 (mod current_pos grid_width)) nil (dec current_pos)))

(defn find-word [current_idx word grid grid_width grid_height visited]
  (cond
    (nil? current_idx) false
    (zero? (count word)) true
    (true? (contains? visited current_idx)) false ; we've already visited this node
    (not (= (first word) (nth grid current_idx))) false ; this node doesn't match the first letter
    :else (cond
      (find-word (north current_idx grid_width grid_height) (rest word) grid grid_width grid_height (conj visited current_idx)) true
      (find-word (east current_idx grid_width grid_height) (rest word) grid grid_width grid_height (conj visited current_idx)) true
      (find-word (south current_idx grid_width grid_height) (rest word) grid grid_width grid_height (conj visited current_idx)) true
      (find-word (west current_idx grid_width grid_height) (rest word) grid grid_width grid_height (conj visited current_idx)) true
      :else false)))

(defn exists-in-grid
  "find a word in a grid by traversing N, S, E, W (no diagonals, no repeats)"
  [word grid grid_width]
  (contains? (set (for
    [current_tile (map-indexed vector grid)]
    (let [[current_idx current_char] current_tile
          grid_height (/ (count grid) grid_width)
          visited #{}]
      (find-word current_idx word grid grid_width grid_height visited)))) true))
