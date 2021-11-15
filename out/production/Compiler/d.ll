@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define i32 @main() #0 {
  %1 = alloca i8, align 8
  store i8 1, i8* %1, align 8
  store i8 0, i8* %1, align 8
  %2 = load i8, i8* %1, align 8
  %3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i32 0, i32 0), i8 %2)
  ret i32 0
}

declare i32 @printf(i8*, ...) #1
