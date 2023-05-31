# Example file showing a basic pygame "game loop"
import pygame
import random

# pygame setup
pygame.init()

# screen dimensions
width = 1280
height = 720

num_bars = 100
white_space = 4
bar_space = (1280)/num_bars
bar_width = bar_space - white_space #works
print("Bar width is {}", bar_width)

running = True

background = (50, 50, 50) # background color
rectangle_color = (50, 168, 82) # rectangle color

screen = pygame.display.set_mode((width, height))
clock = pygame.time.Clock()

nums = []
rectangles = []

# this should be called every frame
def generate_rectangles():
    
    current_bar = 0
    for o in nums:
        bar_height = (height * (o/100))
        bar_top = height - bar_height
        current_rect = ((current_bar * bar_space) + (white_space/2), bar_top, bar_width, bar_height)
        rectangles.append(current_rect)
        current_bar += 1

def draw_rectangles():
    
    for rect in rectangles:
        pygame.draw.rect(screen, rectangle_color, rect)

def update_screen():
    rectangles.clear()

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()

    # fill the screen with a color to wipe away anything from last frame
    screen.fill(background)

    # RENDER YOUR GAME HERE
    generate_rectangles()
    draw_rectangles()
    #random.shuffle(nums)
    
 
    # flip() the display to put your work on screen
    pygame.display.flip()

    clock.tick(15)  # limits FPS to 60

# def quicksort(nums):
#     if len(nums) <= 1:
#         return nums
#     else:
#         pivot = nums[0]
#         left = [x for x in nums[1:] if x < pivot]
#         right = [x for x in nums[1:] if x >= pivot]
#         update_screen()
#         return quicksort(left) + [pivot] + quicksort(right)

def selection_sort(): 
    size = len(nums)
    for ind in range(size):
        update_screen()
        min_index = ind
 
        for j in range(ind + 1, size):
            # select the minimum element in every iteration
            if nums[j] < nums[min_index]:
                min_index = j
         # swapping the elements to sort the array
        (nums[ind], nums[min_index]) = (nums[min_index], nums[ind])


for i in range(1, num_bars + 1):
    nums.append(i)

random.shuffle(nums)

# quicksort(nums)

selection_sort()



# for i in range(0, 120):
#     update_screen()
