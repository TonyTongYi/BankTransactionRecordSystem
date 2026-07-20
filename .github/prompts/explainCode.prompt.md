---
description: "Explain the purpose, structure, and design of the selected Java code in the Bank Transaction Record System"
agent: agent
tools: ['codebase', 'search', 'usages']
model: Claude Sonnet 4.5 (copilot)
---
<!--
Four-part structure: Context -> Task -> Constraints -> Output Format
Invoke with a file open or code selected, then run /explainCode in Chat.
-->

## Context
You are reviewing Java source from the **BankTransactionRecordSystem** project. The domain model uses polymorphism: `Customer` is the abstract base class, with `StandardCustomer`, `PremiumCustomer`, and `CorporateCustomer` overriding balance, overdraft, and fee rules. `BankTransaction` and `TransactionType` model individual transactions. Code comments in this repo are written in Chinese; explanations should stay consistent with that documentation style where relevant.

## Task
Explain the code in ${selection}, or if nothing is selected, the entire file ${file}. Cover what the code does, how it fits into the wider class hierarchy, and why it is designed the way it is.

## Constraints
- Do not modify any code — this is a read-only explanation task.
- Explicitly identify which OOP principle(s) are demonstrated (inheritance, polymorphism, encapsulation) and where.
- Call out interactions with other classes in the project (e.g., `Customer`, `TransactionType`, `BankTransaction`) by name.
- Note any edge cases, potential bugs, or assumptions the code makes (e.g., null checks, overdraft limits, rounding).
- Keep the explanation technical and concise — no filler or restating the code line-by-line.

## Output Format
Respond in Markdown with these sections:
1. **Overview** — one or two sentences on the purpose of the class/method.
2. **Key Members** — bullet list of important fields/methods and their role.
3. **Interactions** — how this code depends on or is used by other classes.
4. **Design Notes** — OOP principles applied, plus any risks or edge cases worth flagging.
