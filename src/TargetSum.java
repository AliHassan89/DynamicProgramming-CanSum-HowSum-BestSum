import java.util.*;

public class TargetSum
{
    public static void main(String[] args) {
        int[] numbers = {5,4,3,7};
        System.out.println(canSum(7, numbers, new HashMap<>())); // expected result: true
        System.out.println(howSum(7, numbers, new HashMap<>())); // expected result: [3,4]
        System.out.println(bestSum(7, numbers, new HashMap<>())); // expected result: [7]
    }

    private static boolean canSum(int targetSum, int[] numbers, Map<Integer, Boolean> memo)
    {
        if (memo.get(targetSum) != null)
        {
            return memo.get(targetSum);
        }
        if (targetSum == 0)
        {
            return true;
        }
        else if (targetSum < 0)
        {
            return false;
        }
        for (int number : numbers)
        {
            int remainder = targetSum - number;
            if (canSum(remainder, numbers, memo))
            {
                memo.put(targetSum, true);
                return true;
            }
        }

        memo.put(targetSum, false);
        return false;
    }

    private static List<Integer> howSum(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo)
    {
        if (memo.get(targetSum) != null)
        {
            return memo.get(targetSum);
        }
        else if (targetSum == 0)
        {
            return new ArrayList<>();
        }
        else if (targetSum < 0)
        {
            return null;
        }
        for (int number : numbers)
        {
            int remainder = targetSum - number;
            List<Integer> remainderList = howSum(remainder, numbers, memo);
            if (remainderList != null)
            {
                List<Integer> resultantList = new ArrayList<>(remainderList);
                resultantList.add(number);
                memo.put(targetSum, resultantList);
                return resultantList;
            }
        }

        memo.put(targetSum, null);
        return null;
    }

    private static List<Integer> bestSum(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo)
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
            List<Integer> remainderResult = bestSum(remainder, numbers, memo);
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
