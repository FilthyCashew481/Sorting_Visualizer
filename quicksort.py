# Approach 2: Quicksort using list comprehension
import main 

def quicksort(nums):
    if len(nums) <= 1:
        return nums
    else:
        pivot = nums[0]
        left = [x for x in nums[1:] if x < pivot]
        right = [x for x in nums[1:] if x >= pivot]
        main.update_screen()
        return quicksort(left) + [pivot] + quicksort(right)
 
