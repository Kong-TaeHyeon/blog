<script>
	import { BACK_API } from '$lib/constants/BackAPI';
	import { onMount } from 'svelte';

	let post = {};

	onMount(async () => {
		const url = BACK_API('/api' + window.location.pathname);
		const response = await fetch(url, {
			method: 'GET',
			credentials: 'include'
		});

		post = await response.json();
		console.log(post);
	});
</script>

{#key post}
	<div class="flex flex-col items-center justify-center max-w-xl px-4 py-8 mx-auto">
		<div class="mb-2 text-2xl font-semibold text-gray-800">
			{post?.title}
		</div>

		<div class="mb-4 text-sm text-gray-600">
			by {post?.author?.nickname}
		</div>

		<div
			class="p-4 mx-auto prose-sm prose transition-all duration-300 ease-in-out bg-white rounded-lg tiptap ProseMirror sm:prose lg:prose-lg xl:prose-2xl focus:outline-none hover:shadow-lg"
		>
			{@html post?.content}
		</div>
	</div>
{/key}

<style>
	/* Custom styling for the post container */
	.flex {
		transition: transform 0.3s ease;
	}

	.flex:hover {
		transform: translateY(-5px);
	}

	.prose {
		max-width: 100%;
	}
</style>
