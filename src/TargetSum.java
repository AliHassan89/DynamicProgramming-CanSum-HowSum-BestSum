import java.util.*;

public class TargetSum
{
    public static void main(String[] args) {
        int[] numbers = {1,2,5,25};
        System.out.println(bestSumShortestArray(100, numbers, new HashMap<>()));
    }

    private static boolean canSum(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo)
    {

    }

    private static List<Integer> bestSumShortestArray(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo)
    {
        if (memo.get(targetSum) != null)
        {
            return memo.get(targetSum);
        }
        if (targetSum == 0)
        {
            return new ArrayList<>();
        }
        else if (targetSum < 0)
        {
            return null;
        }

        List<Integer> shortestCombination = null;

        for (int number : numbers)
        {
            int remainder = targetSum - number;
            List<Integer> remainderResult = bestSumShortestArray(remainder, numbers, memo);
            if (remainderResult != null)
            {
                List<Integer> remainderCombination = new ArrayList<>(remainderResult);
                remainderCombination.add(number);
                if (shortestCombination == null || shortestCombination.size() > remainderCombination.size())
                {
                    shortestCombination = remainderCombination;
                }
            }
        }

        memo.put(targetSum, shortestCombination);
        return shortestCombination;
    }
}
