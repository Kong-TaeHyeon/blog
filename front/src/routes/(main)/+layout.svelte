<script>
	import { goto } from '$app/navigation';
	import NavBarButton from '$lib/components/navbarButton/NavBarButton.svelte';

	let activatedMenu = 'home';

	// @ts-ignore
	const onClickHandler = (e) => {
		activatedMenu = e.detail.menu;
		goto(`/${activatedMenu}`);
	};
</script>

<div class="flex justify-between border-b">
	<div class="flex flex-row">
		<NavBarButton name="홈" bind:activatedMenu menu="home" on:click={onClickHandler} />
		<NavBarButton name="글목록" bind:activatedMenu menu="posts" on:click={onClickHandler} />
		<NavBarButton name="프로젝트" bind:activatedMenu menu="projects" on:click={onClickHandler} />
	</div>
	<div class="mr-1">
		<a class="inline-block px-4 py-2 font-semibold text-gray-400 bg-white" href="/login">로그인</a>
		<a class="inline-block px-4 py-2 font-semibold text-gray-400 bg-white" href="/join">회원가입</a>
	</div>
</div>

<slot />
