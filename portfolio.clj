(ns portfolio)

;defines the buckets of the portfolio
(def allocation ["Real Estate"
                 "Private Equity"
                 "Public Securities"
                 "Inflationary Hedge"])

;defines the portfolio details
;Risk and cash are the only things we will be considering but we need to define what range "low" "average" and "High" risk means
(def portfolio_details {"Risk" "average"
                        "Cash" 100000})


(def RealEstate
  ;These are a mix of Real Estate investment funds, Direct RE investments and Real Estate trusts
  ;Risk and return potential will be defined below
  ;Direct investment is riskier and less public information is available, so the return ratio will be an average of what we can find online
  {"Blackstone I" 1.09
   "Blackstone II" 1.15
   "Direct Investment I" 1.103
   "Direct Investment II" 1.20
   "REIT I" 1.06
   "REIT II" 1.03})
(def PrivateEquity
  ;These are a mix of bulge bracket PE funds, Venture funds and Angel investing
  ;These three catagories vary in risk, but also in return potential
  ;The risk to return ratios will be set later and impliment a random element to dictate how each investment performes within a range
  {"General Atlantic Fund" 1.09
   "Blackstone Fund" 1.15
   "KKR Fund" 1.103
   "Boutique Venture Fund" 1.20
   "Angel Investor" 1.06
   "Angel Investor II" 1.03})
(def PublicSecurities
  {}
  ;need to fill this section in after speaking with group
  )

(def treasuryBonds
  ;These are government treasury bonds
  ;These are defined by the fed and change frequently but for this project we are using the rates as of 5/2/23
  {"3 Month Bond" 1.049
   "6 Month Bond" 1.047
   "12 Month Bond" 1.044
   "2 Year Bond" 1.037
   "5 Year Bond" 1.033})


