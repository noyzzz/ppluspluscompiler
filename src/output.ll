@v28 = private unnamed_addr constant [3 x i8] c"%d\00"
@v34 = private unnamed_addr constant [4 x i8] c"%d
\00"
declare i32 @printf(i8*, ...)
declare i32 @__isoc99_scanf(i8*, ...)
@v3 = common global  i32 0
define i32 @fibINT (i32 %v2 ){
%v0 = alloca i32
store i32 0, i32* %v0
%v4 = alloca i32
store i32 %v2, i32* %v4
%t1 = load i32, i32* %v4
%v5 = alloca i32
store i32 %t1, i32* %v5
%v6 = alloca i32
store i32 1, i32* %v6
%t2 = load i32, i32* %v6
%v7 = alloca i32
store i32 %t2, i32* %v7
%v8 = alloca i8
%t3 = load i32, i32* %v5
%t4 = load i32, i32* %v7
%t5 = icmp sle i32 %t3, %t4
%t6 = zext i1 %t5 to i8
store i8 %t6, i8* %v8
%t7 = load i8, i8* %v8
%t8 = trunc i8 %t7 to i1
br i1 %t8,  label %l0, label %l1
br label %l0
l0: 
%v9 = alloca i32
store i32 %v2, i32* %v9
%t10 = load i32, i32* %v9
%v10 = alloca i32
store i32 %t10, i32* %v10
%t11 = load i32, i32* %v10
store i32 %t11, i32* %v0
br label %l2
br label %l1
l1: 
%v11 = alloca i32
store i32 %v2, i32* %v11
%t15 = load i32, i32* %v11
%v12 = alloca i32
store i32 %t15, i32* %v12
%v13 = alloca i32
store i32 1, i32* %v13
%t16 = load i32, i32* %v13
%v14 = alloca i32
store i32 %t16, i32* %v14
%v15 = alloca i32
%t17 = load i32 , i32* %v12
%t18 = load i32 , i32* %v14
%t19 = sub i32 %t17, %t18
store i32 %t19, i32* %v15
%v16 = alloca i32
%t20 = load i32, i32* %v15
%t21 = call i32 @fibINT( i32 %t20 )
store i32 %t21, i32* %v16
%t22 = load i32, i32* %v16
%v17 = alloca i32
store i32 %t22, i32* %v17
%v18 = alloca i32
store i32 %v2, i32* %v18
%t24 = load i32, i32* %v18
%v19 = alloca i32
store i32 %t24, i32* %v19
%v20 = alloca i32
store i32 2, i32* %v20
%t25 = load i32, i32* %v20
%v21 = alloca i32
store i32 %t25, i32* %v21
%v22 = alloca i32
%t26 = load i32 , i32* %v19
%t27 = load i32 , i32* %v21
%t28 = sub i32 %t26, %t27
store i32 %t28, i32* %v22
%v23 = alloca i32
%t29 = load i32, i32* %v22
%t30 = call i32 @fibINT( i32 %t29 )
store i32 %t30, i32* %v23
%t31 = load i32, i32* %v23
%v24 = alloca i32
store i32 %t31, i32* %v24
%v25 = alloca i32
%t32 = load i32 , i32* %v17
%t33 = load i32 , i32* %v24
%t34 = add i32 %t32, %t33
store i32 %t34, i32* %v25
%t35 = load i32, i32* %v25
store i32 %t35, i32* %v0
br label %l2
br label %l2
l2: 
%t38 = load i32 , i32* %v0
ret i32 %t38
}
define i32 @main ( ){
%v1 = alloca i32
store i32 0, i32* %v1
%v26 = alloca i32
%t39 = load i32, i32* @v3
store i32 %t39, i32* %v26
%t40 = load i32, i32* %v26
%v27 = alloca i32
store i32 %t40, i32* %v27
call i32 (i8* , ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @v28, i32 0, i32 0), i32* @v3)
%v30 = alloca i32
%t42 = load i32, i32* @v3
store i32 %t42, i32* %v30
%t43 = load i32, i32* %v30
%v31 = alloca i32
store i32 %t43, i32* %v31
%v32 = alloca i32
%t44 = load i32, i32* %v31
%t45 = call i32 @fibINT( i32 %t44 )
store i32 %t45, i32* %v32
%t46 = load i32, i32* %v32
%v33 = alloca i32
store i32 %t46, i32* %v33
%t47 = load i32, i32* %v33
call i32(i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @v34, i32 0, i32 0), i32 %t47)
br label %l3
l3: 
%t48 = load i32 , i32* %v1
ret i32 %t48
}
