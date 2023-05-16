(ns proj.evolver)


(def createPortfolio2
  ;Portfolio Parameters, preventing evaluation of returns
  {:realEstate {"RE fund I" '(+ 0.9 (* 0.35 (rand)))
                "RE fund II" '(+ 0.9 (* 0.35 (rand)))
                "Direct Investment I" '(+ 0.8 (* 0.6 (rand)))
                "Direct Investment II" '(+ 0.8 (* 0.6 (rand)))
                "REIT I" '(+ 0.95 (* 0.15 (rand)))
                "REIT II" '(+ 0.95 (* 0.15 (rand)))}
   :privateEquity {"PE Fund I" '(+ 0.9 (* 0.3 (rand)))
                   "PE Fund II" '(+ 0.9 (* 0.35 (rand)))
                   "VC Fund I" '(+ 0.75 (* 0.75 (rand)))
                   "VC Fund II" '(+ 0.75 (* 0.68 (rand)))}
   :publicSecurities {"Stock I" '(+ 0.95 (* 0.19 (rand)))
                      "Stock II" '(+ 0.95 (* 0.19 (rand)))
                      "Stock III" '(+ 0.95 (* 0.19 (rand)))
                      "Stock IV" '(+ 0.95 (* 0.18 (rand)))}
   :inflationaryHedge {"3 Month Bond" 1.05
                       "6 Month Bond" 1.06
                       "12 Month Bond" 1.065
                       "2 Year Bond" 1.07
                       "5 Year Bond" 1.075}})

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
        realEstateReturns (map #(* realEstateAlloc (* %1 0.5)) (filter number? (flatten (get portfolio "Real Estate"))))
        privateEquityReturns (map #(* privateEquityAlloc (* %1 0.5)) (filter number? (flatten (get portfolio "Private Equity"))))
        publicSecuritiesReturns (map #(* publicSecuritiesAlloc (* %1 0.5)) (filter number? (flatten (get portfolio "Public Securities"))))
        inflationaryHedgeReturns (map #(* inflationaryHedgeAlloc (* %1 0.5)) (filter number? (flatten (get portfolio "Inflationary Hedge"))))]
    (reduce + (concat realEstateReturns privateEquityReturns publicSecuritiesReturns inflationaryHedgeReturns))))



(defn standard-deviation [data]
  (let [n (count data)
        mean (/ (apply + data) n)
        deviations (map #(- % mean) data)
        squares (map #(* % %) deviations)
        sum-squares (apply + squares)]
    (Math/sqrt (/ sum-squares n))))





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
     :diversity (* (rand) 5)}  ;Placeholder
    ))


(defn similar-fitness 
  "Determines if two numbers are within a certain percentage"
  [num1 num2]
  (if (< (/ (Math/abs (- num1 num2)) num1) 0.03)
    true
    false
))



;;;-----Creating Test stuff

(def testparams (genp createPortfolio2))
(def testpop (repeatedly 10 #(make-individual testparams 10000)))
(def cases [:avg-return :risk :diversity])



;;;---------Selection and Genetic Operators ---------


(defn lexi   ;NOT YET WORKING
  "Run Lexicase Selection Algorithm"
  [pop, casein]
  (if (empty? casein) (rand-nth pop)
      (let [cases (shuffle casein)
            thiscase (first cases)
            nextset (if (= thiscase ":avg-return")
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
  (if (< (rand) 1)
    (let [x (:genome individual)
          port (:port-params individual)]
      (let [y (vec (map + x (shuffle (mutators))))]
        (if (some #(neg? %)  y)
          (mutate individual totalcash)
          (let [x (repeatedly 100 #(try (calculatePortfolioReturns
                                    (evalport port)
                                    totalcash
                                    y) 
                                        (catch Exception e
                                          (println port))))]
            {:genome y :port-params port
             :avg-return (/ (reduce + x) 100)
             :risk (standard-deviation x)
             :diversity 1}))))
    individual))

(defn taken 
  "Takes 5 from pop using lexi"
  [pop, returners, gen, n]
  (let [x (lexi pop cases)]
    (if (= gen (+ n 1))
      returners
      (taken (remove #(= x %) pop) (conj returners x) (inc gen) n))))

(defn evolve
  "Run Evolution Algorithm"
  [population gens totalcash]
  (loop [generation 0
         population population]
    (let [best (lexi population cases)]
      (print "\nGeneration: " generation
             "\nAvg-Return: " (:avg-return best)
             "\nRisk: " (:risk best)
             "\nDiversity: " (:diversity best)
             "\n")
      (if (= gens generation)
        best
        (let [better-half
              (taken population () 0 (/ (count population) 2))]
          (recur
           (inc generation)
           (do (println population) (map #(mutate % totalcash)
                (conj better-half better-half) ))))))))


;;;---Run evolution with new portfolio parameters
(evolve (take 10 (repeatedly #(make-individual (genp createPortfolio2) 10000))) 10 10000)



