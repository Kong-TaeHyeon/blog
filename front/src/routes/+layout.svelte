<script>
	import { goto } from '$app/navigation';
	import NavBarButton from '$lib/components/navbarButton/NavBarButton.svelte';
	import { onMount } from 'svelte';
	import '../app.css';

	let activatedMenu;

	export let data;
	let { isLogin } = data;

	onMount(() => {
		if (window.location.pathname.startsWith('/post')) {
			activatedMenu = '/post';
		} else if (window.location.pathname.startsWith('/project')) {
			activatedMenu = 'project';
		} else if (window.location.pathname.startsWith('/')) {
			activatedMenu = '/';
		}
	});

	const onClickHandler = (e) => {
		activatedMenu = e.detail.menu;
		window.location.href = activatedMenu;
	};

	const onLogoutHandler = () => {
		isLogin = false;
		goto('/logout');
	};
</script>

<div class="flex justify-between border-b">
	<div class="flex flex-row">
		<NavBarButton name="홈" bind:activatedMenu menu="/" on:click={onClickHandler} />
		<NavBarButton name="글목록" bind:activatedMenu menu="/post" on:click={onClickHandler} />
		<NavBarButton name="프로젝트" bind:activatedMenu menu="/project" on:click={onClickHandler} />
	</div>
	<div class="mr-1">
		{#key isLogin}
			{#if !isLogin}
				<a
					class="inline-block px-4 py-2 font-semibold text-gray-400 bg-white hover:text-blue-300"
					href="/login">로그인</a
				>
				<a
					class="inline-block px-4 py-2 font-semibold text-gray-400 bg-white hover:text-blue-300"
					href="/join">회원가입</a
				>
			{:else}
				<button
					class="inline-block px-4 py-2 font-semibold text-gray-400 bg-white hover:text-blue-300"
					on:click={onLogoutHandler}>로그아웃</button
				>
			{/if}
		{/key}
	</div>
</div>

<slot />
