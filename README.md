# 第七组：银行交易记录系统

## 一、任务要求

使用数组保存 5 位客户的交易资料，每条资料包含：

- Customer ID（客户编号）
- Account Number（账户号码）
- Transaction Amount（交易金额）
- Transaction Type（交易类型）

程序只使用基础 Java 知识：变量、Scanner、if/else、switch、循环、数组、类、对象和方法。

## 二、文件说明

- `BankTransaction.java`：交易记录类，保存并显示一条客户交易资料。
- `BankTransactionRecordSystem.java`：主程序，负责输入、校验、创建对象、存入数组和汇总显示。
- `sample_input.txt`：可用于测试的 5 条示例输入。
- `sample_output.txt`：使用示例输入运行程序得到的输出。

## 三、运行方法

在项目目录中打开命令行，执行：

```bash
javac BankTransaction.java BankTransactionRecordSystem.java
java BankTransactionRecordSystem
```

也可以使用示例输入测试：

```bash
java BankTransactionRecordSystem < sample_input.txt
```

## 四、程序结构说明

1. 创建长度为 5 的 `BankTransaction[]` 数组。
2. 使用 `for` 循环输入 5 位客户资料。
3. 使用正则表达式对客户编号、账户号码、交易金额和交易类型进行输入校验。
4. 每次输入后创建一个 `BankTransaction` 对象并存入数组。
5. 使用循环调用对象的 `displayTransaction()` 方法显示全部记录。

## 五、输入校验规则

- `Customer ID`：必须为 1 个英文字母加 3 位数字，例如 `C001`。
- `Account Number`：必须为 8 到 18 位数字，允许使用空格或连字符分隔，例如 `0012-345-678`、`1234 5678 9012`。系统保存时会自动移除分隔符。
- `Transaction Amount`：必须为大于 0 的正数，且最多保留 2 位小数，例如 `1500`、`1500.50`。
- `Transaction Type`：只能输入 `1` 到 `4`，分别代表 `Deposit`、`Withdrawal`、`Transfer`、`Payment`。

## 六、建议小组分工

- 组长：讲解整体设计和控制开发进度。
- 软件工程师：编写两个 Java 类。
- 测试工程师：测试合法输入、空输入、负金额、文字金额和错误菜单编号。
- 代码评审员：检查命名、缩进、重复代码、输入校验和是否使用了未学习技术。
- 技术汇报员：演示输入 5 条记录并说明数组、对象、循环和方法的作用。

## 七、代码评审检查点

- 是否固定使用数组保存 5 条数据。
- 四个必需字段是否全部存在。
- 金额是否使用 `double`。
- 客户编号和账户号码是否使用 `String`，从而保留前导零。
- 是否使用正则表达式完成格式校验。
- 是否在输入失败后提示用户重新输入。
- 是否没有使用集合框架、继承、多态、接口或数据库。
