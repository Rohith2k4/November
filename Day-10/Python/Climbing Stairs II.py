class Solution(object):
    def climbStairs(self, n, costs):
        """
        :type n: int
        :type costs: List[int]
        :rtype: int
        """
        dp = [0] * (n + 1)

        for i in range(1, n + 1):
            minCost = float('inf')

            for j in range(max(0, i - 3), i):
                minCost = min(minCost, dp[j] + (i - j) * (i - j) + costs[i - 1])

            dp[i] = minCost

        return dp[n]
