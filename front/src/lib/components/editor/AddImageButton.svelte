<script>
	import { BACK_API } from '$lib/constants/BackAPI';

	export let editor;
	let fileInput;

	const onFileSelected = async (e) => {
		const image = e.target.files[0];

		// FormData 객체 생성
		const formData = new FormData();
		formData.append('file', image);

		try {
			// 파일을 서버에 전송
			const url = BACK_API('/api/image');
			const response = await fetch(url, {
				method: 'POST',
				body: formData,
				credentials: 'include'
			});

			// 서버에서 이미지 URL 받기
			if (response.ok) {
				const data = await response.json();
				console.log(data);
				const imageUrl = data.url; // 서버에서 받은 이미지 URL

				// 에디터에 이미지 URL 설정
				editor.commands.setImage({ src: imageUrl });

				// 파일 입력 초기화
				fileInput.value = '';
			} else {
				console.error('Failed to upload image:', response.statusText);
			}
		} catch (error) {
			console.error('Error uploading image:', error);
		}
	};

	// const onFileSelected = (e) => {
	// 	let image = e.target.files[0];
	// 	let reader = new FileReader();
	// 	reader.readAsDataURL(image);
	// 	reader.onload = (e) => {
	// 		editor.commands.setImage({ src: e.target.result });
	// 		// 업로드 후 file input 의 값을 비워줘야 동일한 파일을 연속해서 업로드할 수 있다.
	// 		// 그리고 이게 없으면 클립보드와 함께 쓰는 방식이 잘 안됨.
	// 		fileInput.value = '';
	// 	};
	// };

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

<button
	class="px-2 py-1 font-bold text-white bg-gray-600 rounded hover:bg-blue-700"
	on:click={onClick}
	on:keydown={onKeyDown}
>
	사진 첨부
	<input
		style="display:none"
		aria-label="lorem ipsum"
		type="file"
		accept=".jpg, .jpeg, .png"
		on:change={(e) => onFileSelected(e)}
		bind:this={fileInput}
	/>
</button>
