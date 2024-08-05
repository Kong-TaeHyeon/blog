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

	let element;
	let editor;

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
			content: ``,
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

	let htmlTest;

	const onSaveHandler = () => {
		console.log(editor.getHTML());
		htmlTest = editor.getHTML();
	};
</script>

{#if editor}
	<div>
		<AddImageButton bind:editor />
	</div>
{/if}
<div class="min-h-40 wrapper border-[1px]">
	<div class="element-wrapper" bind:this={element} />
</div>

<div class="flex flex-row items-center justify-center gap-4">
	<button on:click={onSaveHandler}>저장</button>
	<button>취소</button>
</div>

<div
	class="mx-auto prose-sm prose tiptap ProseMirror sm:prose lg:prose-lg xl:prose-2xl focus:outline-none"
>
	{@html htmlTest}
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
