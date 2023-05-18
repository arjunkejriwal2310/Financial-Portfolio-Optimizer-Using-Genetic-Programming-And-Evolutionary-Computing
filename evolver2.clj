(ns finalprojectcode.evolver)

(require 'clojure.core)

(def createPortfolio2
  {:realEstate {"RE fund I" {:returns '(let [r1 (rand)]
                                  (cond
                                    (< r1 0.125) (+ -0.5 (rand 0.5))
                                    (< r1 0.25) (rand 0.9)
                                    (< r1 0.75) (+ 0.9 (rand 0.35))
                                    :else (+ 1.25 (rand 0.5))))
                                 :socialImpact 0.34}
                    
                    "RE fund II" {:returns '(let [r1 (rand)]
                                             (cond
                                               (< r1 0.10) (+ -0.5 (rand 0.5))
                                               (< r1 0.20) (rand 0.9)
                                               (< r1 0.70) (+ 0.9 (rand 0.35))
                                               :else (+ 1.25 (rand 0.5))))
                                  :socialImpact 0.45}
                    "Direct Investment I" {:returns '(let [r1 (rand)]
                                  (cond
                                    (< r1 0.15) (+ -0.5 (rand 0.5))
                                    (< r1 0.25) (rand 0.9)
                                    (< r1 0.80) (+ 0.9 (rand 0.35))
                                    :else (+ 1.25 (rand 0.5))))
                                 :socialImpact 0.78}
                    "Direct Investment II" {:returns '(let [r1 (rand)]
                                                       (cond
                                                         (< r1 0.08) (+ -0.5 (rand 0.5))
                                                         (< r1 0.20) (rand 0.9)
                                                         (< r1 0.65) (+ 0.9 (rand 0.35))
                                                         :else (+ 1.25 (rand 0.5))))
                                            :socialImpact 0.89}
                    "REIT I" {:returns '(let [r1 (rand)]
                                         (cond
                                           (< r1 0.18) (+ -0.5 (rand 0.5))
                                           (< r1 0.30) (rand 0.9)
                                           (< r1 0.60) (+ 0.9 (rand 0.35))
                                           :else (+ 1.25 (rand 0.5))))
                              :socialImpact 0.67}
                    "REIT II" {:returns '(let [r1 (rand)]
                                          (cond
                                            (< r1 0.10) (+ -0.5 (rand 0.5))
                                            (< r1 0.30) (rand 0.9)
                                            (< r1 0.70) (+ 0.9 (rand 0.35))
                                            :else (+ 1.25 (rand 0.5))))
                               :socialImpact 0.78}}
        :privateEquity {"PE Fund I" {:returns '(let [r1 (rand)]
                                               (cond
                                                 (< r1 0.14) (+ -0.5 (rand 0.5))
                                                 (< r1 0.29) (rand 0.9)
                                                 (< r1 0.71) (+ 0.9 (rand 0.35))
                                                 :else (+ 1.25 (rand 0.5))))
                                    :socialImpact 0.36}
                       "PE Fund II" {:returns '(let [r1 (rand)]
                                                (cond
                                                  (< r1 0.15) (+ -0.5 (rand 0.5))
                                                  (< r1 0.31) (rand 0.9)
                                                  (< r1 0.89) (+ 0.9 (rand 0.35))
                                                  :else (+ 1.25 (rand 0.5))))
                                     :socialImpact 0.24}
                       "VC Fund I" {:returns '(let [r1 (rand)]
                                               (cond
                                                 (< r1 0.16) (+ -0.5 (rand 0.5))
                                                 (< r1 0.23) (rand 0.9)
                                                 (< r1 0.68) (+ 0.9 (rand 0.35))
                                                 :else (+ 1.25 (rand 0.5))))
                                    :socialImpact 0.49}
                       "VC Fund II" {:returns '(let [r1 (rand)]
                                                (cond
                                                  (< r1 0.09) (+ -0.5 (rand 0.5))
                                                  (< r1 0.18) (rand 0.9)
                                                  (< r1 0.60) (+ 0.9 (rand 0.35))
                                                  :else (+ 1.25 (rand 0.5))))
                                     :socialImpact 0.85}}
        :publicSecurities {"Stock I" {:returns '(let [r1 (rand)]
                                                (cond
                                                  (< r1 0.19) (+ -0.5 (rand 0.5))
                                                  (< r1 0.25) (rand 0.9)
                                                  (< r1 0.83) (+ 0.9 (rand 0.35))
                                                  :else (+ 1.25 (rand 0.5))))
                                     :socialImpact 0.2}
                          "Stock II" {:returns '(let [r1 (rand)]
                                                 (cond
                                                   (< r1 0.11) (+ -0.5 (rand 0.5))
                                                   (< r1 0.27) (rand 0.9)
                                                   (< r1 0.78) (+ 0.9 (rand 0.35))
                                                   :else (+ 1.25 (rand 0.5))))
                                      :socialImpact 0.3}
                          "Stock III" {:returns '(let [r1 (rand)]
                                                  (cond
                                                    (< r1 0.10) (+ -0.5 (rand 0.5))
                                                    (< r1 0.20) (rand 0.9)
                                                    (< r1 0.70) (+ 0.9 (rand 0.35))
                                                    :else (+ 1.25 (rand 0.5))))
                                       :socialImpact 0.4}
                          "Stock IV" {:returns '(let [r1 (rand)]
                                                 (cond
                                                   (< r1 0.125) (+ -0.5 (rand 0.5))
                                                   (< r1 0.25) (rand 0.9)
                                                   (< r1 0.75) (+ 0.9 (rand 0.35))
                                                   :else (+ 1.25 (rand 0.5))))
                                      :socialImpact 0.5}}
        :inflationaryHedge {"3 Month Bond" {:returns 1.05
                                           :socialImpact 0.1}
                           "6 Month Bond" {:returns 1.06
                                           :socialImpact 0.1}
                           "12 Month Bond" {:returns 1.065
                                            :socialImpact 0.1}
                           "2 Year Bond" {:returns 1.07
                                          :socialImpact 0.1}
                           "5 Year Bond" {:returns 1.075
                                          :socialImpact 0.1}}})

