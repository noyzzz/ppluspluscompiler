define i32 @main ( ){
%v0 = alloca i32
%v1 = alloca i32
store i32 0, i32* %v1
%t0 = load i32, i32* %v1
%v2 = alloca i32
store i32 %t0, i32* %v2
%t1 = load i32, i32* %v2
store i32 %t1, i32* %v0
%t2 = load i32, i32* %v0
ret i32 %t2
%v3 = alloca i32
store i32 1, i32* %v3
%t4 = load i32, i32* %v3
%v4 = alloca i32
store i32 %t4, i32* %v4
%t5 = load i32, i32* %v4
store i32 %t5, i32* %v0
%t6 = load i32, i32* %v0
ret i32 %t6
}
