<script>
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';

	let post = {
		id: 1,
		title: '제목1',
		writer: '작성자1'
	};
	let post1 = {
		id: 1,
		title: '제목112312321321321',
		writer: '작성자2'
	};
	let posts = [post, post1];

	onMount(async () => {
		// const response = await fetch('/api/posts');
		// posts = await response.json();
		let post = {
			id: 1,
			title: '제목1',
			summary: '요약1'
		};

		console.log(post);
		posts.push(post);
	});

	const viewPost = (id) => {
		goto(`/posts/${id}`);
	};
</script>

<div class="p-4">
	<div class="flex justify-between">
		<h1 class="mb-4 text-2xl font-bold">글목록</h1>
		<button class="mb-4 text-xl font-semibold text-gray-400 hover:text-blue-300">글쓰기</button>
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
							<h2 class="text-xl font-semibold">{post.title}</h2>
							<p class="text-gray-600">{post.writer}</p>
						</button>
					</li>
				{/each}
			</ul>
		{/if}
	</div>
</div>
