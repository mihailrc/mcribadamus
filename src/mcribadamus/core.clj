(ns mcribadamus.core)

(defn to-symbol [year symbol-entry]
  (str (:symbol symbol-entry) year ".CME"))

(defn symbols-after-month [month symbols-map]
  (drop-while #(> month (:month %)) symbols-map))

(defn symbols-for-year [year month symbols-map]
  (map #(to-symbol year %)
        (symbols-after-month month symbols-map)))

(defn future-symbols [start-year start-month symbols-map]
  (lazy-seq
    (concat (symbols-for-year start-year start-month symbols-map)
      (future-symbols (inc start-year) 1 symbols-map))))
