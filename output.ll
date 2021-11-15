@v16 = private unnamed_addr constant [3 x i8] c"%d\00"
@v38 = private unnamed_addr constant [4 x i8] c"%d
\00"
declare i32 @printf(i8*, ...)
declare i32 @__isoc99_scanf(i8*, ...)
@v1 = common global  [10 x  i32  ] zeroinitializer
@v2 = common global  i32 0
@v3 = common global  i32 0
define i32 @main ( ){
%v0 = alloca i32
store i32 0, i32* %v0
%v4 = alloca i32
store i32 10, i32* %v4
%t0 = load i32, i32* %v4
%v5 = alloca i32
store i32 %t0, i32* %v5
%v6 = alloca i32
%t1 = load i32, i32* @v2
store i32 %t1, i32* %v6
%v7 = alloca i32
store i32 0, i32* %v7
%t2 = load i32, i32* %v7
%v8 = alloca i32
store i32 %t2, i32* %v8
%t3 = load i32, i32* %v8
store i32 %t3, i32* @v2
br label %l0
l0: 
%v9 = alloca i32
%t4 = load i32, i32* @v2
store i32 %t4, i32* %v9
%t5 = load i32, i32* %v9
%v10 = alloca i32
store i32 %t5, i32* %v10
%v11 = alloca i32
store i32 10, i32* %v11
%t6 = load i32, i32* %v11
%v12 = alloca i32
store i32 %t6, i32* %v12
%v13 = alloca i8
%t7 = load i32, i32* %v10
%t8 = load i32, i32* %v12
%t9 = icmp slt i32 %t7, %t8
%t10 = zext i1 %t9 to i8
store i8 %t10, i8* %v13
%t11 = load i8, i8* %v13
%t12 = trunc i8 %t11 to i1
br i1 %t12,  label %l2, label %l1
br label %l2
l2: 
%v14 = alloca i32
%t13 = load i32, i32* @v3
store i32 %t13, i32* %v14
%t14 = load i32, i32* %v14
%v15 = alloca i32
store i32 %t14, i32* %v15
call i32 (i8* , ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @v16, i32 0, i32 0), i32* @v3)
%v18 = alloca i32
%t16 = load i32, i32* @v2
store i32 %t16, i32* %v18
%t17 = load i32, i32* %v18
%v19 = alloca i32
store i32 %t17, i32* %v19
%t18 = load i32, i32* %v19
%t19 = sext i32 %t18 to i64
%t20 = getelementptr inbounds [10 x i32 ], [10 x i32 ]* @v1, i64 0, i64 %t19
%v20 = alloca i32
%t21 = load i32, i32* %t20
store i32 %t21, i32* %v20
%v21 = alloca i32
%t22 = load i32, i32* @v3
store i32 %t22, i32* %v21
%t23 = load i32, i32* %v21
%v22 = alloca i32
store i32 %t23, i32* %v22
%t25 = load i32, i32* %v19
%t26 = sext i32 %t25 to i64
%t27 = getelementptr inbounds [10 x i32 ], [10 x i32 ]* @v1, i64 0, i64 %t26
%t28 = load i32, i32* %v22
store i32 %t28, i32* %t27
%v23 = alloca i32
%t29 = load i32, i32* @v2
store i32 %t29, i32* %v23
%v24 = alloca i32
%t30 = load i32, i32* %v23
%t31 = add i32 %t30, 1
store i32 %t31, i32* %v24
%t32 = load i32, i32* %v24
store i32 %t32, i32* @v2
%t33 = load i32, i32* %v24
%t34 = sub i32 %t33, 1
store i32 %t34, i32* %v24
%t35 = load i32, i32* %v24
%v25 = alloca i32
store i32 %t35, i32* %v25
br label %l0
br label %l1
l1: 
%v26 = alloca i32
%t36 = load i32, i32* @v2
store i32 %t36, i32* %v26
%v27 = alloca i32
store i32 0, i32* %v27
%t37 = load i32, i32* %v27
%v28 = alloca i32
store i32 %t37, i32* %v28
%t38 = load i32, i32* %v28
store i32 %t38, i32* @v2
br label %l3
l3: 
%v29 = alloca i32
%t39 = load i32, i32* @v2
store i32 %t39, i32* %v29
%t40 = load i32, i32* %v29
%v30 = alloca i32
store i32 %t40, i32* %v30
%v31 = alloca i32
store i32 10, i32* %v31
%t41 = load i32, i32* %v31
%v32 = alloca i32
store i32 %t41, i32* %v32
%v33 = alloca i8
%t42 = load i32, i32* %v30
%t43 = load i32, i32* %v32
%t44 = icmp slt i32 %t42, %t43
%t45 = zext i1 %t44 to i8
store i8 %t45, i8* %v33
%t46 = load i8, i8* %v33
%t47 = trunc i8 %t46 to i1
br i1 %t47,  label %l5, label %l4
br label %l5
l5: 
%v34 = alloca i32
%t48 = load i32, i32* @v2
store i32 %t48, i32* %v34
%t49 = load i32, i32* %v34
%v35 = alloca i32
store i32 %t49, i32* %v35
%t50 = load i32, i32* %v35
%t51 = sext i32 %t50 to i64
%t52 = getelementptr inbounds [10 x i32 ], [10 x i32 ]* @v1, i64 0, i64 %t51
%v36 = alloca i32
%t53 = load i32, i32* %t52
store i32 %t53, i32* %v36
%t54 = load i32, i32* %v36
%v37 = alloca i32
store i32 %t54, i32* %v37
%t55 = load i32, i32* %v37
call i32(i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @v38, i32 0, i32 0), i32 %t55)
%v40 = alloca i32
%t56 = load i32, i32* @v2
store i32 %t56, i32* %v40
%v41 = alloca i32
%t57 = load i32, i32* %v40
%t58 = add i32 %t57, 1
store i32 %t58, i32* %v41
%t59 = load i32, i32* %v41
store i32 %t59, i32* @v2
%t60 = load i32, i32* %v41
%t61 = sub i32 %t60, 1
store i32 %t61, i32* %v41
%t62 = load i32, i32* %v41
%v42 = alloca i32
store i32 %t62, i32* %v42
br label %l3
br label %l4
l4: 
br label %l6
l6: 
%t63 = load i32 , i32* %v0
ret i32 %t63
}
