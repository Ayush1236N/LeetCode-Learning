class Solution {
    class BinaryIndexedTree {
        int n;
        int[] c;

        BinaryIndexedTree(int n) {
            this.n = n;
            c = new int[n + 1];
        }

        void update(int x, int delta) {
            while (x <= n) {
                c[x] += delta;
                x += x & -x;
            }
        }

        int query(int x) {
            int res = 0;
            while (x > 0) {
                res += c[x];
                x -= x & -x;
            }
            return res;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        BinaryIndexedTree bit = new BinaryIndexedTree(2 * n + 1);

        int prefix = n + 1;      // shifted prefix sum
        bit.update(prefix, 1);

        long ans = 0;

        for (int x : nums) {
            if (x == target)
                prefix++;
            else
                prefix--;

            ans += bit.query(prefix - 1);
            bit.update(prefix, 1);
        }

        return ans;
    }
}