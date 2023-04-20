(ns week8code.core (:require [clj-genetic.core :as genetic]))

;; Define the problem
(def investments ["IBM" "GOOG" "AAPL" "AMZN" "MSFT"])
(def returns [0.05 0.07 0.10 0.12 0.09])
(def risks [0.10 0.15 0.20 0.25 0.18])

;; Define the fitness function
(defn fitness [solution]
  (let [return (apply + (mapv * solution returns))
        risk (apply + (mapv * solution risks))]
    (/ return risk)))

;; Generate an initial population
(defn generate-solution []
  (vec (repeatedly (count investments) #(rand-int 2))))

(defn generate-population [size]
  (vec (repeatedly size generate-solution)))

;; Run the genetic algorithm
(defn optimize-portfolio [population-size num-generations]
  (genetic/evolve {
                    :population-size population-size
                    :fitness-fn fitness
                    :termination {:generations num-generations}
                    :crossover-fn genetic/crossover-n-point
                    :crossover-args {:n 1}
                    :mutation-fn genetic/mutate-bit
                    :mutation-rate 0.1
                    :generation-gap 0.5
                    :seed (System/currentTimeMillis)
                    :reporting-fn #(println "Generation" (:generation %) "Best fitness:" (:best-fitness %))
                    :population (generate-population population-size)
                    }))

;; Print the optimized portfolio
(defn print-optimized-portfolio [solution]
  (let [portfolio (mapv #(if (= % 0) nil (investments %)) solution)]
    (println "Optimized portfolio:" portfolio)))

;; Run the optimization
(defn -main []
  (let [optimized-solution (optimize-portfolio 100 100)]
    (print-optimized-portfolio optimized-solution)))





