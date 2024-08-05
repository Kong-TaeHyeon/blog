<script>
	export let editor;
	let fileInput;

	const onFileSelected = (e) => {
		let image = e.target.files[0];
		let reader = new FileReader();
		reader.readAsDataURL(image);
		reader.onload = (e) => {
			editor.commands.setImage({ src: e.target.result });
			// 업로드 후 file input 의 값을 비워줘야 동일한 파일을 연속해서 업로드할 수 있다.
			// 그리고 이게 없으면 클립보드와 함께 쓰는 방식이 잘 안됨.
			fileInput.value = '';
		};
	};

	function handleKeyDown(e) {
		if (e.key === 'Enter' || e.key === ' ') {
			e.preventDefault();
			e.target.click();
		}
	}

	const onClick = () => {
		fileInput.click();
	};

	const onKeyDown = (e) => {
		handleKeyDown(e);
	};
</script>

<button on:click={onClick} on:keydown={onKeyDown}>
	Image
	<input
		style="display:none"
		aria-label="lorem ipsum"
		type="file"
		accept=".jpg, .jpeg, .png"
		on:change={(e) => onFileSelected(e)}
		bind:this={fileInput}
	/>
</button>
