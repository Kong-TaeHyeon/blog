<script>
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import { BACK_API } from '$lib/constants/BackAPI';
	import { page } from '$app/stores';

	let posts = [];

	onMount(async () => {
		const pageNum = $page.url.searchParams.get('page') ?? 0;

		const response = await fetch(BACK_API(`/api/post?page=${pageNum}&size=10`), {
			method: 'GET',
			credentials: 'include'
		});
		posts = await response.json();
		console.log(posts);
	});

	const viewPost = (id) => {
		goto(`/post/${id}`);
	};
</script>

<div class="p-4">
	<div class="flex justify-between">
		<h1 class="mb-4 text-2xl font-bold">글목록</h1>
		<a href="/post/add" class="mb-4 text-xl font-semibold text-gray-400 hover:text-blue-300"
			>글쓰기</a
		>
	</div>
	<div class="flex justify-center itmes-center">
		{#if posts.length === 0}
			<p class="text-gray-500">아직 작성된 글이 없습니다.</p>
		{:else}
			<ul class="w-full">
				{#each posts as post}
					<li class="flex flex-col mb-2">
						<button
							class="w-full h-32 p-4 border rounded cursor-pointer hover:bg-gray-100"
							on:click={() => viewPost(post.id)}
						>
							<h2 class="text-xl font-semibold">{post?.title}</h2>
							<p class="text-gray-600">{post?.author?.nickname}</p>
						</button>
					</li>
				{/each}
			</ul>
		{/if}
	</div>
</div>
