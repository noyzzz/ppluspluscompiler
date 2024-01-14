# P++ Compiler

## Grammar Generation and Code Generation

This programming language's grammar was generated using the CUP (Construction of Useful Parsers) library, which is commonly used for parsing and generating parsers for context-free grammars. CUP allows for the definition of syntax and the automatic generation of parsers based on the specified grammar rules.

### CUP Grammar

Our programming language's formal grammar is defined using CUP notation, a Java-based LALR parser generator. CUP allows you to specify grammar symbols, productions, and associated action code. These actions are executed post-reduction and can be employed for various purposes, such as building an Abstract Syntax Tree (AST). CUP works seamlessly with JFlex, a complementary scanner generator.

For more details, visit [CUP Project](http://www2.cs.tum.edu/projects/cup/).


### Code Generation

Our code generator translates the parsed code into LLVM (Low-Level Virtual Machine) code. LLVM provides a versatile and efficient intermediate representation that can be further optimized and compiled to machine code.

If you are interested in the details of the grammar or the LLVM code generated by our compiler, feel free to explore the relevant files in the repository.

## Programming Language Grammar

### Productions

- `<program>` → {`<var_decl>`* | `<func_extern>`* | `<struct_dec>`* }+ 
- `<func_extern>` → `<func_decl>` | `<extern_decl>`

### Function Declarations

- `<func_decl>` → function `<type>` id ([ `<arguments>` ]) ; 
- `<extern_decl>` → extern `<type>` id ( `<arguments>` ) `<block>` 

### Arguments

- `<arguments>` → `<type>` id [ { `[` `]` }+ ] [ , `<arguments>`]

### Types

- `<type>` → int | bool | float | long |  char | double | id | string | void | auto

### Struct Declaration

- `<struct_dec>` → record id { `<var_decl>` | `<statement>` }+ end record ;

### Struct Member Declaration

- `<var_decl>` → `<type>` id [ = { `<exp>` } ]

### Function Call

- `<func_call>` → id [ `<exp>` ] 

### Statements

- `<statement>` → `<expression>` ; 
- `<extern_decl>` → extern `<type>` id ( `<arguments>` ) `<block>` 

### Blocks

- `<block>` → begin { `<var_decl>` | `<statement>` }+ end

### Expressions

- `<exp>` → `<exp>` `<binary_op>` `<exp>` | ( `<exp>` ) | `<method_call>` | - `<exp>` | ~ `<exp>` | sizeof(`<type>`) | `<assignment>` | id

### Binary Operators

- `<binary_op>` → + | - | * | / | % | & | '|' | ^ | == | != | >= | <= | < | > | and | or | not

### Method Call

- `<method_call>` → id [ `<exp>` ]

### Loops

- `<loop_stmt>` → for ([ `<assignment>` ]; `<exp>` ; [ `<assignment>` ]) `<block>` | while (`<exp>`) `<block>` | do `<block>` while (`<exp>`) ;

### Conditionals

- `<if_stmt>` → if (`<exp>`) `<block>` [ else `<block>` ]

### Functions

- `<function>` → function `<type>` id ([ `<arguments>` ]) `<block>`

### Structs

- `<struct>` → record id { `<var_decl>` | `<statement>` }+ end record ;

### Variables

- `<var>` → `<type>` id [ = `<exp>` ]

### Comments

- Comments can be added using `#` at the beginning of a line.

### Examples of P++ code

```your_programming_language
# Your example code here
function int fib(int n)
begin
    if (n <= 1) 
    begin
        return n;
    end
    return fib(n - 1) + fib(n - 2);

end

function int main()
begin

    int n;
    scanf("%d", n);
    printf("%d\n", fib(n));
end


```


# Running the P++ Compiler

Our P++ programming language compiler can be run from the command line using the provided command. Follow the steps below to set up and execute the compiler:

## Prerequisites

Make sure you have the following prerequisites installed on your system:

- Java Development Kit (JDK)

## Steps

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/noyzzz/ppluspluscompiler.git
    cd ppluspluscompiler/src
    ```

2. **Install Dependencies:**

    Ensure you have Java installed on your system.

3. **Build the Compiler:**

    Before running the compiler, ensure you have compiled the Java code.

    Example (using javac):

    ```bash
    javac -cp .:java-cup-11b-runtime.jar *.java
    ```

4. **Run the Compiler:**

    Execute the compiler using the provided command.

    Example:

    ```bash
    java -cp .:java-cup-11b-runtime.jar parser input.in output.ll
    ```

    In this example, `input.in` contains the P++ source code, and the generated LLVM code will be saved in `output.ll`.

5. **Generate Executable from LLVM Code:**

    After successfully generating the LLVM code, you can use clang to create an executable file. Follow the commands below:

    ```bash
    # Compile LLVM code to object file
    clang -c -o output.o output.ll

    # Link the object file to create an executable
    clang -o output output.o
    ```

    Finally, execute the generated executable:

    ```bash
    ./output
    ```

    These commands compile the LLVM code into an object file (`output.o`) and then link it to create the final executable (`output`). You can run the executable to execute the compiled P++ program.


Now, you can easily compile and test your P++ programming language by following the provided steps. Feel free to customize the configuration to suit your specific project structure and compiler setup.
