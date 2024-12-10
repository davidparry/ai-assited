# AI Assisted Coding

**Navigating the Strengths, Challenges, and Future of Coding Assistants**

---

## Project Overview

This project demonstrates how to leverage AI-assisted coding tools effectively using the **8 Best Practices**. These practices ensure efficiency, clarity, and high-quality results while working with AI coding assistants. Below is a breakdown of these practices and their applications.

---

## 8 Best Practices for AI Coding Tools

### 1. Break Requests into Smaller Units of Work
- **Why:** AI performs best with focused, specific tasks.
- **How:** Divide complex features into manageable tasks.
- **Example:**  
  Instead of asking for a complete "user authentication system," request:  
  `Write a function to verify user credentials. Use BCrypt for hashing.`

---

### 2. Provide Context in Each Ask
- **Why:** Context helps AI understand the task's scope and purpose.
- **How:** Reference relevant code and explain how the new code should integrate.
- **Example:**  
  `I’m building a web server; write a function to handle GET requests. Here’s the current routing setup. (Provide the routes.)`

---

### 3. Be Clear and Specific
- **Why:** Clear instructions lead to better results.
- **How:** Avoid ambiguity by specifying outcomes, inputs, and outputs.
- **Example:**  
  `Write a function to parse a CSV file and return a list of known participants.`

---

### 4. Keep Requests Distinct and Focused
- **Why:** Focused asks reduce confusion.
- **How:** Address one functionality or question at a time.
- **Example:**  
  First, ask for a data processing function. Later, optimize its performance.

---

### 5. Iterate and Refine
- **Why:** Iterative refinement improves accuracy.
- **How:** Request optimizations or changes until the code meets your needs.
- **Example:**  
  `After developing a function, ask: Can you optimize this for better performance?`

---

### 6. Leverage Previous Conversations or Code
- **Why:** Maintain coherent flow for cumulative improvements.
- **How:** Provide context from earlier interactions or code snippets.
- **Example:**  
  `Building on the login function provided earlier, can you add a password hashing feature?`

---

### 7. Use Advanced Predefined Commands
- **Why:** Utilize advanced capabilities for tailored responses.
- **How:** Use predefined commands for specific tools or frameworks.
- **Example:**  
  `Use the /improve command to get suggestions for code improvements.`

---

### 8. Ask for Explanations When Needed
- **Why:** Understanding the code enhances flexibility and maintenance.
- **How:** Request explanations for unfamiliar code sections.
- **Example:**  
  `Explain how this sorting algorithm works.`

---

## Demo Project

### Declarative vs Imperative
1. Ask/Prompt for plugins: ``` implement the following requirements in the scrub method: check if the joke has words that match a word in swearWords from the Scrubber Interface and if so replace with REDACTED_WORD```
2. Apply response to either D or I Scrubber respectively if response is declarative or imperative. Iterate with 
   prompt or other techniques to get the desired D or I response.
3. ./gradlew clean run
4. Review the console output discuss which implementation is better and discuss how to use AI plugins to get you to this outcome.

### Run example project 
1. Add cli argument : -output joke.txt <- this is your minimal needed flag to see output
2. Pick ai.qodo.joke.CommandLine as your Main class in ide runtime

### Requirements:
Create a [robust command-line tool in Java](class_diagram.png) (JDK 21) to: 
1. Interact with a public web API (`https://api.chucknorris.io/jokes/random`).
2. Process the response to remove swear words.
3. Use OpenAI API to generate a funny story in the voice of George Carlin based on the joke.
4. Allow storing responses to a file and support error/debug logs via command-line options.

---

## Looking Ahead: The Future of AI Coding Assistants
1. **AI-Assisted Requirements Refinement**
2. **Architectural AI Project Navigation**
3. **Developer-Assisted Code Implementation**
4. **Requirement-Based Test Generation**
5. **AI-Augmented Continuous Integration and Delivery (CI/CD)**

---

## Resources and Links
- [Get Started with Qodo.ai](https://www.qodo.ai/get-started)  
  Use code for 3 Enterprise months free.
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