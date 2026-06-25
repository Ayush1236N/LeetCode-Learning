class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        // Length of the input array
        int length = nums.length;

        // Counter for subarrays where target is the majority element
        int answer = 0;

        // Map to track the frequency of each value within the current subarray window
        Map<Integer, Integer> frequencyMap = new HashMap<>(length);

        // Fix the left boundary of the subarray at index 'start'
        for (int start = 0; start < length; ++start) {
            // Extend the right boundary of the subarray to index 'end'
            for (int end = start; end < length; ++end) {
                // Current length of the subarray [start, end]
                int subarrayLength = end - start + 1;

                // Add the current element to the frequency map
                frequencyMap.merge(nums[end], 1, Integer::sum);

                // A "majority" element must appear more than half the times.
                // If the target's count exceeds subarrayLength / 2, count this subarray.
                if (frequencyMap.getOrDefault(target, 0) > subarrayLength / 2) {
                    ++answer;
                }
            }
            // Reset the frequency map before moving the left boundary forward
            frequencyMap.clear();
        }

        return answer;
    }
}
