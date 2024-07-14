<script>
	import NavBarButton from '$lib/components/navbarButton/NavBarButton.svelte';

	let activatedMenu = 'home';
</script>

<div class="flex justify-between border-b">
	<div class="flex flex-row">
		<NavBarButton name="홈" bind:activatedMenu menu="home" />
		<NavBarButton name="글목록" bind:activatedMenu menu="posts" />
		<NavBarButton name="프로젝트" bind:activatedMenu menu="projects" />
	</div>
	<div class="mr-1">
		<a class="inline-block px-4 py-2 font-semibold text-gray-400 bg-white" href="/login">로그인</a>
		<a class="inline-block px-4 py-2 font-semibold text-gray-400 bg-white" href="/join">회원가입</a>
	</div>
</div>

<slot />
