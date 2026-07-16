# 银行交易记录系统项目说明

## 一、项目简介

这是一个使用 Java 开发的控制台程序，用于记录 5 位客户的银行交易资料。系统在用户输入每条交易信息后，会进行格式校验，校验通过后将数据保存到数组中，最后统一显示全部交易记录。

该项目适合用于基础 Java 课程练习，重点体现数组、类与对象、方法封装、循环控制、条件判断以及正则表达式输入校验的使用。

## 二、项目功能

系统支持以下功能：

1. 录入 5 位客户的交易记录。
2. 每条记录包含 4 个字段：
   - Customer ID
   - Account Number
   - Transaction Amount
   - Transaction Type
3. 对用户输入进行格式校验。
4. 将合法数据保存到对象数组中。
5. 显示全部交易记录。
6. 显示总记录数量。

## 三、输入校验规则

系统使用正则表达式对输入内容进行检查，规则如下：

1. Customer ID：必须为 1 个英文字母加 3 位数字，例如 `C001`。
2. Account Number：必须为 8 到 18 位数字，允许使用空格或连字符分隔，例如 `0012-345-678`、`1234 5678 9012`。系统保存时会自动移除分隔符。
3. Transaction Amount：必须是大于 0 的数字，且最多保留 2 位小数，例如 `500`、`500.75`。
4. Transaction Type：只能输入 `1`、`2`、`3` 或 `4`。

如果输入不符合规则，系统会提示错误并要求重新输入，直到数据合法为止。

## 四、实现方法

本项目采用“类封装单条记录，主程序负责流程控制”的方式实现。

### 1. 交易记录类

[BankTransaction.java](BankTransaction.java) 用于定义单条交易记录对象，包含以下成员：

- `customerId`：客户编号
- `accountNumber`：账户号码
- `transactionAmount`：交易金额
- `transactionType`：交易类型

主要方法：

1. 构造方法 `BankTransaction(...)`
   用于在创建对象时接收并保存完整的交易信息。
2. `displayTransaction(int recordNumber)`
   用于格式化输出一条交易记录。

### 2. 主程序类

[BankTransactionRecordSystem.java](BankTransactionRecordSystem.java) 是系统入口，负责整体业务流程。

主要实现步骤：

1. 创建 `Scanner` 对象接收输入。
2. 创建长度为 5 的 `BankTransaction[]` 数组。
3. 使用 `for` 循环录入 5 条交易记录。
4. 调用不同方法分别读取客户编号、账号、金额和交易类型。
5. 对每项输入进行正则校验。
6. 创建 `BankTransaction` 对象并保存到数组中。
7. 最后遍历数组输出所有记录。

### 3. 主要方法说明

1. `printTitle()`
   显示系统标题。

2. `readValidatedText(Scanner scanner, String prompt, String pattern, String errorMessage)`
   用于读取并验证文本输入，例如客户编号和账户号码。

3. `readPositiveAmount(Scanner scanner)`
   用于读取交易金额，并利用正则表达式检查格式，再判断金额是否大于 0。

4. `readTransactionType(Scanner scanner)`
   用于读取交易类型菜单选项，并将数字转换为文字类型。

5. `displayAllTransactions(BankTransaction[] transactions)`
   用于遍历数组并显示全部交易记录。

## 五、项目结构

项目目录如下：

- [BankTransaction.java](BankTransaction.java)
  交易记录类。

- [BankTransactionRecordSystem.java](BankTransactionRecordSystem.java)
  主程序类。

- [README.md](README.md)
  项目说明文件。

- [sample_input.txt](sample_input.txt)
  示例输入文件。

- [sample_output.txt](sample_output.txt)
  示例输出文件。

## 六、程序执行流程

1. 显示系统标题。
2. 依次输入 5 位客户的交易资料。
3. 每输入一项数据，系统立即检查格式是否合法。
4. 合法数据保存为一个 `BankTransaction` 对象。
5. 5 条记录全部录入后，系统统一显示所有内容。
6. 输出总记录数。

## 七、项目特点

本项目具有以下特点：

1. 结构简单清晰，适合教学和展示。
2. 使用数组保存固定数量的数据。
3. 使用类和对象表示真实业务数据。
4. 使用方法拆分逻辑，便于理解和维护。
5. 使用正则表达式增强输入校验能力。
6. 不使用集合框架、数据库、继承、多态等高级特性，符合基础 Java 课程要求。

## 八、运行方式

在项目目录下运行：

```bash
javac BankTransaction.java BankTransactionRecordSystem.java
java BankTransactionRecordSystem
```

如果使用示例输入文件测试：

```bash
java BankTransactionRecordSystem < sample_input.txt
```

## 九、总结

该项目完整展示了一个基础 Java 控制台系统从数据输入、格式校验、对象创建、数组存储到结果输出的全过程。通过这个项目，可以练习以下知识点：

1. 类与对象
2. 构造方法
3. 数组
4. 循环结构
5. 条件判断
6. 方法封装
7. Scanner 输入处理
8. 正则表达式校验