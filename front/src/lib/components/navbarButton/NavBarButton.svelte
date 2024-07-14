<script>
	export let name;

	export let menu;
	export let activatedMenu;
</script>

{#if activatedMenu == menu}
	<div class="mr-1 -mb-px">
		<button
			class="inline-block px-4 py-2 font-semibold text-blue-700 bg-white border-t border-l border-r rounded-t"
			>{name}</button
		>
	</div>
{:else}
	<div class="mr-1">
		<button class="inline-block px-4 py-2 font-semibold text-gray-400 bg-white hover:text-blue-800"
			>{name}</button
		>
	</div>
{/if}
