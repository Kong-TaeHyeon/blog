<script src="https://cdn.tailwindcss.com?plugins=typography">
	import { Color } from '@tiptap/extension-color';
	import ListItem from '@tiptap/extension-list-item';
	import TextStyle from '@tiptap/extension-text-style';
	import StarterKit from '@tiptap/starter-kit';
	import { Image as TipTapImage } from '@tiptap/extension-image';

	import { Editor } from '@tiptap/core';
	import { onMount } from 'svelte';
	import { getHandlePaste, getOnTransaction } from './editorCustom';
	import AddImageButton from './AddImageButton.svelte';
	import { BACK_API } from '$lib/constants/BackAPI';

	let element;
	let editor;
	let imageIds = [];

	onMount(() => {
		editor = new Editor({
			editorProps: {
				attributes: {
					class: 'prose prose-sm sm:prose lg:prose-lg xl:prose-2xl mx-auto focus:outline-none'
				}
			},
			element: element,
			extensions: [
				Color.configure({ types: [TextStyle.name, ListItem.name] }),
				TextStyle.configure({ types: [ListItem.name] }),
				StarterKit,
				TipTapImage.configure({
					allowBase64: true
				})
			],
			content: `내용을 입력하세요`,
			onTransaction: () => {
				// force re-render so `editor.isActive` works as expected
				editor = editor;
			}
		});
		editor.setOptions({
			onTransaction: getOnTransaction(editor),
			editorProps: {
				handlePaste: getHandlePaste(editor)
			}
		});
	});

	let content = ``;
	let title = ``;

	const onSaveHandler = async () => {
		content = editor.getHTML();

		const body = {
			title,
			content
		};

		fetch('http://localhost:8080/api/post', {
			headers: {
				'Content-Type': 'application/json'
			},
			method: 'post',
			credentials: 'include',
			body: JSON.stringify(body)
		}).then((res) => {
			if (res.status === 200) {
				alert('저장되었습니다.');
				window.location.href = '/post';
			}
		});

		// const test = await response.json();
		// console.log(test);
	};
</script>

<input bind:value={title} class="p-4 m-4 w-full h-10 border-[1px]" placeholder="제목" />

{#if editor}
	<div class="p-4">
		<AddImageButton bind:editor />
	</div>
{/if}
<div class="h-[400px] overflow-scroll wrapper border-[1px]">
	<div class="w-full h-full element-wrapper" bind:this={element} />
</div>

<div class="flex flex-row items-center justify-center gap-4 p-4">
	<button
		class="px-4 py-2 font-bold text-white bg-blue-500 rounded hover:bg-blue-700"
		on:click={onSaveHandler}>저장</button
	>
	<button class="px-4 py-2 font-bold text-white bg-blue-500 rounded hover:bg-blue-700">취소</button>
</div>

<style>
	.wrapper:focus-within {
		border: 1px solid red;
	}

	.element-wrapper {
		padding: 1rem;
		resize: vertical;
		overflow: auto;
	}

	.element-wrapper > :global(.ProseMirror > p:first-child::before) {
		color: #adb5bd;
		content: attr(data-placeholder);
		float: left;
		height: 0;
		pointer-events: none;
	}

	.element-wrapper :global(p:first-of-type) {
		margin-top: 0;
	}

	.element-wrapper :global(p:last-of-type) {
		margin-bottom: 0;
	}

	.element-wrapper > :global(.ProseMirror) {
		outline: 0;
	}
</style>
