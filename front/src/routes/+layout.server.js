import { redirect } from '@sveltejs/kit';

export const load = ({ locals, url }) => {
	if (url.pathname.startsWith('/logout')) {
		redirect(302, '/');
	}

	if (locals.token)
		return {
			isLogin: true
		};
};
