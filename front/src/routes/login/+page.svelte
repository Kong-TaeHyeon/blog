<script>
	const goOAuthLogin = (/** @type {any} */ registrationId) => {
		window.location.href = `http://localhost:8080/oauth2/authorization/${registrationId}`;
	};
</script>

<div class="flex flex-col items-center justify-center p-20 font-semibold">
	<p class="text-[32px]">Kong's Blog</p>

	<div class="w-[400px]">
		<div class="flex flex-col items-center justify-center gap-2 mb-4">
			<input class="w-full border-[1px] border-black" placeholder="EMAIL" />
			<input class="w-full border-[1px] border-black" placeholder="비밀번호" />
			<button class="w-full h-8 text-white bg-black">로그인</button>
		</div>

		<div class="flex flex-col items-center justify-center w-full gap-1">
			<button on:click={() => goOAuthLogin('kakao')} class="w-full border-black border-[1px]"
				>KaKao Login</button
			>
			<button class="w-full border-black border-[1px]">GitHub Login</button>
		</div>
	</div>
</div>
