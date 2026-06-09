import java.util.HashMap;
import java.util.Map;

class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {

        Map<Integer, Integer> hashmap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashmap.containsKey(nums[i])){
                return true;
            }
            else{
                hashmap.put(nums[i], i);
            }
        }
        return false;
    }
}