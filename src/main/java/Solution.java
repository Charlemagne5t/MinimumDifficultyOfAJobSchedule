import java.util.HashMap;
import java.util.Map;

public class Solution {
    int minSoFar = Integer.MAX_VALUE / 2;
    public int minDifficulty(int[] jobDifficulty, int d) {
        if(d == 1){
            int max = -1;
            for (int j : jobDifficulty) {
                max = Math.max(max, j);
            }
            return max;
        }
        Map<String, Integer> memo = new HashMap<>();
        int res = dfs(jobDifficulty, d - 1, 0, -1, memo, 0);

        return res == Integer.MAX_VALUE / 2 ? -1 : res;
    }
    private int dfs(int[] difficulty, int d, int i, int maxJobCostThatDay, Map<String, Integer> memo, int sum){
        if(i == difficulty.length && d == 0){
            int tot = maxJobCostThatDay == -1 ? Integer.MAX_VALUE / 2 : maxJobCostThatDay;
            if(tot > 0){
                minSoFar = Math.min(sum + tot, minSoFar);
            }
            return tot;
        }
        if(sum > minSoFar){
            return Integer.MAX_VALUE / 2;
        }
        if(i == difficulty.length || difficulty.length - i < d){
            return Integer.MAX_VALUE / 2;
        }
        if(memo.containsKey(d + " " + i + " " + maxJobCostThatDay)){
            return memo.get(d + " " + i + " " + maxJobCostThatDay);
        }

        int submitAndMoveToTheNextDay = Math.max(maxJobCostThatDay, difficulty[i]) + dfs(difficulty, d - 1, i + 1, -1, memo, sum + Math.max(maxJobCostThatDay, difficulty[i]));
        int addWorkAndStay = dfs(difficulty, d, i + 1, Math.max(maxJobCostThatDay, difficulty[i]), memo, sum);
        int res = Math.min(submitAndMoveToTheNextDay, addWorkAndStay);

        memo.put(d + " " + i + " " + maxJobCostThatDay, res);
        return res;
    }
}
