<script>
	import { goto } from '$app/navigation';
	import NavBarButton from '$lib/components/navbarButton/NavBarButton.svelte';
	import { onMount } from 'svelte';

	let activatedMenu = 'home';

	export let data;
	let { isLogin } = data;

	const onClickHandler = (e) => {
		activatedMenu = e.detail.menu;
		goto(`/${activatedMenu}`);
	};

	const onLogoutHandler = () => {
		isLogin = false;
		goto('/logout');
	};
</script>

<div class="flex justify-between border-b">
	<div class="flex flex-row">
		<NavBarButton name="홈" bind:activatedMenu menu="home" on:click={onClickHandler} />
		<NavBarButton name="글목록" bind:activatedMenu menu="posts" on:click={onClickHandler} />
		<NavBarButton name="프로젝트" bind:activatedMenu menu="projects" on:click={onClickHandler} />
	</div>
	<div class="mr-1">
		{#key isLogin}
			{#if !isLogin}
				<a class="inline-block px-4 py-2 font-semibold text-gray-400 bg-white" href="/login"
					>로그인</a
				>
				<a class="inline-block px-4 py-2 font-semibold text-gray-400 bg-white" href="/join"
					>회원가입</a
				>
			{:else}
				<button
					class="inline-block px-4 py-2 font-semibold text-gray-400 bg-white"
					on:click={onLogoutHandler}>로그아웃</button
				>
			{/if}
		{/key}
	</div>
</div>

<slot />
