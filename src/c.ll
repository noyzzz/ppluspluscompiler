@msg = private unnamed_addr constant  [4 x i8] c"%lf\00"
declare i32 @printf(i8*, ...)

@cc = constant i32 5
@dd = constant [7 x i32] zeroinitializer

define i32 @main(){
    %b = alloca double
    store double 8.5, double* %b
    %tr = load double, double* %b
    %tt = fsub double %tr, 1.0
    store double %tt, double* %b
    %rrrr = load double, double* %b
    %ty = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @msg, i32 0, i32 0), double %rrrr)



;    %2 = getelementptr inbounds [30 x i32], [30 x i32]* %a, i64 0, i64 6
    ret i32 0
}