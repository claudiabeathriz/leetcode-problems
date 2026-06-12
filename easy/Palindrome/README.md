# Palindrome Number

**Difficulty:** Easy

**Topics:** String, Two Pointers

## Problem

Given an integer `x`, return `true` if `x` is a palindrome, and `false` otherwise.

A palindrome reads the same from left to right and right to left.

Examples:

```text
Input: 121
Output: true

Input: -121
Output: false

Input: 10
Output: false
```

---

## Approach

The first idea is to compare the characters from the beginning and the end of the number.

Instead of reversing the entire string, I only need to compare the first half with the second half.

The reasoning is:

```text
Convert number to String
        ↓
Get the String size
        ↓
Compare first character with last
        ↓
Compare second character with second-to-last
        ↓
Continue until the middle
        ↓
If any pair is different → false
        ↓
If all pairs are equal → true
```

## Implementation

```java
class Palindrome {

    public boolean isPalindrome(int x) {

        String numero = String.valueOf(x);
        int size = numero.length();

        for (int i = 0; i < (size / 2); i++) {
            if (numero.charAt(i) != numero.charAt(size - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
```

---

## Step-by-Step Explanation

### 1. Convert the number to a String

```java
String numero = String.valueOf(x);
```

`String.valueOf(x)` converts the integer into a `String`.

For example:

```text
x = 1221

numero = "1221"
```

This allows me to access each digit using `charAt()`.

---

### 2. Store the String size

```java
int size = numero.length();
```

This gives the number of characters in the String.

For:

```text
"1221"
```

we have:

```text
size = 4
```

---

### 3. Iterate only through half of the String

```java
for (int i = 0; i < (size / 2); i++)
```

I only need to check half of the characters.

For example:

```text
1 2 2 1
↑     ↑
```

After comparing the first and last characters, the second and third characters are the remaining pair.

There is no need to compare the same pairs again in the opposite direction.

---

### 4. Compare characters from opposite sides

```java
numero.charAt(i) != numero.charAt(size - 1 - i)
```

The first character is accessed using:

```java
numero.charAt(i)
```

The character from the opposite side is:

```java
numero.charAt(size - 1 - i)
```

The `-1` is necessary because String indexes start at `0`.

For:

```text
numero = "1221"
size = 4
```

the indexes are:

```text
index:   0 1 2 3
         ↓     ↓
value:   1 2 2 1
```

When `i = 0`:

```text
size - 1 - i
4 - 1 - 0
= 3
```

So we compare:

```text
numero.charAt(0) → '1'
numero.charAt(3) → '1'
```

When `i = 1`:

```text
size - 1 - i
4 - 1 - 1
= 2
```

So we compare:

```text
numero.charAt(1) → '2'
numero.charAt(2) → '2'
```

---

### 5. Return false if a pair doesn't match

```java
if (numero.charAt(i) != numero.charAt(size - 1 - i)) {
    return false;
}
```

If any pair of characters is different, the number cannot be a palindrome.

There is no need to continue checking the remaining characters, so we can immediately return `false`.

---

### 6. Return true

```java
return true;
```

If the loop finishes without finding any different pair, all corresponding characters match.

Therefore, the number is a palindrome.

---

## Example

For:

```text
x = 1221
```

The comparisons are:

```text
i = 0

1 2 2 1
↑     ↑
1 == 1
```

Then:

```text
i = 1

1 2 2 1
  ↑   ↑
2 == 2
```

No differences were found.

Therefore:

```text
true
```

---

## Complexity

### Time

**O(n)**

The algorithm iterates through half of the characters.

More precisely, it performs approximately `n / 2` comparisons, which simplifies to:

```text
O(n)
```

where `n` is the number of digits in the number.

### Space

**O(n)**

The number is converted into a `String`, which requires space proportional to the number of digits.

---

## Alternative Approach

Another possible solution is to solve the problem without converting the number to a `String`.

Instead, we could use mathematical operations such as:

```text
% 10
/ 10
```

to extract and reverse the digits of the number.

That approach would use **O(1) extra space**, since it would not require creating a String proportional to the input size.

I chose the String approach because it makes the character comparison straightforward and easy to reason about.

---

## Key Takeaways

* `String.valueOf(x)` converts an integer into a String.
* `length()` returns the number of characters.
* `charAt(index)` accesses a character at a specific index.
* String indexes start at `0`.
* `size - 1 - i` gives the corresponding index from the opposite side.
* Only half of the String needs to be checked.
* The solution uses a two-pointer-like approach: one position starts from the beginning and the other from the end.
* The current implementation has **O(n) time** and **O(n) space** complexity.

## Main Insight

The key idea is:

> **To determine whether something is symmetric, compare corresponding elements from opposite sides and stop as soon as a mismatch is found.**
