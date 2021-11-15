@a = common global i32 0, align 4

; Function Attrs: noinline nounwind optnone uwtable
define i32 @main() #0 {
  %1 = alloca i32, align 4
  br label %l9

    l9:
  %a9 = load i32, i32* %1, align 4
  ret i32 %a9
}
