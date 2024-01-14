@v35 = private unnamed_addr constant [3 x i8] c"%d\00"
@v41 = private unnamed_addr constant [4 x i8] c"%d
\00"
declare i32 @printf(i8*, ...)
declare i32 @__isoc99_scanf(i8*, ...)
@v5 = common global  i32 0
define i32 @kirINT (i32 %v3 ){
%v0 = alloca i32
store i32 0, i32* %v0
%v6 = alloca i32
store i32 %v3, i32* %v6
%t1 = load i32, i32* %v6
%v7 = alloca i32
store i32 %t1, i32* %v7
%v8 = alloca i32
store i32 2, i32* %v8
%t2 = load i32, i32* %v8
%v9 = alloca i32
store i32 %t2, i32* %v9
%v10 = alloca i32
%t3 = load i32 , i32* %v7
%t4 = load i32 , i32* %v9
%t5 = add i32 %t3, %t4
store i32 %t5, i32* %v10
%t6 = load i32, i32* %v10
store i32 %t6, i32* %v0
br label %l0
br label %l0
l0: 
%t9 = load i32 , i32* %v0
ret i32 %t9
}
define i32 @fibINT (i32 %v4 ){
%v1 = alloca i32
store i32 0, i32* %v1
%v11 = alloca i32
store i32 %v4, i32* %v11
%t11 = load i32, i32* %v11
%v12 = alloca i32
store i32 %t11, i32* %v12
%v13 = alloca i32
store i32 1, i32* %v13
%t12 = load i32, i32* %v13
%v14 = alloca i32
store i32 %t12, i32* %v14
%v15 = alloca i8
%t13 = load i32, i32* %v12
%t14 = load i32, i32* %v14
%t15 = icmp sle i32 %t13, %t14
%t16 = zext i1 %t15 to i8
store i8 %t16, i8* %v15
%t17 = load i8, i8* %v15
%t18 = trunc i8 %t17 to i1
br i1 %t18,  label %l1, label %l2
br label %l1
l1: 
%v16 = alloca i32
store i32 %v4, i32* %v16
%t20 = load i32, i32* %v16
%v17 = alloca i32
store i32 %t20, i32* %v17
%t21 = load i32, i32* %v17
store i32 %t21, i32* %v1
br label %l3
br label %l2
l2: 
%v18 = alloca i32
store i32 %v4, i32* %v18
%t25 = load i32, i32* %v18
%v19 = alloca i32
store i32 %t25, i32* %v19
%v20 = alloca i32
store i32 1, i32* %v20
%t26 = load i32, i32* %v20
%v21 = alloca i32
store i32 %t26, i32* %v21
%v22 = alloca i32
%t27 = load i32 , i32* %v19
%t28 = load i32 , i32* %v21
%t29 = sub i32 %t27, %t28
store i32 %t29, i32* %v22
%v23 = alloca i32
%t30 = load i32, i32* %v22
%t31 = call i32 @fibINT( i32 %t30 )
store i32 %t31, i32* %v23
%t32 = load i32, i32* %v23
%v24 = alloca i32
store i32 %t32, i32* %v24
%v25 = alloca i32
store i32 %v4, i32* %v25
%t34 = load i32, i32* %v25
%v26 = alloca i32
store i32 %t34, i32* %v26
%v27 = alloca i32
store i32 2, i32* %v27
%t35 = load i32, i32* %v27
%v28 = alloca i32
store i32 %t35, i32* %v28
%v29 = alloca i32
%t36 = load i32 , i32* %v26
%t37 = load i32 , i32* %v28
%t38 = sub i32 %t36, %t37
store i32 %t38, i32* %v29
%v30 = alloca i32
%t39 = load i32, i32* %v29
%t40 = call i32 @fibINT( i32 %t39 )
store i32 %t40, i32* %v30
%t41 = load i32, i32* %v30
%v31 = alloca i32
store i32 %t41, i32* %v31
%v32 = alloca i32
%t42 = load i32 , i32* %v24
%t43 = load i32 , i32* %v31
%t44 = add i32 %t42, %t43
store i32 %t44, i32* %v32
%t45 = load i32, i32* %v32
store i32 %t45, i32* %v1
br label %l3
br label %l3
l3: 
%t48 = load i32 , i32* %v1
ret i32 %t48
}
define i32 @main ( ){
%v2 = alloca i32
store i32 0, i32* %v2
%v33 = alloca i32
%t49 = load i32, i32* @v5
store i32 %t49, i32* %v33
%t50 = load i32, i32* %v33
%v34 = alloca i32
store i32 %t50, i32* %v34
call i32 (i8* , ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @v35, i32 0, i32 0), i32* @v5)
%v37 = alloca i32
%t52 = load i32, i32* @v5
store i32 %t52, i32* %v37
%t53 = load i32, i32* %v37
%v38 = alloca i32
store i32 %t53, i32* %v38
%v39 = alloca i32
%t54 = load i32, i32* %v38
%t55 = call i32 @fibINT( i32 %t54 )
store i32 %t55, i32* %v39
%t56 = load i32, i32* %v39
%v40 = alloca i32
store i32 %t56, i32* %v40
%t57 = load i32, i32* %v40
call i32(i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @v41, i32 0, i32 0), i32 %t57)
br label %l4
l4: 
%t58 = load i32 , i32* %v2
ret i32 %t58
}
