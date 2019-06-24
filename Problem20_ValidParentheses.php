class Solution {

    /**
     * @param String $s
     * @return Boolean
     */
    function isValid($s) {
        if (!strlen($s)) return true;
        $st = [];
        $map = ["}"=>"{", ")" => "(", "]"=>"["];
        for ($i = 0; $i < strlen($s); $i++) {
            $c = $s[$i];
            if (!array_key_exists($c, $map)) {
                array_push($st, $c);
            } elseif(!count($st) || $map[$c] != array_pop($st)) {
                return false;
            }
        }
        return !count($st);
    }
}
