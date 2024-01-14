## Programming Language Grammar

### Productions

- `<program>` → {`<var_decl>`\* | `<func_extern>`\* | `<struct_dec>`\* }+ 
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

### Examples

```your_programming_language
# Your example code here
function int add(int a, int b) {
    return a + b;
}

record Point {
    int x;
    int y;
} end record;
