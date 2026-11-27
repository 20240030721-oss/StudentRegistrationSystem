Category: Verdict<br>
Findings

Naming conventions:	Needs work<br>
n, a, g are cryptic. Use name, age, grade. add and display are vague. addStudent and displayStudents say what they do.

Code formatting: Needs work<br>
Everything lives in one class with static state. There are no blank lines between methods, and students has no access modifier (it should be private). add mixes storing data with printing output.

Logical errors:	Fails<br>
only the name is stored, so age and grade are lost. "Student Added!" prints even for obviously invalid data. Duplicate students ("John" twice) are accepted. display() shows names only, not full records.

Input validation: Fails<br>
add("", -5, 150) is accepted: an empty name, a negative age and a grade over 100. There is also no null check and no handling of NaN grades.

Exception handling:	Fails<br>
There is no try/catch and nothing is thrown. Invalid input should raise IllegalArgumentException, and main should catch it and report a clear message.

Duplicate code:	Needs work<br>
The same add(...) call is repeated, and the four println lines could live in one toString() or helper. A validation helper would also remove repeated checks.

Security: Minor risk<br>
There is no injection risk now, since nothing touches a database or shell. But unvalidated, unbounded input is echoed straight out, and the mutable static list is shared global state. If this later feeds SQL or HTML, you would need prepared statements and output encoding.

Maintainability: Poor<br>
There is no Student class, so adding a field means changing method signatures everywhere. Limits like age 0 to 120 and grade 0 to 100 would be magic numbers. Static state makes it hard to unit test.

Testability: Poor<br>
All logic sits in main and in static methods that print directly to the console, so there is no way to verify behavior without reading the output by eye.

Scalability: Poor<br>
Students live in a plain ArrayList<String>, so any lookup or duplicate check means scanning the whole list, which is O(n).