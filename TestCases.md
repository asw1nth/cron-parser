# Test Cases for Cron Parser

### 1. Valid Expression with All Fields

#### Input:
```bash
"*/15 0 1-7/2 * 1 /usr/bin/find"
```

#### Output:
```bash
minute        0 15 30 45 
hour          0 
day of month  1 3 5 7 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   1 
command       /usr/bin/find
```

### 2. Multiple Value Ranges

#### Input:
```bash
"0 12 1-15,20-25 1 1,7 /usr/bin/find"
```

#### Output:
```bash
minute        0 
hour          12 
day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 20 21 22 23 24 25 
month         1 
day of week   1 7 
command       /usr/bin/find
```

### 3. Wildcard - */

#### Input:
```bash
"*/10 * * * 2,4,6 /usr/bin/find"
```

#### Output:
```bash
minute        0 10 20 30 40 50 
hour          0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 
day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   2 4 6 
command       /usr/bin/find
```

### 4. List and Range combination

#### Input:
```bash
"0 0 1,5,10-12 3 3 /usr/bin/find"
```

#### Output:
```bash
minute        0 
hour          0 
day of month  1 5 10 11 12 
month         3 
day of week   3 
command       /usr/bin/find
```

### 5. Range & step expression

#### Input:
```bash
"*/5 1-6/2 1-15/3 * 2-4 /usr/bin/find"
```

#### OUtput:
```
minute        0 5 10 15 20 25 30 35 40 45 50 55 
hour          1 3 5 
day of month  1 4 7 10 13 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   2 3 4 
command       /usr/bin/find
```

### 6. Combination of Wildcard, Range, List, and Step

#### Input:
```bash
"*/10 3-6/2,12 1,15-20/3 1 2,4-6 /usr/bin/find"
```

#### Output:
```bash
minute        0 10 20 30 40 50 
hour          3 5 12 
day of month  1 15 18 
month         1 
day of week   2 4 5 6 
command       /usr/bin/find
```

### 7. Duplicates

#### Input:
```bash
"0 0-10/2,7-13 1,2,3,4,2-6 1 1 /usr/bin/find"
```

#### Output:
```bash
minute        0 
hour          0 2 4 6 7 8 9 10 11 12 13 
day of month  1 2 3 4 5 6 
month         1 
day of week   1 
command       /usr/bin/find
```

### 8. Invalid Range

#### Input:
```bash
"0 0 1 1 0 /usr/bin/find"
```

#### Output:
```bash
Error: The expression 0 passed for cron field day of week is invalid. 
Accepted range for cron field day of week is [1-7]. 
Error message is : Values passed are not in give range
```

