	.text
	.file	"output.ll"
	.globl	main                    # -- Begin function main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$32, %rsp
	movl	$0, -4(%rbp)
	movl	$10, -24(%rbp)
	movl	$10, -20(%rbp)
	movl	v2(%rip), %eax
	movl	%eax, -16(%rbp)
	movl	$0, -12(%rbp)
	movl	$0, -8(%rbp)
	movl	$0, v2(%rip)
	.p2align	4, 0x90
.LBB0_1:                                # %l0
                                        # =>This Inner Loop Header: Depth=1
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	v2(%rip), %ecx
	movl	%ecx, -16(%rax)
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	%ecx, -16(%rax)
	movq	%rsp, %rcx
	leaq	-16(%rcx), %rsp
	movl	$10, -16(%rcx)
	movq	%rsp, %rcx
	leaq	-16(%rcx), %rsp
	movl	$10, -16(%rcx)
	movq	%rsp, %rdx
	leaq	-16(%rdx), %rsp
	movl	-16(%rax), %eax
	cmpl	-16(%rcx), %eax
	setl	-16(%rdx)
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	jge	.LBB0_3
# %bb.2:                                # %l2
                                        #   in Loop: Header=BB0_1 Depth=1
	movl	v3(%rip), %ecx
	movl	%ecx, -16(%rax)
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	%ecx, -16(%rax)
	movl	$.Lv16, %edi
	movl	$v3, %esi
	xorl	%eax, %eax
	callq	__isoc99_scanf
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movslq	v2(%rip), %rcx
	movl	%ecx, -16(%rax)
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	%ecx, -16(%rax)
	movq	%rsp, %rdx
	leaq	-16(%rdx), %rsp
	movl	v1(,%rcx,4), %ecx
	movl	%ecx, -16(%rdx)
	movq	%rsp, %rcx
	leaq	-16(%rcx), %rsp
	movl	v3(%rip), %edx
	movl	%edx, -16(%rcx)
	movq	%rsp, %rcx
	leaq	-16(%rcx), %rsp
	movl	%edx, -16(%rcx)
	movslq	-16(%rax), %rax
	movl	%edx, v1(,%rax,4)
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	v2(%rip), %ecx
	movl	%ecx, -16(%rax)
	movq	%rsp, %rcx
	leaq	-16(%rcx), %rsp
	movl	-16(%rax), %eax
	incl	%eax
	movl	%eax, -16(%rcx)
	movl	%eax, v2(%rip)
	movl	-16(%rcx), %eax
	decl	%eax
	movl	%eax, -16(%rcx)
	movq	%rsp, %rcx
	leaq	-16(%rcx), %rsp
	movl	%eax, -16(%rcx)
	jmp	.LBB0_1
.LBB0_3:                                # %l1.loopexit
	movl	v2(%rip), %ecx
	movl	%ecx, -16(%rax)
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	$0, -16(%rax)
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	$0, -16(%rax)
	movl	$0, v2(%rip)
	.p2align	4, 0x90
.LBB0_4:                                # %l3
                                        # =>This Inner Loop Header: Depth=1
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	v2(%rip), %ecx
	movl	%ecx, -16(%rax)
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	%ecx, -16(%rax)
	movq	%rsp, %rcx
	leaq	-16(%rcx), %rsp
	movl	$10, -16(%rcx)
	movq	%rsp, %rcx
	leaq	-16(%rcx), %rsp
	movl	$10, -16(%rcx)
	movq	%rsp, %rdx
	leaq	-16(%rdx), %rsp
	movl	-16(%rax), %eax
	cmpl	-16(%rcx), %eax
	setl	-16(%rdx)
	jge	.LBB0_6
# %bb.5:                                # %l5
                                        #   in Loop: Header=BB0_4 Depth=1
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movslq	v2(%rip), %rcx
	movl	%ecx, -16(%rax)
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	%ecx, -16(%rax)
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	v1(,%rcx,4), %esi
	movl	%esi, -16(%rax)
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	%esi, -16(%rax)
	movl	$.Lv38, %edi
	xorl	%eax, %eax
	callq	printf
	movq	%rsp, %rax
	leaq	-16(%rax), %rsp
	movl	v2(%rip), %ecx
	movl	%ecx, -16(%rax)
	movq	%rsp, %rcx
	leaq	-16(%rcx), %rsp
	movl	-16(%rax), %eax
	incl	%eax
	movl	%eax, -16(%rcx)
	movl	%eax, v2(%rip)
	movl	-16(%rcx), %eax
	decl	%eax
	movl	%eax, -16(%rcx)
	movq	%rsp, %rcx
	leaq	-16(%rcx), %rsp
	movl	%eax, -16(%rcx)
	jmp	.LBB0_4
.LBB0_6:                                # %l4.loopexit
	movl	-4(%rbp), %eax
	movq	%rbp, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end0:
	.size	main, .Lfunc_end0-main
	.cfi_endproc
                                        # -- End function
	.type	.Lv16,@object           # @v16
	.section	.rodata.str1.1,"aMS",@progbits,1
.Lv16:
	.asciz	"%d"
	.size	.Lv16, 3

	.type	.Lv38,@object           # @v38
.Lv38:
	.asciz	"%d\n"
	.size	.Lv38, 4

	.type	v1,@object              # @v1
	.comm	v1,40,16
	.type	v2,@object              # @v2
	.comm	v2,4,4
	.type	v3,@object              # @v3
	.comm	v3,4,4
	.section	".note.GNU-stack","",@progbits