;This creates a random portfolio creation that takes 2 investments from each bucket
(defn genp [portlist]
  {"Real Estate" (take 2 (shuffle (seq (:realEstate portlist))))
   "Private Equity" (take 2 (shuffle (seq (:privateEquity portlist))))
   "Public Securities" (take 2 (shuffle (seq (:publicSecurities portlist))))
   "Inflationary Hedge" (take 2 (shuffle (seq (:inflationaryHedge portlist))))})


;;;---Interior Tools--------------

(defn evalport
  "Creates a portfolio with it's evaluated return values"
  [m]
  (into {}
        (map (fn [[k v]]
               [k (mapv (fn [x]
                          (if (vector? x)
                            (vector (first x) (eval (second x)))
                            x))
                        v)])
             m)))

(defn get-weights
  "Produce four decimals adding up to 1"
  []
  (let [w1 (rand)
        w2 (rand (- 1 w1))
        w3 (rand (- 1 w1 w2))
        w4 (- 1 (+ w1 w2 w3))]
    [w1 w2 w3 w4]))




(defn calculatePortfolioReturns
  "Calculate an evaluated portfolios Returns"
  [portfolio totalCash alloc]
  (let [realEstateAlloc (* totalCash (nth alloc 0))
        privateEquityAlloc (* totalCash (nth alloc 1))
        publicSecuritiesAlloc (* totalCash (nth alloc 2))
        inflationaryHedgeAlloc (* totalCash (nth alloc 3))
        realEstateReturns (map #(* realEstateAlloc (* %1 0.5)) (map #(+ 1 %) (map #(:returns %) (filter map? (flatten (get portfolio "Real Estate"))))))
        privateEquityReturns (map #(* privateEquityAlloc (* %1 0.5)) (map #(+ 1 %) (map #(:returns %) (filter map? (flatten (get portfolio "Private Equity"))))))
        publicSecuritiesReturns (map #(* publicSecuritiesAlloc (* %1 0.5)) (map #(+ 1 %) (map #(:returns %) (filter map? (flatten (get portfolio "Public Securities"))))))
        inflationaryHedgeReturns (map #(* inflationaryHedgeAlloc (* %1 0.5)) (map #(:returns %) (filter map? (flatten (get portfolio "Inflationary Hedge")))))]
    (reduce + (concat realEstateReturns privateEquityReturns publicSecuritiesReturns inflationaryHedgeReturns))))



(defn standard-deviation [data]
  (let [n (count data)
        mean (/ (apply + data) n)
        deviations (map #(- % mean) data)
        squares (map #(* % %) deviations)
        sum-squares (apply + squares)]
    (Math/sqrt (/ sum-squares n))))


   ;---For removing last index of an element
(defn last-index-of [element vector]
  (loop [i (dec (count vector))]
    (if (neg? i)
      nil
      (if (= element (nth vector i))
        i
        (recur (dec i))))))

(defn remove-last [element vector]
  (let [last-index (last-index-of element vector)]
    (if (nil? last-index)
      vector
      (concat (subvec vector 0 last-index) (subvec vector (inc last-index))))))
        ;--------------


(defn getimpacts 
    "Used in social Grade
     Extracts all the impact values from portfolio parameters"
  [data]
  (->> data
       (vals)                             ; Retrieve the values from the map
       (mapcat #(map second %))           ; Extract the nested maps
       (map :socialImpact)                ; Retrieve social impacts
       (vec)))


(defn calculate-sums 
  "Also Used in Social Grade
   adds every 2 numbers together"
  [input-vector]
  (->> input-vector
       (partition 2)        ; Partition the input vector into pairs
       (map #(apply + %))))


(defn multiply-by-position 
  "Also used in social grade
   Multiply four #s by another 4"
  [vector1 vector2]
  (map-indexed (fn [index value]
                 (* value (nth vector2 index)))
               vector1))


(defn social-grade 
  "Calculates the overall social grade of a portfolio
   Adds together each bucket's interior social grades,
   multiplies by the weight of that bucket,
   then adds together every bucket... higher is better"
  [weights port]
  (reduce + 
          (multiply-by-position weights 
                                (calculate-sums (getimpacts port)))))

(defn similar-fitness
  "Determines if two numbers are within a certain percentage"
  [num1 num2]
  (if (< (/ (Math/abs (- num1 num2)) num1) 0.02)
    true
    false))

;;;-----Population Creation-----

(defn make-individual
  "Create an individual based off given portfolio parameters"
  [port totalCash]
  (let [weights (get-weights)
        x (repeatedly 100 #(calculatePortfolioReturns
                            (evalport port)
                            totalCash
                            weights))]
    {:genome weights :port-params port
     :avg-return (/ (reduce + x) 100)
     :risk (standard-deviation x)
     :diversity (standard-deviation weights)
     :social (social-grade weights port)}))






;;;-----Creating Test stuff

(def testparams (genp createPortfolio2))
(def testpop (repeatedly 10 #(make-individual testparams 10000)))
(def cases [:avg-return :risk :diversity :social :avg-return])





;;;---------Selection and Genetic Operators ---------


(defn lexi  
  "Run Lexicase Selection Algorithm"
  [pop, casein]
  (if (empty? casein) (rand-nth pop)
      (let [cases (remove-last :avg-return (shuffle casein))
            thiscase (first cases)
            nextset (if (or (= thiscase ":social") (= thiscase ":avg-return"))
                      (do #(println %) (reverse (sort-by thiscase (if (map? pop) (list pop) pop))))
                      (do #(println %) (sort-by thiscase (if (map? pop) (list pop) pop))))]
        (loop [x nextset
               bestport (first x)
               returns (list (first x))]
          (if (< (count x) 2)
            (rand-nth returns)
            (if (similar-fitness
                 (thiscase bestport)
                 (do #(print %) (thiscase (second x))))
              (recur (rest x) bestport (conj returns (second x)))
              (if (< (count returns) 2)
                (rand-nth returns)
                (lexi returns (remove #(= thiscase %) casein)))))))))
  



(defn mutators
  "Create four decimals adding up to 0 in the range of 0.05
   to map onto an individual's weights"
  []
  (let [w1 (rand 0.05)
        w2 (rand 0.05)
        w3 (rand (- 0 w1 w2))
        w4 (- 0 (+ w1 w2 w3))]
    [w1 w2 w3 w4]))

(defn mutate 
  "Mutate an individual's weights -DIVERSITY NOT YET IMPLEMENTED"
  [individual totalcash]
  (if (< (rand) 0.5)
    (let [x (:genome individual)
          port (:port-params individual)
      y (vec (map + x (shuffle (mutators))))]
        (if (some #(neg? %)  y)
          (mutate individual totalcash)
          (let [x (repeatedly 100 #(calculatePortfolioReturns
                                    (evalport port)
                                    totalcash
                                    y))]
            {:genome y :port-params port
             :avg-return (/ (reduce + x) 100)
             :risk (standard-deviation x)
             :diversity (standard-deviation y)
             :social (social-grade y port)})))
    individual))

(defn taken 
  "Takes n from pop using lexi"
  [pop, returners, gen, n]
  (let [x (lexi pop cases)]
    (if (= gen (+ n 1))
      returners
      (taken (remove #(= x %) pop) (conj returners x) (inc gen) n))))

(defn crossover
  "Perform crossover between two weight vectors"
  [w1 w2 totalcash]
  (let [weights1 (:genome w1)
        weights2 (:genome w2)
        crossover-point (rand-int 3)
        new-weights1 (concat (take (inc crossover-point) weights1)
                             (drop (inc crossover-point) weights2))
        new-weights2 (concat (take (inc crossover-point) weights2)
                             (drop (inc crossover-point) weights1))
        total-sum1 (apply + new-weights1)
        total-sum2 (apply + new-weights2)
        normalized-weights1 (mapv #(/ % total-sum1) new-weights1)
        normalized-weights2 (mapv #(/ % total-sum2) new-weights2)
        x1 (repeatedly 100 #(calculatePortfolioReturns
                             (evalport (:port-params w1))
                             totalcash
                             normalized-weights1))
        x2 (repeatedly 100 #(calculatePortfolioReturns
                             (evalport (:port-params w2))
                             totalcash
                             normalized-weights2))]
    (list {:genome normalized-weights1
           :port-params (:port-params w1)
           :avg-return (do #(println %) (/ (reduce + x1) 100))
           :risk (standard-deviation x1)
           :diversity (standard-deviation normalized-weights1)
           :social (social-grade  normalized-weights1 (:port-params w1))}
          {:genome normalized-weights2
           :port-params (:port-params w2)
           :avg-return (/ (reduce + x2) 100)
           :risk (standard-deviation x2)
           :diversity (standard-deviation normalized-weights2)
           :social (social-grade normalized-weights2 (:port-params w2))})))



(defn evolve
  "Run Evolution Algorithm"
  [population gens totalcash]
  (spit "running.txt" (str "\n----NEW RUN----\npopsize: "(count population)
                           "\ngens: " gens
                           "\nstarting cash: " totalcash "\n"))
  (loop [generation 0
         population population]
    (let [best (lexi population cases)
          printout (str "\nGeneration: " generation
                        "\nAvg-Return: " (:avg-return best)
                        "\nRisk: " (:risk best)
                        "\nDiversity: " (:diversity best)
                        "\nImpact: " (:social best)
                        "\nBest Returns: " (:avg-return (first (reverse (sort-by :avg-return population)))))]
      (println printout)
      (spit "running.txt" printout :append true)
      (if (= gens generation)
        best
        (let [better-half
              (taken population () 0 (- (/ (count population) 2) 1))]
          (recur
           (inc generation)
           (concat (crossover (second better-half)
                              (nth better-half 3) totalcash)
                   (map #(mutate % totalcash)
                        (concat (list best)
                                (concat  better-half (take 2 better-half)))))))))))


;;;---Run evolution with new portfolio parameters
(evolve (take  10 (repeatedly #(make-individual (genp createPortfolio2) 10000))) 4 10000)



