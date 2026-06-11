# Two Sum

**Difficulty:** Easy
**Topics:** Array, HashMap

> **"I am repeatedly searching for a value that I could store when I see it."**

## 🧠 Problem-Solving Process

```text
1. Brute force
        ↓
2. What is the complexity?
        ↓
3. Where is the repeated work?
        ↓
4. Can I store some information?
        ↓
5. What data structure allows me to do that?
        ↓
6. HashMap
        ↓
7. O(n) time / O(n) space
```

---

## 1. Brute Force

### What is the brute-force approach?

Iterate through every possible **pair of numbers** in the array using nested loops.

For each pair, check whether:

```text
nums[i] + nums[j] == target
```

If the condition is true, return their indices.

### Implementation

```java
class TwoSumBruteForce {

    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{};
    }
}
```

### Complexity

**Time:** `O(n²)`

In the worst case, we need to compare many pairs of elements. Because there are two nested loops, the number of comparisons grows quadratically with the size of the input.

**Space:** `O(1)`

We don't create any data structure whose size depends on the input.

---

## 2. Can We Avoid Repeated Work?

Yes.

The brute-force solution repeatedly searches for a number that completes the current number to reach the target.

For example:

```text
nums = [2, 7, 11, 15]
target = 9
```

When we see `2`, we need:

```text
9 - 2 = 7
```

So we are looking for `7`.

Instead of repeatedly searching through the array, we can **store information about the numbers we have already seen**.

This leads to the question:

> What data structure allows us to quickly check whether we have already seen a number?

### HashMap

A `HashMap` allows us to associate:

```text
KEY   → number
VALUE → index
```

For example:

```text
2 → 0
7 → 1
11 → 2
```

---

## 3. Optimized Solution

For each number:

1. Calculate its complement:

```java
int complement = target - nums[i];
```

2. Check whether the complement already exists in the `HashMap`.

3. If it exists, we found the two numbers.

4. If it doesn't exist, store the current number and its index.

### Implementation

```java
class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> hashmap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if (hashmap.containsKey(complement)) {
                return new int[]{hashmap.get(complement), i};
            }

            hashmap.put(nums[i], i);
        }

        return new int[]{};
    }
}
```

---

## 4. Step-by-Step Explanation

### 1. Create the HashMap

```java
Map<Integer, Integer> hashmap = new HashMap<>();
```

The map stores:

```text
KEY   → number from the array
VALUE → index of that number
```

For example:

```text
2 → 0
7 → 1
```

---

### 2. Iterate through the array

```java
for (int i = 0; i < nums.length; i++)
```

We process each number once.

---

### 3. Calculate the complement

```java
int complement = target - nums[i];
```

The complement is the value we need to find in order to reach the target.

For example:

```text
target = 9
nums[i] = 2

complement = 9 - 2
complement = 7
```

We are looking for `7`.

---

### 4. Check if the complement already exists

```java
if (hashmap.containsKey(complement))
```

`containsKey()` checks whether a specific **key** exists in the map.

If `7` is already a key:

```text
7 → 1
```

we know that we previously found the number we need.

---

### 5. Retrieve the index

```java
hashmap.get(complement)
```

`get()` receives a **KEY** and returns the **VALUE associated with that key**.

So:

```text
hashmap:

7 → 1

hashmap.get(7)
       ↓
       1
```

We then return:

```java
return new int[]{hashmap.get(complement), i};
```

The first index comes from the HashMap, and `i` is the index of the current number.

---

### 6. Store the current number

If the complement hasn't been found yet:

```java
hashmap.put(nums[i], i);
```

For example:

```text
nums[i] = 2
i = 0

hashmap.put(2, 0)
```

The map becomes:

```text
2 → 0
```

**Important:** we are not storing the complement.

We store the **current number** and its index.

The complement is only used to search the HashMap.

---

## 5. HashMap Methods

These were the main methods used in the solution:

### `put(key, value)`

Adds an association between a key and a value.

```java
hashmap.put(key, value);
```

Example:

```java
hashmap.put(2, 0);
```

Result:

```text
2 → 0
```

---

### `containsKey(key)`

Checks whether a key exists.

```java
hashmap.containsKey(key);
```

Example:

```java
hashmap.containsKey(2);
```

Returns:

```text
true
```

if `2` exists as a key.

---

### `get(key)`

Retrieves the value associated with a key.

```java
hashmap.get(key);
```

Example:

```text
HashMap:

2 → 0
7 → 1
```

Then:

```java
hashmap.get(7);
```

returns:

```text
1
```

### Important distinction

```text
containsKey(key)
        ↓
"Does this key exist?"

get(key)
        ↓
"What value is associated with this key?"

put(key, value)
        ↓
"Store this key → value association."
```

---

## 6. Why Don't We Store the Complement?

This was an important point of confusion.

We **do not store the complement**.

We store:

```java
hashmap.put(nums[i], i);
```

So the current number becomes the key.

Example:

```text
target = 9
nums[i] = 2
```

The complement is:

```text
9 - 2 = 7
```

But we store:

```text
2 → 0
```

Later, when we reach:

```text
nums[i] = 7
```

we calculate:

```text
9 - 7 = 2
```

Then we ask:

```java
hashmap.containsKey(2)
```

The answer is `true`.

So:

```java
hashmap.get(2)
```

gives us the index of `2`.

This is how the two numbers are connected.

---

## 7. Complexity

### Time

**O(n)**

We iterate through the array once.

`HashMap` operations such as `containsKey()`, `get()`, and `put()` are **O(1) average time**.

Therefore:

```text
n elements × O(1) operations
= O(n)
```

### Space

**O(n)**

In the worst case, we may store almost every element of the array in the `HashMap`.

---

## 8. Key Takeaways

* Brute force uses nested loops → `O(n²)`.
* The repeated work is searching for the complementary number.
* We can store previously seen numbers in a `HashMap`.
* The **number is the key**.
* The **index is the value**.
* The complement is calculated but **not stored**.
* `containsKey()` checks whether a key exists.
* `get()` retrieves the value associated with a key.
* `put()` creates the key → value association.
* The optimized solution achieves **O(n) time** and **O(n) space**.

## 💭 Main Insight

The most important idea I want to remember from this problem is:

> **When I repeatedly search for something I've already seen, I should ask whether I can store the information I need in a data structure that makes future lookups faster.**

In this case:

```text
Repeated search
      ↓
Store previous values
      ↓
HashMap
      ↓
O(n²) → O(n)
```
