(ns Money.portfolio)

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
  (let [realEstate {"RE fund I" (+ 1 (* 0.25 (rand)))
                    "RE fund II" (+ 1 (* 0.25 (rand)))
                    "Direct Investment I" (+ 1 (* 0.4 (rand)))
                    "Direct Investment II" (+ 1 (* 0.4 (rand)))
                    "REIT I" (+ 1 (* 0.1 (rand)))
                    "REIT II" (+ 1 (* 0.1 (rand)))}
        privateEquity {"PE Fund I" (+ 1 (* 0.4 (rand)))
                       "PE Fund II" (+ 1 (* 0.3 (rand)))
                       "VC Fund I" (+ 1 (* 0.5 (rand)))
                       "VC Fund II" (+ 1 (* 0.5 (rand)))}
        publicSecurities {"Stock I" (+ 1 (* 0.08 (rand)))
                          "Stock II" (+ 1 (* 0.09 (rand)))
                          "Stock III" (+ 1 (* 0.1 (rand)))
                          "Stock IV" (+ 1 (* 0.1 (rand)))}
        inflationaryHedge {"3 Month Bond" (+ 1 (* 0.1 (rand)))
                           "6 Month Bond" (+ 1 (* 0.06 (rand)))
                           "12 Month Bond" (+ 1 (* 0.05 (rand)))
                           "2 Year Bond" (+ 1 (* 0.04 (rand)))
                           "5 Year Bond" (+ 1 (* 0.04 (rand)))}]
    ;This creates a random portfolio creation that takes 2 investments from each bucket
    (fn []
      {"Real Estate" (take 2 (shuffle (seq realEstate)))
       "Private Equity" (take 2 (shuffle (seq privateEquity)))
       "Public Securities" (take 2 (shuffle (seq publicSecurities)))
       "Inflationary Hedge" (take 2 (shuffle (seq inflationaryHedge)))})))

(def portfolio (createPortfolio))

(println portfolio)


(defn calculatePortfolioReturns 
  [portfolio realEstatePct privateEquityPct publicSecuritiesPct inflationaryHedgePct totalCash]
  (let [realEstateAlloc (* totalCash realEstatePct)
        privateEquityAlloc (* totalCash privateEquityPct)
        publicSecuritiesAlloc (* totalCash publicSecuritiesPct)
        inflationaryHedgeAlloc (* totalCash inflationaryHedgePct)
        realEstateReturns (map #(* realEstateAlloc (* %1 0.5)) (vals (get portfolio "Real Estate")))
        privateEquityReturns (map #(* privateEquityAlloc (* %1 0.5)) (vals (get portfolio "Private Equity")))
        publicSecuritiesReturns (map #(* publicSecuritiesAlloc (* %1 0.5)) (vals (get portfolio "Public Securities")))
        inflationaryHedgeReturns (map #(* inflationaryHedgeAlloc (* %1 0.5)) (vals (get portfolio "Inflationary Hedge")))]
    (reduce + (concat realEstateReturns privateEquityReturns publicSecuritiesReturns inflationaryHedgeReturns))))

(calculatePortfolioReturns portfolio 0.3 0.2 0.3 0.2 100000)

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


