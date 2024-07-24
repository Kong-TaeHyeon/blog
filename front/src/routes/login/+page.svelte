<script>
	// @ts-nocheck

	import { BACK_API } from '$lib/constants/BackAPI';

	let email;
	let password;

	const loginHandler = async () => {
		const httpURL = BACK_API('/api/auth/login');
		const response = await fetch(httpURL, {
			method: 'POST',
			body: JSON.stringify({
				email,
				password
			})
		});

		const token = response.json();
		console.log(token);
	};
</script>

<div class="flex flex-col items-center justify-center p-20 font-semibold">
	<p class="text-[32px]">Kong's Blog</p>

	<div class="w-[400px]">
		<div class="flex flex-col items-center justify-center gap-2 mb-4">
			<input bind:value={email} class="w-full border-[1px] border-black" placeholder="EMAIL" />
			<input
				bind:value={password}
				class="w-full border-[1px] border-black"
				placeholder="비밀번호"
			/>
			<div class="flex justify-end w-full">
				<a href="/join" class="text-sm text-blue-300 hover:text-black">회원가입</a>
			</div>
			<button on:click={loginHandler} class="w-full h-8 text-white bg-black">로그인</button>
		</div>

		<div class="flex flex-col items-center justify-center w-full gap-1">
			<button on:click={() => goOAuthLogin('kakao')} class="w-full border-black border-[1px]"
				>KaKao Login</button
			>
			<button class="w-full border-black border-[1px]">GitHub Login</button>
		</div>
	</div>
</div>
