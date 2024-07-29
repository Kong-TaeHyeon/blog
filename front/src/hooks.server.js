/** @type {import('@sveltejs/kit').Handle} */
export async function handle({ event, resolve }) {
	const token = event.cookies.get('token');

	if (event.url.pathname.startsWith('/logout')) {
		event.cookies.delete('token', {
			path: '/'
		});
	} else if (token) {
		event.locals.token = token;
	}

	const response = await resolve(event);
	return response;
}