(def createPortfolio
  ;There is a random return within the average range 
  (let [realEstate {"RE fund I" {:returns(let [r1 (rand)]
                                  (cond
                                    (< r1 0.125) (+ -0.5 (rand 0.5))
                                    (< r1 0.25) (rand 0.9)
                                    (< r1 0.75) (+ 0.9 (rand 0.35))
                                    :else (+ 1.25 (rand 0.5))))
                                 :socialImpact 0.34}
                    
                    "RE fund II" {:returns (let [r1 (rand)]
                                             (cond
                                               (< r1 0.10) (+ -0.5 (rand 0.5))
                                               (< r1 0.20) (rand 0.9)
                                               (< r1 0.70) (+ 0.9 (rand 0.35))
                                               :else (+ 1.25 (rand 0.5))))
                                  :socialImpact 0.45}
                    "Direct Investment I" {:returns(let [r1 (rand)]
                                  (cond
                                    (< r1 0.15) (+ -0.5 (rand 0.5))
                                    (< r1 0.25) (rand 0.9)
                                    (< r1 0.80) (+ 0.9 (rand 0.35))
                                    :else (+ 1.25 (rand 0.5))))
                                 :socialImpact 0.78}
                    "Direct Investment II" {:returns (let [r1 (rand)]
                                                       (cond
                                                         (< r1 0.08) (+ -0.5 (rand 0.5))
                                                         (< r1 0.20) (rand 0.9)
                                                         (< r1 0.65) (+ 0.9 (rand 0.35))
                                                         :else (+ 1.25 (rand 0.5))))
                                            :socialImpact 0.89}
                    "REIT I" {:returns (let [r1 (rand)]
                                         (cond
                                           (< r1 0.18) (+ -0.5 (rand 0.5))
                                           (< r1 0.30) (rand 0.9)
                                           (< r1 0.60) (+ 0.9 (rand 0.35))
                                           :else (+ 1.25 (rand 0.5))))
                              :socialImpact 0.67}
                    "REIT II" {:returns (let [r1 (rand)]
                                          (cond
                                            (< r1 0.10) (+ -0.5 (rand 0.5))
                                            (< r1 0.30) (rand 0.9)
                                            (< r1 0.70) (+ 0.9 (rand 0.35))
                                            :else (+ 1.25 (rand 0.5))))
                               :socialImpact 0.78}}
        privateEquity {"PE Fund I" {:returns (let [r1 (rand)]
                                               (cond
                                                 (< r1 0.14) (+ -0.5 (rand 0.5))
                                                 (< r1 0.29) (rand 0.9)
                                                 (< r1 0.71) (+ 0.9 (rand 0.35))
                                                 :else (+ 1.25 (rand 0.5))))
                                    :socialImpact 0.36}
                       "PE Fund II" {:returns (let [r1 (rand)]
                                                (cond
                                                  (< r1 0.15) (+ -0.5 (rand 0.5))
                                                  (< r1 0.31) (rand 0.9)
                                                  (< r1 0.89) (+ 0.9 (rand 0.35))
                                                  :else (+ 1.25 (rand 0.5))))
                                     :socialImpact 0.24}
                       "VC Fund I" {:returns (let [r1 (rand)]
                                               (cond
                                                 (< r1 0.16) (+ -0.5 (rand 0.5))
                                                 (< r1 0.23) (rand 0.9)
                                                 (< r1 0.68) (+ 0.9 (rand 0.35))
                                                 :else (+ 1.25 (rand 0.5))))
                                    :socialImpact 0.49}
                       "VC Fund II" {:returns (let [r1 (rand)]
                                                (cond
                                                  (< r1 0.09) (+ -0.5 (rand 0.5))
                                                  (< r1 0.18) (rand 0.9)
                                                  (< r1 0.60) (+ 0.9 (rand 0.35))
                                                  :else (+ 1.25 (rand 0.5))))
                                     :socialImpact 0.85}}
        publicSecurities {"Stock I" {:returns (let [r1 (rand)]
                                                (cond
                                                  (< r1 0.19) (+ -0.5 (rand 0.5))
                                                  (< r1 0.25) (rand 0.9)
                                                  (< r1 0.83) (+ 0.9 (rand 0.35))
                                                  :else (+ 1.25 (rand 0.5))))
                                     :socialImpact 0.2}
                          "Stock II" {:returns (let [r1 (rand)]
                                                 (cond
                                                   (< r1 0.11) (+ -0.5 (rand 0.5))
                                                   (< r1 0.27) (rand 0.9)
                                                   (< r1 0.78) (+ 0.9 (rand 0.35))
                                                   :else (+ 1.25 (rand 0.5))))
                                      :socialImpact 0.3}
                          "Stock III" {:returns (let [r1 (rand)]
                                                  (cond
                                                    (< r1 0.10) (+ -0.5 (rand 0.5))
                                                    (< r1 0.20) (rand 0.9)
                                                    (< r1 0.70) (+ 0.9 (rand 0.35))
                                                    :else (+ 1.25 (rand 0.5))))
                                       :socialImpact 0.4}
                          "Stock IV" {:returns (let [r1 (rand)]
                                                 (cond
                                                   (< r1 0.125) (+ -0.5 (rand 0.5))
                                                   (< r1 0.25) (rand 0.9)
                                                   (< r1 0.75) (+ 0.9 (rand 0.35))
                                                   :else (+ 1.25 (rand 0.5))))
                                      :socialImpact 0.5}}
        inflationaryHedge {"3 Month Bond" {:returns 1.05
                                           :socialImpact 0.1}
                           "6 Month Bond" {:returns 1.06
                                           :socialImpact 0.1}
                           "12 Month Bond" {:returns 1.065
                                            :socialImpact 0.1}
                           "2 Year Bond" {:returns 1.07
                                          :socialImpact 0.1}
                           "5 Year Bond" {:returns 1.075
                                          :socialImpact 0.1}}]
    ;This creates a random portfolio creation that takes 2 investments from each bucket
    (fn []
      {"Real Estate" (take 2 (shuffle (seq realEstate)))
       "Private Equity" (take 2 (shuffle (seq privateEquity)))
       "Public Securities" (take 2 (shuffle (seq publicSecurities)))
       "Inflationary Hedge" (take 2 (shuffle (seq inflationaryHedge)))}))) 

(def portfolio (createPortfolio))

(println portfolio)

