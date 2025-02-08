# AI Assisted Coding

**Navigating the Strengths, Challenges, and Future of Coding Assistants**

---

## Project Overview

This project demonstrates how to leverage AI-assisted coding tools effectively using the **8 Best Practices**. These practices ensure efficiency, clarity, and high-quality results while working with AI coding assistants. Below is a breakdown of these practices and their applications.

---

## [Best Practices to follow while doing the demo](best_practices_for_ai_tools.md)

---

### Requirements for the project:
Create a [robust command-line tool in Java](class_diagram.png) (JDK 21) to:
1. Interact with a public web API (`https://api.chucknorris.io/jokes/random`).
2. Process the response to remove swear words.
3. Use OpenAI API to generate a funny story in the voice of George Carlin based on the joke.
4. Allow storing responses to a file and support error/debug logs via command-line options.

---

## Demo Project

### Step One Run example project
1. Add cli argument : -output joke.txt <- this is your minimal needed flag to see output
2. Pick ai.qodo.joke.CommandLine as your Main class in ide runtime

### 

### Declarative vs Imperative
1. Ask/Prompt for plugins: 
```
implement the scrub method requirements from the javadoc in the
```
2. Apply response to either DataSanitizer or LogCleaner respectively if response is declarative or imperative. 
   Iterate with ask or other techniques to get the desired response.
3. ./gradlew clean run
4. Review the console output discuss which implementation is better and discuss how to use AI plugins to get you to this outcome.


## Looking Ahead: The Future of AI Coding Assistants
1. **AI-Assisted Requirements Refinement**
2. **Architectural AI Project Navigation**
3. **Developer-Assisted Code Implementation**
4. **Requirement-Based Test Generation**
5. **AI-Augmented Continuous Integration and Delivery (CI/CD)**

---

## Resources and Links
- [Get Started with Qodo.ai](https://www.qodo.ai/get-started)  
- [System 2 Thinking Blog](https://www.qodo.ai/blog/system-2-thinking-alphacodium-outperforms-direct-prompting-of-openai-o1/)
- [Qodo Merge Documentation](https://qodo-merge-docs.qodo.ai/core-abilities/metadata/)

---

## Author
**David Parry**  
Developer Advocate
- [LinkedIn](https://www.linkedin.com/in/david-parry-47b4a44)
- [YouTube](https://www.youtube.com/@QodoAI)
- [Email](mailto:david.p@qodo.ai)

---

## Questions?
Visit: [https://www.qodo.ai/get-started](https://www.qodo.ai/get-started)