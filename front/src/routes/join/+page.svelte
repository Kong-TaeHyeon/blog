<script>
	let email;
	let nickname;
	let password;

	const onSubmitHandler = async () => {};
</script>

<div class="flex flex-col items-center justify-center p-20 font-semibold">
	<a href="/main" class="text-[32px]">Kong's Blog</a>

	<div class="w-[400px]">
		<div class="flex flex-col items-center justify-center gap-2 mb-4">
			<input class=" w-full border-[1px] border-black" placeholder="EMAIL" type="email" />
			<input class="w-full border-[1px] border-black" placeholder="NICKNAME" type="text" />
			<input class="w-full border-[1px] border-black" placeholder="비밀번호" type="password" />
			<button class="w-full h-8 text-white bg-black">회원가입</button>
		</div>
	</div>
</div>
