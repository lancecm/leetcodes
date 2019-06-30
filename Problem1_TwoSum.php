class Solution {

    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer[]
     */
    function twoSum($nums, $target) {
        $target_vals = [];
        foreach($nums as $key => $num) {
            if(in_array($num, $target_vals)) {
                return [array_search($num, $target_vals), $key];
            }
            else {
                $target_vals[$key] = $target - $num;
            }
        }
    }
}
