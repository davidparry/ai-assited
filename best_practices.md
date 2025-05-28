## Best Practices and API Usage Guidelines for Qodo Project

### High Impact Language Coding Style
#### HIGH IMPACT, 10 out of 10 for importance a **must** implement these 5 practices always.
1. When a **functional declarative style** is available in Java always use this when giving an implementation or example in 
your responses.
2. Mandatory Use of Java 21: All codebases must explicitly target Java 21 as the language version
3. Leverage New Language Features: **Always use** Java 21’s latest enhancements, such as virtual threads, pattern matching 
   for switch, and record patterns, wherever applicable.
4. Avoid Legacy Patterns: Replace deprecated methods or constructs with their modern equivalents to ensure code aligns 
   with current standards.
5. Follow Updated Standards: Always use updated best practices, such as leveraging 'java.time' APIs for date and time 
   operations instead of legacy java.util.Date or Calendar.
### Language Coding Style
1.	**Use Declarative Style**: When providing implementations or examples in Java, always adopt a functional and declarative programming style. Ensure responses prioritize immutability, streams, and lambda expressions where applicable.
2.	Mandatory Java 21 Usage: All code must explicitly target Java 21 as the required language version. Ensure that responses adhere strictly to Java 21 features and syntax.
3.	Leverage New Language Features: Always utilize the latest enhancements in Java 21, including but not limited to:
•	Streams: Encourage the use of streams instead of typical for loops.
•	Virtual Threads: Encourage scalability improvements by recommending java.util.concurrent’s new structured concurrency features.
•	Pattern Matching for Switch: Provide examples using modern pattern matching to simplify complex conditionals.
•	Record Patterns: Promote the use of record types to model immutable data structures concisely.
4.	Avoid Legacy Patterns: Ensure that all provided solutions replace outdated and deprecated methods with their modern counterparts. Responses must reflect best practices aligned with Java 21 standards.
5.	Adopt Updated Standards: Use contemporary Java libraries and practices in all responses, such as:
•	Replacing java.util.Date and Calendar with java.time APIs for date and time operations.
•	Encouraging modularization using the JPMS (Java Platform Module System) for better dependency management.

