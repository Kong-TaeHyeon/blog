<script>
	let email;
	let nickname;
	let password;

	const onSubmitHandler = async () => {
		const body = JSON.stringify({
			email,
			nickname,
			password
		});
		console.log(body);

		const response = await fetch('http://localhost:8080/api/auth/join', {
			body: JSON.stringify({ email, nickname, password }),
			method: 'post',
			headers: {
				'Content-Type': 'application/json'
			}
		});

		const data = await response.json();
		console.log(data);
	};
</script>

<div class="flex flex-col items-center justify-center p-20 font-semibold">
	<a href="/main" class="text-[32px]">Kong's Blog</a>

	<div class="w-[400px]">
		<div class="flex flex-col items-center justify-center gap-2 mb-4">
			<input
				class=" w-full border-[1px] border-black"
				placeholder="EMAIL"
				type="email"
				bind:value={email}
			/>
			<input
				class="w-full border-[1px] border-black"
				placeholder="NICKNAME"
				type="text"
				bind:value={nickname}
			/>
			<input
				class="w-full border-[1px] border-black"
				placeholder="비밀번호"
				type="password"
				bind:value={password}
			/>
			<button on:click={onSubmitHandler} class="w-full h-8 text-white bg-black">회원가입</button>
		</div>
	</div>
</div>
