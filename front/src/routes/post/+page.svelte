<script>
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';
	import { BACK_API } from '$lib/constants/BackAPI';
	import { page } from '$app/stores';

	let posts = [];
	let selectedPosts = [];

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

	const deleteSelectedPosts = () => {
		posts = posts.filter((post) => !selectedPosts.includes(post));

		console.log(selectedPosts.map((p) => p.id));
		selectedPosts = []; // 삭제 후 선택된 목록 초기화
	};

	const togglePostSelection = (post) => {
		if (selectedPosts.includes(post)) {
			selectedPosts = selectedPosts.filter((id) => id !== post.id);
		} else {
			selectedPosts = [...selectedPosts, post];
		}
	};
</script>

<div class="p-4">
	<div class="flex justify-between">
		<h1 class="mb-4 text-2xl font-bold">글목록</h1>
		<a href="/post/add" class="mb-4 text-xl font-semibold text-gray-400 hover:text-blue-300"
			>글쓰기</a
		>
	</div>

	<!-- 선택된 글들을 삭제하는 버튼 -->
	<div class="flex justify-between mb-4">
		<button class="px-4 py-2 text-white bg-red-300 rounded hover:bg-red-400">전체 삭제</button>
		<button
			class="px-4 py-2 text-white bg-red-300 rounded hover:bg-red-400"
			on:click={deleteSelectedPosts}
			disabled={selectedPosts.length === 0}
		>
			선택한 글 삭제
		</button>
	</div>

	<div class="flex flex-col items-center justify-center">
		{#if posts.length === 0}
			<p class="text-gray-500">아직 작성된 글이 없습니다.</p>
		{:else}
			<ul class="w-full">
				{#each posts as post}
					<li class="flex items-center justify-between mb-2">
						<div class="flex flex-row w-full h-32 p-4 border rounded cursor-pointer">
							<input
								type="checkbox"
								checked={selectedPosts.includes(post)}
								on:change={() => togglePostSelection(post)}
							/>
							<button class="w-full" on:click={() => viewPost(post.id)}>
								<h2 class="text-xl font-semibold">{post?.title}</h2>
								<p class="text-gray-600">{post?.author?.nickname}</p>
							</button>
						</div>
					</li>
				{/each}
			</ul>
		{/if}

		<nav aria-label="Page navigation example">
			<ul class="flex items-center h-8 -space-x-px text-sm">
				<li>
					<a
						href="#"
						class="flex items-center justify-center h-8 px-3 leading-tight text-gray-500 bg-white border border-gray-300 ms-0 border-e-0 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
					>
						<span class="sr-only">Previous</span>
						<svg
							class="w-2.5 h-2.5 rtl:rotate-180"
							aria-hidden="true"
							xmlns="http://www.w3.org/2000/svg"
							fill="none"
							viewBox="0 0 6 10"
						>
							<path
								stroke="currentColor"
								stroke-linecap="round"
								stroke-linejoin="round"
								stroke-width="2"
								d="M5 1 1 5l4 4"
							/>
						</svg>
					</a>
				</li>
				<li>
					<a
						href="#"
						class="flex items-center justify-center h-8 px-3 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
						>1</a
					>
				</li>
				<li>
					<a
						href="#"
						class="flex items-center justify-center h-8 px-3 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
						>2</a
					>
				</li>
				<li>
					<a
						href="#"
						aria-current="page"
						class="z-10 flex items-center justify-center h-8 px-3 leading-tight text-blue-600 border border-blue-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700 dark:border-gray-700 dark:bg-gray-700 dark:text-white"
						>3</a
					>
				</li>
				<li>
					<a
						href="#"
						class="flex items-center justify-center h-8 px-3 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
						>4</a
					>
				</li>
				<li>
					<a
						href="#"
						class="flex items-center justify-center h-8 px-3 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
						>5</a
					>
				</li>
				<li>
					<a
						href="#"
						class="flex items-center justify-center h-8 px-3 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
					>
						<span class="sr-only">Next</span>
						<svg
							class="w-2.5 h-2.5 rtl:rotate-180"
							aria-hidden="true"
							xmlns="http://www.w3.org/2000/svg"
							fill="none"
							viewBox="0 0 6 10"
						>
							<path
								stroke="currentColor"
								stroke-linecap="round"
								stroke-linejoin="round"
								stroke-width="2"
								d="m1 9 4-4-4-4"
							/>
						</svg>
					</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