;produces four weights that add up to 1
;We need to look at this sometimes it is producing weights that are ridiculous like .99
(defn get-weights
  []
  (let [w1 (rand)
        w2 (rand (- 1 w1))
        w3 (rand (- 1 w1 w2))
        w4 (- 1 (+ w1 w2 w3))]
    [w1 w2 w3 w4]))
(get-weights)
(def weights (get-weights))
(get weights 0)
(get weights 1)
(get weights 2)
(get weights 3)


;shows the weights adding to one work
(+ (get weights 0)
   (get weights 1)
   (get weights 2)
   (get weights 3))




(defn calculatePortfolioReturns
  [portfolio totalCash alloc]
  (let [realEstateAlloc (* totalCash (nth alloc 0))
        privateEquityAlloc (* totalCash (nth alloc 1))
        publicSecuritiesAlloc (* totalCash (nth alloc 2))
        inflationaryHedgeAlloc (* totalCash (nth alloc 3))
        realEstateReturns (map #(* realEstateAlloc (* %1 0.5)) (vals (get portfolio "Real Estate")))
        privateEquityReturns (map #(* privateEquityAlloc (* %1 0.5)) (vals (get portfolio "Private Equity")))
        publicSecuritiesReturns (map #(* publicSecuritiesAlloc (* %1 0.5)) (vals (get portfolio "Public Securities")))
        inflationaryHedgeReturns (map #(* inflationaryHedgeAlloc (* %1 0.5)) (vals (get portfolio "Inflationary Hedge")))]
    (reduce + (concat realEstateReturns privateEquityReturns publicSecuritiesReturns inflationaryHedgeReturns))))




(defn fitness
  [portfolio_params totalcash alloc])



(calculatePortfolioReturns portfolio 100000 weights)


(defn make-individual
  [port totalCash]
  (let [x (get-weights)]
    {:genome x :fitness (calculatePortfolioReturns (port) totalCash x)}))

(defn fittest
  [individuals]
  (let [x (reduce (fn [i1 i2]

                    (if (> (:fitness i1) (:fitness i2))
                      i1
                      i2))
                  individuals)]
    x))

;Simple tournament selectio as shown in class
(defn tourny-select
  "Returns an individual selected from population using a tournament"
  [population]
  (fittest (repeatedly 5 #(rand-nth population))))




(defn mutators
  []
  (let [w1 (rand 0.05)
        w2 (rand 0.05)
        w3 (rand (- 0 w1 w2))
        w4 (- 0 (+ w1 w2 w3))]
    [w1 w2 w3 w4]))

(defn mutate [individual port totalcash]
  (if (< (rand) 0.5)
    (let [x (:genome individual)]
      (let [y (vec (map + x (shuffle (mutators))))]
        (if (some #(neg? %)  y)
          (mutate individual port totalcash)
          {:genome y :fitness (calculatePortfolioReturns port totalcash y)})))
    individual))

(defn evolve
  [population gens portfolio totalcash]
  (loop [generation 0
         population population]
    (let [best (fittest population)]
      ;(print "Generation: " generation
       ;      "\nBest: " best
        ;     "\nHow-close: " (fitness best))
      ;(spit "Result.txt" (str "Generation: " generation " How-close: " (:fitness best) "\n") :append true)
      (if (= gens generation)
        (print (str best))
        ;(spit "Result.txt" best :append true)
        (let [better-half (take (/ (count population) 2) (repeatedly #(tourny-select population)))]
          (recur
           (inc generation)
           (map #(mutate % portfolio totalcash)
                (concat better-half better-half))))))))


;get the specific buckets
(get portfolio "Real Estate")
(get portfolio "Private Equity")
(get portfolio "Public Securities")
(get portfolio "Inflationary Hedge")

;get the specific investments
(nth (get portfolio "Real Estate") 0)
(nth (get portfolio "Real Estate") 1)
(nth (get portfolio "Private Equity") 0)
(nth (get portfolio "Private Equity") 1)
(nth (get portfolio "Public Securities") 0)
(nth (get portfolio "Public Securities") 1)
(nth (get portfolio "Inflationary Hedge") 0)
(nth (get portfolio "Inflationary Hedge") 1)

(+ -0.5 (rand 0.5))
(+ 0.9 (rand 0.35))
(+ 1.25 (rand 0.5))


