class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size(), best = Integer.MAX_VALUE;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums.get(i);
        TreeMap<Integer, Integer> starts = new TreeMap<>();  // pre[i] -> how many starts that fit the end j have it
        for (int j = l; j <= n; j++) {
            starts.merge(pre[j - l], 1, Integer::sum);  // start j - l: length l
            if (j > r)
                starts.compute(pre[j - r - 1], (key, c) -> c == 1 ? null : c - 1);  // start j - r - 1: length r + 1, too long
            Integer below = starts.lowerKey(pre[j]);  // the largest pre[i] < pre[j]
            if (below != null)
                best = Math.min(best, pre[j] - below);
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
}
