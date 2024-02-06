Our project aimed to build an evolutionary algorithm in Clojure with the goal of optimizing financial asset portfolios in terms of maximizing returns and minimizing overall risk (volatility). We also looked at the overall diversity of the portfolio (measured by the standard deviation of the weight vector) as well as the social impact score (how impactful our portfolio investments are to society) when trying to optimize our portfolios. 

The code first starts by initializing the different asset classes (‘buckets’) as well as the different assets within each class/bucket. The four major asset classes we looked at include: Real Estate (which has 6 different assets within it), Private Equity (which has 4 different assets within it), Public Securities/Stocks (which has 4 different assets within it), and Inflationary Hedges/Fixed Income (which has 5 different assets within it). While defining each asset within each asset class, we used a numeric randomizer along with hardcoded numbers to assign a realized return for each asset. The hardcoded numbers and numeric randomizers were used together in arithmetic expression that were carefully defined to mimic the usual behavior of the asset in terms of its annual returns and risk. We also assigned social impact scores between 0 and 1 to each asset to measure how impactful an investment in that asset is to society as a whole, where a higher social impact score means more positive externalities to society.    

The code then defines a function that takes 2 random assets from each asset class to arrive at a portfolio of 8 assets from the 4 different asset classes/buckets. The goal was then to use the genetic operators of mutation and crossover, the Lexicase selection method with multiple relevant cases (returns, risk, diversity, and social impact score), as well as an evolutionary process to arrive at an optimized weight vector with 4 different weights. Each of these weights represent how much is proportionally invested in each asset class as a whole. Since the weights add up to 1, if an asset class X has a higher weight than an asset class Y, then naturally more money is invested proportionally in asset class X compared to asset class Y.

Here is a detailed description of the most important functions defined in the code:
1.	The `createPortfolio2` map (not a function) defines the four different asset classes (Real Estate, Private Equity, Public Securities, and Inflationary hedges) and the different assets within each asset class (along with their realistic return values and social impact scores)

2.	The `genp` function randomly selects 2 assets from each asset class to create a portfolio of 8 different assets.

3.	The `evalport` function evaluates a portfolio by calculating returns of each asset within the portfolio. It takes a map ‘m’ representing the portfolio with assets and their associated returns. The function iterates over each key-value pair in the map, where the key represents the asset class (e.g., "Real Estate," "Private Equity") and the value is a sequence of assets. The function returns a new map with the same structure as the input map, but with the investment options replaced by their evaluated returns.

4.	The `get-weights` function returns a vector of four random weights that add up to 1.

5.	The `calculatePortfolioReturns` function calculates the overall return of a portfolio based on total cash available, weight allocations for each asset class, and individual asset returns.

6.	The `standard-deviation` function calculates the standard deviation of a given dataset. In later functions, we will be simulating 100 days of asset data and use this function to calculate the standard deviation of the asset prices as a whole. This would provide us with a strong measure of the risk of our portfolio.

7.	The ‘social-grade’ function calculates an overall social impact grade for a portfolio by taking each bucket’s total social impact, multiplying it by the weight of that bucket, and adding all four together. A higher social impact grade means that the portfolio has higher positive impact and externalities to society.

8.	The `make-individual` function generates an individual of the population with random weights for portfolio allocation and evaluates the individual’s average return, risk, diversity and social impact. This function will be used to create the population of weight vectors that we will be feeding into our evolutionary algorithm

9.	The `similar-fitness` function determines if two numbers are within a certain percentage of each other. This function will be used in our lexicase selection function later. By considering similar-fitness individuals (individuals that have very similar scores in any of the 4 cases) separately, the `lexi` function ensures that they are properly ranked based on their performance on each objective, allowing for a more comprehensive evaluation and comparison.

10.	The `lexi` function implements lexicase selection, which selects individuals based on multiple fitness criteria (returns, risk, diversity, and social impact score). The function also doubles the likelihood of an average-return round, as this is the most important case.

11.	The `mutators` function generates a vector of 4 random decimal numbers (in the range of 0 to 0.05) that add up to 0. This will be added to an individual weight vector in order to mutate it. 

12.	The `mutate` function performs the genetic operation of mutation on an individual weight vector. It uses the ‘mutators’ function, as defined above, to achieve this.

13.	The `taken` function selects a specified number of individuals from the population using lexicase selection, removing each selected individual from the pool of other individuals it will select.

14.	The `crossover` function performs the genetic operation of crossover between two individual weight vectors.

15.	The `evolve` function runs the evolution algorithm on the specified population of weight vectors by repeatedly mutating, crossing-over, and selecting individual weight vectors over multiple pre-specified generations. The function conducts the evolution given a certain amount of initial cash. 
![image](https://github.com/arjunkejriwal2310/Financial-Portfolio-Optimizer-Using-Genetic-Programming-And-Evolutionary-Computing/assets/73984306/2f1cf728-b29f-4639-a3bf-d57d831a5f99)
