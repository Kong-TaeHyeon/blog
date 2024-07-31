<script>
	import { goto } from '$app/navigation';
	import { BACK_API } from '$lib/constants/BackAPI';
	import { onMount } from 'svelte';

	export let data;
	const { isLogin } = data;

	onMount(() => {
		console.log(isLogin);
		if (isLogin) {
			goto('/main');
		}
	});

	let email;

	let password;

	const goOAuthLogin = () => {
		const URL = BACK_API('/oauth2/authorization/kakao');
		window.location.href = URL;
	};

	const loginHandler = async () => {
		const httpURL = BACK_API('/api/auth/login');
		const response = await fetch(httpURL, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				email,
				password
			}),

			credentials: 'include'
		});

		console.log(response);
		return;
	};
</script>

<div class="flex flex-col items-center justify-center p-20 font-semibold">
	<a href="/main" class="text-[32px]">Kong's Blog</a>

	<div class="w-[400px]">
		<form
			action="http://localhost:8080/api/auth/login"
			method="post"
			class="flex flex-col items-center justify-center gap-2 mb-4"
		>
			<input
				bind:value={email}
				name="email"
				type="email"
				class="w-full border-[1px] border-black"
				placeholder="EMAIL"
			/>
			<input
				bind:value={password}
				type="password"
				name="password"
				class="w-full border-[1px] border-black"
				placeholder="비밀번호"
			/>
			<div class="flex justify-end w-full">
				<a href="/join" class="text-sm text-blue-300 hover:text-black">회원가입</a>
			</div>
			<button type="submit" class="w-full h-8 text-white bg-black">로그인</button>
		</form>

		<div class="flex flex-col items-center justify-center w-full gap-1">
			<button on:click={() => goOAuthLogin('kakao')} class="w-full border-black border-[1px]"
				>KaKao Login</button
			>
			<button class="w-full border-black border-[1px]">GitHub Login</button>
		</div>
	</div>
</div>
